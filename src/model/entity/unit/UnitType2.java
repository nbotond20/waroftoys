package model.entity.unit;

import java.awt.Color;

import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public class UnitType2 extends Unit {
    public UnitType2(Position pos, Game gp, MouseHandler mouseH){
        super(gp, mouseH);
        this.speed = 1;
        this.pos = pos;
        this.dir = Direction.DOWN;
        this.health = 300;
        this.color = Color.BLUE;
        getPlayerImage("unit-type1");
    }
}