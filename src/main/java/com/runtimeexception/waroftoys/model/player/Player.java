package com.runtimeexception.waroftoys.model.player;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.entity.Base;
import com.runtimeexception.waroftoys.model.entity.tower.Tower;
import com.runtimeexception.waroftoys.model.entity.unit.Unit;
import com.runtimeexception.waroftoys.model.pathfinding.AStar;
import com.runtimeexception.waroftoys.model.pathfinding.Cell;
import com.runtimeexception.waroftoys.model.utility.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private final Game game;
    public String name;
    public ArrayList<Unit> units;
    private final ArrayList<Tower> towers;
    public Base base;
    public double balance = 25000;

    public ArrayList<Unit> unitDone;
    public ArrayList<int[]> availableIndexes;

    public Player(final String name, final Game game, final double balance) {
        this.name = name;
        this.game = game;
        this.balance = balance;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.unitDone = new ArrayList<>();
    }

    public int[] getIndexFromPos(final Position pos) {
        final int[] arr = { (int) (pos.y / game.tileSize), (int) (pos.x / game.tileSize) };
        return arr;
    }

    public void setAvailableFields() {
        availableIndexes = new ArrayList<>();
        availableIndexes.clear();
        final int[] indexes = getIndexFromPos(base.pos);

        if (!game.tileM.grid[indexes[0] - 1][indexes[1] - 1].collision) { // TOP LEFT
            availableIndexes.add(new int[] { indexes[0] - 1, indexes[1] - 1 });
        }
        if (!game.tileM.grid[indexes[0] - 1][indexes[1]].collision) { // MIDDLE LEFT
            availableIndexes.add(new int[] { indexes[0] - 1, indexes[1] });
        }
        if (!game.tileM.grid[indexes[0] - 1][indexes[1] + 1].collision) { // BOTTOM LEFT
            availableIndexes.add(new int[] { indexes[0] - 1, indexes[1] + 1 });
        }
        if (!game.tileM.grid[indexes[0]][indexes[1] - 1].collision) { // TOP MIDDLE
            availableIndexes.add(new int[] { indexes[0], indexes[1] - 1 });
        }
        if (!game.tileM.grid[indexes[0]][indexes[1] + 1].collision) { // BOTTOM MIDDLE
            availableIndexes.add(new int[] { indexes[0], indexes[1] + 1 });
        }
        if (!game.tileM.grid[indexes[0] + 1][indexes[1] - 1].collision) { // TOP RIGHT
            availableIndexes.add(new int[] { indexes[0] + 1, indexes[1] - 1 });
        }
        if (!game.tileM.grid[indexes[0] + 1][indexes[1]].collision) { // MIDDLE RIGHT
            availableIndexes.add(new int[] { indexes[0] + 1, indexes[1] });
        }
        if (!game.tileM.grid[indexes[0] + 1][indexes[1] + 1].collision) { // BOTTOM RIGHT
            availableIndexes.add(new int[] { indexes[0] + 1, indexes[1] + 1 });
        }

        Collections.shuffle(availableIndexes);
    }

    public void earnMoney(final Player enemy) {
        this.balance += enemy.unitDone.get(enemy.unitDone.size() - 1).cost / 2; // player gets half of the cost of a
                                                                                // dead unit
    }

    public void checkRange(final Player enemy) {
        // ArrayList<Unit> delete = new ArrayList<Unit>();
        for (final Tower tower : this.towers) {

            for (final Unit unit : this.units) { // own unit is in range of own tower
                if (tower.isInRange(unit)) {
                    tower.useAbility(unit, true, enemy);
                }
            }
            for (final Unit unit : enemy.units) { // enemy unit is in range of own tower
                if (tower.isInRange(unit)) {
                    if (tower.useAbility(unit, false, enemy))
                        this.earnMoney(enemy);
                }
                // delete.add(unit);
            }
        }
        // enemy.units = delete;
    }

    public void addUnit(final Unit unit) {
        if (balance > unit.cost) {
            this.balance -= unit.cost;
            if (availableIndexes.size() == 0) {
                setAvailableFields();
            }
            unit.pos = unit.getPosFromIndex(availableIndexes.get(0));
            availableIndexes.remove(0);
            unit.calcCorrectPosition(unit.pos);
            if (game.activePlayer == 0) {
                unit.setEnemyBasePos(game.players.get(1).base.pos);
            } else {
                unit.setEnemyBasePos(game.players.get(0).base.pos);
            }
            unit.updatePath();
            units.add(unit);

            game.updateAllTileOffsets();
        }
    }

    public void addTower(final Tower tower) {
        final int[] i = tower.getIndexFromPos(game.hoverPosition);

        final int[][] temp = new int[game.tileM.blocks.length + 1][2];
        for (int j = 0; j < game.tileM.blocks.length; j++) {
            temp[j] = game.tileM.blocks[j];
        }
        temp[game.tileM.blocks.length] = i;
        final int[] startInd = tower.getIndexFromPos(game.players.get(0).base.pos);
        final int[] destInd = tower.getIndexFromPos(game.players.get(1).base.pos);
        final AStar aStar = new AStar(game.maxScreenRow, game.maxScreenCol, startInd[0], startInd[1], destInd[0],
                destInd[1], temp);
        aStar.process();
        final ArrayList<Cell> destCells = aStar.displaySolution(false);
        if (destCells == null || destCells.size() == 0) {
            return;
        }

        int half = 0;
        if (base.pos.x < (game.maxScreenCol * game.tileSize) / 2) {
            half = 0;
        } else {
            half = 1;
        }
        if ((half == 0 && game.hoverPosition.x >= (game.maxScreenCol * game.tileSize) / 2)) {
            return;
        }
        if ((half == 1 && game.hoverPosition.x < (game.maxScreenCol * game.tileSize) / 2)) {
            return;
        }

        if (balance > tower.cost && !game.tileM.grid[i[0]][i[1]].collision) {
            this.balance -= tower.cost;
            tower.pos = game.hoverPosition;
            tower.getTowerImage(tower.img);
            towers.add(tower);
            game.tileM.grid[i[0]][i[1]].collision = true;
            game.tileM.addToBlocks(i);
        }

        for (final Unit u : game.players.get(0).units) {
            u.updatePath();
        }
        for (final Unit u : game.players.get(1).units) {
            u.updatePath();
        }
    }

    public void setBase(final Base base) {
        this.base = base;
    }

    public void draw(final Graphics2D g2) {
        base.draw(g2);

        for (final Tower t : towers) {
            t.draw(g2);
        }

        for (final Unit p : units) {
            p.draw(g2);
        }
    }

    public void update() {
        for (final Unit u : units) {
            u.update();
        }

        for (final Unit u : unitDone) {
            this.units.remove(u);
        }
        unitDone.clear();
    }
}
