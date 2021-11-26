package ui;

import model.EventLog;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//GUI code based of off the Alarm System
//Code also based off JsonSerializationDemo
//Fantasy soccer team app
public class GUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JDesktopPane background;
    private Player player;
    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
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
    private static final String TEAM_1_NAME = "Manchester United";
    private static final String TEAM_2_NAME = "Tottenham Hotspur";
    private static final String TEAM_3_NAME = "Manchester City";
    private static final String TEAM_4_NAME = "Liverpool";

    private static final int PLAYER_PANEL_WIDTH = 500;
    private static final int PLAYER_PANEL_HEIGHT = 500;
    private static final int ROSTER_PANEL_WIDTH = 200;
    private static final int ROSTER_PANEL_HEIGHT = 250;
    private Label pace;
    private JTextField paceInput;
    private Label shooting;
    private JTextField shootingInput;
    private Label defending;
    private JTextField defendingInput;
    private Label goalsScored;
    private JTextField goalsScoredInput;
    private Label playerName;
    private JTextField playerNameInput;

    private JInternalFrame createPlayerMenu;
    private Label playerMenuMessage;
    private JButton playerMenu1;
    private JButton playerMenu2;
    private JButton playerMenu3;
    private JButton playerMenu4;
    private JButton enter;

    private JInternalFrame createRosterMenu;
    private Label rosterMenuMessage;
    private JButton rosterMenu1;
    private JButton rosterMenu2;
    private JButton rosterMenu3;
    private JButton rosterMenu4;

    private JInternalFrame createTeam1Roster;
    private JInternalFrame createTeam2Roster;
    private JInternalFrame createTeam3Roster;
    private JInternalFrame createTeam4Roster;

    private JInternalFrame savedImage;

    //modifies: this
    //effects: run fantasy team application and constructs teams
    public GUI() {
        background = new JDesktopPane();
        setContentPane(background);

        setTitle("CPSC 210 Project: Fantasy Team App ");
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        teamInit();
        leftMenu();
        background.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter1 = new JsonWriter(JSON_STORE1);
        jsonReader1 = new JsonReader(JSON_STORE1);
        jsonWriter2 = new JsonWriter(JSON_STORE2);
        jsonReader2 = new JsonReader(JSON_STORE2);
        jsonWriter3 = new JsonWriter(JSON_STORE3);
        jsonReader3 = new JsonReader(JSON_STORE3);

    }

    //Modifies: this
    //effects: initializes teams
    public void teamInit() {
        team1 = new Team(TEAM_1_NAME, 0);
        team2 = new Team(TEAM_2_NAME, 0);
        team3 = new Team(TEAM_3_NAME, 0);
        team4 = new Team(TEAM_4_NAME, 0);
    }

    //modifies: this
    //effects: initializes and creates JFraame main menu and adds it too desktop pane
    public void leftMenu() {
        JInternalFrame left = new JInternalFrame();
        left.setLayout(new BoxLayout(left.getContentPane(), BoxLayout.Y_AXIS));
        left.setTitle("Menu");
        left.add(new JButton(new CreatePlayerAction()));
        left.add(new JButton(new ViewRosterAction()));
        left.add(new JButton(new SaveAction()));
        left.add(new JButton(new LoadAction()));
        left.add(new JButton(new QuitAction()));
        pack();
        left.setVisible(true);
        left.pack();
        background.add(left);
    }

    //modifies: this
    //effects: creates player menu JFrame and its is to desktop pane
    public void createPlayerMenu() {
        createPlayerMenu = new JInternalFrame("Create a Player", true, true, true, false);
        initPlayerMenuLabelsAndButtons();
        createPlayerMenu.setLayout(new BoxLayout(createPlayerMenu.getContentPane(), BoxLayout.Y_AXIS));
        createPlayerMenu.add(playerName);
        createPlayerMenu.add(playerNameInput);
        createPlayerMenu.add(pace);
        createPlayerMenu.add(paceInput);
        createPlayerMenu.add(shooting);
        createPlayerMenu.add(shootingInput);
        createPlayerMenu.add(defending);
        createPlayerMenu.add(defendingInput);
        createPlayerMenu.add(goalsScored);
        createPlayerMenu.add(goalsScoredInput);
        createPlayerMenu.add(enter);
        addTeamButtons();
        pack();
        createPlayerMenu.setSize(PLAYER_PANEL_WIDTH, PLAYER_PANEL_HEIGHT);
        createPlayerMenu.setLocation((background.getWidth() / 2) - 190, 0);
        createPlayerMenu.setVisible(true);
        background.add(createPlayerMenu);
    }

    //modifies: this
    //effects: adds team buttons to menu
    public void addTeamButtons() {
        createPlayerMenu.add(playerMenuMessage);
        createPlayerMenu.add(playerMenu1);
        createPlayerMenu.add(playerMenu2);
        createPlayerMenu.add(playerMenu3);
        createPlayerMenu.add(playerMenu4);
    }

    //modifies: this
    //effects: initializes labels and buttons for player menu
    public void initPlayerMenuLabelsAndButtons() {
        playerMenuMessage = new Label("Select a team to add you player too: ");
        pace = new Label("Enter pace:");
        shooting = new Label("Enter shooting:");
        defending = new Label("Enter defending:");
        goalsScored = new Label("Enter goals scored:");
        playerName = new Label("Enter player name");
        paceInput = new JTextField("0");
        goalsScoredInput = new JTextField("0");
        defendingInput = new JTextField("0");
        shootingInput = new JTextField("0");
        playerNameInput = new JTextField("");
        playerMenu1 = new JButton(new GUI.Team1Action());
        playerMenu2 = new JButton(new GUI.Team2Action());
        playerMenu3 = new JButton(new GUI.Team3Action());
        playerMenu4 = new JButton(new GUI.Team4Action());
        enter = new JButton(new GUI.EnterAction());
    }

    //modifies: this
    //effects: initializes labels and buttons for roster menu
    public void initRosterMenuLabelsAndButtons() {
        rosterMenuMessage = new Label("Select a team");
        rosterMenu1 = new JButton(new GUI.Roster1Action());
        rosterMenu2 = new JButton(new GUI.Roster2Action());
        rosterMenu3 = new JButton(new GUI.Roster3Action());
        rosterMenu4 = new JButton(new GUI.Roster4Action());

    }

    //modifies: this
    //effects: creates roster menu JFrame and its is to desktop pane
    public void createRosterMenu() {
        createRosterMenu = new JInternalFrame("Select a Team", true, true, true, false);
        initRosterMenuLabelsAndButtons();
        createRosterMenu.setLayout(new BoxLayout(createRosterMenu.getContentPane(), BoxLayout.Y_AXIS));
        createRosterMenu.add(rosterMenuMessage);
        createRosterMenu.add(rosterMenu1);
        createRosterMenu.add(rosterMenu2);
        createRosterMenu.add(rosterMenu3);
        createRosterMenu.add(rosterMenu4);
        pack();
        createRosterMenu.setSize(ROSTER_PANEL_WIDTH, ROSTER_PANEL_HEIGHT);
        createRosterMenu.setLocation((background.getWidth() / 2) - 100, 200);
        createRosterMenu.setVisible(true);
        background.add(createRosterMenu);

    }

    //modifies: this
    //effects: creates roster1 JFrame and its is to desktop pane
    public void createTeam1Roster() {
        createTeam1Roster = new JInternalFrame("Roster", true, true, true, false);
        createTeam1Roster.setLayout(new GridLayout(3, 2));
        ArrayList<String> firstTeam = team1.returnPlayerList();
        for (String p : firstTeam) {
            createTeam1Roster.add(new JButton(p));
        }
        createTeam1Roster.setSize(ROSTER_PANEL_WIDTH, ROSTER_PANEL_HEIGHT);
        createTeam1Roster.setLocation((background.getWidth() / 2) - 100, 200);
        createTeam1Roster.setVisible(true);
        background.add(createTeam1Roster);
    }

    //modifies: this
    //effects: creates roster2 JFrame and its is to desktop pane
    public void createTeam2Roster() {
        createTeam2Roster = new JInternalFrame("Roster", true, true, true, false);
        createTeam2Roster.setLayout(new GridLayout(3, 2));
        ArrayList<String> firstTeam = team2.returnPlayerList();
        for (String p : firstTeam) {
            createTeam2Roster.add(new JButton(p));
        }
        createTeam2Roster.setSize(ROSTER_PANEL_WIDTH, ROSTER_PANEL_HEIGHT);
        createTeam2Roster.setLocation((background.getWidth() / 2) - 100, 200);
        createTeam2Roster.setVisible(true);
        background.add(createTeam2Roster);
    }

    //modifies: this
    //effects: creates roster3 JFrame and its is to desktop pane
    public void createTeam3Roster() {
        createTeam3Roster = new JInternalFrame("Roster", true, true, true, false);
        createTeam3Roster.setLayout(new GridLayout(3, 2));
        ArrayList<String> firstTeam = team3.returnPlayerList();
        for (String p : firstTeam) {
            createTeam3Roster.add(new JButton(p));

        }
        createTeam3Roster.setSize(ROSTER_PANEL_WIDTH, ROSTER_PANEL_HEIGHT);
        createTeam3Roster.setLocation((background.getWidth() / 2) - 100, 200);
        createTeam3Roster.setVisible(true);
        background.add(createTeam3Roster);
    }

    //modifies: this
    //effects: creates roster4 JFrame and its is to desktop pane
    public void createTeam4Roster() {
        createTeam4Roster = new JInternalFrame("Roster", true, true, true, false);
        createTeam4Roster.setLayout(new GridLayout(3, 2));
        ArrayList<String> firstTeam = team4.returnPlayerList();
        for (String p : firstTeam) {
            createTeam4Roster.add(new JButton(p));
        }
        createTeam4Roster.setSize(ROSTER_PANEL_WIDTH, ROSTER_PANEL_HEIGHT);
        createTeam4Roster.setLocation((background.getWidth() / 2) - 100, 200);
        createTeam4Roster.setVisible(true);
        background.add(createTeam4Roster);
    }


    //effects: Action event for Roster Button
    private class ViewRosterAction extends AbstractAction {
        ViewRosterAction() {
            super("View Roster");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createRosterMenu();
        }

    }

    //effects: Action event for create player Button
    private class CreatePlayerAction extends AbstractAction {
        CreatePlayerAction() {
            super("Create Player");

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createPlayerMenu();
        }

    }

    //requires: user input for pace, shooting and defending to be from 0-99
    //effects: Action event for enter Button in player menu
    private class EnterAction extends AbstractAction {
        EnterAction() {
            super("enter");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int pace = Integer.parseInt(paceInput.getText());
            int shooting = Integer.parseInt(shootingInput.getText());
            int defending = Integer.parseInt(defendingInput.getText());
            int goalsScored = Integer.parseInt(goalsScoredInput.getText());
            String playerName = playerNameInput.getText();
            player = new Player(playerName, pace, shooting, defending, goalsScored);

        }
    }

    //effects: Action event for Team 1 Button in create player menu
    private class Team1Action extends AbstractAction {
        Team1Action() {
            super("Manchester United");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            team1.addPlayer(player);
            //System.out.print("Added Player");
            createPlayerMenu.setVisible(false);
        }
    }

    //effects: Action event for Team2 Button in create player menu
    private class Team2Action extends AbstractAction {
        Team2Action() {
            super("Tottenham Hotspur");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            team2.addPlayer(player);
            //System.out.print("Added Player");
            createPlayerMenu.setVisible(false);
        }
    }

    //effects: Action event for Team3 Button in create player menu
    private class Team3Action extends AbstractAction {
        Team3Action() {
            super("Manchester City");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            team3.addPlayer(player);
            //System.out.print("Added Player");
            createPlayerMenu.setVisible(false);
        }
    }

    //effects: Action event for Team4 Button in create player menu
    private class Team4Action extends AbstractAction {
        Team4Action() {
            super("Liverpool");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            team4.addPlayer(player);
            //System.out.print("Added Player");
            createPlayerMenu.setVisible(false);
        }
    }

    //effects: Action event for Team1 Button in roster menu
    private class Roster1Action extends AbstractAction {
        Roster1Action() {
            super("Manchester United");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createRosterMenu.setVisible(false);
            createTeam1Roster();
        }
    }

    //effects: Action event for Team2 Button in roster menu
    private class Roster2Action extends AbstractAction {
        Roster2Action() {
            super("Tottenham Hotspur");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createRosterMenu.setVisible(false);
            createTeam2Roster();
        }
    }

    //effects: Action event for Team3 Button in roster menu
    private class Roster3Action extends AbstractAction {
        Roster3Action() {
            super("Manchester City");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createRosterMenu.setVisible(false);
            createTeam3Roster();
        }
    }

    //effects: Action event for Team4 Button in roster menu
    private class Roster4Action extends AbstractAction {
        Roster4Action() {
            super("Liverpool");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            createRosterMenu.setVisible(false);
            createTeam4Roster();
        }
    }

    //effects: Action event for Save Player Information Button
    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Player Information");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveWorkRoom();
            savedPanel();
        }
    }

    //effects: Action event for Load Player Information Button
    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Player Information");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadWorkRoom();

        }
    }

    //modifies: this
    //effects: create a JInternalFrame for the visual component(image) and ats it to desktop pane
    private void savedPanel() {
        savedImage = new JInternalFrame("Saved Player Information!", true, true, true, false);
        savedImage.setLocation(200, 60);
        savedImage.setSize(600,500);
        savedImage.add(new JButton(new ImageIcon("src/main/Images/saved.png")));
        //image taken from: https://www.flaticon.com/premium-icon/saved_2731143
        //validate();
        savedImage.setVisible(true);
        background.add(savedImage);


    }

    //effects:action event for quit button
    private class QuitAction extends AbstractAction {
        QuitAction() {
            super("Quit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.print("Log:" + "\n");
            LogPrinter lp = new ConsolePrinter();
            lp.printLog(EventLog.getInstance());
            System.exit(0);
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


    //main method that runs everything
    public static void main(String[] args)  {
        new GUI();
    }
}








