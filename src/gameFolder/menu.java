package gameFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;

import logic.GamePanel;

public class menu extends JFrame implements ActionListener {
    // buttons are initialised outside of constructor, so they are global and actionListener can work as intended

    // Player VS Computer
    buttonTemplate pvcButton = new buttonTemplate(282, 75, 300, 50, "Player VS Computer");
    // Player VS Player
    buttonTemplate pvpButton = new buttonTemplate(282, 175, 300, 50, "Player VS Player");
    // Skins
    buttonTemplate skinsButton = new buttonTemplate(282, 275, 300, 50, "Skins");
    // Leaderboard
    buttonTemplate leaderBoardButton = new buttonTemplate(282, 375, 300, 50, "Leaderboard");
    // Exit
    buttonTemplate exitButton = new buttonTemplate(282, 475, 300, 50, "Exit");

    // PVC Normal
    buttonTemplate pvcNormalButton = new buttonTemplate(282, 75, 300, 50, "P VS C - Normal");
    // PVC Hard
    buttonTemplate pvcHardButton = new buttonTemplate(282, 175, 300, 50, "P VS C - Hard");

    // PVP Normal
    buttonTemplate pvpNormalButton = new buttonTemplate(282, 75, 300, 50, "P VS P - Normal");
    // PVP Hard
    buttonTemplate pvpHardButton = new buttonTemplate(282, 175, 300, 50, "P VS P - Hard");

    // PVC or PVP Back (button)
    buttonTemplate backButton = new buttonTemplate(282, 275, 300, 50, "Back");

    JFrame menuFrame;

    private void run(){
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

        menuFrame.add(pvcNormalButton);
        menuFrame.add(pvcHardButton);
        menuFrame.add(backButton);

        menuFrame.repaint();
    }

    menu() {
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
        if(e.getSource()==pvcButton){ // when player vs computer pressed
            removeOldButtons();
        }
        if(e.getSource()==pvpButton) { // when player vs player pressed
            removeOldButtons();
        }
        if(e.getSource()==skinsButton){ // when skins pressed
            // put code here
        }
        if(e.getSource()==leaderBoardButton){ // when leaderboard pressed
            // put code here
        }
        if(e.getSource()==pvcNormalButton){ // when pvc normal pressed
            // put code here
        }
        if(e.getSource()==pvcHardButton){ // when pvc hard pressed
            // put code here
        }
        if(e.getSource()==pvpNormalButton){ // when pvp normal pressed
            menuFrame.dispose();
            run();
        }
        if(e.getSource()==pvpHardButton){ // when pvp hard pressed
            // put code here
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