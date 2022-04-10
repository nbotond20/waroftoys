package com.runtimeexception.waroftoys.model.entity.tower;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class TowerType2 extends Tower {
    public TowerType2(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 2000;
        this.MAX_HEALTH = 2000;
        this.range = 100;
        this.cost = 1000;
        this.color = new Color(0, 200, 0, 255);
        this.type = "FASTER";

        calcCorrectPosition(this.pos);
    }
}
