package org.codecop.pushcounter;

import static org.hamcrest.Matchers.containsString;

import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import spark.Spark;

class APITest {

    private static final int PORT = 5000 + new Random().nextInt(5000);

    @BeforeEach
    void startApplication() {
        System.getProperties().put("PORT", "" + PORT);
        Main.main(new String[0]);
        Spark.awaitInitialization();
    }

    @AfterEach
    void stopApplication() throws InterruptedException {
        Spark.stop();
        System.getProperties().remove("PORT");
        // Spark.awaitTermination();
        Thread.sleep(250);
    }

    @Test
    void shouldRecordEntries() {
        // http://127.0.0.1:4567/record/<branch-pair>?build=red|green
        RestAssured. //
            given(). //
                port(PORT). //
            when(). //
                params("build", "green"). //
                get("/record/" + "branch"). //
            then(). //
                assertThat(). //
                    statusCode(201). //
                assertThat(). //
                    body(containsString("OK"));
        // TODO Response should be valid JSON with ContentType JSON.
    }

    @Test
    void shouldClearEntries() {
        // http://127.0.0.1:4567/clear
        RestAssured. //
            given(). //
                port(PORT). //
            when(). //
                get("/clear"). //
            then(). //
                assertThat(). //
                    statusCode(200);
        // TODO this is not really a test
        // TODO Response should be valid JSON with ContentType JSON.
    }

    @Test
    void shouldListEntriesHtml() {
        // http://127.0.0.1:4567/

        for (int i = 0; i < 3; i++) {
            shouldRecordEntries();
        }

        RestAssured. //
            given(). //
                port(PORT). //
            when(). //
                get("/"). //
            then(). //
                assertThat(). //
                    statusCode(200). //
                assertThat(). //
                    body(containsString("branch"), //
                         containsString("" + 3));
        // TODO Test for number of score images. 
    }

    // TODO Index page has refresh of 1 minute or 30' (meta refresh).
    // TODO /winner button on the page displays the winner name with an proper image.  
    // TODO Click on branch name brings the same page where the branch is highlighted so I see my own score better.
    // http://127.0.0.1:4567/<branch-pair>/
    // TODO When the state of the current branch-pair (when selected) changes, a winning audio signal is played.
    // TODO When the state of the overall scores changes, an audio signal is played. (by appending the previous total score)
    // TODO if accept is JSON show the board as JSON - like load/save
    // TODO if type is JSON and post, merge the given data into the board (use DTOs using GSON?)
    // TODO admin=true parameter which adds links to clear, to add to each user and to add new user with form field
    // (user name should also be a form post param)
    // TODO finished=true highlights the winner (possible more than 1) and show some icon, the winner stairs large)

}
