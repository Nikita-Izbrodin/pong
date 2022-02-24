package gameFolder;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class SecretButtonTemplate extends JButton {

    public SecretButtonTemplate(int x, int  y) {
        this.setBounds(x, y, 20, 20);
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
    }
}