package com.company;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;

public class menu extends JFrame implements ActionListener {

    JButton pvpButton;
    JFrame menuFrame;
    JFrame pvpFrame;

    menu() {

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

        // creating the menu frame

        pvpFrame = new JFrame();
        pvpFrame.setTitle("Pong");
        pvpFrame.setSize(864, 672);
        pvpFrame.setLayout(null);
        pvpFrame.setLocationRelativeTo(null); // makes frame appear in middle of screen
        pvpFrame.getContentPane().setBackground(Color.black);
        pvpFrame.setResizable(false);
        pvpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pvpFrame.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png

            // easier to make font "sans" rather than "new Font..." for each button
            Font sans = new Font("Comic Sans", Font.BOLD, 20);

            // creating the player vs computer button
            JButton pvcButton = new JButton();
            pvcButton.setBounds(282, 75, 300, 50);
            pvcButton.setBorder(BorderFactory.createEtchedBorder());
            pvcButton.addActionListener(e -> System.out.println("pvc"));
            pvcButton.setText("Player VS Computer");
            pvcButton.setFont(sans);
            pvcButton.setFocusable(false); // removes focus box around text

            // creating the pvp button
            pvpButton = new JButton();
            pvpButton.setBounds(282, 175, 300, 50);
            pvpButton.setBorder(BorderFactory.createEtchedBorder());
            pvpButton.addActionListener(this);
            //pvpButton.addActionListener(e -> System.out.println("pvp"));
            pvpButton.setText("Player VS Player");
            pvpButton.setFont(sans);
            pvpButton.setFocusable(false); // removes focus box around text

            // creating the skins button
            JButton skinsButton = new JButton();
            skinsButton.setBounds(282, 275, 300, 50);
            skinsButton.setBorder(BorderFactory.createEtchedBorder());
            skinsButton.addActionListener(e -> System.out.println("skins"));
            skinsButton.setText("Skins");
            skinsButton.setFont(sans);
            skinsButton.setFocusable(false); // removes focus box around text

            // creating the leaderboard button
            JButton leaderBoardButton = new JButton();
            leaderBoardButton.setBounds(282, 375, 300, 50);
            leaderBoardButton.setBorder(BorderFactory.createEtchedBorder());
            leaderBoardButton.addActionListener(e -> System.out.println("leaderboard"));
            leaderBoardButton.setText("Leaderboard");
            leaderBoardButton.setFont(sans);
            leaderBoardButton.setFocusable(false); // removes focus box around text

            // creating the exit button
            JButton exitButton = new JButton();
            exitButton.setBounds(282, 475, 300, 50);
            exitButton.setBorder(BorderFactory.createEtchedBorder());
            exitButton.addActionListener(e -> menuFrame.dispose());
            exitButton.setText("Exit");
            exitButton.setFont(sans);
            exitButton.setFocusable(false); // removes focus box around text

        // creating the pvpNormal button
        JButton pvpNormalButton = new JButton();
        pvpNormalButton.setBounds(282, 475, 300, 50);
        pvpNormalButton.setBorder(BorderFactory.createEtchedBorder());
        pvpNormalButton.addActionListener(e -> menuFrame.dispose());
        pvpNormalButton.setText("Normal");
        pvpNormalButton.setFont(sans);
        pvpNormalButton.setFocusable(false); // removes focus box around text

            // adding the buttons to the menu frame
            menuFrame.add(pvcButton);
            menuFrame.add(pvpButton);
            menuFrame.add(skinsButton);
            menuFrame.add(leaderBoardButton);
            menuFrame.add(exitButton);

        menuFrame.setVisible(true);

        pvpFrame.add(pvpNormalButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==pvpButton) {
            menuFrame.setVisible(false);
            pvpFrame.setVisible(true);
        }
    }
}
