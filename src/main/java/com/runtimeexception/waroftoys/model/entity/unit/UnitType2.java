package com.runtimeexception.waroftoys.model.entity.unit;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.Direction;
import com.runtimeexception.waroftoys.model.utility.MouseHandler;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;

public class UnitType2 extends Unit {
    public UnitType2(final Position pos, final Game gp, final MouseHandler mouseH, final int playerNum) {
        super(gp, mouseH, playerNum);
        this.speed = 0.5;
        this.REGULAR_SPEED = 0.5;
        this.pos = pos;
        calcCorrectPosition(this.pos);
        this.dir = Direction.DOWN;
        this.health = 1500;
        this.MAX_HEALTH = 1500;
        this.color = new Color(0, 200, 0, 255);
        this.cost = 2000;
        this.damage = 40;
        getPlayerImage("unit-type1");
    }
}