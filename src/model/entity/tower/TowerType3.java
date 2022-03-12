package model.entity.tower;

import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;
import java.awt.Color;

public class TowerType3 extends Tower {
    public TowerType3(Position pos, Game gp, MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");
        
        this.pos = pos;
        this.health = 150;
        this.MAX_HEALTH = 300;
        this.range = 300;

        this.color = Color.RED;

        calcCorrectPosition(this.pos);
    }
}   
