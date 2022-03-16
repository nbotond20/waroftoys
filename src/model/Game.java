package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.entity.Base;
import model.player.Player;
import model.tile.TileManager;
import model.utility.KeyHandler;
import model.utility.MouseHandler;
import model.utility.MouseMovementHandler;
import model.utility.Position;

public class Game extends JPanel implements Runnable {
    public Position hoverPosition;
    public Position selectedPosition;

    public int selectedButtonNum = -1;

    final int originalTileSize = 32;
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
    public int activePlayer = 0;

    public boolean isAttacking = false;

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addMouseMotionListener(mouseMH);
        this.setFocusable(true);
        this.hoverPosition = new Position();

        this.players = new ArrayList<Player>();

        final Player p = new Player("James", this);
        final Base b = new Base(new Position(), this);
        p.setBase(b);
        players.add(p);

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

    public void setGameState() {
    }

    public void update() {
        for(final Player p : players){
            p.update();
        }
    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(final Player p : players) {
            p.draw(g2);
        }

        g2.setColor(new Color(1f, 0f, 0f, .5f));
        g2.fillRect((int) hoverPosition.x, (int) hoverPosition.y, tileSize, tileSize);

        if (selectedPosition != null) {
            g2.setColor(new Color(0, 160, 200, 150));
            g2.fillRect((int) selectedPosition.x, (int) selectedPosition.y, tileSize, tileSize);
        }

        g2.setFont(g.getFont().deriveFont(30f));
        g2.drawString(String.valueOf(players.get(0).balance), 0, 0 + g2.getFontMetrics().getHeight());

        g2.dispose();
    }
}
