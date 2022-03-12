package model.entity.unit;

import java.awt.Color;

import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public class UnitType1 extends Unit {
    public UnitType1(Position pos, Game gp, MouseHandler mouseH){
        super(gp, mouseH);
        this.speed = 2;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 50;
        this.MAX_HEALTH = 100;
        this.color = Color.GREEN;
        getPlayerImage("unit-type1");
    }
}
