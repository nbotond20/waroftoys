package model.player;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.entity.Base;
import model.entity.tower.Tower;
import model.entity.unit.Unit;

public class Player {
    private final Game game;
    public String name;
    public ArrayList<Unit> units;
    private final ArrayList<Tower> towers;
    public Base base;
    public double balance = 25000;

    public ArrayList<Unit> unitDone;

    public Player(final String name, final Game game, double balance) {
        this.name = name;
        this.game = game;
        this.balance = balance;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
        this.unitDone = new ArrayList<>();
    }

    public void checkRange(Player enemy) {
        // ArrayList<Unit> delete = new ArrayList<Unit>();
        for (Tower tower : this.towers) {

            for (Unit unit : this.units) { // own unit is in range of own tower
                if (tower.isInRange(unit))
                    tower.useAbility(unit, true, enemy);
            }
            for (Unit unit : enemy.units) { // enemy unit is in range of own tower
                if (tower.isInRange(unit))
                    tower.useAbility(unit, false, enemy);
                // delete.add(unit);

            }
        }
        // enemy.units = delete;
    }

    public void addUnit(final Unit unit) {
        int[] i = unit.getIndexFromPos(game.hoverPosition);
        if (balance > unit.cost && !game.tileM.grid[i[0]][i[1]].collision) {
            this.balance -= unit.cost;
            unit.pos = game.hoverPosition;
            unit.calcCorrectPosition(unit.pos);
            if (game.activePlayer == 0) {
                unit.setEnemyBasePos(game.players.get(1).base.pos);
            } else {
                unit.setEnemyBasePos(game.players.get(0).base.pos);
            }
            unit.updatePath();
            units.add(unit);
        }
    }

    public void addTower(final Tower tower) {
        int[] i = tower.getIndexFromPos(game.hoverPosition);
        if (balance > tower.cost && !game.tileM.grid[i[0]][i[1]].collision) {
            this.balance -= tower.cost;
            tower.pos = game.hoverPosition;
            towers.add(tower);
            game.tileM.grid[i[0]][i[1]].collision = true;
            game.tileM.addToBlocks(i);
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

        for (Unit u : unitDone) {
            this.units.remove(u);
        }
        unitDone.clear();
    }
}
