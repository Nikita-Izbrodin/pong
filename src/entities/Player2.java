package entities;

import logic.GamePanel;
import logic.P2KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player2 extends Entity{
    GamePanel gp;
    P2KeyHandler keyH;
    public int score;
    public Player2(GamePanel gp, P2KeyHandler keyH){
        this.gp=gp;
        this.keyH = keyH;

        solidArea = new Rectangle(18, 0, 15,142);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = gp.tileSize*16;
        y = 100;
        speedY=6;
        speedX = 0;
        direction = "down";
        score = 0;
    }
    public void getPlayerImage(){

        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/resources/player/paddle02.png")));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed || keyH.downPressed){
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
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
