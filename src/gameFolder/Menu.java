package gameFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;

import logic.GamePanel;

public class Menu implements ActionListener {

    public static int globalDifficulty;

    public static int playerOrComp;

    public static String player1Colour = "/resources/player/paddleWhite.png";
    public static String player2Colour = "/resources/player/paddleWhite.png";

    // buttons are initialised outside of constructor, so they are global and actionListener can work as intended

    // Player VS Computer
    ButtonTemplate pvcButton = new ButtonTemplate(75, "Player VS Computer");
    // Player VS Player
    ButtonTemplate pvpButton = new ButtonTemplate(175, "Player VS Player");
    // Skins
    ButtonTemplate skinsButton = new ButtonTemplate(275, "Skins");
    // Leaderboard
    ButtonTemplate leaderBoardButton = new ButtonTemplate(375, "Leaderboard");
    // Exit
    ButtonTemplate exitButton = new ButtonTemplate(475, "Exit");

    // PVC Normal
    ButtonTemplate pvcNormalButton = new ButtonTemplate(75, "P VS C - Normal");
    // PVC Hard
    ButtonTemplate pvcHardButton = new ButtonTemplate(175, "P VS C - Hard");

    // PVP Normal
    ButtonTemplate pvpNormalButton = new ButtonTemplate(75, "P VS P - Normal");
    // PVP Hard
    ButtonTemplate pvpHardButton = new ButtonTemplate(175, "P VS P - Hard");

    // PVC or PVP Back (button)
    ButtonTemplate backButton = new ButtonTemplate(275, "Back");
    ButtonTemplate backButton2 = new ButtonTemplate(525, "Back");

    // initialising skin select buttons
    ButtonTemplate player1 = new ButtonTemplate(10, "Player 1:");
    ButtonTemplate player2 = new ButtonTemplate(250, "Player 2:");

    skinSelectTemplate white = new skinSelectTemplate(257, 75);
    skinSelectTemplate cyan = new skinSelectTemplate(357, 75);
    skinSelectTemplate green = new skinSelectTemplate(457, 75);
    skinSelectTemplate pink = new skinSelectTemplate(557, 75);
    skinSelectTemplate yellow = new skinSelectTemplate(257, 175);
    skinSelectTemplate magenta = new skinSelectTemplate(357, 175);
    skinSelectTemplate red = new skinSelectTemplate(457, 175);
    skinSelectTemplate orange = new skinSelectTemplate(557, 175);

    skinSelectTemplate white2 = new skinSelectTemplate(257, 325);
    skinSelectTemplate cyan2 = new skinSelectTemplate(357, 325);
    skinSelectTemplate green2 = new skinSelectTemplate(457, 325);
    skinSelectTemplate pink2 = new skinSelectTemplate(557, 325);
    skinSelectTemplate yellow2 = new skinSelectTemplate(257, 425);
    skinSelectTemplate magenta2 = new skinSelectTemplate(357, 425);
    skinSelectTemplate red2 = new skinSelectTemplate(457, 425);
    skinSelectTemplate orange2 = new skinSelectTemplate(557, 425);

    JFrame menuFrame;

    private void run(int dif, int vs) {
        globalDifficulty = dif;
        playerOrComp = vs;
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Pong");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        window.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    private void removeMainButtons() {
        menuFrame.remove(pvcButton);
        menuFrame.remove(pvpButton);
        menuFrame.remove(skinsButton);
        menuFrame.remove(leaderBoardButton);
        menuFrame.remove(exitButton);

    }

    private void addMainButtons() {
        menuFrame.add(pvcButton);
        menuFrame.add(pvpButton);
        menuFrame.add(skinsButton);
        menuFrame.add(leaderBoardButton);
        menuFrame.add(exitButton);
    }

    public Menu() {
        // allows button to do something when pressed
        pvcButton.addActionListener(this);
        pvpButton.addActionListener(this);
        skinsButton.addActionListener(this);
        leaderBoardButton.addActionListener(this);
        exitButton.addActionListener(e -> menuFrame.dispose());

        pvcNormalButton.addActionListener(this);
        pvcHardButton.addActionListener(this);

        pvpNormalButton.addActionListener(this);
        pvpHardButton.addActionListener(this);

        backButton.addActionListener(this);
        backButton2.addActionListener(this);

        white.addActionListener(this);
        cyan.addActionListener(this);
        green.addActionListener(this);
        pink.addActionListener(this);
        yellow.addActionListener(this);
        magenta.addActionListener(this);
        red.addActionListener(this);
        orange.addActionListener(this);

        white2.addActionListener(this);
        cyan2.addActionListener(this);
        green2.addActionListener(this);
        pink2.addActionListener(this);
        yellow2.addActionListener(this);
        magenta2.addActionListener(this);
        red2.addActionListener(this);
        orange2.addActionListener(this);

        // setting colours for buttons in skins
        white.setBackground(Color.WHITE);
        cyan.setBackground(Color.CYAN);
        green.setBackground(Color.GREEN);
        pink.setBackground(Color.PINK);
        yellow.setBackground(Color.YELLOW);
        magenta.setBackground(Color.MAGENTA);
        red.setBackground(Color.RED);
        orange.setBackground(Color.ORANGE);

        white2.setBackground(Color.WHITE);
        cyan2.setBackground(Color.CYAN);
        green2.setBackground(Color.GREEN);
        pink2.setBackground(Color.PINK);
        yellow2.setBackground(Color.YELLOW);
        magenta2.setBackground(Color.MAGENTA);
        red2.setBackground(Color.RED);
        orange2.setBackground(Color.ORANGE);



        // creating the menu frame
        ImageIcon icon = new ImageIcon("pong_icon.png"); // create an ImageIcon
        menuFrame = new JFrame();
        menuFrame.setTitle("Pong");
        menuFrame.setSize(864, 672);
        menuFrame.setLayout(null);
        menuFrame.setLocationRelativeTo(null); // makes frame appear in middle of screen
        menuFrame.getContentPane().setBackground(Color.black);
        menuFrame.setResizable(false);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setIconImage(icon.getImage()); // change icon of frame to pong_icon.png

        // adding the buttons to the menu frame
        addMainButtons();

        menuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        // main menu
        //
        if (e.getSource() == pvcButton) { // when player vs computer pressed
            removeMainButtons();
            menuFrame.add(pvcNormalButton);
            menuFrame.add(pvcHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if (e.getSource() == pvpButton) { // when player vs player pressed
            removeMainButtons();
            menuFrame.add(pvpNormalButton);
            menuFrame.add(pvpHardButton);
            menuFrame.add(backButton);

            menuFrame.repaint();
        }
        if (e.getSource() == skinsButton) { // when skins pressed
            removeMainButtons();
            menuFrame.add(player1);
            menuFrame.add(player2);

            menuFrame.add(white);
            menuFrame.add(cyan);
            menuFrame.add(green);
            menuFrame.add(pink);
            menuFrame.add(yellow);
            menuFrame.add(magenta);
            menuFrame.add(red);
            menuFrame.add(orange);

            menuFrame.add(white2);
            menuFrame.add(cyan2);
            menuFrame.add(green2);
            menuFrame.add(pink2);
            menuFrame.add(yellow2);
            menuFrame.add(magenta2);
            menuFrame.add(red2);
            menuFrame.add(orange2);

            menuFrame.add(backButton2);

            menuFrame.repaint();
        }
        if (e.getSource() == leaderBoardButton) { // when leaderboard pressed
            // put code here
        }
        //
        // pvp OR pvc
        //
        if (e.getSource() == pvcNormalButton) { // when pvc normal is pressed
            menuFrame.dispose();
            run(1, 2);
        }
        if (e.getSource() == pvcHardButton) { // when pvc hard is pressed
            menuFrame.dispose();
            run(2, 2);
        }
        if (e.getSource() == pvpNormalButton) { // when pvp normal is pressed
            menuFrame.dispose();
            run(1, 1);
        }
        if (e.getSource() == pvpHardButton) { // when pvp hard is pressed
            menuFrame.dispose();
            run(2, 1);
        }
        if (e.getSource() == backButton || e.getSource() == backButton2) { // when either back button is pressed
            menuFrame.remove(pvpNormalButton);
            menuFrame.remove(pvpHardButton);

            menuFrame.remove(pvcNormalButton);
            menuFrame.remove(pvcHardButton);

            menuFrame.remove(player1);
            menuFrame.remove(player2);

            menuFrame.remove(white);
            menuFrame.remove(cyan);
            menuFrame.remove(green);
            menuFrame.remove(pink);
            menuFrame.remove(yellow);
            menuFrame.remove(magenta);
            menuFrame.remove(red);
            menuFrame.remove(orange);

            menuFrame.remove(white2);
            menuFrame.remove(cyan2);
            menuFrame.remove(green2);
            menuFrame.remove(pink2);
            menuFrame.remove(yellow2);
            menuFrame.remove(magenta2);
            menuFrame.remove(red2);
            menuFrame.remove(orange2);

            menuFrame.remove(backButton);
            menuFrame.remove(backButton2);

            addMainButtons();

            menuFrame.repaint();
            System.out.println("the back button was pressed");
        }
        //
        // skins
        //
        if (e.getSource() == white) { // when white pressed
            player1Colour = "/resources/player/paddleWhite.png";
        }
        if (e.getSource() == cyan) { // when cyan pressed
            player1Colour = "/resources/player/paddleCyan.png";
        }
        if (e.getSource() == green) { // when green pressed
            player1Colour = "/resources/player/paddleGreen.png";
        }
        if (e.getSource() == pink) { // when pink pressed
            player1Colour = "/resources/player/paddlePink.png";
        }
        if (e.getSource() == yellow) { // when yellow pressed
            player1Colour = "/resources/player/paddleYellow.png";
        }
        if (e.getSource() == magenta) { // when magenta pressed
            player1Colour = "/resources/player/paddleMagenta.png";
        }
        if (e.getSource() == red) { // when red pressed
            player1Colour = "/resources/player/paddleRed.png";
        }
        if (e.getSource() == orange) { // when orange pressed
            player1Colour = "/resources/player/paddleOrange.png";
        }


        if (e.getSource() == white2) { // when white pressed
            player2Colour = "/resources/player/paddleWhite.png";
        }
        if (e.getSource() == cyan2) { // when cyan pressed
            player2Colour = "/resources/player/paddleCyan.png";
        }
        if (e.getSource() == green2) { // when green pressed
            player2Colour = "/resources/player/paddleGreen.png";
        }
        if (e.getSource() == pink2) { // when pink pressed
            player2Colour = "/resources/player/paddlePink.png";
        }
        if (e.getSource() == yellow2) { // when yellow pressed
            player2Colour = "/resources/player/paddleYellow.png";
        }
        if (e.getSource() == magenta2) { // when magenta pressed
            player2Colour = "/resources/player/paddleMagenta.png";
        }
        if (e.getSource() == red2) { // when red pressed
            player2Colour = "/resources/player/paddleRed.png";
        }
        if (e.getSource() == orange2) { // when orange pressed
            player2Colour = "/resources/player/paddleOrange.png";
        }
    }
}