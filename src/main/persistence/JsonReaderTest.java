package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//Code based off JsonSerializationDemo

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        JsonReader reader1 = new JsonReader("./data/testReaderEmptyTeam.json");
        try {

            Team t1 = reader.read();
            t1 = reader1.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTeam() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        JsonReader reader1 = new JsonReader("./data/noSuchFile.json");
        try {
            Team t1 = reader.read();
            assertEquals("Manchester United", t1.getTeamName());
            assertEquals(0, t1.getTeamPoints());
            assertEquals(0, t1.getTeamSize());
            t1 = reader1.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeam.json");
        JsonReader reader1 = new JsonReader("./data/noSuchFile.json");
        try {
            Team t1 = reader.read();
            assertEquals("Manchester United", t1.getTeamName());
            assertEquals(0, t1.getTeamPoints());
            List<Player> team = t1.getPlayers();
            assertEquals(2, team.size());
            checkPlayer("aashish",0,0,0,0, team.get(0));
            checkPlayer("joe", 5,33,2,2, team.get(1));
            t1 = reader1.read();
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}