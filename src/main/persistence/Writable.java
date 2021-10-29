package persistence;

import org.json.JSONObject;

//Code based off JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
