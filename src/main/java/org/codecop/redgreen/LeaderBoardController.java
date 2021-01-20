package org.codecop.redgreen;

import java.util.HashMap;
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

    public ModelAndView record(Request req, Response res) {
        String name = req.params("name");
        String build = req.queryParams("build");
        logger.info(String.format("Record %s %s ", name, build));

        if (name == null || name.length() == 0) {
            res.status(400);
            return render(0);
        }

        int currentScore = 0;
        if ("green".equalsIgnoreCase(build)) {
            currentScore = leaderBoard.record(name, 1);
        } else {
            currentScore = leaderBoard.record(name, -1);
        }
        logger.info(String.format("%s %s currently %s", name, build, currentScore));

        res.status(201);
        return render(currentScore);
    }

    private ModelAndView render(int currentScore) {
        Map<Object, Object> model = new HashMap<>();
        model.put("current", currentScore);
        return new ModelAndView(model, "ok.mustache");
    }

    public ModelAndView clear(@SuppressWarnings("unused") Request req, Response res) {
        logger.info(String.format("Clear"));

        leaderBoard.clear();
        res.status(200);

        return render(1);
    }

}
