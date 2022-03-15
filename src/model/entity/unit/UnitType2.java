package model.entity.unit;

import java.awt.Color;

import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public class UnitType2 extends Unit {
    public UnitType2(Position pos, Game gp, MouseHandler mouseH){
        super(gp, mouseH);
        this.speed = 0.5;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 300;
        this.MAX_HEALTH = 300;
        this.color = new Color(0, 200, 0, 255);
        this.cost = 2000;
        getPlayerImage("unit-type1");
    }
}