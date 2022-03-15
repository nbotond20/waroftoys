package model.entity.unit;

import java.awt.Color;

import model.utility.Direction;
import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;

public class UnitType3 extends Unit {
    public UnitType3(Position pos, Game gp, MouseHandler mouseH){
        super(gp, mouseH);
        this.speed = 1.5;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 50;
        this.MAX_HEALTH = 50;
        this.color = new Color(200, 0, 0, 255);
        this.cost = 3000;
        getPlayerImage("unit-type1");
    }
}
