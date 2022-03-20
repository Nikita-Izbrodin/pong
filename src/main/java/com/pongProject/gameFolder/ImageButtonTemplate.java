package com.pongProject.gameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class ImageButtonTemplate extends JButton {

    public ImageButtonTemplate(int x, int  y,int width,int height, String imageLoc) {
        this.setBounds(x, y, width, height);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
        setButtonImage(imageLoc);
    }

    public void setButtonImage(String imageLoc) {
        try {
            Image img = ImageIO.read(getClass().getResource(imageLoc));
            this.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
