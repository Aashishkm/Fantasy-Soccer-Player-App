package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team teamTest = new Team("tester", 0);
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;
    Player player6;
    Player player7;
    Player player8;

    @BeforeEach
    void runBefore() {
        player1 = new Player("1", 1, 1, 1, 1);
        player2 = new Player("2", 2, 2, 2, 2);
        player3 = new Player("3", 3, 3, 3, 3);
        player4 = new Player("4", 4, 4, 4, 4);
        player5 = new Player("5", 5, 5, 5, 5);
        player6 = new Player("6", 6, 6, 6, 6);
        player7 = new Player("7", 7, 7, 7, 7);
        player8 = new Player("8", 8, 8, 8, 8);

    }

    @Test
    void testAddPlayer() {
        assertTrue(teamTest.addPlayer(player1));
        assertTrue(teamTest.addPlayer(player2));
        assertTrue(teamTest.addPlayer(player3));
        assertTrue(teamTest.addPlayer(player4));
        assertTrue(teamTest.addPlayer(player5));
        assertTrue(teamTest.addPlayer(player6));
        assertFalse(teamTest.addPlayer(player7));

    }

    @Test
    void testRemovePlayer() {
        teamTest.addPlayer(player1);
        teamTest.addPlayer(player2);
        teamTest.addPlayer(player3);
        teamTest.addPlayer(player4);
        teamTest.addPlayer(player5);
        assertTrue(teamTest.removePlayer("1"));
        assertFalse(teamTest.removePlayer("hello"));
        assertTrue(teamTest.removePlayer("2"));
        assertTrue(teamTest.removePlayer("3"));
        assertTrue(teamTest.removePlayer("4"));
        assertTrue(teamTest.removePlayer("5"));
        assertFalse(teamTest.removePlayer("3"));


    }

    @Test
    void testSelectPlayer() {
        teamTest.addPlayer(player1);
        teamTest.addPlayer(player2);
        teamTest.addPlayer(player3);
        teamTest.addPlayer(player4);
        teamTest.addPlayer(player5);
        teamTest.addPlayer(player6);

        assertEquals(player1, teamTest.selectPlayer("1"));
        assertEquals(player2, teamTest.selectPlayer("2"));
        assertEquals(player3, teamTest.selectPlayer("3"));
        assertEquals(player4, teamTest.selectPlayer("4"));
        assertEquals(player5, teamTest.selectPlayer("5"));
        assertEquals(player6, teamTest.selectPlayer("6"));
        assertEquals(null, teamTest.selectPlayer("29"));


    }

    @Test
    void testReturnPlayerList() {
        teamTest.addPlayer(player1);
        teamTest.addPlayer(player2);
        teamTest.addPlayer(player3);
        teamTest.addPlayer(player4);
        teamTest.addPlayer(player5);
        teamTest.addPlayer(player6);

        ArrayList<String> rosterList = teamTest.returnPlayerList();

        assertTrue(rosterList.get(0) == "1");
        assertTrue(rosterList.get(1) == "2");
        assertTrue(rosterList.get(2) == "3");
        assertTrue(rosterList.get(3) == "4");
        assertTrue(rosterList.get(4) == "5");
        assertTrue(rosterList.get(5) == "6");
        assertFalse(rosterList.get(3) == "1");

    }

    @Test
    void setTeamPoints() {
        teamTest.setTeamPoints(3);
        assertTrue(3 == teamTest.getTeamPoints());
        teamTest.setTeamPoints(12);
        assertEquals(15, teamTest.getTeamPoints());
    }

    @Test
    void setTeamName() {
        teamTest.setTeamName("bestTeam");
        assertTrue(teamTest.getTeamName().equals("bestTeam"));
    }

}