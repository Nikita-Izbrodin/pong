package com.company;

import javax.swing.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame(); // creates a frame
        frame.setTitle("pong"); // title of frame
        frame.setResizable(false);
        frame.setSize(960,540); // sets x and y dimension of frame
        frame.setVisible(true); // make frame visible

        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        frame.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png
    }
}
