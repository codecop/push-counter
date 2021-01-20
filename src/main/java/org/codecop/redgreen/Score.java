package org.codecop.redgreen;

public class Score {

    public final String name;
    private int score;

    public Score(String name) {
        this.name = name;
    }

    public void add(int scoreValue) {
        score += scoreValue;
    }

    public int getScore() {
        return score;
    }

}
