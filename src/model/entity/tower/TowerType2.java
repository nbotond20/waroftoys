package model.entity.tower;

import model.utility.MouseHandler;
import model.utility.Position;
import model.Game;
import java.awt.Color;

public class TowerType2 extends Tower {
    public TowerType2(Position pos, Game gp, MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");
        
        this.pos = pos;
        this.health = 20;
        this.MAX_HEALTH = 200;
        this.range = 100;

        this.color = Color.GREEN;

        calcCorrectPosition(this.pos);
    }
}   
