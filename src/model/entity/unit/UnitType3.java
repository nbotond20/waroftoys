package model.entity.unit;

import java.awt.Color;

import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public class UnitType3 extends Unit {
    public UnitType3(Position pos, Game gp, MouseHandler mouseH){
        super(gp, mouseH);
        this.speed = 2.5;
        this.pos = pos;
        this.dir = Direction.DOWN;
        this.health = 50;
        this.color = Color.MAGENTA;
        getPlayerImage("unit-type1");
    }
}
