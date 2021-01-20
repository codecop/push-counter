package org.codecop.redgreen;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {

    private final List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return scores;
    }

    public void record(String name, int scoreValue) {
        Score score = findScore(name);
        score.add(scoreValue);
    }

    private Score findScore(String name) {
        return scores.stream(). //
                filter(score -> score.name.equals(name)). //
                findFirst(). //
                orElseGet(() -> newScore(name));
    }

    private Score newScore(String name) {
        Score score = new Score(name);
        scores.add(score);
        return score;
    }

}
