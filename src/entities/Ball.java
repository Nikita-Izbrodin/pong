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
        if(rand.nextInt(2)==1){
            speedX=  -7;
        }else{
            speedX= 7;
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
        int paddleCollision = gp.cChecker.checkObject(this);

        if (Menu.globalDifficulty == 1) {
            acceleration = 0.3;
            variation=4;

        }
        if (Menu.globalDifficulty == 2) {
            acceleration = 0.6;
            variation=7;
        }

        Random rand = new Random();

        y += speedY;
        x += speedX;

        if (paddleCollision == 1){
            speedX = -speedX;
            speedX += acceleration;
            if(y+(gp.ball.solidArea.height/2) > gp.player.y+(gp.player.solidArea.height/2)){         ////////make it bounce up if it hits the top half doesnt work
                speedY= (rand.nextInt(variation)+3);
            }else{
                speedY= -(rand.nextInt(variation)+3);
            }
            System.out.println(speedX);
        }else if (paddleCollision == 2){
            speedX += acceleration;
            speedX = -speedX;
            if(y+solidArea.height/2 > gp.player2.y+gp.player2.solidArea.height/2 || y+solidArea.height/2 > gp.compAI.y+gp.compAI.solidArea.height/2){
                speedY= (rand.nextInt(variation)+2);
            }else{
                speedY= -(rand.nextInt(variation)+2);
            }
            System.out.println(speedX);
        }

        if(y + solidArea.y + solidArea.height >= gp.screenHeight - 48 || y + solidArea.y <= 48 ){
            speedY = -speedY;
        }
        if (x + solidArea.x +solidArea.width >= gp.screenWidth - 48){
            gp.playSE(3);
            setDefaultValues();
            gp.player.score +=1;

        }
        if (x + solidArea.x <= 48){
            gp.playSE(3);
            setDefaultValues();
            gp.player2.score +=1;

        }

        spriteCounter++;
        if (spriteCounter > 8) {
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