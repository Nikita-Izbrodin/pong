package entities;

import gameFolder.Menu;
import logic.GamePanel;

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
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = gp.tileSize*(GamePanel.maxScreenCol-2);
        y = 100;
        if(Menu.globalDifficulty == 2){
            speedY = 6;
        }else{speedY = 4;}
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

