package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speedX ,speedY;


    public BufferedImage entityImage;
    public String direction;
    public Rectangle solidArea;
//    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
