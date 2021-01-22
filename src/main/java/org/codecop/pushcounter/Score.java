package org.codecop.pushcounter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public List<String> ones() {
        return fill(getScore() % 5, Integer.toString(1));
    }

    public List<String> fives() {
        return fill((getScore() % 25) / 5, Integer.toString(5));
    }

    public List<String> twentyFives() {
        return fill(getScore() / 25, Integer.toString(25));
    }

    private List<String> fill(int number, String value) {
        return IntStream.range(0, number). //
                mapToObj(i -> value). //
                collect(Collectors.toList());
    }

}
