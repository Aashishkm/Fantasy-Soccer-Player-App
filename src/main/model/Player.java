package model;

import org.json.JSONObject;
import persistence.Writable;

//Class that creates a player, and assigns that player specific attributes
// such as a name, pace, shooting, defending and goalsScored
//Code based off JsonSerializationDemo

public class Player implements Writable {
    private String playerName;
    private int pace;
    private int shooting;
    private int defending;
    private int goalsScored;

    //Constructor
    //Modifies: this
    //Effects: initialises a player object with values such as name, pace shooting, defending and goalsscored

    public Player(String playerName, int pace, int shooting, int defending, int goalsScored) {
        this.playerName = playerName;
        this.pace = pace;
        this.shooting = shooting;
        this.defending = defending;
        this.goalsScored = goalsScored;
    }
    //setters and getters

    public String getPlayerName() {
        return playerName;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public int getPace() {
        return pace;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getShooting() {
        return shooting;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    public int getDefending() {
        return defending;
    }



    public void setGoalsScored(int goalsScored) {
        this.goalsScored = this.goalsScored + goalsScored;
    }

    public int getGoalsScored() {
        return goalsScored;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", playerName);
        json.put("pace", pace);
        json.put("defending", defending);
        json.put("goalsScored", goalsScored);
        json.put("shooting", shooting);
        return json;
    }

}
