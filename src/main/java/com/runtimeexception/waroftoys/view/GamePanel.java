package com.runtimeexception.waroftoys.view;

import java.awt.Dimension;

import javax.swing.*;
import java.awt.*;

import com.runtimeexception.waroftoys.model.Game;

public class GamePanel extends JPanel {
    public Game game;

    public GamePanel(String name1, String name2) {
        this.game = new Game(name1, name2, false);
        this.game.setGamePanel(this);

        this.setPreferredSize(new Dimension(game.screenWidth, game.screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(game.keyH);
        this.addMouseMotionListener(game.mouseMH);
        this.setFocusable(true);

        addMouseListener(game.mouseH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;

        if(game.GAME_PANEL){
            game.draw(g2);
        }
    }
}
