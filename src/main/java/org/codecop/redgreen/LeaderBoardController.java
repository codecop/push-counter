package org.codecop.redgreen;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LeaderBoardController {

    private final LeaderBoard leaderBoard;

    public LeaderBoardController(LeaderBoard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public ModelAndView record(Request req, Response res) {
        String name = req.params("name");
        int current = 0;

        if (name != null && name.length() > 0) {

            String build = req.queryParams("build");
            if ("green".equalsIgnoreCase(build)) {
                current = leaderBoard.record(name, 1);
            } else {
                current = leaderBoard.record(name, -1);
            }
            res.status(201);

        } else {
            res.status(400);
        }

        Map<Object, Object> model = new HashMap<>();
        model.put("current", current);
        return new ModelAndView(model, "ok.mustache");
    }

    public ModelAndView clear(@SuppressWarnings("unused") Request req, Response res) {
        leaderBoard.clear();
        res.status(200);

        Map<Object, Object> model = new HashMap<>();
        model.put("current", 1);
        return new ModelAndView(model, "ok.mustache");
    }

}
