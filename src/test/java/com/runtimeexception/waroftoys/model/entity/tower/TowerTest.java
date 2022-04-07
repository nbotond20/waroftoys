package com.runtimeexception.waroftoys.model.entity.tower;

import static org.junit.jupiter.api.Assertions.*;

import com.runtimeexception.waroftoys.model.Game;

import com.runtimeexception.waroftoys.model.entity.unit.*;
import com.runtimeexception.waroftoys.model.player.Player;
import com.runtimeexception.waroftoys.model.utility.Position;
import org.junit.Test;

import java.util.Timer;

public class TowerTest {
    Game game;

    @Test
    public void unitInRangeTest(){
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType1(new Position(0,0), game, game.mouseH, 0);
        Tower attackTower = new TowerType1(new Position(0,2), game, game.mouseH);
        assertTrue(attackTower.isInRange(basicUnit));
    }

    @Test
    public void unitBarelyOutOfRangeTest(){ // barely out of range
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType1(new Position(0,106), game, game.mouseH, 0);
        Tower slowingTower = new TowerType2(new Position(0,5), game, game.mouseH);
        assertFalse(slowingTower.isInRange(basicUnit));
    }

    @Test
    public void unitDefinitelyOutOfRangeTest(){ // barely out of range
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType3(new Position(0,900), game, game.mouseH, 0);
        Tower speedupTower = new TowerType3(new Position(0,5), game, game.mouseH);
        assertFalse(speedupTower.isInRange(basicUnit));
    }

    @Test
    public void attackingTowerTest(){
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType1(new Position(0,50), game, game.mouseH, 0);
        Tower attackTower = new TowerType1(new Position(0,20), game, game.mouseH);
        assertTrue(attackTower.isInRange(basicUnit));
        double unitSpawnHealth = basicUnit.health;
        attackTower.useAbility(basicUnit, false, game.players.get(1));
        assertTrue(basicUnit.health < unitSpawnHealth); // attackTower attacked basicUnit, reducing it's health.
        basicUnit.pos = new Position(50, 550); // unit moves out of range
        assertFalse(attackTower.isInRange(basicUnit));
    }

    @Test
    public void speedupTowerTest(){
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType1(new Position(0,20), game, game.mouseH, 0);
        Tower speedupTower = new TowerType2(new Position(0,0), game, game.mouseH);
        assertTrue(speedupTower.isInRange(basicUnit));
        double unitSpawnSpeed = basicUnit.speed;
        speedupTower.useAbility(basicUnit, true, game.players.get(1));
        assertTrue(basicUnit.speed > unitSpawnSpeed);
        basicUnit.pos = new Position(50, 150);// unit moves out of range
        assertFalse(speedupTower.isInRange(basicUnit));
    }

    @Test
    public void slowingTowerTest(){
        game = new Game("name1", "name2");
        Unit basicUnit = new UnitType1(new Position(0,20), game, game.mouseH, 0);
        Tower slowingTower = new TowerType3(new Position(0,0), game, game.mouseH);
        assertTrue(slowingTower.isInRange(basicUnit));
        double unitSpawnSpeed = basicUnit.speed;
        slowingTower.useAbility(basicUnit, false, game.players.get(1));
        assertTrue(basicUnit.speed < unitSpawnSpeed);
        basicUnit.pos = new Position(50, 650);// unit moves out of range
        assertFalse(slowingTower.isInRange(basicUnit));
    }

}