package entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speedY;
    public double speedX;


    public BufferedImage entityImage, entityImage2, entityImage3;
    public String direction;
    public Rectangle solidArea,solidArea2;
    public int solidAreaDefaultX, solidAreaDefaultY, solidArea2DefaultX, solidArea2DefaultY;
    public boolean collisionOn = false;
}
