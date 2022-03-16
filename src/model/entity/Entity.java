package model.entity;

import model.Game;
import model.utility.KeyHandler;
import model.utility.MouseHandler;
import model.utility.Position;

public abstract class Entity {
    public Game gp;
    public KeyHandler keyH;
    public MouseHandler mouseH;
    public Position pos;
    public int height, width;
    public double speed, health, range, MAX_HEALTH;
    public int HEALTH_BAR_WIDTH;
    public double cost;

    public Position getPosFromIndex(final int i, final int j) {
        return new Position(j * gp.tileSize + (gp.tileSize / 2) - width / 2,
                i * gp.tileSize + (gp.tileSize / 2) - height / 2);
    }

    public int[] getIndexFromPos(final Position pos) {
        final int[] arr = { (int) (pos.y / gp.tileSize), (int) (pos.x / gp.tileSize) };
        return arr;
    }

    public void calcCorrectPosition(final Position pos){
        final int[] ind = getIndexFromPos(pos); 
        this.pos = new Position(getPosFromIndex(ind[0], ind[1]));
    }
}