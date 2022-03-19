package com.pongProject.gameFolder;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SkinSelectTemplate extends JButton {

    public SkinSelectTemplate(int x, int  y) {
        this.setBounds(x, y, 50, 50);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
    }
}