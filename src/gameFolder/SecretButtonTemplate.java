package gameFolder;

import javax.swing.*;

public class SecretButtonTemplate extends JButton {

    public SecretButtonTemplate(int x, int  y) {
        ImageIcon disco = new ImageIcon("resources/buttonImages/discoBall.png");
        this.setIcon(disco);
        this.setBounds(x, y, 60, 60);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
    }
}