package com.pongProject.gameFolder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.pongProject.database.PongDB;
import com.pongProject.logic.GamePanel;

public class Menu implements ActionListener {

    public static  String username;

    private PongDB db;

    public JFrame window = new JFrame();

    public static int globalDifficulty;

    public static int playerOrComp;

    public static boolean discoMode = false;

    public static boolean musicToggle = true;

    public static String player1Colour = "/com/pongProject/player/paddleWhite.png";
    public static String player2Colour = "/com/pongProject/player/paddleWhite.png";

    // buttons are initialised outside of constructor, so they are global and actionListener can work as intended

    // Player VS Computer (PVC)
    ButtonTemplate pvcButton = new ButtonTemplate(75, "Player VS Computer");
    // Player VS Player (PVP)
    ButtonTemplate pvpButton = new ButtonTemplate(175, "Player VS Player");
    // Skins
    ButtonTemplate skinsButton = new ButtonTemplate(275, "Skins");
    // Leaderboard
    ButtonTemplate leaderBoardButton = new ButtonTemplate(375, "Leaderboard");
    // Exit
    ButtonTemplate exitButton = new ButtonTemplate(475, "Exit");

    // PVC Normal
    ButtonTemplate pvcNormalButton = new ButtonTemplate(75, "P VS C - Normal");
    // PVC Hard
    ButtonTemplate pvcHardButton = new ButtonTemplate(175, "P VS C - Hard");

    // PVP Normal
    ButtonTemplate pvpNormalButton = new ButtonTemplate(75, "P VS P - Normal");
    // PVP Hard
    ButtonTemplate pvpHardButton = new ButtonTemplate(175, "P VS P - Hard");

    // back buttons (backButton1 is placed near the centre of the frame, backButton2 is placed near the bottom of the frame)
    ButtonTemplate backButton = new ButtonTemplate(275, "Back");
    ButtonTemplate backButton2 = new ButtonTemplate(525, "Back");

    // Leaderboard panels
    LeaderboardPanelTemplate scoresPanel = new LeaderboardPanelTemplate(50, 50);
    LeaderboardPanelTemplate ralliesPanel = new LeaderboardPanelTemplate(432, 50);

    // initialising skin select buttons
    ButtonTemplate player1 = new ButtonTemplate(10, "Player 1:");
    ButtonTemplate player2 = new ButtonTemplate(250, "Player 2:");

    SkinSelectTemplate white = new SkinSelectTemplate(257, 75);
    SkinSelectTemplate cyan = new SkinSelectTemplate(357, 75);
    SkinSelectTemplate green = new SkinSelectTemplate(457, 75);
    SkinSelectTemplate pink = new SkinSelectTemplate(557, 75);
    SkinSelectTemplate yellow = new SkinSelectTemplate(257, 175);
    SkinSelectTemplate magenta = new SkinSelectTemplate(357, 175);
    SkinSelectTemplate red = new SkinSelectTemplate(457, 175);
    SkinSelectTemplate orange = new SkinSelectTemplate(557, 175);

    SkinSelectTemplate white2 = new SkinSelectTemplate(257, 325);
    SkinSelectTemplate cyan2 = new SkinSelectTemplate(357, 325);
    SkinSelectTemplate green2 = new SkinSelectTemplate(457, 325);
    SkinSelectTemplate pink2 = new SkinSelectTemplate(557, 325);
    SkinSelectTemplate yellow2 = new SkinSelectTemplate(257, 425);
    SkinSelectTemplate magenta2 = new SkinSelectTemplate(357, 425);
    SkinSelectTemplate red2 = new SkinSelectTemplate(457, 425);
    SkinSelectTemplate orange2 = new SkinSelectTemplate(557, 425);

    //SPECIAL BUTTONS
    public ImageButtonTemplate discoButton = new ImageButtonTemplate(0, 0,32,32, "/com/pongProject/buttonImages/discoBallOff.png");
    public ImageButtonTemplate musicButton = new ImageButtonTemplate( 600,475,50,50, "/com/pongProject/buttonImages/musicOnButton.png");

    public JFrame menuFrame;

    private void run(int dif, int vs) {
        globalDifficulty = dif;
        playerOrComp = vs;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pong");

        GamePanel gamePanel = new GamePanel(db);
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        window.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    } // when the game itself is run

    private void removeMainButtons() {
        menuFrame.remove(pvcButton);
        menuFrame.remove(pvpButton);
        menuFrame.remove(skinsButton);
        menuFrame.remove(leaderBoardButton);
        menuFrame.remove(exitButton);
        menuFrame.remove(discoButton);
        menuFrame.remove(musicButton);

    } // removes the buttons on the main menu

    private void addMainButtons() {
        menuFrame.add(pvcButton);
        menuFrame.add(pvpButton);
        menuFrame.add(skinsButton);
        menuFrame.add(leaderBoardButton);
        menuFrame.add(exitButton);
        menuFrame.add(discoButton);
        menuFrame.add(musicButton);
    } // adds the main menu buttons to main menu

    public void changeMusicButton(boolean isOn){
        if (isOn) {
            musicButton.setButtonImage("/com/pongProject/buttonImages/musicOnButton.png");
        }
        else {
            musicButton.setButtonImage("/com/pongProject/buttonImages/musicOffButton.png");
        }
    }

    public void changeDiscoButton(boolean isOn){
        if (isOn) {
            discoButton.setButtonImage("/com/pongProject/buttonImages/discoBallOn.png");
        }
        else {
            discoButton.setButtonImage("/com/pongProject/buttonImages/discoBallOff.png");
        }
    }

    public Menu(String importedUsername, PongDB pongDB) {

        this.db = pongDB;

        username = importedUsername;
        // allows button to do something when pressed

        pvcButton.addActionListener(this);
        pvpButton.addActionListener(this);
        skinsButton.addActionListener(this);
        leaderBoardButton.addActionListener(this);
        exitButton.addActionListener(e -> menuFrame.dispose());
        musicButton.addActionListener(this);
        discoButton.addActionListener(this);

        pvcNormalButton.addActionListener(this);
        pvcHardButton.addActionListener(this);

        pvpNormalButton.addActionListener(this);
        pvpHardButton.addActionListener(this);

        backButton.addActionListener(this);
        backButton2.addActionListener(this);

        white.addActionListener(this);
        cyan.addActionListener(this);
        green.addActionListener(this);
        pink.addActionListener(this);
        yellow.addActionListener(this);
        magenta.addActionListener(this);
        red.addActionListener(this);
        orange.addActionListener(this);

        white2.addActionListener(this);
        cyan2.addActionListener(this);
        green2.addActionListener(this);
        pink2.addActionListener(this);
        yellow2.addActionListener(this);
        magenta2.addActionListener(this);
        red2.addActionListener(this);
        orange2.addActionListener(this);

        // setting colours for buttons in skins
        white.setBackground(Color.WHITE);
        cyan.setBackground(Color.CYAN);
        green.setBackground(Color.GREEN);
        pink.setBackground(Color.PINK);
        yellow.setBackground(Color.YELLOW);
        magenta.setBackground(Color.MAGENTA);
        red.setBackground(Color.RED);
        orange.setBackground(Color.ORANGE);

        white2.setBackground(Color.WHITE);
        cyan2.setBackground(Color.CYAN);
        green2.setBackground(Color.GREEN);
        pink2.setBackground(Color.PINK);
        yellow2.setBackground(Color.YELLOW);
        magenta2.setBackground(Color.MAGENTA);
        red2.setBackground(Color.RED);
        orange2.setBackground(Color.ORANGE);



        // creating the menu frame
        ImageIcon icon = new ImageIcon("pong_icon.png"); // this is the normal sized pong icon, normal sized as it is used as icon for JFrame
        menuFrame = new JFrame();
        menuFrame.setTitle("Pong");
        menuFrame.setSize(864, 672);
        menuFrame.setLayout(null);
        menuFrame.setLocationRelativeTo(null); // makes frame appear in middle of screen
        menuFrame.getContentPane().setBackground(Color.black);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png

        // adding the buttons to the menu frame
        addMainButtons();

        menuFrame.setVisible(true);
    } // creates the menu & adds the main menu buttons, adds addActionListener (allows buttons to do something when pressed)

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // main menu
        //
        if (e.getSource() == pvcButton) {
            removeMainButtons();
            menuFrame.add(pvcNormalButton);
            menuFrame.add(pvcHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if (e.getSource() == pvpButton) {
            removeMainButtons();
            menuFrame.add(pvpNormalButton);
            menuFrame.add(pvpHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if (e.getSource() == skinsButton) {
            removeMainButtons();
            menuFrame.add(player1);
            menuFrame.add(player2);

            menuFrame.add(white);
            menuFrame.add(cyan);
            menuFrame.add(green);
            menuFrame.add(pink);
            menuFrame.add(yellow);
            menuFrame.add(magenta);
            menuFrame.add(red);
            menuFrame.add(orange);

            menuFrame.add(white2);
            menuFrame.add(cyan2);
            menuFrame.add(green2);
            menuFrame.add(pink2);
            menuFrame.add(yellow2);
            menuFrame.add(magenta2);
            menuFrame.add(red2);
            menuFrame.add(orange2);

            menuFrame.add(backButton2);

            menuFrame.repaint();
        }
        if (e.getSource() == leaderBoardButton) {

            removeMainButtons();
            menuFrame.add(backButton2);

            JTextArea scoresLabel = new JTextArea();
            scoresLabel.setEditable(false);
            scoresLabel.setBounds(0, 0, scoresPanel.getWidth(), scoresPanel.getHeight());

            String topScoresText = db.getTopScores(10);
            scoresLabel.setText("Top 10 Scores:\n"+topScoresText);
            scoresLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
            scoresPanel.add(scoresLabel);

            JTextArea ralliesLabel = new JTextArea();
            ralliesLabel.setEditable(false);
            ralliesLabel.setBounds(0, 0, ralliesPanel.getWidth(), ralliesPanel.getHeight());

            String topRalliesText = db.getTopRallies(10);
            ralliesLabel.setText("Top 10 Rallies:\n"+topRalliesText);
            ralliesLabel.setFont(new Font("Comic Sans", Font.BOLD, 30));
            ralliesPanel.add(ralliesLabel);

            menuFrame.add(scoresPanel);
            menuFrame.add(ralliesPanel);

            menuFrame.repaint();
        }
        //
        // pvp OR pvc
        //
        if (e.getSource() == pvcNormalButton) {
            menuFrame.dispose();
            run(1, 2);
        }
        if (e.getSource() == pvcHardButton) {
            menuFrame.dispose();
            run(2, 2);
        }
        if (e.getSource() == pvpNormalButton) {
            menuFrame.dispose();
            run(1, 1);
        }
        if (e.getSource() == pvpHardButton) {
            menuFrame.dispose();
            run(2, 1);
        }
        if (e.getSource() == backButton || e.getSource() == backButton2) {
            menuFrame.remove(pvpNormalButton);
            menuFrame.remove(pvpHardButton);

            menuFrame.remove(pvcNormalButton);
            menuFrame.remove(pvcHardButton);

            menuFrame.remove(player1);
            menuFrame.remove(player2);

            menuFrame.remove(white);
            menuFrame.remove(cyan);
            menuFrame.remove(green);
            menuFrame.remove(pink);
            menuFrame.remove(yellow);
            menuFrame.remove(magenta);
            menuFrame.remove(red);
            menuFrame.remove(orange);

            menuFrame.remove(white2);
            menuFrame.remove(cyan2);
            menuFrame.remove(green2);
            menuFrame.remove(pink2);
            menuFrame.remove(yellow2);
            menuFrame.remove(magenta2);
            menuFrame.remove(red2);
            menuFrame.remove(orange2);

            menuFrame.remove(backButton);
            menuFrame.remove(backButton2);

            menuFrame.remove(scoresPanel);
            menuFrame.remove(ralliesPanel);

            addMainButtons();

            menuFrame.repaint();
        }
        //
        // skins
        //
        // when player1 colour pressed
        if (e.getSource() == white) {
            player1Colour = "/com/pongProject/player/paddleWhite.png";
        }
        if (e.getSource() == cyan) {
            player1Colour = "/com/pongProject/player/paddleCyan.png";
        }
        if (e.getSource() == green) {
            player1Colour = "/com/pongProject/player/paddleGreen.png";
        }
        if (e.getSource() == pink) {
            player1Colour = "/com/pongProject/player/paddlePink.png";
        }
        if (e.getSource() == yellow) {
            player1Colour = "/com/pongProject/player/paddleYellow.png";
        }
        if (e.getSource() == magenta) {
            player1Colour = "/com/pongProject/player/paddleMagenta.png";
        }
        if (e.getSource() == red) {
            player1Colour = "/com/pongProject/player/paddleRed.png";
        }
        if (e.getSource() == orange) {
            player1Colour = "/com/pongProject/player/paddleOrange.png";
        }
        // when player2 colour pressed
        if (e.getSource() == white2) {
            player2Colour = "/com/pongProject/player/paddleWhite.png";
        }
        if (e.getSource() == cyan2) {
            player2Colour = "/com/pongProject/player/paddleCyan.png";
        }
        if (e.getSource() == green2) {
            player2Colour = "/com/pongProject/player/paddleGreen.png";
        }
        if (e.getSource() == pink2) {
            player2Colour = "/com/pongProject/player/paddlePink.png";
        }
        if (e.getSource() == yellow2) {
            player2Colour = "/com/pongProject/player/paddleYellow.png";
        }
        if (e.getSource() == magenta2) {
            player2Colour = "/com/pongProject/player/paddleMagenta.png";
        }
        if (e.getSource() == red2) {
            player2Colour = "/com/pongProject/player/paddleRed.png";
        }
        if (e.getSource() == orange2) {
            player2Colour = "/com/pongProject/player/paddleOrange.png";
        }
        //
        // special buttons
        //
        if (e.getSource() == discoButton){
            discoMode = !discoMode;
            changeDiscoButton(discoMode);
        }
        if (e.getSource() == musicButton) {
            musicToggle = !musicToggle;
            changeMusicButton(musicToggle);

        }
    } // if specific button is pressed, executes contents of if statement
}