package model.entity.unit;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import javax.imageio.ImageIO;

import model.pathfinding.AStar;
import model.pathfinding.Cell;
import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;
import model.entity.Entity;

public abstract class Unit extends Entity {

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

    LinkedList<Position> destinations;

    Color color;

    public Unit(Game gp, MouseHandler mouseH) {
        this.width = 32 * gp.scale;
        this.height = 48 * gp.scale;
        this.dotSize = 8 * gp.scale;
        this.gp = gp;
        this.mouseH = mouseH;
        this.destinations = new LinkedList<Position>();
    }

    // Moves unit by position values
    private void move(Position pos) {
        this.pos.x += pos.x;
        this.pos.y += pos.y;
    }

    private void addDestination(Position pos) {
        this.destinations.add(pos);
    }

    // Checks if the unit is at the destination
    private boolean isAtDestination() {
        if (abs(destinations.get(0).x - pos.x) == 0 && abs(destinations.get(0).y - pos.y) == 0) {
            return true;
        }
        return false;
    }

    // Calculates the next position based on the unit's speed
    private Position calcNextPos(Position curr, Position dest) {
        double distX = (dest.x - curr.x);
        double distY = (dest.y - curr.y);
        if (sqrt(distX * distX + distY * distY) <= speed) {
            return new Position(dest.x - curr.x, dest.y - curr.y);
        }
        double ratio = sqrt((distX * distX + distY * distY) / (speed * speed));
        return new Position(distX / ratio, distY / ratio);
    }

    // Converts grid index (i, j) to position (x, y) the position is going to be in the center of the tile
    private Position getPosFromIndex(int i, int j) {
        return new Position(j * gp.tileSize + (gp.tileSize / 2) - width / 2,
                i * gp.tileSize + (gp.tileSize / 2) - height / 2);
    }

    // Converts position (x, y) to grid index (i, j)
    private int[] getIndexFromPos(Position pos) {
        int[] arr = { (int) (pos.y / gp.tileSize), (int) (pos.x / gp.tileSize) };
        return arr;
    }

    private void calcSpriteNum() {
            spriteCounter++;
            if (spriteCounter > 5) {
                spriteNum++;
                if (spriteNum == 8) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
        }
    }  

    // Handles mouse clicks ( adds new destinations based on click position )
    public void handleMouseClick() {
        if (this.mouseH.isClicked && !gp.isAttacking) {
            Position dest = new Position(mouseH.pos.x, mouseH.pos.y);

            int[] startInd;
            int[] destInd = getIndexFromPos(dest);
            if (destinations.size() == 0) {
                startInd = getIndexFromPos(this.pos);
            } else {
                startInd = getIndexFromPos(destinations.get(destinations.size() - 1));
            }

            if (startInd != null && destInd != null && mouseH.pos != null) {
                AStar aStar = new AStar(gp.maxScreenRow, gp.maxScreenCol, startInd[0], startInd[1], destInd[0], destInd[1], gp.tileM.blocks/* new int[][] {} */);
                aStar.process(); // Apply A* Algorithm
                ArrayList<Cell> destCells = aStar.displaySolution(false); // Returns destination cells ( displays it to console if parameter is true ) 

                if (destCells != null) {
                    for (int k = destCells.size() - 1; k >= 0; k--) {
                        addDestination(getPosFromIndex(destCells.get(k).i, destCells.get(k).j));
                    }
                }
            }
        }
        this.mouseH.isClicked = false;
    }

    // Handles movement
    private void startMoving() {
        Position p = calcNextPos(pos, destinations.get(0));
        move(p);

        if (isAtDestination()) {
            destinations.removeFirst();
        }

        setDirection(p); // Sets direction based on next position
        calcSpriteNum(); // Changes Sprite Count
    }

    // Sets sprite direction based on next position
    private void setDirection(Position p) {
        if (p.x <= 0 && p.y <= 0) { // LEFT or UP
            if (abs(p.x) < abs(p.y)) {
                dir = Direction.UP;
            } else {
                dir = Direction.LEFT;
            }
        }
        if (p.x >= 0 && p.y <= 0) { // RIGHT or UP
            if (abs(p.x) < abs(p.y)) {
                dir = Direction.UP;
            } else {
                dir = Direction.RIGHT;
            }
        }
        if (p.x <= 0 && p.y >= 0) { // LEFT or DOWN
            if (abs(p.x) < abs(p.y)) {
                dir = Direction.DOWN;
            } else {
                dir = Direction.LEFT;
            }
        }
        if (p.x >= 0 && p.y >= 0) { // RIGHT or DOWN
            if (abs(p.x) < abs(p.y)) {
                dir = Direction.DOWN;
            } else {
                dir = Direction.RIGHT;
            }
        }
    }

    // Sets sprite image based on current direction
    private BufferedImage setImageBasedOnDirection(Direction dir, boolean standing) {
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
        handleMouseClick(); // Listens for mouse clicks (adds new destination(s))
        if (destinations.size() != 0 && gp.isAttacking) startMoving(); // Handles movement
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = setImageBasedOnDirection(dir, destinations.size() == 0);

        if (this.equals(gp.activeUnit) && !gp.isAttacking) {
            g2.setColor(Color.RED);
            g2.fillOval((int) pos.x + (width / 2) - (dotSize / 2), (int) pos.y - (dotSize / 2), dotSize, dotSize);
        } else {
            g2.setColor(color);
        }

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
                Position p1 = destinations.get(i - 1);
                Position p2 = destinations.get(i);
                g2.fillOval((int) p2.x + (width / 2) - dotSize / 2, (int) p2.y + (height / 2) - dotSize / 2, dotSize,
                        dotSize);
                g2.drawLine((int) p1.x + (width / 2), (int) p1.y + (height / 2),
                        (int) p2.x + (width / 2), (int) p2.y + (height / 2));
            }
        }
    }

    // Loads the player sprite pictures
    public void getPlayerImage(String filename) {
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
        } catch (IOException e) {
            System.out.println("entity.Player.getPlayerImage()");
        }
    }
}
