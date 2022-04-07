package com.runtimeexception.waroftoys.model.utility;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean GAME_RUNNING = true;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean active = false;
    public int switchPlayer = 0;

    @Override
    public void keyTyped(final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        final int code = e.getKeyCode();

        if( code == KeyEvent.VK_ESCAPE){
            GAME_RUNNING = !GAME_RUNNING;
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_SPACE){
            active = !active;
        }
        if(code == KeyEvent.VK_LEFT){
            switchPlayer = -1;
        }
        if(code == KeyEvent.VK_RIGHT){
            switchPlayer = 1;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        final int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
