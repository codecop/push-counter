package org.codecop.pushcounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class LeaderBoard {

    private List<Score> scores = new CopyOnWriteArrayList<>();
    private AtomicInteger totalScore = new AtomicInteger();

    public List<Score> getScores() {
        return scores;
    }

    public synchronized void record(String name, int scoreValue) {
        Score score = findScore(name);
        score.add(scoreValue);
        totalScore.addAndGet(scoreValue);
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
        totalScore.set(0);
    }

    public int totalScore() {
        return totalScore.get();
    }

}
