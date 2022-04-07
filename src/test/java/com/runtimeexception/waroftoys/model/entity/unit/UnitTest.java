package com.runtimeexception.waroftoys.model.entity.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.utility.Position;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class UnitTest {
    Unit unit;
    Game game = new Game("", "");

    @Test
    @DisplayName("UnitType1 Class Test")
    public void unit1ClassTest() {
        unit = new UnitType1(new Position(), game, game.mouseH, 0);
        assertEquals(unit.speed, 1);
        assertEquals(unit.health, 100);
        assertEquals(unit.MAX_HEALTH, 100);
        assertEquals(unit.cost, 1000);
        assertEquals(unit.damage, 30);
    }

    @Test
    @DisplayName("UnitType2 Class Test")
    public void unit2ClassTest() {
        unit = new UnitType2(new Position(), game, game.mouseH, 0);
        assertEquals(unit.speed, 0.5);
        assertEquals(unit.health, 300);
        assertEquals(unit.MAX_HEALTH, 300);
        assertEquals(unit.cost, 2000);
        assertEquals(unit.damage, 40);
    }

    @Test
    @DisplayName("UnitType3 Class Test")
    public void unit3ClassTest() {
        unit = new UnitType3(new Position(), game, game.mouseH, 0);
        assertEquals(unit.speed, 1.5);
        assertEquals(unit.health, 50);
        assertEquals(unit.MAX_HEALTH, 50);
        assertEquals(unit.cost, 3000);
        assertEquals(unit.damage, 50);
    }

    @Test
    @DisplayName("Correct Position Calculation Test")
    public void unitCalcCorrectPositionTest() {
        unit = new UnitType1(new Position(), game, game.mouseH, 0);
        unit.calcCorrectPosition(unit.pos);
        assertEquals(unit.pos.x, 0 * game.tileSize + (game.tileSize / 2) - unit.width / 2);
        assertEquals(unit.pos.y, 0 * game.tileSize + (game.tileSize / 2) - unit.height / 2);

        unit.pos = new Position(2 * game.tileSize, 2 * game.tileSize);
        unit.calcCorrectPosition(unit.pos);
        assertEquals(unit.pos.x, 2 * game.tileSize + (game.tileSize / 2) - unit.width / 2);
        assertEquals(unit.pos.y, 2 * game.tileSize + (game.tileSize / 2) - unit.height / 2);
    }

    @Test
    @DisplayName("Unit Move Test")
    public void unitMoveTest() {
        unit = new UnitType1(new Position(), game, game.mouseH, 0);
        unit.pos = new Position(0, 0);
        assertEquals(unit.pos.x, 0);
        assertEquals(unit.pos.y, 0);

        unit.move(new Position(1, 1));
        assertEquals(unit.pos.x, 1);
        assertEquals(unit.pos.y, 1);
    }

    @Test
    @DisplayName("Calculate Next Position Test")
    public void calculateNextPositionTest() {
        unit = new UnitType1(new Position(), game, game.mouseH, 0);
        unit.pos = new Position(0, 0);
        assertEquals(unit.pos.x, 0);
        assertEquals(unit.pos.y, 0);

        Position p = unit.calcNextPos(unit.pos, new Position(0, 100));
        assertEquals(p.x, 0);
        assertEquals(p.y, unit.speed);

        unit.pos = new Position(0, 0);
        p = unit.calcNextPos(unit.pos, new Position(100, 0));
        assertEquals(p.x, unit.speed);
        assertEquals(p.y, 0);
    }

    @Test
    @DisplayName("Set Enemy Base Position Test")
    public void setEnemyBasePosTest() {
        unit = new UnitType1(new Position(), game, game.mouseH, 0);
        unit.setEnemyBasePos(new Position(100, 100));
        assertEquals(unit.enemyBasePosition.x, 100);
        assertEquals(unit.enemyBasePosition.y, 100);
    }
}