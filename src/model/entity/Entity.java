package model.entity;

import model.utility.KeyHandler;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public abstract class Entity {
    public Game gp;
    public KeyHandler keyH;
    public MouseHandler mouseH;
    public Position pos;
    public int height, width;
    public double speed, health, range, MAX_HEALTH;
    public int HEALTH_BAR_WIDTH;

    public Position getPosFromIndex(int i, int j) {
        return new Position(j * gp.tileSize + (gp.tileSize / 2) - width / 2,
                i * gp.tileSize + (gp.tileSize / 2) - height / 2);
    }

    public int[] getIndexFromPos(Position pos) {
        int[] arr = { (int) (pos.y / gp.tileSize), (int) (pos.x / gp.tileSize) };
        return arr;
    }

    public void calcCorrectPosition(Position pos){
        int[] ind = getIndexFromPos(pos); 
        this.pos = new Position(getPosFromIndex(ind[0], ind[1]));
    }
}
