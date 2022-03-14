package entities;

import gameFolder.Menu;
import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.*;

public class Ball extends Entity{
    GamePanel gp;

    public double acceleration;
    public int variation;
    public int rallyCount;

    public int lastCollision = 0;
    public int maxRally = 0;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Ball(GamePanel gp ){
        this.gp=gp;

        solidArea = new Rectangle(15,15,18,18);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getBallImage();
    }

    public void setDefaultValues(){
        Random rand = new Random();
        x = gp.screenWidth/2 - gp.tileSize/2;
        y = gp.screenHeight/2 - gp.tileSize/2;
        speedY= 0;
        rallyCount = 0;
        if(rand.nextInt(2)==1){
            speedX=  -7;
        }else{
            speedX= 7;
        }
        if (Menu.globalDifficulty == 1) {
            acceleration = 0.3;
            variation=4;
        }
        if (Menu.globalDifficulty == 2) {
            acceleration = 0.6;
            variation=7;
        }
    }

    public void getBallImage(){

        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/balls/ball01.png")));
            entityImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/balls/ball02.png")));
            entityImage3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/balls/ball03.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //CHECK PADDLE COLLISION
        int collisionValue = gp.cChecker.checkObject(this);

        y += speedY;
        x += speedX;

        if (collisionValue == 0 || collisionValue == 1 || collisionValue == 2 || collisionValue == 3 || collisionValue == 4 ){
            pickUpObject(collisionValue);
        }

        if (collisionValue == 901 || collisionValue == 902){
            paddleCollision(collisionValue);
        }

        if (rallyCount>maxRally){
            maxRally=rallyCount;
        }

        if(y + solidArea.y + solidArea.height >= gp.screenHeight - 48){
            y = gp.screenHeight -(solidArea.height +solidArea.y + GamePanel.tileSize);
            speedY = -speedY;

        }
        if( y + solidArea.y <= 48 ){
            y = 48 -solidArea.y;
            speedY = -speedY;
        }

        checkPoint();

        checkWin();

        if (speedX>10 ||speedX<-10){
            spriteCounter ++;
        }
        spriteCounter += 2;
        if (spriteCounter > 10) {
            if (speedX < 0) {
                if (spriteNum == 1) {
                    spriteNum = 3;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                }
            } else if (speedX > 0) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
            }
            spriteCounter = 0;
        }
    }

    public void checkPoint(){
        if (x + solidArea.x +solidArea.width >= gp.screenWidth - 48){
            gp.playSE(3);
            setDefaultValues();
            gp.player.score +=1;
            gp.compAI.setDefaultValues();

        }
        if (x + solidArea.x <= 48){
            gp.playSE(3);
            setDefaultValues();
            gp.player2.score +=1;
            gp.compAI.setDefaultValues();

        }
    }

    public void checkWin(){
        if (gp.player.score == 10){
            gp.winner = "Player 1";
            gp.gameFinished = true;
        }
        else if(gp.player2.score == 10){
            if (Menu.playerOrComp == 1){
                gp.winner = "Player 2";
            }
            else{gp.winner = "Computer";}
            gp.gameFinished = true;
        }
    }

    public void paddleCollision(int collisionValue){
        Random rand = new Random();
        if (collisionValue == 901){
            x = gp.tileSize + gp.player.solidArea.x + gp.player.solidArea.width;

            speedX = -speedX;
            speedX += acceleration;
            rallyCount += 1;
            lastCollision = 1;

            if(y+((solidArea.height/4)*3) > gp.player.y+(gp.player.solidArea.height/6)*5) {
                speedY = (rand.nextInt(variation) + 5);
            }else if(y+(solidArea.height/2) > gp.player.y+(gp.player.solidArea.height/2)){
                speedY= (rand.nextInt(variation)+2);
            }else if(y+(solidArea.height/6) > gp.player.y+(gp.player.solidArea.height/6)){
                speedY= -(rand.nextInt(variation)+2);
            }else{
                speedY= -(rand.nextInt(variation)+5);
            }

        }else if (collisionValue == 902){
            x = gp.tileSize*(GamePanel.maxScreenCol-2) - gp.ball.solidArea.width;

            speedX += acceleration;
            speedX = -speedX;
            rallyCount += 1;
            lastCollision = 2;

            if(y+((solidArea.height/4)*3) > gp.player2.y+(gp.player2.solidArea.height/6)*5 || y+((solidArea.height/4)*3) > gp.compAI.y+(gp.compAI.solidArea.height/6)*5) {
                speedY = (rand.nextInt(variation) + 5);
            }else if(y+(solidArea.height/2) > gp.player2.y+(gp.player2.solidArea.height/2) || y+(solidArea.height/2) > gp.compAI.y+(gp.compAI.solidArea.height/4)*3){
                speedY= (rand.nextInt(variation)+2);
            }else if(y+(solidArea.height/4) > gp.player2.y+(gp.player2.solidArea.height/6) || y+(solidArea.height/4) > gp.compAI.y+(gp.compAI.solidArea.height/6)){
                speedY= -(rand.nextInt(variation)+2);
            }else{
                speedY= -(rand.nextInt(variation)+5);
            }

            System.out.println(speedX);
        }
    }

    public void pickUpObject(int i){

        if(i!= 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "DirectionOrb":
                    if (speedY > 0) {
                        speedY += 1;
                    } else {
                        speedY -= 1;
                    }
                    speedY = -speedY;
                    break;
                case "DirectionOrb2":
                    if (speedX > 0) {
                        speedX += 1;
                    } else {
                        speedX -= 1;
                    }
                    speedX = -speedX;
                    break;
                case "ReverseOrb":
                    if (speedX > 0) {
                        speedX += 2;
                    } else {
                        speedX -= 2;
                    }
                    if (speedY > 0) {
                        speedY += 2;
                    } else {
                        speedY -= 2;
                    }
                    speedY = -speedY;
                    speedX = -speedX;
                    break;
                case "SpeedOrb":
                    if (speedX > 0) {
                        speedX += 3;
                    } else {
                        speedX -= 3;
                    }
                    break;
                case "ControlOrb":

                    break;
            }
            gp.obj[i] = null;
        }
    }

    public void draw(Graphics g2){
        BufferedImage image = null;

        if (spriteNum == 1) {
            image = entityImage;
        }
        if (spriteNum == 2) {
            image = entityImage2;
        }
        if (spriteNum == 3) {
            image = entityImage3;
        }


        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null );

    }
}