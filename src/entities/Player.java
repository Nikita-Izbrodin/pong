package entities;

import gameFolder.Menu;
import logic.KeyHandler;
import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public int score;
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
        y = 100;
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

        g2.drawImage(image, x, y, gp.tileSize, 3* gp.tileSize, null);

    }
}
