package org.codecop.redgreen;

public class Score {

    public final String name;
    private int score;

    public Score(String name) {
        this.name = name;
    }

    public int add(int scoreValue) {
        score += scoreValue;
        return score;
    }

    public int getScore() {
        return score;
    }

}
