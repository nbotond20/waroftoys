package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;

import model.tile.TileManager;
import model.utility.*;
import model.entity.unit.*;

public class Game extends JPanel implements Runnable {

    final int originalTileSize = 64;
    public final int scale = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 9;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;

    public TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler();
    private MouseHandler mouseH = new MouseHandler();
    private Thread gameThread;

    private ArrayList<Unit> units;

    public boolean isAttacking = false;
    public int unitNum = 0;
    public Unit activeUnit;

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        units = new ArrayList<Unit>();

        Unit p1 = new UnitType1(new Position(), this, mouseH);
        Unit p2 = new UnitType2(new Position(), this, mouseH);
        Unit p3 = new UnitType3(new Position(), this, mouseH);

        units.add(p1);
        units.add(p2);
        units.add(p3);

        activeUnit = units.get(0);

        addMouseListener(mouseH);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta > 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    private void setNextUnitIndex(){
        if(keyH.switchPlayer != 0){
            if(keyH.switchPlayer == -1){
                if(unitNum == 0){
                    unitNum = units.size()-1;
                }else{
                    unitNum--;
                }
            }else{
                if(unitNum == units.size()-1){
                    unitNum = 0;
                }else{
                    unitNum++;
                }
            }
            keyH.switchPlayer = 0;
            activeUnit = units.get(unitNum);
        }
    }

    public void update() {
        setNextUnitIndex();
        if(keyH.active){
            isAttacking = !isAttacking;
            keyH.active = false;
        }
        if(isAttacking){
            for(Unit u : units){
                u.update();
            }
        }else{
            units.get(unitNum).update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        for (Unit p : units) {
            p.draw(g2);
        }

        g2.dispose();
    }
}
