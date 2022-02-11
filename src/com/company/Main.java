package com.company;

import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame(); // creates a frame
        frame.setTitle("pong"); // title of frame
        frame.setResizable(false);
        frame.setSize(420,420); // sets x and y dimension of frame
        frame.setVisible(true); // make frame visible

        ImageIcon icon = new ImageIcon("com/company/pong_icon.png");
        frame.setIconImage(icon.getImage());
    }
}
