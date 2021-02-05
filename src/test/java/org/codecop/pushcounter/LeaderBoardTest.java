package org.codecop.pushcounter;

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

        leaderBoard.record("john", 2);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(2, scores.size());
        assertEquals(3, scores.get(0).getScore());
        assertEquals(1, scores.get(1).getScore());
    }

    @Test
    void shouldClearAllEntries() {
        leaderBoard.record("john", 1);

        leaderBoard.clear();

        List<Score> scores = leaderBoard.getScores();
        assertEquals(0, scores.size());
    }

    // TODO Keep each entry with its time stamp as history for detailed score, too.
    // TODO allow entries with delta 0 and delta -1 as well and show them too
    // entries have +1/0/-1 as category
}
