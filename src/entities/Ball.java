package entities;

import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.*;

public class Ball extends Entity{
    GamePanel gp;


    public Ball(GamePanel gp ){
        this.gp=gp;

        solidArea = new Rectangle(15,15,18,18);
        setDefaultValues();
        getBallImage();
    }

    public void setDefaultValues(){
        Random rand = new Random();
        x = gp.screenWidth/2 - gp.tileSize/2;
        y = gp.screenHeight/2 - gp.tileSize/2;
        if(rand.nextInt(2)==1){
            speedY= -(rand.nextInt(4)+2);
        }else{
            speedY= (rand.nextInt(4)+2);
        }
        if(rand.nextInt(2)==1){
            speedX=  -(rand.nextInt(6)+5);
        }else{
            speedX= (rand.nextInt(6)+5);
        }
    }

    public void getBallImage(){

        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/balls/ball01.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //CHECK PADDLE COLLISION
        int paddleCollision = gp.cChecker.checkObject(this);

        Random rand = new Random();

        y += speedY;
        x += speedX;

        if (paddleCollision == 1){
            speedX = -speedX;
            if(rand.nextInt(2)==1){
                speedY= -(rand.nextInt(3)+3);
            }else{
                speedY= (rand.nextInt(3)+3);
            }
        }else if (paddleCollision == 2){
            speedX = -speedX;
            if(rand.nextInt(2)==1){
                speedY= -(rand.nextInt(3)+2);
            }else{
                speedY= (rand.nextInt(3)+2);
            }
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

    }

    public void draw(Graphics g2){
        BufferedImage image = null;

        image = entityImage;

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null );

    }
}
