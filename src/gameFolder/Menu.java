package gameFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Objects;

import logic.GamePanel;



public class Menu implements ActionListener {

    public static int globalDifficulty;

    public static int playerOrComp;

    // buttons are initialised outside of constructor, so they are global and actionListener can work as intended

    // Player VS Computer
    ButtonTemplate pvcButton = new ButtonTemplate(282, 75, 300, 50, "Player VS Computer");
    // Player VS Player
    ButtonTemplate pvpButton = new ButtonTemplate(282, 175, 300, 50, "Player VS Player");
    // Skins
    ButtonTemplate skinsButton = new ButtonTemplate(282, 275, 300, 50, "Skins");
    // Leaderboard
    ButtonTemplate leaderBoardButton = new ButtonTemplate(282, 375, 300, 50, "Leaderboard");
    // Exit
    ButtonTemplate exitButton = new ButtonTemplate(282, 475, 300, 50, "Exit");

    // PVC Normal
    ButtonTemplate pvcNormalButton = new ButtonTemplate(282, 75, 300, 50, "P VS C - Normal");
    // PVC Hard
    ButtonTemplate pvcHardButton = new ButtonTemplate(282, 175, 300, 50, "P VS C - Hard");

    // PVP Normal
    ButtonTemplate pvpNormalButton = new ButtonTemplate(282, 75, 300, 50, "P VS P - Normal");
    // PVP Hard
    ButtonTemplate pvpHardButton = new ButtonTemplate(282, 175, 300, 50, "P VS P - Hard");

    // PVC or PVP Back (button)
    ButtonTemplate backButton = new ButtonTemplate(282, 275, 300, 50, "Back");

    JFrame menuFrame;

    private void run(int dif, int vs){
        globalDifficulty = dif;
        playerOrComp = vs;
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Pong");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        window.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    private void removeOldButtons(){
        menuFrame.remove(pvcButton);
        menuFrame.remove(pvpButton);
        menuFrame.remove(skinsButton);
        menuFrame.remove(leaderBoardButton);
        menuFrame.remove(exitButton);

    }

    Menu() {
        // allows button to do something when pressed
        pvcButton.addActionListener(this);
        pvpButton.addActionListener(this);
        skinsButton.addActionListener(this);
        leaderBoardButton.addActionListener(this);
        exitButton.addActionListener(e -> menuFrame.dispose());

        pvcNormalButton.addActionListener(this);
        pvcHardButton.addActionListener(this);

        pvpNormalButton.addActionListener(this);
        pvpHardButton.addActionListener(this);

        backButton.addActionListener(this);

        // creating the menu frame
        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
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
        menuFrame.add(pvcButton);
        menuFrame.add(pvpButton);
        menuFrame.add(skinsButton);
        menuFrame.add(leaderBoardButton);
        menuFrame.add(exitButton);

        menuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pvcButton){ // when player vs computer is pressed
            removeOldButtons();
            menuFrame.add(pvcNormalButton);
            menuFrame.add(pvcHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if(e.getSource()==pvpButton) { // when player vs player is pressed
            removeOldButtons();
            menuFrame.add(pvpNormalButton);
            menuFrame.add(pvpHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if(e.getSource()==skinsButton){ // when skins is pressed
            // put code here
        }
        if(e.getSource()==leaderBoardButton){ // when leaderboard is pressed
            // put code here
        }
        if(e.getSource()==pvcNormalButton){ // when pvc normal is pressed
            menuFrame.dispose();
            run(1,2);
        }
        if(e.getSource()==pvcHardButton){ // when pvc hard is pressed
            menuFrame.dispose();
            run(2,2);
        }
        if(e.getSource()==pvpNormalButton){ // when pvp normal is pressed
            menuFrame.dispose();
            run(1,1);
        }
        if(e.getSource()==pvpHardButton){ // when pvp hard is pressed
            menuFrame.dispose();
            run(2,1);
        }
        if(e.getSource()==backButton) { // when back button is pressed
            menuFrame.remove(pvpNormalButton);
            menuFrame.remove(pvpHardButton);

            menuFrame.remove(pvcNormalButton);
            menuFrame.remove(pvcHardButton);

            menuFrame.remove(backButton);

            menuFrame.add(pvcButton);
            menuFrame.add(pvpButton);
            menuFrame.add(skinsButton);
            menuFrame.add(leaderBoardButton);
            menuFrame.add(exitButton);

            menuFrame.repaint();
        }
    }
}