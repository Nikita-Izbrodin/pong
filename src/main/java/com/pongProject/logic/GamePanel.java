package com.pongProject.logic;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import com.pongProject.database.PongDB;
import com.pongProject.entities.Ball;
import com.pongProject.entities.Computer;
import com.pongProject.entities.Player;
import com.pongProject.entities.Player2;
import com.pongProject.gameFolder.Menu;
import com.pongProject.gameFolder.Sound;
import com.pongProject.gameFolder.UI;
import com.pongProject.object.SuperObject;
import com.pongProject.tile.TileManager;
import com.pongProject.gameFolder.Menu;

public class GamePanel extends JPanel implements Runnable  {

    static final int originalTileSize = 16;
    static final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    public int maxScreenCol = 18; // were all static, they were made static to fix a problem
    public int maxScreenRow = 14; // were all static, they were made static to fix a problem
    public int screenWidth = tileSize * maxScreenCol; // were all static, they were made static to fix a problem
    public int screenHeight = tileSize * maxScreenRow; // were all static, they were made static to fix a problem
    public boolean gameFinished = false;
    public String winner = "";
    public boolean pauseState= false;
    public int finalScore;

    //fps
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH;
    P2KeyHandler keyG = new P2KeyHandler();
    Sound music = new Sound();
    Sound sEffect = new Sound();

    public Thread gameThread;
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player;
    public Player2 player2;
    public Computer compAI = new Computer(this);
    public Ball ball = new Ball(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];

    private PongDB db;

    public GamePanel(PongDB pongDB) {

        this.db = pongDB;
        this.keyH = new KeyHandler(this, pongDB);

        this.player = new Player(this, keyH);
        this.player2 = new Player2(this, keyG);

        if (Menu.globalDifficulty == 2) {
            maxScreenCol = 22;
            maxScreenRow = 18;
            screenWidth = tileSize * maxScreenCol;
            screenHeight = tileSize * maxScreenRow;
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addKeyListener(keyG);
        this.setFocusable(true);
    }

    public void setupGame(){
        if (Menu.musicToggle){
            playMusic(0);
        }
    }

    public void gameDelete(){
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        stopMusic();
        gameThread = null;
        frame.dispose();

        finalScore = player.score;

        db.updateHighScore(Menu.username, finalScore);
        db.updateHighestRally(Menu.username, ball.maxRally);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1) {
                // update information eg positions
                update();
                // draw the screen with updated information
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000000000){
                //System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {
        if(!pauseState) {
            Random rand = new Random();
            if (rand.nextInt(100) == 1) {
                aSetter.setObject();
            }
            player.update();
            if (Menu.playerOrComp == 1) {
                player2.update();
            } else if (Menu.playerOrComp == 2) {
                compAI.update();
            }

            ball.update();
        }

    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        player.draw(g2);
        if(Menu.playerOrComp == 1){
            player2.draw(g2);
        }else if(Menu.playerOrComp==2){
            compAI.draw(g2);
        }

        ball.draw(g2);

        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        sEffect.setFile(i);
        sEffect.play();
    }

}
