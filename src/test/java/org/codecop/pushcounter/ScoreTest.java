package org.codecop.pushcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ScoreTest {

    Score score = new Score("foo");

    @Test
    void shouldListFactorsOfScore() {
        score.add(3);

        assertEquals(3, score.ones().size());
        assertEquals(0, score.fives().size());
        assertEquals(0, score.twentyFives().size());
    }

    @Test
    void shouldListFactorsOfFive() {
        score.add(12);

        assertEquals(2, score.ones().size());
        assertEquals(2, score.fives().size());
        assertEquals(0, score.twentyFives().size());
    }

    @Test
    void shouldListMaximum() {
        score.add(124);

        assertEquals(4, score.ones().size());
        assertEquals(4, score.fives().size());
        assertEquals(4, score.twentyFives().size());
    }

}
