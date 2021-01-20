package org.codecop.redgreen;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {

    private final List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return scores;
    }

    public void record(String name, int scoreValue) {
        Score score = new Score(name);
        score.add(scoreValue);
        scores.add(score);
    }

}
