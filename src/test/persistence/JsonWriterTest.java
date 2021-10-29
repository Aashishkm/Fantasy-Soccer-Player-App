package persistence;
//Code based off JsonSerializationDemo

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Team t1 = new Team("Liverpool", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team t1 = new Team("Liverpool", 0);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(t1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeam.json");
            t1 = reader.read();
            assertEquals("Liverpool", t1.getTeamName());
            assertEquals(0, t1.getTeamPoints());
            assertEquals(0, t1.getTeamSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTeam() {
        try {
            Team t1 = new Team("Liverpool", 0);
            t1.addPlayer(new Player("aashish", 90, 60, 20, 4));
            t1.addPlayer(new Player("bob", 99,99,99,9));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeam.json");
            writer.open();
            writer.write(t1);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeam.json");
            t1 = reader.read();
            assertEquals("Liverpool", t1.getTeamName());
            assertEquals(0, t1.getTeamPoints());
            List<Player> team = t1.getPlayers();
            assertEquals(2, team.size());
            checkPlayer("aashish",90,60,20,4, team.get(0));
            checkPlayer("bob", 99,99,99, 9, team.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}