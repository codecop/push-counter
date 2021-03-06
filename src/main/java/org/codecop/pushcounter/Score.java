package org.codecop.pushcounter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Score {

    public final String name;
    private final AtomicInteger score = new AtomicInteger();

    public Score(String name) {
        this.name = name;
    }

    public void add(int scoreValue) {
        score.addAndGet(scoreValue);
    }

    public int getScore() {
        return score.get();
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
