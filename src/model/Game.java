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
import model.entity.Base;
import model.entity.tower.*;

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
    private ArrayList<Tower> towers;
    private ArrayList<Base> bases;

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

        Unit u1 = new UnitType1(new Position(), this, mouseH);
        Unit u2 = new UnitType2(new Position(), this, mouseH);
        Unit u3 = new UnitType3(new Position(), this, mouseH);

        units.add(u1);
        units.add(u2);
        units.add(u3);

        activeUnit = units.get(0);

        towers = new ArrayList<Tower>();

        Tower t1 = new TowerType1(new Position(128,64), this, mouseH);
        Tower t2 = new TowerType2(new Position(128,450), this, mouseH);
        Tower t3 = new TowerType3(new Position(555,120), this, mouseH);

        towers.add(t1);
        towers.add(t2);
        towers.add(t3);

        bases = new ArrayList<Base>();

        Base b1 = new Base(new Position(500,500), this, mouseH);

        bases.add(b1);

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

        for(Base b : bases){
            b.draw(g2);
        }

        for(Tower t : towers) {
            t.draw(g2);
        }

        for (Unit p : units) {
            p.draw(g2);
        }

        g2.dispose();
    }
}
