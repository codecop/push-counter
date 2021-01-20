package org.codecop.redgreen;

import static org.junit.jupiter.api.Assertions.*;

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

}

/*
 * keep order of first element
 * 
 * Eine Datenbank mit 1 Tabelle und 2 Spalten
  Branch - Green Pushes
  ev, red pushes auch

API
Eine einzelne HTML Seite die das Board zeigt
  * seite mit meta refresh drinnen

Eine Post URL wo ich Branch und rot/grün pushed kann
Eine Post URL wo ich DB löschen kann

Ein Curl in den Builds

 */
