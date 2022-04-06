package model.entity.tower;

import java.awt.Color;

import model.Game;
import model.utility.MouseHandler;
import model.utility.Position;

public class TowerType3 extends Tower {
    public TowerType3(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 300;
        this.MAX_HEALTH = 300;
        this.range = 300;
        this.cost = 500;
        this.color = new Color(200, 0, 0, 255);
        this.type = "SLOWER";

        calcCorrectPosition(this.pos);
    }
}
