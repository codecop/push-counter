package org.codecop.pushcounter;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {

    private List<Score> scores = new ArrayList<>();

    public List<Score> getScores() {
        return scores;
    }

    public int record(String name, int scoreValue) {
        Score score = findScore(name);
        return score.add(scoreValue);
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

    public void clear() {
        scores = new ArrayList<>();
    }

}
