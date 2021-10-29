package persistence;

import model.Player;
import static org.junit.jupiter.api.Assertions.assertEquals;
//Code based off JsonSerializationDemo

public class JsonTest {
    protected void checkPlayer(String playerName, int pace, int shooting, int defending, int goalsScored, Player p1) {
        assertEquals(playerName, p1.getPlayerName());
        assertEquals(pace, p1.getPace());
        assertEquals(defending, p1.getDefending());
        assertEquals(goalsScored, p1.getGoalsScored());
        assertEquals(shooting, p1.getShooting());
    }
}
