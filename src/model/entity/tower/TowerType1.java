package model.entity.tower;

import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;
import java.awt.Color;

public class TowerType1 extends Tower {
    public TowerType1(Position pos, Game gp, MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");
        
        this.pos = pos;
        this.health = 80;
        this.MAX_HEALTH = 100;
        this.range = 200;

        this.color = new Color(0, 0, 200, 255);

        calcCorrectPosition(this.pos);
    }
}   
