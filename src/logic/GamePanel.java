package logic;

import javax.swing.JPanel;
import java.awt.*;

import entities.Ball;
import entities.Computer;
import entities.Player;
import entities.Player2;
import gameFolder.Menu;
import gameFolder.SecretButtonTemplate;
import gameFolder.Sound;
import gameFolder.UI;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable  {
    static final int originalTileSize = 16;
    static final int scale = 3;

    public static final int tileSize = originalTileSize * scale;

    public static int maxScreenCol = 18;
    public static int maxScreenRow = 14;
    public static int screenWidth = tileSize * maxScreenCol;
    public static int screenHeight = tileSize * maxScreenRow;

    SecretButtonTemplate discoButton = new SecretButtonTemplate(0, 0);

    //fps
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    P2KeyHandler keyG = new P2KeyHandler();
    Sound music = new Sound();
    Sound sEffect = new Sound();

    public Thread gameThread;
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);
    public Player2 player2 = new Player2(this, keyG);
    public Computer compAI = new Computer(this);
    public Ball ball = new Ball(this);

    public GamePanel() {
        if (Menu.globalDifficulty == 2) {
            GamePanel.maxScreenCol = 22;
            GamePanel.maxScreenRow = 18;
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
        playMusic(0);
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
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }

    }

    public void update() {
        player.update();
        if(Menu.playerOrComp == 1){
            player2.update();
        }else if(Menu.playerOrComp==2){
            compAI.update();
        }

        ball.update();
    }


    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

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
    public void stopMusic(){music.stop();}
    public void playSE(int i){
        sEffect.setFile(i);
        sEffect.play();
    }
}
