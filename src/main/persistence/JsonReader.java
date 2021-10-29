package persistence;

import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Code based off JsonSerializationDemo
// Represents a reader that reads team from JSON data stored in file

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("teamName");
        int teamPoints = jsonObject.getInt("teamPoints");
        Team t1 = new Team(name,teamPoints);
        addTeam(t1, jsonObject);
        return t1;
    }

    // MODIFIES: t1
    // EFFECTS: parses thingies from JSON object and adds them team
    private void addTeam(Team t1, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("team");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(t1, nextPlayer);
        }
    }

    // MODIFIES: t1
    // EFFECTS: parses thingy from JSON object and adds it to team
    private void addPlayer(Team t1, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int pace = jsonObject.getInt("pace");
        int defending = jsonObject.getInt("defending");
        int shooting = jsonObject.getInt("shooting");
        int goalsScored = jsonObject.getInt("goalsScored");
        Player p1 = new Player(name, pace, shooting, defending, goalsScored);
        t1.addPlayer(p1);
    }
}
