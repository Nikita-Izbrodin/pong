package com.pongProject.gameFolder;

import com.pongProject.logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;

    Font arial_30, arial_80B;

    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    DecimalFormat dFormat1 = new DecimalFormat("#0.0");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }

    public void showMessage(String text){

        message= text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gp.gameFinished) {
            g2.setFont(arial_30);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text = gp.winner + " wins!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "The total time was : " + dFormat.format(playTime) + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 2;
            g2.drawString(text, x, y);

            text = "the highest rally was : " + gp.ball.maxRally + "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text = "NERD!!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 2;
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else if (gp.pauseState) {
            String text;
            int textLength;
            int x;
            int y;
            g2.setFont(arial_80B);
            g2.setColor(Color.BLUE);
            text = "PAUSED";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 2;
            g2.drawString(text, x, y);
        }else{

            String text;
            int textLength;

            g2.setFont(arial_30);
            g2.setColor(Color.BLACK);

            //TIME
            playTime += (double) 1 / 60;
            text = gp.player.score + "  Time " + dFormat.format(playTime) + "  " + gp.player2.score;
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            g2.drawString(text, gp.screenWidth / 2 - textLength / 2, 44);

            g2.drawString("Rally count: " + gp.ball.rallyCount, gp.tileSize * (gp.maxScreenCol / 2 - 2), gp.screenHeight - 10);

            g2.drawString("Ball x speed: " + dFormat1.format(Math.sqrt(gp.ball.speedX * gp.ball.speedX)), gp.tileSize * (gp.maxScreenCol - 5), gp.screenHeight - 10);

            //g2.drawString("Max rally: " + gp.ball.maxRally, gp.tileSize, gp.screenHeight - 10);

            if (messageOn) {
                g2.setFont(arial_30);
                g2.setColor(Color.WHITE);
                textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
                g2.drawString(message, gp.screenWidth / 2 - textLength / 2, gp.tileSize * 2);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }


        }
    }

}
