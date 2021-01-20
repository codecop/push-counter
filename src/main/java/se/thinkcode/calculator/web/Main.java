package se.thinkcode.calculator.web;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import spark.TemplateEngine;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {

    public static void main(String[] args) {
        // configure Spark
        port(determinePort());
        staticFileLocation("/public");
        startServer();

        // routes
        get("/", (req, res) -> templates().render(new CalculationController().render("")));
        post("/calculate", (req, res) -> templates().render(new CalculationController().apply(req, res)));
    }

    private static int determinePort() {
        String port = System.getenv().getOrDefault("PORT", "4567");
        return Integer.parseInt(port);
    }

    private static void startServer() {
        boolean isMaven = System.getenv().containsKey("MAVEN_CMD_LINE_ARGS");
        boolean fail = Math.random() > 0.75;

        if (isMaven && fail) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private static TemplateEngine templates() {
        return new MustacheTemplateEngine();
    }
        
}
