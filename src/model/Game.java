package model;

import static java.lang.Math.abs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.entity.Base;
import model.entity.unit.Unit;
import model.player.Player;
import model.tile.TileManager;
import model.utility.KeyHandler;
import model.utility.MouseHandler;
import model.utility.MouseMovementHandler;
import model.utility.Position;
import view.ButtonPanel;
import view.Scoreboard;

public class Game extends JPanel implements Runnable {

    private static final boolean DRAW_DRAG = false;

    public Position hoverPosition;
    public Position dragPosition;
    public Position prevDragPosition;
    
    public Unit selectedUnit = null;
    public int selectedButtonNum = -1;

    final int originalTileSize = 32;
    public final int scale = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 44;
    public final int maxScreenRow = 20;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;

    public TileManager tileM = new TileManager(tileSize);
    public final KeyHandler keyH = new KeyHandler();
    public final MouseHandler mouseH = new MouseHandler(this);
    public final MouseMovementHandler mouseMH = new MouseMovementHandler(this);
    private Thread gameThread;

    public ArrayList<Player> players;
    public int activePlayer = (int)Math.round(Math.random());
    private int prevPlayer = activePlayer;
    public int readyBtnPushCount = 0;

    public boolean isAttacking = false;

    private ButtonPanel btnPanel;
    private Scoreboard score;

    public Game(final String name1, final String name2) {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseMH);
        this.setFocusable(true);
        this.hoverPosition = new Position();

        this.players = new ArrayList<Player>();

        final Player p1 = new Player(name1, this, 1000000000);
        final Base b1 = new Base(new Position(1 * tileSize, 1 * tileSize), this);
        p1.setBase(b1);
        p1.setAvailableFields();

        final Player p2 = new Player(name2, this, 1000000000);
        final Base b2 = new Base(new Position(42 * tileSize, 18 * tileSize), this);
        p2.setBase(b2);
        p2.setAvailableFields();

        players.add(p1);
        players.add(p2);

        addMouseListener(mouseH);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        final double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            if(keyH.GAME_RUNNING){
                if(!isAttacking){
                    enableButtons();
                }
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
                    for (final Player player : players) {
                        for (final Unit unit : player.units) {
                            unit.speed = unit.REGULAR_SPEED;
                        }
                    }
                    drawCount = 0;
                    timer = 0;
                }
            }else{
                disableButtons();
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;
                if (delta > 1) {
                    delta--;
                    drawCount++;
                }
            }
        }
    }

    public void addButtonPanel(final ButtonPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public void addScoreboardPanel(final Scoreboard score) {
        this.score = score;
    }

    public void setNextPlayer() {
        readyBtnPushCount++;
        if (readyBtnPushCount == 2) {
            for (final Unit u : players.get(0).units) {
                u.updatePath();
            }

            for (final Unit u : players.get(1).units) {
                u.updatePath();
            }

            disableButtons();
            isAttacking = true;

            if (prevPlayer == 0) {
                activePlayer = 1;
                prevPlayer = 1;
            } else {
                activePlayer = 0;
                prevPlayer = 0;
            }
            readyBtnPushCount = 0;
        } else {
            if (activePlayer == 0) {
                activePlayer = 1;
            } else {
                activePlayer = 0;
            }
        }
    }
    long counter = 0;
    public void update() {
        for (final Player p : players) {
            p.update();
        }

        if (isAttacking) {
            players.get(0).checkRange(players.get(1));
            players.get(1).checkRange(players.get(0));
        }

        boolean allFinished = true;
        for (final Player p : players) {
            for (final Unit u : p.units) {
                if (u.destinations.size() > 0) {
                    allFinished = false;
                }
            }
        }

        /* updateAllTileOffsets(); */

        if (allFinished) {
            isAttacking = false;
            enableButtons();
            btnPanel.Ready.setText("Ready");
        }

        score.Player1Balance.setText(String.valueOf(players.get(0).balance));
        score.Player2Balance.setText(String.valueOf(players.get(1).balance));

        if (activePlayer == 0) {
            score.Player1Name.setForeground(Color.RED);
            score.Player2Name.setForeground(Color.BLACK);
        } else {
            score.Player1Name.setForeground(Color.BLACK);
            score.Player2Name.setForeground(Color.RED);
        }
    }

    private void enableButtons() {
        btnPanel.Ready.setEnabled(true);
        btnPanel.Unit1Btn.setEnabled(true);
        btnPanel.Unit2Btn.setEnabled(true);
        btnPanel.Unit3Btn.setEnabled(true);
        btnPanel.Tower1Btn.setEnabled(true);
        btnPanel.Tower2Btn.setEnabled(true);
        btnPanel.Tower3Btn.setEnabled(true);
    }

    private void disableButtons() {
        btnPanel.Ready.setEnabled(false);
        btnPanel.Unit1Btn.setEnabled(false);
        btnPanel.Unit2Btn.setEnabled(false);
        btnPanel.Unit3Btn.setEnabled(false);
        btnPanel.Tower1Btn.setEnabled(false);
        btnPanel.Tower2Btn.setEnabled(false);
        btnPanel.Tower3Btn.setEnabled(false);
    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for (final Player p : players) {
            p.draw(g2);
        }

        g2.setColor(new Color(1f, 0f, 0f, .5f));
        g2.fillRect((int) hoverPosition.x, (int) hoverPosition.y, tileSize, tileSize);

        if (DRAW_DRAG) {
            if (prevDragPosition != null) {
                g2.setColor(new Color(0, 0, 255, 100));
                g2.fillRect((int) prevDragPosition.x, (int) prevDragPosition.y, tileSize, tileSize);
            }

            if (dragPosition != null) {
                g2.setColor(new Color(0, 255, 0, 100));
                g2.fillRect((int) dragPosition.x, (int) dragPosition.y, tileSize, tileSize);
            }
        }

        g2.setColor(Color.WHITE);
        g2.drawLine((maxScreenCol * tileSize) / 2, 0, (maxScreenCol * tileSize) / 2, maxScreenRow * tileSize);
        g2.dispose();
    }

    private final int order1[] = { 4 };
    private final int order2[] = { 3, 5 };
    private final int order3[] = { 6, 8, 1 };
    private final int order4[] = { 0, 2, 6, 8 };
    private final int order5[] = { 4, 0, 2, 6, 8 };
    private final int order6[] = { 4, 0, 2, 6, 8, 1 };
    private final int order7[] = { 4, 0, 2, 6, 8, 1, 3 };
    private final int order8[] = { 4, 0, 2, 6, 8, 1, 3, 5 };
    private final int order9[] = { 4, 0, 2, 6, 8, 1, 3, 5, 7 };

    public void updateAllTileOffsets(){
        for (int i = 0; i <= maxScreenRow; i++) {
            for (int j = 0; j <= maxScreenCol; j++) {
                setTileOffsets(new Position((j * tileSize) - tileSize / 2, (i * tileSize) - tileSize / 2));
            }
        }
    }

    public void setTileOffsets(final Position closeTo){
        int count = 0;
        for (final Player p : players) {
            if (p.units != null) {
                for (final Unit u : p.units) {
                    if ((abs(u.pos.x - closeTo.x + u.width / 2) < tileSize / 2
                    && abs(u.pos.y - closeTo.y + u.height / 2) < tileSize / 2)) {
                        count++;
                    }
                }
            }
        }
        int k = 0;
        for (final Player p : players) {
            if (p.units != null) {
                for (final Unit u : p.units) {
                    if ((abs(u.pos.x - closeTo.x + u.width / 2) < tileSize / 2
                            && abs(u.pos.y - closeTo.y + u.height / 2) < tileSize / 2)) {
                        switch (count) {
                            case 1:
                                u.setOffset(order1[k]);
                                break;
                            case 2:
                                u.setOffset(order2[k]);
                                break;
                            case 3:
                                u.setOffset(order3[k]);
                                break;
                            case 4:
                                u.setOffset(order4[k]);
                                break;
                            case 5:
                                u.setOffset(order5[k]);
                                break;
                            case 6:
                                u.setOffset(order6[k]);
                                break;
                            case 7:
                                u.setOffset(order7[k]);
                                break;
                            case 8:
                                u.setOffset(order8[k]);
                                break;
                            case 9:
                                u.setOffset(order9[k]);
                                break;
                        }
                        k++;
                    }
                }
            }
        }
    }
}
