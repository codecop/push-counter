package org.codecop.redgreen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class LeaderBoardTest {

    LeaderBoard leaderBoard = new LeaderBoard();

    @Test
    void newLeaderBoardIsEmpty() {
        List<Score> scores = leaderBoard.getScores();
        assertEquals(0, scores.size());
    }

    @Test
    void recordFirstEntry() {
        leaderBoard.record("name", 1);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(1, scores.size());
        assertEquals("name", scores.get(0).name);
        assertEquals(1, scores.get(0).getScore());
    }

    @Test
    void recordFollowingEntries() {
        leaderBoard.record("name", 1);

        leaderBoard.record("name", 1);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(1, scores.size());
        assertEquals(2, scores.get(0).getScore());
    }

    @Test
    void recordMultipleEntriesKeepsOrder() {
        leaderBoard.record("john", 1);
        leaderBoard.record("jack", 1);
        
        leaderBoard.record("john", 2);

        List<Score> scores = leaderBoard.getScores();
        assertEquals(2, scores.size());
        assertEquals(3, scores.get(0).getScore());
        assertEquals(1, scores.get(1).getScore());
    }

    @Test
    void clearAllEntries() {
        leaderBoard.record("john", 1);

        leaderBoard.clear();

        List<Score> scores = leaderBoard.getScores();
        assertEquals(0, scores.size());
    }

    // ideas: keep each entry with time stamp in history for detail score
}

/*

API
Eine einzelne HTML Seite die das Board zeigt
  * seite mit meta refresh drinnen

Eine Post URL wo ich Branch und rot/grün pushed kann
Eine Post URL wo ich DB löschen kann

Ein Curl in den Builds

 */
