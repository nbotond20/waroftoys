package com.runtimeexception.waroftoys.model.entity;

import com.runtimeexception.waroftoys.model.entity.unit.*;
import com.runtimeexception.waroftoys.model.utility.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.runtimeexception.waroftoys.model.Game;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class BaseTest {
    Unit unit;
    Game game = new Game("", "");

    @Test
    @DisplayName("Set Enemy Base Position Test")
    public void setEnemyBasePosTest() {
        Base base = new Base(new Position(), game);

        assertEquals(base.pos.x, 0);
        assertEquals(base.pos.y, 0);

        base = new Base(new Position(game.tileSize + 1, game.tileSize + 1), game);
        assertEquals(base.pos.x, game.tileSize);
        assertEquals(base.pos.y, game.tileSize);
    }
}
