package gameFolder;

import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_30;

    public boolean messageOn = true;
    public String message = "";


    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    DecimalFormat dFormat1 = new DecimalFormat("#0.0");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_30 = new Font("Arial", Font.PLAIN, 30);

    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_30);
        g2.setColor(Color.BLACK);

        //TIME
        playTime += (double) 1 / 60;
//        g2.drawString(gp.player.score + "  Time " + dFormat.format(playTime) + "  " +gp.player2.score , gp.tileSize * (gp.maxScreenCol/2 -3), 44);

        if (messageOn) {
            g2.drawString(gp.player.score + "  Time " + dFormat.format(playTime) + "  " +gp.player2.score , gp.tileSize * (gp.maxScreenCol/2 -2), 44);
//            g2.setFont(g2.getFont().deriveFont(30F));
//            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            g2.drawString("Rally count: " + gp.ball.rallyCount, gp.tileSize * (gp.maxScreenCol/2 -2) , gp.screenHeight -10);

            g2.drawString("Ball x speed: " + dFormat1.format(Math.sqrt(gp.ball.speedX * gp.ball.speedX )) , gp.tileSize * (gp.maxScreenCol -5),gp.screenHeight -10);

            g2.drawString("Max rally: " +gp.ball.maxRally, gp.tileSize, gp.screenHeight -10);

        }
    }

}
