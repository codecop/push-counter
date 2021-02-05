package org.codecop.pushcounter.version;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.codecop.pushcounter.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import spark.Spark;

class VersionTest {

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
    void shouldShowVersion() {
        // http://127.0.0.1:4567/version
        RestAssured. //
            given(). //
                port(PORT). //
                accept("application/json"). //
            when(). //
                get("/version"). //
            then(). //
                statusCode(200). //
                header("Content-Type", equalTo("application/json")). //
                body(containsString("{ \"version\": \""));
    }

}
