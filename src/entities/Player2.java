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
    public boolean reverseControls = false;
    int reverseCounter = 0;
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
        reverseCounter++;
        if (reverseCounter > 500) {
            reverseCounter = 0;
            reverseControls = false;
        }

    }

    public void draw(Graphics g2){
        BufferedImage image = null;

        image = entityImage;

        g2.drawImage(image, x, y, gp.tileSize, 3*gp.tileSize, null);

    }
}
