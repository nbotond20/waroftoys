package model.entity.unit;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import model.Game;
import model.entity.Entity;
import model.pathfinding.AStar;
import model.pathfinding.Cell;
import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;

public abstract class Unit extends Entity {
    private final boolean AStarVerbose = false;

    private BufferedImage front1, front2, front3, front4, front5, front6, front7, front8;
    private BufferedImage back1, back2, back3, back4, back5, back6, back7, back8;
    private BufferedImage left1, left2, left3, left4, left5, left6, left7, left8;
    private BufferedImage right1, right2, right3, right4, right5, right6, right7, right8;

    ArrayList<BufferedImage> front;
    ArrayList<BufferedImage> back;
    ArrayList<BufferedImage> left;
    ArrayList<BufferedImage> right;

    int spriteCounter = 0;
    int spriteNum = 0;

    Direction dir;
    int dotSize;

    public LinkedList<Position> destinations;

    Color color;

    public Unit(final Game gp, final MouseHandler mouseH) {
        this.width = 16 * gp.scale;
        this.height = 24 * gp.scale;
        this.dotSize = 4 * gp.scale;
        this.gp = gp;
        this.mouseH = mouseH;
        this.destinations = new LinkedList<Position>();
        HEALTH_BAR_WIDTH = 30;
    }

    private void move(final Position pos) {
        this.pos.x += pos.x;
        this.pos.y += pos.y;
    }

    private boolean isAtDestination() {
        if (abs(destinations.get(0).x - pos.x) == 0 && abs(destinations.get(0).y - pos.y) == 0) {
            return true;
        }
        return false;
    }

    private Position calcNextPos(final Position curr, final Position dest) {
        final double distX = (dest.x - curr.x);
        final double distY = (dest.y - curr.y);
        if (sqrt(distX * distX + distY * distY) <= speed) {
            return new Position(dest.x - curr.x, dest.y - curr.y);
        }
        final double ratio = sqrt((distX * distX + distY * distY) / (speed * speed));
        return new Position(distX / ratio, distY / ratio);
    }

    private void calcSpriteNum() {
        if (destinations.size() != 0 && gp.isAttacking) {
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum++;
                if (spriteNum == 8) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
        }
    }

    public void addDestination(Position dest) {
        if (!gp.isAttacking) {

            int[] startInd;
            final int[] destInd = getIndexFromPos(dest);
            if (destinations.size() == 0) {
                startInd = getIndexFromPos(this.pos);
            } else {
                startInd = getIndexFromPos(destinations.get(destinations.size() - 1));
            }

            if (startInd != null && destInd != null && mouseH.pos != null) {
                final AStar aStar = new AStar(gp.maxScreenRow, gp.maxScreenCol, startInd[0], startInd[1], destInd[0],
                        destInd[1], gp.tileM.blocks/* new int[][] {} */);

                /* aStar.display(); */
                aStar.process(); // Apply A* Algorithm
                /* aStar.displayScores(); */ // Display Scores on grid

                ArrayList<Cell> destCells;
                if (!AStarVerbose) {
                    destCells = aStar.displaySolution(false); // Display Solution
                } else {
                    aStar.display();
                    aStar.process(); // Apply A* Algorithm
                    aStar.displayScores(); // Display Scores on grid
                    destCells = aStar.displaySolution(true); // Display Solution
                }

                if (destCells != null) {
                    for (int k = destCells.size() - 1; k >= 0; k--) {
                        this.destinations.add(getPosFromIndex(destCells.get(k).i, destCells.get(k).j));
                    }
                }
            }
        }
    }

    private void startMoving() {
        if (destinations.size() != 0 && gp.isAttacking) {
            final Position p = calcNextPos(pos, destinations.get(0));
            move(p);

            if (isAtDestination()) {
                destinations.removeFirst();
            }

            if (p.x <= 0 && p.y <= 0) { // LEFT UP
                if (abs(p.x) < abs(p.y)) {
                    dir = Direction.UP;
                } else {
                    dir = Direction.LEFT;
                }
            }
            if (p.x >= 0 && p.y <= 0) { // RIGHT UP
                if (abs(p.x) < abs(p.y)) {
                    dir = Direction.UP;
                } else {
                    dir = Direction.RIGHT;
                }
            }
            if (p.x <= 0 && p.y >= 0) { // LEFT DOWN
                if (abs(p.x) < abs(p.y)) {
                    dir = Direction.DOWN;
                } else {
                    dir = Direction.LEFT;
                }
            }
            if (p.x >= 0 && p.y >= 0) { // RIGHT DOWN
                if (abs(p.x) < abs(p.y)) {
                    dir = Direction.DOWN;
                } else {
                    dir = Direction.RIGHT;
                }
            }
        }
    }

    private BufferedImage setImageBasedOnDirection(final Direction dir, final boolean standing) {
        BufferedImage image = null;
        if (standing || !gp.isAttacking) {
            switch (dir) {
                case UP:
                    image = back.get(0);
                    break;
                case DOWN:
                    image = front.get(0);
                    break;
                case LEFT:
                    image = left.get(0);
                    break;
                case RIGHT:
                    image = right.get(0);
                    break;
            }
        } else {
            switch (dir) {
                case UP:
                    image = back.get(spriteNum);
                    break;
                case DOWN:
                    image = front.get(spriteNum);
                    break;
                case LEFT:
                    image = left.get(spriteNum);
                    break;
                case RIGHT:
                    image = right.get(spriteNum);
                    break;
            }
        }
        return image;
    }

    public void update() {
        calcSpriteNum(); // Changes Sprite Count
        startMoving(); // Changes Player Sprite Direction (when using the mouse)
    }

    public void draw(final Graphics2D g2) {
        final BufferedImage image = setImageBasedOnDirection(dir, destinations.size() == 0);

        g2.setColor(color);

        g2.drawImage(image, (int) pos.x, (int) pos.y, width, height, null);
        g2.drawRect((int) pos.x + 6, (int) pos.y + height / 2, width - 12, height / 2);

        if (destinations.size() != 0) {
            g2.fillOval((int) destinations.get(0).x + (width / 2) - dotSize / 2,
                    (int) destinations.get(0).y + (height / 2) - dotSize / 2, dotSize,
                    dotSize);
            g2.drawLine((int) destinations.get(0).x + (width / 2),
                    (int) destinations.get(0).y + (height / 2), (int) pos.x + (width / 2),
                    (int) pos.y + (height / 2));
            for (int i = 1; i < destinations.size(); i++) {
                final Position p1 = destinations.get(i - 1);
                final Position p2 = destinations.get(i);
                g2.fillOval((int) p2.x + (width / 2) - dotSize / 2, (int) p2.y + (height / 2) - dotSize / 2, dotSize,
                        dotSize);
                g2.drawLine((int) p1.x + (width / 2), (int) p1.y + (height / 2),
                        (int) p2.x + (width / 2), (int) p2.y + (height / 2));
            }
        }

        g2.setColor(Color.RED);
        g2.fillRect((int) pos.x + (width - HEALTH_BAR_WIDTH) / 2, (int) pos.y - 5,
                (int) (HEALTH_BAR_WIDTH * (health / MAX_HEALTH)), 5);
        g2.setColor(Color.BLACK);
        g2.drawRect((int) pos.x + (width - HEALTH_BAR_WIDTH) / 2, (int) pos.y - 5, HEALTH_BAR_WIDTH, 5);
    }

    public void getPlayerImage(final String filename) {
        try {
            front1 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front1.png"));
            front2 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front2.png"));
            front3 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front3.png"));
            front4 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front4.png"));
            front5 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front5.png"));
            front6 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front6.png"));
            front7 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front7.png"));
            front8 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/front/front8.png"));
            front = new ArrayList<>(Arrays.asList(front1, front2, front3, front4, front5, front6, front7, front8));

            back1 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back1.png"));
            back2 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back2.png"));
            back3 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back3.png"));
            back4 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back4.png"));
            back5 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back5.png"));
            back6 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back6.png"));
            back7 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back7.png"));
            back8 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/back/back8.png"));
            back = new ArrayList<>(Arrays.asList(back1, back2, back3, back4, back5, back6, back7, back8));

            left1 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left4.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left5.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left6.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left7.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/left/left8.png"));
            left = new ArrayList<>(Arrays.asList(left1, left2, left3, left4, left5, left6, left7, left8));

            right1 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right4.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right5.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right6.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right7.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/player/" + filename + "/right/right8.png"));
            right = new ArrayList<>(Arrays.asList(right1, right2, right3, right4, right5, right6, right7, right8));
        } catch (final IOException e) {
            System.out.println("entity.Player.getPlayerImage()");
        }
    }
}