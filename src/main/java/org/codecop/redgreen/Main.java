package org.codecop.redgreen;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import se.thinkcode.calculator.web.CalculationController;
import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

    public static void main(String[] args) {
        // configure Spark
        port(determinePort());
        staticFiles.location("/public");

        LeaderBoardController leaderBoardController = new LeaderBoardController(new LeaderBoard());
        
        get("/record/:name", (req, res) -> templates().render(leaderBoardController.record(req, res)));
        get("/clear", (req, res) -> templates().render(leaderBoardController.clear(req, res)));

        CalculationController calculationController = new CalculationController();
        // routes
        get("/", (req, res) -> templates().render(calculationController.render("")));
        post("/calculate", (req, res) -> templates().render(calculationController.apply(req, res)));
        /*
        // static pages
        get(to("/"), (req, res) -> pages.welcome());
        get(to("/welcome"), (req, res) -> pages.welcome());
        get(to("/requirements"), (req, res) -> pages.requirements());
        get(to("/add"), (req, res) -> pages.newItemForm());
        */
    }

    private static int determinePort() {
        String port = System.getenv().getOrDefault("PORT", "4567");
        return Integer.parseInt(port);
    }

    private static TemplateEngine templates() {
        return new MustacheTemplateEngine();
    }

}
