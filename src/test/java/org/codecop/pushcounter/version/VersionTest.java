package org.codecop.pushcounter.version;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.codecop.pushcounter.ApplicationSetupExtension;
import org.codecop.pushcounter.ApplicationSetupExtension.Port;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.restassured.RestAssured;

@ExtendWith(ApplicationSetupExtension.class)
class VersionTest {

    @Test
    void shouldShowVersion(@Port int port) {
        // http://127.0.0.1:4567/version
        RestAssured. //
            given(). //
                port(port). //
                accept("application/json"). //
            when(). //
                get("/version"). //
            then(). //
                statusCode(200). //
                header("Content-Type", equalTo("application/json")). //
                body(containsString("{ \"version\": \""));
    }

}
