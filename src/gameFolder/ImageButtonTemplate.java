package gameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class ImageButtonTemplate extends JButton {

    public ImageButtonTemplate(int x, int  y, String imageLoc) {
        this.setBounds(x, y, 32, 32);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box

        try {
            Image img = ImageIO.read(getClass().getResource(imageLoc));
            this.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}