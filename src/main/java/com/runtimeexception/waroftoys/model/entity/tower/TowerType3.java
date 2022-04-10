package com.runtimeexception.waroftoys.model.entity.tower;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class TowerType3 extends Tower {
    public TowerType3(final Position pos, final Game gp, final MouseHandler mouseH) {
        super(gp, mouseH);
        getTowerImage("stone");

        this.pos = pos;
        this.health = 3000;
        this.MAX_HEALTH = 3000;
        this.range = 300;
        this.cost = 500;
        this.color = new Color(200, 0, 0, 255);
        this.type = "SLOWER";

        calcCorrectPosition(this.pos);
    }
}
