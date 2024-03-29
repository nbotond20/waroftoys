package com.runtimeexception.waroftoys.model.entity;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.KeyHandler;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

public abstract class Entity {
    public Game game;
    public KeyHandler keyH;
    public MouseHandler mouseH;
    public Position pos;
    public int height, width;
    public double speed, health, damage, range, MAX_HEALTH, REGULAR_SPEED;
    public int HEALTH_BAR_WIDTH;
    public double cost;
    public String type = "unit";
    public String img;

    public Position getPosFromIndex(final int i, final int j) {
        return new Position(j * game.tileSize + (game.tileSize / 2) - width / 2,
                i * game.tileSize + (game.tileSize / 2) - height / 2);
    }

    public Position getPosFromIndex(final int[] indexes) {
        return getPosFromIndex(indexes[0], indexes[1]);
    }

    public int[] getIndexFromPos(final Position pos) {
        final int[] arr = { (int) (pos.y / game.tileSize), (int) (pos.x / game.tileSize) };
        return arr;
    }

    public void calcCorrectPosition(final Position pos) {
        final int[] ind = getIndexFromPos(pos);
        this.pos = new Position(getPosFromIndex(ind[0], ind[1]));
    }

    public boolean isInSameTile(final Position pos1) {
        return equal(getCorrectPos(pos1), getCorrectPos(this.pos));
    }

    public Position getCorrectPos(final Position pos) {
        final int[] ind = getIndexFromPos(pos);
        return new Position(getPosFromIndex(ind[0], ind[1]));
    }

    private boolean equal(final Position pos1, final Position pos2) {
        return (pos1.x == pos2.x && pos1.y == pos2.y);
    }
}
