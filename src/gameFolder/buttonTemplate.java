package gameFolder;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;

public class buttonTemplate extends JButton {

    public buttonTemplate(int  y, String text) {
        this.setBounds(282, y, 300, 50);
        this.setText(text);
        this.setFont(new Font("Comic Sans", Font.BOLD, 20));
        this.setFocusable(false); // removes focus box around text
        this.setBorder(BorderFactory.createEtchedBorder()); // adds border around box
    }
}
