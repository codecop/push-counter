package org.codecop.pushcounter.version;

import spark.Request;
import spark.Response;

public class VersionController {

    public String show(@SuppressWarnings("unused") Request req, Response res) {
        res.status(200);

        res.header("Content-Type", "application/json");
        String gitVersion = new ManifestVersion("Git-Version").extract();
        return "{ " + //
                "\"version\": \"" + gitVersion + "\"," + // 
                "\"commit\": \"https://github.com/codecop/push-counter/commit/" + gitVersion + "\"" + // 
                "}";
    }

}
