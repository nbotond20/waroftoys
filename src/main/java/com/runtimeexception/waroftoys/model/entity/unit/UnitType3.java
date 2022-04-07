package com.runtimeexception.waroftoys.model.entity.unit;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.Direction;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class UnitType3 extends Unit {
    public UnitType3(final Position pos, final Game gp, final MouseHandler mouseH, final int playerNum) {
        super(gp, mouseH, playerNum);
        this.speed = 1.5;
        this.REGULAR_SPEED = 1.5;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 50;
        this.MAX_HEALTH = 50;
        this.color = new Color(200, 0, 0, 255);
        this.cost = 3000;
        this.damage = 50;
        getPlayerImage("unit-type1");
    }
}
