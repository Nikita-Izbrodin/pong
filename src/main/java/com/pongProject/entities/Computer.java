package com.pongProject.entities;

import com.pongProject.gameFolder.Menu;
import com.pongProject.logic.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Computer extends Entity{
    GamePanel gp;
    public int score;
    Menu mU;

    public Computer(GamePanel gp){
        this.gp=gp;

        solidArea = new Rectangle(18, 0, 15,142);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = gp.tileSize*(gp.maxScreenCol-2);
        y = gp.screenHeight/2 - solidArea.height/2;
        if(Menu.globalDifficulty == 2){
            speedY = 8;

        }else{speedY = 6;}
        speedX = 0;
        direction = "down";
        score = 0;
    }
    public void getPlayerImage(){

        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(mU.player2Colour)));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(gp.ball.y > y+(solidArea.height/4*3) || gp.ball.y < y+(solidArea.height/4*3)){
        if(gp.ball.y < y+(solidArea.height/2)){
            direction = "up";
        } else if (gp.ball.y > y+ (solidArea.height/2)) {
            direction = "down";
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);

        if(collisionOn == false){
            switch(direction){
                case "up": y -= speedY;break;
                case"down": y += speedY;break;
            }
        }
    }
    }

    public void draw(Graphics g2){
        BufferedImage image = null;

        image = entityImage;

        g2.drawImage(image, x, y, gp.tileSize, 3*gp.tileSize, null);

    }
}

