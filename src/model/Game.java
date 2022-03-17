package model;

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
    public Position hoverPosition;

    public int selectedButtonNum = -1;

    final int originalTileSize = 40;
    public final int scale = 1;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 32;
    public final int maxScreenRow = 18;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    final int FPS = 60;

    public TileManager tileM = new TileManager(tileSize);
    private final KeyHandler keyH = new KeyHandler();
    public final MouseHandler mouseH = new MouseHandler(this);
    private final MouseMovementHandler mouseMH = new MouseMovementHandler(this);
    private Thread gameThread;

    public ArrayList<Player> players;
    public int activePlayer = 0/* (int)Math.round(Math.random()) */;
    private int prevPlayer = activePlayer;
    public int readyBtnPushCount = 0;

    public boolean isAttacking = false;

    private ButtonPanel btnPanel;

    private Scoreboard score;

    public Game(String name1, String name2) {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseMH);
        this.setFocusable(true);
        this.hoverPosition = new Position();

        this.players = new ArrayList<Player>();

        final Player p1 = new Player(name1, this, 25000);
        final Base b1 = new Base(new Position(160, 100), this);
        p1.setBase(b1);

        final Player p2 = new Player(name2, this, 15000);
        final Base b2 = new Base(new Position(600, 460), this);
        p2.setBase(b2);

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

    public void addButtonPanel(ButtonPanel btnPanel) {
        this.btnPanel = btnPanel;
    }

    public void addScoreboardPanel(Scoreboard score) {
        this.score = score;
    }

    public void setNextPlayer() {
        readyBtnPushCount++;
        if (readyBtnPushCount == 2) {
            btnPanel.Ready.setEnabled(false);
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

    public void update() {
        for (final Player p : players) {
            p.update();
        }

        boolean allFinished = true;
        for (final Player p : players) {
            for (Unit u : p.units) {
                if (u.destinations.size() > 0) {
                    allFinished = false;
                }
            }
        }

        if (allFinished) {
            isAttacking = false;
            btnPanel.Ready.setEnabled(true);
            btnPanel.Ready.setText("Ready");
        }

        score.Player1Balance.setText(String.valueOf(players.get(0).balance));
        score.Player2Balance.setText(String.valueOf(players.get(1).balance));
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

        g2.setFont(g.getFont().deriveFont(30f));
        g2.drawString(String.valueOf(players.get(activePlayer).name), 0, 0 + g2.getFontMetrics().getHeight());
        g2.drawString(String.valueOf(players.get(activePlayer).balance), 0, 0 + 2 * g2.getFontMetrics().getHeight());

        g2.dispose();
    }
}
