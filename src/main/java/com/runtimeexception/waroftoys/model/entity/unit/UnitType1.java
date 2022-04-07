package com.runtimeexception.waroftoys.model.entity.unit;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.Direction;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class UnitType1 extends Unit {
    public UnitType1(final Position pos, final Game gp, final MouseHandler mouseH, final int playerNum) {
        super(gp, mouseH, playerNum);
        this.speed = 1;
        this.REGULAR_SPEED = 1;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 100;
        this.MAX_HEALTH = 100;
        this.color = new Color(0, 0, 200, 255);
        this.cost = 1000;
        this.damage = 30;
        getPlayerImage("unit-type1");
    }
}
