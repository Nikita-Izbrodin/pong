package com.pongProject.gameFolder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class OrbButtonTemplate extends JButton {

    public OrbButtonTemplate(int  y, String text, String imageLoc) {
        this.setBounds(282, y, 300, 50);
        this.setFont(new Font("Comic Sans", Font.PLAIN, 13));
        this.setText(text);
        this.setHorizontalAlignment(JButton.LEFT);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
        try {
            Image img = ImageIO.read(getClass().getResource(imageLoc));
            Image newimg = img.getScaledInstance(50, 50, 0);
            this.setIcon(new ImageIcon(newimg));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}