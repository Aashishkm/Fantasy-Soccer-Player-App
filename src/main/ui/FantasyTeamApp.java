package ui;

import model.Player;
import model.Team;

import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class FantasyTeamApp {
    private Scanner input;
    private int playerNumber = 0;
    private Player player;
    private Team team1 = new Team("team1", 0);
    private Team team2 = new Team("team2", 0);
    private Team team3 = new Team("team3", 0);
    private Team team4 = new Team("team4", 0);

    public FantasyTeamApp() {

        runFantasyTeam();
    }

    private void runFantasyTeam() {
        boolean keepPrompting = true;
        String getInput = null;

        teamInit();

        while (keepPrompting) {
            outputMenu();
            getInput = input.next();
            getInput = getInput.toLowerCase();
            if (getInput.equals("yes")) {
                createPlayer();
                addPlayerToTeam();
                viewPlayersOnTeam();
                viewPlayersStats();

            } else {
                keepPrompting = false;
            }

        }
        System.out.println("Bye");
    }

    private void teamInit() {
        team1 = new Team("team1", 0);
        team2 = new Team("team2", 0);
        team3 = new Team("team3", 0);
        team4 = new Team("team4", 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");


    }

    private void outputMenu() {
        System.out.println("\nWould you like to create another player? (yes or no)");

    }

    private void createPlayer() {
        String playerName = null;
        System.out.println("What is your player's name?");
        playerName = input.next();
        this.player = new Player(playerName, 0, 0, 0, 0);
        boolean keepGoing = true;

        while (keepGoing) {
            displayStats();
            String command = input.next();
            if (command.equals("p")) {
                addPace();
            } else if (command.equals("d")) {
                addDefending();
            } else if (command.equals("g")) {
                addGoals();
            } else if (command.equals("s")) {
                addShooting();
            } else {
                keepGoing = false;
            }

        }
    }

    private void addPace() {
        System.out.println("Enter pace (between 0 -99)");
        int pace = parseInt(input.next());
        player.setPace(pace);

    }

    private void addDefending() {
        System.out.println("Enter defending (between 0 -99)");
        int defending = parseInt(input.next());
        player.setDefending(defending);

    }

    private void addShooting() {
        System.out.println("Enter shooting (between 0 -99)");
        int shooting = parseInt(input.next());
        player.setShooting(shooting);

    }

    private void addGoals() {
        System.out.println("Enter goals scored");
        int goals = parseInt(input.next());
        player.setGoalsScored(goals);

    }

    private void displayStats() {
        System.out.println("what stats would you like to add?");
        System.out.println("\tp -> pace");
        System.out.println("\td -> defending");
        System.out.println("\tg -> goals");
        System.out.println("\ts -> shooting");
        System.out.println("\tq -> quit");
    }

    private void displayTeams() {
        System.out.println("\t1 -> team1");
        System.out.println("\t2 -> team2");
        System.out.println("\t3 -> team3");
        System.out.println("\t4 -> team4");
    }

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

    private void displayTeamToViewPlayers() {
        System.out.println("which team's roster would you like to view?");
        System.out.println("\t1 -> team1");
        System.out.println("\t2 -> team2");
        System.out.println("\t3 -> team3");
        System.out.println("\t4 -> team4");
        System.out.println("\tn -> none");
    }

    private void viewPlayersOnTeam() {
        displayTeamToViewPlayers();
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

    private void viewPlayersStats() {
        System.out.println("would you like to view a player's statistics? (yes or no)");
        String userInput = input.next();
        Player thePlayer;
        if (userInput.equals("yes")) {
            System.out.println("what is the player's name?");
            String playerName = input.next();
            displayTeams();
            System.out.println("what team is the player on");
            String team = input.next();
            if (team.equals("1")) {
                thePlayer = team1.selectPlayer(playerName);
                getStats(thePlayer);
            } else if (team.equals("2")) {
                thePlayer = team2.selectPlayer(playerName);
                getStats(thePlayer);
            } else if (team.equals("3")) {
                thePlayer = team3.selectPlayer(playerName);
                getStats(thePlayer);
            } else if (team.equals("4")) {
                thePlayer = team4.selectPlayer(playerName);
                getStats(thePlayer);
            }
        }
    }

    private void getStats(Player player) {
        System.out.print("Shooting: " + player.getShooting() + "\n");;
        System.out.print("Pace: " + player.getPace() + "\n");
        System.out.print("Defending: " + player.getDefending() + "\n");
        System.out.print("Goals Scored: " + player.getGoalsScored() + "\n");
    }

}




