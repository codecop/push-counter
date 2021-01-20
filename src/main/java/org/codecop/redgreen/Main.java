package org.codecop.redgreen;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

    public static void main(String[] args) {
        // configure Spark
        port(determinePort());
        staticFiles.location("/public");

        LeaderBoardController leaderBoardController = new LeaderBoardController(new LeaderBoard());

        // routes
        get("/", (req, res) -> templates().render(leaderBoardController.overview(req, res)));
        get("/record/:name", (req, res) -> templates().render(leaderBoardController.record(req, res)));
        get("/clear", (req, res) -> templates().render(leaderBoardController.clear(req, res)));
    }

    private static int determinePort() {
        // get Heroku assigned üort
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        String port = System.getProperty("PORT", "4567"); // -DPORT=4567
        return Integer.parseInt(port);
    }

    private static TemplateEngine templates() {
        return new MustacheTemplateEngine();
    }

}
