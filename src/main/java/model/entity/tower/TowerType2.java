package model.entity.tower;

import java.awt.Color;

import model.Game;
import model.utility.MouseHandler;
import model.utility.Position;

public class TowerType2 extends Tower {
    public TowerType2(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 200;
        this.MAX_HEALTH = 200;
        this.range = 100;
        this.cost = 1000;
        this.color = new Color(0, 200, 0, 255);
        this.type = "FASTER";

        calcCorrectPosition(this.pos);
    }
}
