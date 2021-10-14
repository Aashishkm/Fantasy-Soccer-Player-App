package model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PlayerTest {
    Player player;
    Player player1;
    Player player2;

    @BeforeAll
    void runBefore() {
        player = new Player("player0", 99, 22, 33, 11);
        player1 = new Player("player1", 10, 33, 12, 4);
        player2 = new Player("player2", 50, 99, 88, 0);
    }

    @Test

    void testGetPlayerName() {
        assertTrue(player.getPlayerName().equals("player0"));
        assertFalse(player1.getPlayerName().equals("jhe"));
    }

    @Test
    void testSetPace() {
        player.setPace(33);
        player2.setPace(51);
        assertEquals( 33, player.getPace());
        assertFalse(50 == player2.getPace());

    }

    @Test
    void testGetPace() {
        assertEquals( 10, player1.getPace());
        assertFalse(40 == player2.getPace());
    }

    @Test
    void testSetDefending() {
        player.setDefending(0);
        player1.setDefending(33);
        player2.setDefending(22);
        assertEquals( 0, player.getDefending());
        assertTrue(player1.getDefending() == 33);
        assertFalse(50 == player2.getDefending());

    }

    @Test
    void testGetDefending() {
        assertTrue( 12 ==  player1.getDefending());
        assertFalse(40 == player2.getDefending());
    }

    @Test
    void testSetShooting() {
        player.setShooting(40);
        player1.setShooting(0);
        player2.setShooting(88);
        assertEquals( 40, player.getShooting());
        assertTrue(player1.getShooting() == 0);
        assertFalse(50 == player2.getShooting());

    }

    @Test
    void testGetShooting() {
        assertTrue( 40 == player.getShooting());
        assertFalse(50 == player2.getShooting());
    }

    @Test
    void testSetGoalsScored() {
        player.setGoalsScored(1);
        player1.setGoalsScored(35);
        player2.setGoalsScored(0);
        assertEquals( 12, player.getGoalsScored());
        assertTrue(player1.getGoalsScored() == 39);
        assertFalse(12 == player2.getGoalsScored());

    }

    @Test
    void testGetGoalsScored() {
        assertTrue( 0 ==  player2.getGoalsScored());
        assertTrue( 11 ==  player.getGoalsScored());
        assertFalse(40 == player1.getGoalsScored());
    }
}



