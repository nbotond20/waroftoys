package model.entity.unit;

import java.awt.Color;

import model.Game;
import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;

public class UnitType1 extends Unit {
    public UnitType1(final Position pos, final Game gp, final MouseHandler mouseH, int playerNum){
        super(gp, mouseH, playerNum);
        this.speed = 1;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 50;
        this.MAX_HEALTH = 100;
        this.color = new Color(0, 0, 200, 255);
        this.cost = 1000;
        getPlayerImage("unit-type1");
    }
}
