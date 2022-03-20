package com.pongProject.gameFolder;

import com.pongProject.database.PongDB;
import com.pongProject.database.RegexValidator;

import javax.swing.*;

public class UserAccess {

    private PongDB db;

    public UserAccess(PongDB pongDB) {
        this.db = pongDB;
    } // asks user if they want to log in, sign up, or play as guest

    public void run() {
        ImageIcon icon = new ImageIcon("pong_icon_smaller.png"); // this is the smaller pong icon, smaller because it is displayed on JOptionpane
        String[] responses = {"Log In", "Sign Up", "Play as guest"};

        int loginWindow = JOptionPane.showOptionDialog(
                null,
                "Do you want to:",
                "User Login",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                icon,
                responses,
                0
        ); // "Log In" = 0, "Sign Up" = 1, "Play as guest" =2, X (the x on the window) = -1

        if (loginWindow == 0) { // if user chose "Log In"
            logIn();
        }
        if (loginWindow == 1) { // if user chose "Sign up"
            signUp();
        }
        if (loginWindow == 2) { // if user chose "Play as guest"
            JOptionPane.showMessageDialog(null, "You are playing as a guest,\nyour scores/rallies will be saved to the Guest account.");
            new Menu("Guest", db);
        }
    }

    private void signUp() {

        ImageIcon pfpIcon = new ImageIcon("pfp.jpg");
        boolean check = true;

        String username = ""; // initialised here so that username can be used in password checker loop

        while (check) {
                    username = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter username:",
                    "Sign Up",
                    JOptionPane.INFORMATION_MESSAGE,
                    pfpIcon,
                    null,
                    ""
            );
            if (RegexValidator.isValidUsername(username) == true){
                check = false;
            }
            else {
                JOptionPane.showMessageDialog(null, "Username cannot have blank spaces.\nThis includes no input.", "Sign Up", JOptionPane.WARNING_MESSAGE);
            }
        }

        check = true;
        while (check) {
            String password = (String) JOptionPane.showInputDialog(
                    null,
                    "Enter password:",
                    "Sign Up",
                    JOptionPane.INFORMATION_MESSAGE,
                    pfpIcon,
                    null,
                    ""
            );

            String password2 = (String) JOptionPane.showInputDialog(
                    null,
                    "Re-enter password:",
                    "Sign up",
                    JOptionPane.INFORMATION_MESSAGE,
                    pfpIcon,
                    null,
                    ""
            );

            if (password.equals(password2)) { //check passwords are the same
                if (db.createUser(username,password)) {
                    JOptionPane.showMessageDialog(null, "Account created successfully!\nUsername: " + username, "Sign Up", JOptionPane.PLAIN_MESSAGE);
                    check = false;
                    new Menu(username, db);
                } else {
                    JOptionPane.showMessageDialog(null, "Account not created.\nUsername may already be taken.", "Sign Up", JOptionPane.WARNING_MESSAGE);
                    check = false;
                    run();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Passwords are not the same", "Sign Up", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void logIn() {
        ImageIcon pfpIcon = new ImageIcon("pfp.jpg");

        String username = (String) JOptionPane.showInputDialog(
            null,
            "Enter username:",
            "Log In",
            JOptionPane.INFORMATION_MESSAGE,
            pfpIcon,
            null,
            ""
        );

        String password = (String) JOptionPane.showInputDialog(
                null,
                "Enter password:",
                "Log In",
                JOptionPane.INFORMATION_MESSAGE,
                pfpIcon,
                null,
                ""
        );

        if (db.loginUser(username, password)) {
            new Menu(username, db);
        }
        else {
            JOptionPane.showMessageDialog(
                    null,
                    "User not found.\nPassword or Username may have been entered incorrectly.",
                    "Log In",
                    JOptionPane.WARNING_MESSAGE
            );
            run();
        }
    }
}