package gameFolder;

import logic.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial_40;

    public boolean messageOn = false;
    public String message = "";


    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);

    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);

        //TIME
        playTime += (double) 1 / 60;
        g2.drawString(gp.player.score + "  Time " + dFormat.format(playTime) + "  " +gp.player2.score , gp.tileSize * (gp.maxScreenCol/2 -3), 44);

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

        }
    }

}
