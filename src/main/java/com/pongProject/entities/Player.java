package com.pongProject.entities;

import com.pongProject.gameFolder.Menu;
import com.pongProject.logic.KeyHandler;
import com.pongProject.logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public int score;
    public boolean reverseControls = false;
    int reverseCounter = 0;
    Menu mU;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp=gp;
        this.keyH = keyH;

        solidArea = new Rectangle(18, 0, 15,142);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = gp.tileSize;
        y = gp.screenHeight/2 - solidArea.height/2;
        speedY=8;
        speedX = 0;
        direction = "down";
        score = 0;
    }
    public void getPlayerImage(){
        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(mU.player1Colour)));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed){
            if (keyH.upPressed) {
                if(reverseControls)
                    direction = "down";
                else
                    direction = "up";
            } else if (keyH.downPressed) {
                if(reverseControls)
                    direction = "up";
                else
                    direction = "down";
            }

            collisionOn = false;
            gp.cChecker.checkTile(this);

            if(!collisionOn){
                switch(direction){
                    case "up":y -= speedY;break;
                    case"down":y += speedY;break;
                }
            }

        }
        if (reverseControls)
            reverseCounter++;
        if (reverseCounter > 180) {
            reverseCounter = 0;
            reverseControls = false;
        }

    }

    public void draw(Graphics g2){
        BufferedImage image = null;

        image = entityImage;

        g2.drawImage(image, x, y, gp.tileSize, 3* gp.tileSize, null);

    }
}