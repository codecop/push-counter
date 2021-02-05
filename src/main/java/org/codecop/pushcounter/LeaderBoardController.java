package org.codecop.pushcounter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LeaderBoardController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LeaderBoard leaderBoard;

    public LeaderBoardController(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public String record(Request req, Response res) {
        String name = req.params("name");
        String build = req.queryParams("build");
        logger.info(String.format("Record %s %s", name, build));

        if (name == null || name.length() == 0) {
            res.status(400);
            return renderOkWithCurrentCount("Name path missing.");
        }

        if ("green".equalsIgnoreCase(build)) {
            leaderBoard.record(name, 1);
        } else {
            leaderBoard.record(name, -1);
        }
        logger.info(String.format("%s %s", name, build));

        res.status(201);
        res.header("Content-Type", "application/json");
        return renderOkWithCurrentCount("Saved.");
    }

    private String renderOkWithCurrentCount(String message) {
        return "{ \"message\": \"" + message + "\" }";
    }

    public String clear(@SuppressWarnings("unused") Request req, Response res) {
        logger.info(String.format("Clear"));

        leaderBoard.clear();

        res.header("Content-Type", "application/json");
        return renderOkWithCurrentCount("Cleared.");
    }

    public ModelAndView overview(Request req, @SuppressWarnings("unused") Response res) {
        logger.info(String.format("Overview"));
        List<Score> scores = leaderBoard.getScores();

        Map<Object, Object> model = new HashMap<>();
        model.put("scores", scores);
        model.put("refresh", Boolean.TRUE.toString().equalsIgnoreCase(req.queryParams("refresh")));
        model.put("admin", Boolean.TRUE.toString().equalsIgnoreCase(req.queryParams("admin")));

        return new ModelAndView(model, "counts.mustache");
    }

}
