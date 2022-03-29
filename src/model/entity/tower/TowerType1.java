package model.entity.tower;

import java.awt.Color;

import model.Game;
import model.entity.Entity;
import model.utility.MouseHandler;
import model.utility.Position;

public class TowerType1 extends Tower {
    public TowerType1(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 100;
        this.MAX_HEALTH = 100;
        this.range = 200;
        this.cost = 2500;
        this.color = new Color(0, 0, 200, 255);
        this.type = "ATTACK";

        calcCorrectPosition(this.pos);
    }
}
