package org.codecop.pushcounter;

import java.util.ArrayList;
import java.util.List;

public class LeaderBoard {

    private List<Score> scores = new ArrayList<>();

    public synchronized List<Score> getScores() {
        return scores;
    }

    public synchronized void record(String name, int scoreValue) {
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

    public synchronized void clear() {
        scores = new ArrayList<>();
    }

}
