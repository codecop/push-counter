package org.codecop.pushcounter;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import org.codecop.pushcounter.version.VersionController;

import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

    public static void main(String[] args) {
        // configure Spark
        port(determinePort());
        staticFiles.location("/public");

        // routes
        LeaderBoardController leaderBoardController = new LeaderBoardController(new LeaderBoard());
        get("/", (req, res) -> templates().render(leaderBoardController.overview(req, res)));
        get("/record/:name", (req, res) -> leaderBoardController.record(req, res));
        get("/clear", (req, res) -> leaderBoardController.clear(req, res));
        
        VersionController versionController = new VersionController();
        get("/version", (req, res) -> versionController.show(req, res));
    }

    private static int determinePort() {
        // get the Heroku assigned port
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        // else get the test assigned port
        String port = System.getProperty("PORT", "4567"); // -DPORT=4567
        return Integer.parseInt(port);
    }

    private static TemplateEngine templates() {
        return new MustacheTemplateEngine();
    }

}
