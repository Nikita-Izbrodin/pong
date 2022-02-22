package entities;

import gameFolder.Menu;
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
    Menu mU;

    public Player2(GamePanel gp, P2KeyHandler keyH){
        this.gp=gp;
        this.keyH = keyH;

        solidArea = new Rectangle(18, 0, 15,142);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = gp.tileSize*(GamePanel.maxScreenCol-2);
        y = gp.screenHeight/2 - solidArea.height/2;
        speedY=8;
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
