package com.pongProject.gameFolder;

import com.pongProject.database.PongDB;

public class Main {

    public static void main(String[] args) {

        PongDB pongDB = new PongDB();

        UserAccess gameUserAccess = new UserAccess(pongDB);

        gameUserAccess.run();

        }
}