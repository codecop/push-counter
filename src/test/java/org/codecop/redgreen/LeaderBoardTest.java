package org.codecop.redgreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class LeaderBoardTest {

    LeaderBoard leaderBoard = new LeaderBoard();

    @Test
    void shouldStartWithEmptyLeaderBoard() {
        List<Score> scores = leaderBoard.getScores();
        assertEquals(0, scores.size());
    }

    @Test
    void shouldRecordFirstEntry() {
        leaderBoard.record("name", 1);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(1, scores.size());
        assertEquals("name", scores.get(0).name);
        assertEquals(1, scores.get(0).getScore());
    }

    @Test
    void shouldRecordFollowingEntries() {
        leaderBoard.record("name", 1);

        leaderBoard.record("name", 1);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(1, scores.size());
        assertEquals(2, scores.get(0).getScore());
    }

    @Test
    void shouldRecordMultipleEntriesInOrder() {
        leaderBoard.record("john", 1);
        leaderBoard.record("jack", 1);

        int current = leaderBoard.record("john", 2);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(2, scores.size());
        assertEquals(3, scores.get(0).getScore());
        assertEquals(1, scores.get(1).getScore());
        assertEquals(3, current);
    }

    @Test
    void shouldClearAllEntries() {
        leaderBoard.record("john", 1);

        leaderBoard.clear();

        List<Score> scores = leaderBoard.getScores();
        assertEquals(0, scores.size());
    }

    // ideas: keep each entry with time stamp in history for detail score
    // TODO do not allow values < 0
}
