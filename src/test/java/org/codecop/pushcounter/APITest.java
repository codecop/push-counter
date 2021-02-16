package org.codecop.pushcounter;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.codecop.pushcounter.ApplicationSetupExtension.Port;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.restassured.RestAssured;

@ExtendWith(ApplicationSetupExtension.class)
class APITest {

    private static final String CONTENT_JSON = "application/json";
    private static final String MP3_NAME = "badge-coin-win.mp3";

    private final int port;

    APITest(@Port int port) {
        this.port = port;
    }

    private void givenEntryFor(String userName) {
        // http://127.0.0.1:4567/record/<branch-pair>?build=green|<whatever>
        RestAssured. //
            given(). //
                port(port). //
                accept(CONTENT_JSON). //
            when(). //
                params("build", "green"). //
                get("/record/" + userName). //
            then(). //
                statusCode(201). //
                header("Content-Type", equalTo(CONTENT_JSON)). //
                body(equalTo("{ \"message\": \"Saved.\" }"));
    }
    
    @Test
    void shouldClearEntries() {
        // http://127.0.0.1:4567/clear

        givenEntryFor("anyUserToBeCleared");
        
        RestAssured. //
            given(). //
                port(port). //
                accept(CONTENT_JSON). //
            when(). //
                get("/clear"). //
            then(). //
                statusCode(200). //
                header("Content-Type", equalTo(CONTENT_JSON)). //
                body(equalTo("{ \"message\": \"Cleared.\" }"));

        assertNoEntries();
    }

    private void assertNoEntries() {
        String body = RestAssured. //
            given(). //
                port(port). //
            when(). //
                get("/"). //
                body(). //
                asString();

        // only 1 row which is header row
        assertNumberOfRows(1, body);
    }

    private void assertNumberOfRows(int numberRows, String body) {
        int actualNumberRows = body.split("<div class=\"row\">").length - 1;
        assertEquals(numberRows, actualNumberRows);
    }

    @Test
    void shouldListEntriesHtml() {
        // http://127.0.0.1:4567/
        String someUser = "branch";
        for (int i = 0; i < 3; i++) {
            givenEntryFor(someUser);
        }

        assertEntries(3, someUser);
    }

    private void assertEntries(int number, String userName) {
        RestAssured. //
            given(). //
                port(port). //
            when(). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(containsString(userName), //
                     containsString("<div class=\"col-xs-1\">" + number + "</div>"));
        
        // TODO assert number of score images. 
    }

    @Test
    void shouldRefreshItselfByDefault() {
        // http://127.0.0.1:4567/

        RestAssured. //
            given(). //
                port(port). //
            when(). //
                // default = param("refresh", true). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(containsString("<meta http-equiv=\"refresh\" content=\"15\" />"));
    }

    @Test
    void shouldNotRefreshItself() {
        // http://127.0.0.1:4567/?refresh=false
        
        RestAssured. //
            given(). //
                port(port). //
            when(). //
                param("refresh", false). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(not(containsString("<meta http-equiv=\"refresh\" content=\"15\" />")));
    }

    @Test
    void shouldPlayACoinForNewEntryOnce() {
        String sessionId = givenSessionId();
        givenEntryFor("anyUserForSound");

        assertSound(sessionId);
        assertNoSound(sessionId);
    }

    private String givenSessionId() {
        return RestAssured. //
            given(). //
                port(port). //
            when(). //
                get("/"). //
                sessionId();
    }

    private void assertSound(String sessionId) {
        RestAssured. //
            given(). //
                port(port). //
                sessionId(sessionId). //
            when(). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(containsString(MP3_NAME));
    }

    private void assertNoSound(String sessionId) {
        RestAssured. //
            given(). //
                port(port). //
                sessionId(sessionId). //
            when(). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(not(containsString(MP3_NAME)));
    }

    @Test
    void shouldHaveAddButtonOnAdminModeOnly() {
        // http://127.0.0.1:4567/?admin=true

        givenEntryFor("anyUserForPlus");

        // not show for regular
        RestAssured. //
            given(). //
                port(port). //
            when(). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(not(containsString("\"/record/anyUserForPlus?build=green\""))). //
                and(). //
                body(not(containsString("\"/record/anyUserForPlus?build=red\"")));
        
        RestAssured. //
            given(). //
                port(port). //
            when(). //
                param("admin", true). //
                get("/"). //
            then(). //
                statusCode(200). //
                body(containsString("\"/record/anyUserForPlus?build=green\"")). //
                and(). //
                body(containsString("\"/record/anyUserForPlus?build=red\""));
    }
    
    // admin
    // TODO admin=true to add new user with form field (user name should also be a form post parameter)

    // celebrate the winner    
    // TODO /winner button on the page displays the winner name with an proper image and a clapping hand sound
    // TODO finished=true highlights the winner (possible more than 1) and show some icon (the winner stairs large) next to winners

    // team perspective
    // TODO Click on branch name brings the same page where the branch is highlighted so I see my own score better.
    // http://127.0.0.1:4567/<branch-pair>/
    // TODO When the state of the current branch-pair (when selected) changes, a winning audio signal is played.

    // load/save
    // TODO if accept is JSON show the board as JSON - like load/save
    // TODO if type is JSON and post, merge the given data into the board (use DTOs using GSON?)

    // TODO show detailed history with time stamps for each event as a list on time line (with red and green and orange dots) - with character for each minute.

}
