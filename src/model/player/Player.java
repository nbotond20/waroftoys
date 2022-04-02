package model.player;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.FormattableFlags;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleToIntFunction;

//import javax.swing.text.Position;

import model.Game;
import model.entity.Base;
import model.entity.tower.Tower;
import model.entity.unit.Unit;
import model.pathfinding.AStar;
import model.pathfinding.Cell;
import model.utility.Position;

public class Player {
    private final Game game;
    public String name;
    public ArrayList<Unit> units;
    private final ArrayList<Tower> towers;
    ArrayList<Position> unitSpawnpoints;
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

    public ArrayList<Position> getValidPositions(final Base base) {
        int[] basepos = base.getIndexFromPos(base.pos);
        ArrayList<Position> poslist = new ArrayList<Position>();
        ArrayList<Position> surrounding = new ArrayList<Position>();
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] + 1, basepos[1])));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] - 1, basepos[1])));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0], basepos[1] + 1)));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0], basepos[1] - 1)));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] + 1, basepos[1] + 1)));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] - 1, basepos[1] - 1)));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] + 1, basepos[1] - 1)));
        surrounding.add(new Position(base.getPosFromIndex(basepos[0] - 1, basepos[1] + 1)));
        //System.out.println(name);
        for (Position p : surrounding) {
            int[] indexes = base.getIndexFromPos(p);
            if (!game.tileM.grid[indexes[0]][indexes[1]].collision) {
                poslist.add(p);
                System.out.println("Valid // x: " + p.x + ", y: " + p.y);
            }
        }

        return poslist;
    }

    public void addUnit(final Unit unit) {
        int[] i = unit.getIndexFromPos(game.hoverPosition);
        Random rand = new Random();
        for (Position p : unitSpawnpoints) {
            System.out.println(("Position // x: " + p.x + ", y:" + p.y));
        }
        if (balance > unit.cost && !game.tileM.grid[i[0]][i[1]].collision) {
            System.out.println("plist size: " + unitSpawnpoints.size());
            if(unitSpawnpoints.size() != 0){
                this.balance -= unit.cost;
                Collections.shuffle(unitSpawnpoints);
                unit.pos = unitSpawnpoints.get(0);
                unitSpawnpoints.remove(0);
                unit.calcCorrectPosition(unit.pos);
                if (game.activePlayer == 0) {
                    unit.setEnemyBasePos(game.players.get(1).base.pos);
                } else {
                    unit.setEnemyBasePos(game.players.get(0).base.pos);
                }
                unit.updatePath();
                units.add(unit);
            }
            else{
                unitSpawnpoints = getValidPositions(this.base);
            }
        }
    }

    public boolean checkBaseRoute(int[] tpos){
        boolean l = true;
        int[][] blockarray = new int[game.tileM.blocks.length+1][2];
        for (int i = 0; i < game.tileM.blocks.length; i++) {
            blockarray[i] = game.tileM.blocks[i];
        }
        blockarray[game.tileM.blocks.length] = tpos;
        int[] startInd = base.getIndexFromPos(game.players.get(0).base.pos);
        final int[] destInd = base.getIndexFromPos(game.players.get(1).base.pos);
        final AStar aStar = new AStar(game.maxScreenRow, game.maxScreenCol, startInd[0], startInd[1], destInd[0],
                destInd[1], blockarray);
        aStar.process();
        ArrayList<Cell> destCells = aStar.displaySolution(false);
        if(destCells == null || destCells.size() == 0){
            l = false;
        }

        return l;
    }

    public boolean checkSide(){
        boolean l = true;
        int half = 0;
        if (base.pos.x < (game.maxScreenCol * game.tileSize) / 2) {
            half = 0;
        } else {
            half = 1;
        }
        if ((half == 0 && game.hoverPosition.x >= (game.maxScreenCol * game.tileSize) / 2)) {
            l = false;
        }
        if ((half == 1 && game.hoverPosition.x < (game.maxScreenCol * game.tileSize) / 2)) {
            l = false;
        }

        return l;
    }

    public void addTower(final Tower tower) {
        int[] i = tower.getIndexFromPos(game.hoverPosition);
        if(checkSide() == false || checkBaseRoute(i) == false){
            return;
        }
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
        this.unitSpawnpoints = getValidPositions(this.base);
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
