package com.pongProject.logic;

import com.pongProject.database.PongDB;
import com.pongProject.gameFolder.Menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private PongDB db;

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler (GamePanel gp, PongDB pongDB){
        this.gp = gp;
        this.db = pongDB;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            gp.pauseState = !gp.pauseState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameDelete();
            new Menu(Menu.username, db);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
