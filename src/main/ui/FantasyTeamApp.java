package ui;

import model.Player;
import model.Team;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

//Fantasy soccer team app
//Ui Code based off TellerApp
public class FantasyTeamApp {
    private Scanner input;
    //private int playerNumber = 0;
    private Player player;
    private Team team1 = new Team("team1", 0);
    private Team team2 = new Team("team2", 0);
    private Team team3 = new Team("team3", 0);
    private Team team4 = new Team("team4", 0);

    //effects: run fantasy team application
    public FantasyTeamApp() {
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
        team1 = new Team("team1", 0);
        team2 = new Team("team2", 0);
        team3 = new Team("team3", 0);
        team4 = new Team("team4", 0);
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
        }
    }

    //modifies: this
    //effects: creates a new player and adds initial data
    private void createPlayer() {
        String playerName = null;
        System.out.println("What is your player's name?");
        playerName = input.next();
        playerName.toLowerCase();
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
        System.out.println("\t1 -> team1");
        System.out.println("\t2 -> team2");
        System.out.println("\t3 -> team3");
        System.out.println("\t4 -> team4");
        System.out.println("\tn -> none");
    }

    //effects: view the roster list of the players on a team
    private void viewPlayersOnTeam() {
        System.out.println("which team would you like to view players on?");
        displayTeams();
        String team = input.next();
        if (team.equals("1")) {
            for (String playerName: team1.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("2")) {
            for (String playerName: team2.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("3")) {
            for (String playerName: team3.returnPlayerList()) {
                System.out.print(playerName + "\n");
            }
        } else if (team.equals("4")) {
            for (String playerName: team4.returnPlayerList()) {
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
        System.out.print("Shooting: " + player.getShooting() + "\n");;
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

    //modofies: this
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

}






