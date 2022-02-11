package com.company;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

    static void menu() {
        JFrame frame = new JFrame(); // creates a frame
        frame.setTitle("pong");
        frame.setResizable(false);
        frame.setSize(960,540);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        frame.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png
    }

    public static void main(String[] args) {
        menu();
    }
}
