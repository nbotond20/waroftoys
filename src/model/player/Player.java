package model.player;

import java.awt.Graphics2D;
import java.util.ArrayList;

import model.Game;
import model.entity.Base;
import model.entity.tower.Tower;
import model.entity.unit.Unit;


public class Player { 
    private final Game game;
    public String name;
    private final ArrayList<Unit> units;
    private final ArrayList<Tower> towers;
    private Base base;
    public double balance = 25000;

    public Player(final String name, final Game game){
        this.name = name;
        this.game = game;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    public void addUnit(final Unit unit){
        if(game.selectedPosition != null && balance > unit.cost){
            this.balance-= unit.cost;
            unit.pos = game.hoverPosition;
            unit.calcCorrectPosition(unit.pos);
            units.add(unit);
        }
    }

    public void addTower(final Tower tower){
        if(game.selectedPosition != null && balance > tower.cost){
            this.balance-= tower.cost;
            tower.pos = game.hoverPosition;
            towers.add(tower);
        }
    }

    public void setBase(final Base base){
        this.base = base;
    }

    public void draw(final Graphics2D g2){
        base.draw(g2);

        for(final Tower t : towers) {
            t.draw(g2);
        }

        for (final Unit p : units) {
            p.draw(g2);
        }
    }

    public void update(){
        for(final Unit u : units) {
            u.update();
        }
    }
}
