package com.runtimeexception.waroftoys.model.entity.tower;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class TowerType1 extends Tower {
    public TowerType1(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 100;
        this.MAX_HEALTH = 100;
        this.range = 200;
        this.cost = 2500;
        this.color = new Color(0, 0, 200, 255);
        this.type = "ATTACK";

        calcCorrectPosition(this.pos);
    }
}
