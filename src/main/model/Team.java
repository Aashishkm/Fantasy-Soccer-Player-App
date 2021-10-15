package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Player> team;
    public static final int MAX_TEAM_SIZE = 6;
    private String teamName;
    private int teamPoints;


    public Team(String teamName, int teamPoints) {
        this.teamName = teamName;
        this.teamPoints = teamPoints;
        team = new ArrayList<Player>();
    }

    //modifies: this
    //effects: adds a player to the team
    public boolean addPlayer(Player playerToAdd) {

        if (team.size() < MAX_TEAM_SIZE) {

            team.add(playerToAdd);

            return true;
        }
        return false;
    }

    //Requires: non-empty list of type player, playerName
    //modifies: this
    //effects: removes a player based on their name
    public boolean removePlayer(String playerNameToRemove) {
        for (Player player: team) {
            if (player.getPlayerName().equals(playerNameToRemove)) {
                team.remove(player);
                return true;
            }
        }
        return false;
    }

    //requires: non-empty list of type player, playerName
    //modifies: this
    //effects: selects a player with the respective name
    public Player selectPlayer(String playerNameToSelect) {
        for (Player player: team) {
            if (player.getPlayerName().equals(playerNameToSelect)) {
                return player;
            }
        }
        return null;
    }

    //effects: returns the rosterlist of type string of playernames
    public ArrayList<String> returnPlayerList() {
        ArrayList<String> rosterList = new ArrayList<String>();
        for (Player player: team) {
            rosterList.add(player.getPlayerName());


        }
        return rosterList;
    }

    //modifies: this
    //effects: adds team points
    public void setTeamPoints(int teamPoints) {
        this.teamPoints = this.teamPoints + teamPoints;
    }

    //setters and getters
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

}
