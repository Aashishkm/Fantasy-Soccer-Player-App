package ui;

import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//Fantasy soccer team app
//Ui Code based off TellerApp
//Code based off JsonSerializationDemo
public class FantasyTeamApp {
    private static final String JSON_STORE = "./data/team.json";
    private static final String JSON_STORE1 = "./data/team2.json";
    private static final String JSON_STORE2 = "./data/team3.json";
    private static final String JSON_STORE3 = "./data/team4.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader1;
    private JsonWriter jsonWriter2;
    private JsonReader jsonReader2;
    private JsonWriter jsonWriter3;
    private JsonReader jsonReader3;


    private Scanner input;
    //private int playerNumber = 0;
    private static final String TEAM_1_NAME = "Manchester United";
    private static final String TEAM_2_NAME = "Tottenham Hotspur";
    private static final String TEAM_3_NAME = "Manchester City";
    private static final String TEAM_4_NAME = "Liverpool";

    private Player player;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;

    //effects: run fantasy team application and constructs teams
    public FantasyTeamApp() throws FileNotFoundException {
        //team1 = new Team("Manchester United", 0);
        // team2 = new Team("Tottenham Hotspur", 0);
        //team3 = new Team("Manchester City", 0);
        //team4 = new Team("Liverpool", 0);

        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter3 = new JsonWriter(JSON_STORE3);
        jsonReader3 = new JsonReader(JSON_STORE3);

        runFantasyTeam();
    }

    //modifies: this
    //effects: processes user input
    private void runFantasyTeam() {
        boolean keepPrompting = true;
        String getInput = null;
        teamInit();
        while (keepPrompting) {
            initialOutputMenu();
            getInput = input.next();
            getInput = getInput.toLowerCase();
            if (getInput.equals("q")) {
                keepPrompting = false;
            } else {
                processInitialUserInput(getInput);
            }

        }
        System.out.println("Bye");

    }

    //Modifies: this
    //effects: initializes teams
    private void teamInit() {
        team1 = new Team(TEAM_1_NAME, 0);
        team2 = new Team(TEAM_2_NAME, 0);
        team3 = new Team(TEAM_3_NAME, 0);
        team4 = new Team(TEAM_4_NAME, 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");


    }

    // effects: display menu of options to user
    private void initialOutputMenu() {
        System.out.println("\tWhat would you like to do?");
        System.out.println("\tc -> Create new player, add initial stats to player, add player to a team");
        System.out.println("\tr -> View a team's roster");
        System.out.println("\tp -> View a player's statistics");
        System.out.println("\tup -> update a player's statistics");
        System.out.println("\tut -> update a team's statistics");
        System.out.println("\ts -> save teams to file");
        System.out.println("\tl -> load teams from file");

        System.out.println("\tq -> quit");
    }

    //modifies: this
    //effects: processes initial user command
    private void processInitialUserInput(String getInput) {
        if (getInput.equals("c")) {
            createPlayer();
            addPlayerToTeam();
        } else if (getInput.equals("r")) {
            viewPlayersOnTeam();
        } else if (getInput.equals("p")) {
            viewPlayersStats();
        } else if (getInput.equals("up")) {
            updatePlayersStats();
        } else if (getInput.equals("ut")) {
            updateTeamStatistics();
        } else if (getInput.equals("s")) {
            saveWorkRoom();
        } else if (getInput.equals("l")) {
            loadWorkRoom();
        }
    }

    //modifies: this
    //effects: creates a new player and adds initial data
    private void createPlayer() {
        String playerName = null;
        System.out.println("What is your player's name?");
        playerName = input.next();
        //playerName.toLowerCase();
        this.player = new Player(playerName.toLowerCase(), 0, 0, 0, 0);
        boolean keepGoing = true;

        while (keepGoing) {
            displayStats();
            String command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processUserInputPlayerStats(command, player);
            }
        }
    }


    //modifies: this
    //effects: based on user input, adds stats to players
    private void processUserInputPlayerStats(String command, Player player) {
        if (command.equals("p")) {
            addPace(player);
        } else if (command.equals("d")) {
            addDefending(player);
        } else if (command.equals("g")) {
            addGoals(player);
        } else if (command.equals("s")) {
            addShooting(player);
        }
    }

    //modifies: this
    //effects: adds pace to a player
    private void addPace(Player player) {
        System.out.println("Enter pace (between 0 -99)");
        int pace = parseInt(input.next());
        player.setPace(pace);

    }

    //modifies: this
    //effects: adds defending to a player
    private void addDefending(Player player) {
        System.out.println("Enter defending (between 0 -99)");
        int defending = parseInt(input.next());
        player.setDefending(defending);

    }

    //modifies: this
    //effects: adds shooting to a player
    private void addShooting(Player player) {
        System.out.println("Enter shooting (between 0 -99)");
        int shooting = parseInt(input.next());
        player.setShooting(shooting);

    }

    //modifies: this
    //effects: adds goals to a player
    private void addGoals(Player player) {
        System.out.println("Enter goals scored");
        int goals = parseInt(input.next());
        player.setGoalsScored(goals);

    }

    //effects: display menu of player stats options to update
    private void displayStats() {
        System.out.println("what stats would you like to update?");
        System.out.println("\tp -> pace");
        System.out.println("\td -> defending");
        System.out.println("\tg -> goals");
        System.out.println("\ts -> shooting");
        System.out.println("\tq -> quit");
    }

    //modifies: this
    //effects: adds a player to a team based on user input
    private void addPlayerToTeam() {
        System.out.println("which team would you like to add your player too?");
        displayTeams();
        String teamName = input.next();
        if (teamName.equals("1")) {
            team1.addPlayer(player);
        } else if (teamName.equals("2")) {
            team2.addPlayer(player);
        } else if (teamName.equals("3")) {
            team3.addPlayer(player);
        } else if (teamName.equals("4")) {
            team4.addPlayer(player);
        }
    }

    //effects: display menu of teams to select from
    private void displayTeams() {
        System.out.println("\t1 -> Manchester United");
        System.out.println("\t2 -> Tottenham Hotspur");
        System.out.println("\t3 -> Manchester City");
        System.out.println("\t4 -> Liverpool");
        System.out.println("\tn -> none");
    }

    //effects: view the roster list of the players on a team
    private void viewPlayersOnTeam() {
        System.out.println("which team would you like to view players on?");
        displayTeams();
        String team = input.next();
        if (team.equals("1")) {

            for (String playerName : team1.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("2")) {
            for (String playerName : team2.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("3")) {
            for (String playerName : team3.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("4")) {
            for (String playerName : team4.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        }
    }


    //effects: views a given players stats
    private void viewPlayersStats() {
        Player playerStatsToView = returnPlayerOnTeam();
        getStats(playerStatsToView);
    }

    //effects: method that prints out a players statistics
    private void getStats(Player player) {
        System.out.print("Shooting: " + player.getShooting() + "\n");
        System.out.print("Pace: " + player.getPace() + "\n");
        System.out.print("Defending: " + player.getDefending() + "\n");
        System.out.print("Goals Scored: " + player.getGoalsScored() + "\n");
    }

    //modifies: this
    //effects: returns the player object associated with a respective player's name and team
    private Player returnPlayerOnTeam() {
        Player thePlayer = null;
        System.out.println("what is the player's name?");
        String playerName = input.next();
        playerName.toLowerCase();
        System.out.println("what team is the player on?");
        displayTeams();
        String team = input.next();
        if (team.equals("1")) {
            thePlayer = team1.selectPlayer(playerName);
        } else if (team.equals("2")) {
            thePlayer = team2.selectPlayer(playerName);
        } else if (team.equals("3")) {
            thePlayer = team3.selectPlayer(playerName);
        } else if (team.equals("4")) {
            thePlayer = team4.selectPlayer(playerName);
        }
        return thePlayer;
    }

    //modifies: this
    //effects: updates a players statistics based on user input
    private void updatePlayersStats() {
        boolean keepGoing = true;
        Player thePlayer = returnPlayerOnTeam();
        while (keepGoing) {
            displayStats();
            String command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processUserInputPlayerStats(command, thePlayer);
            }
        }
    }

    //modifies: this
    //effects: For a given team, updates a teams win statistics, and prints out their current points
    private void updateTeamStatistics() {
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("which team would you like to select");
            displayTeams();
            String team = input.next();
            if (team.equals("1")) {
                setTeamStatistics(team1);
                System.out.print("team points: " + team1.getTeamPoints() + "\n");
            } else if (team.equals("2")) {
                setTeamStatistics(team2);
                System.out.print("team points: " + team2.getTeamPoints() + "\n");
            } else if (team.equals("3")) {
                setTeamStatistics(team3);
                System.out.print("team points: " + team3.getTeamPoints() + "\n");
            } else if (team.equals("4")) {
                setTeamStatistics(team4);
                System.out.print("team points: " + team4.getTeamPoints() + "\n");
            } else {
                keepGoing = false;
            }
        }
    }

    //modifies: this
    //effects: sets a teams statistics based on user input
    private void setTeamStatistics(Team team) {
        System.out.print("Did your team win or lose?\n");
        String outcome = input.next();
        outcome.toLowerCase();
        if (outcome.equals("win")) {
            team.setTeamPoints(3);
        }
    }

    // EFFECTS: saves team1s information to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(team1);
            jsonWriter.close();
            System.out.println("Saved " + team1.getTeamName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        saveWorkRoom1();
        saveWorkRoom2();
        saveWorkRoom3();
    }

    // MODIFIES: this
    // EFFECTS: loads team1 info from file
    private void loadWorkRoom() {
        try {
            team1 = jsonReader.read();
            System.out.println("Loaded " + team1.getTeamName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        loadWorkRoom1();
        loadWorkRoom2();
        loadWorkRoom3();

    }

    // EFFECTS: saves team2s information to file
    private void saveWorkRoom1() {
        try {
            jsonWriter1.open();
            jsonWriter1.write(team2);
            jsonWriter1.close();

            System.out.println("Saved " + team2.getTeamName() + " to " + JSON_STORE1);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE1);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team2 info from file
    private void loadWorkRoom1() {
        try {
            team2 = jsonReader1.read();
            System.out.println("Loaded " + team2.getTeamName() + " from " + JSON_STORE1);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE1);
        }
    }

    // EFFECTS: saves team3s information to file
    private void saveWorkRoom2() {
        try {
            jsonWriter2.open();
            jsonWriter2.write(team3);
            jsonWriter2.close();
            System.out.println("Saved " + team3.getTeamName() + " to " + JSON_STORE2);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE2);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team3 info from file
    private void loadWorkRoom2() {
        try {
            team3 = jsonReader2.read();
            System.out.println("Loaded " + team3.getTeamName() + " from " + JSON_STORE2);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE2);
        }
    }

    // EFFECTS: saves team4s information to file
    private void saveWorkRoom3() {
        try {
            jsonWriter3.open();
            jsonWriter3.write(team4);
            jsonWriter3.close();
            System.out.println("Saved " + team4.getTeamName() + " to " + JSON_STORE3);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE3);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads team4 info from file
    private void loadWorkRoom3() {
        try {
            team4 = jsonReader3.read();
            System.out.println("Loaded " + team4.getTeamName() + " from " + JSON_STORE3);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE3);
        }
    }

}






