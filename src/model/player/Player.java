package model.player;

import java.util.ArrayList;
import java.awt.Graphics2D;
import model.entity.unit.*;
import model.entity.tower.*;
import model.Game;
import model.entity.Base;


public class Player { 
    private Game game;
    public String name;
    private ArrayList<Unit> units;
    private ArrayList<Tower> towers;
    private Base base;
    public double balance = 25000;

    public Player(String name, Game game){
        this.name = name;
        this.game = game;
        this.units = new ArrayList<>();
        this.towers = new ArrayList<>();
    }

    public void addUnit(Unit unit){
        if(game.selectedPosition != null && balance > unit.cost){
            this.balance-= unit.cost;
            unit.pos = game.hoverPosition;
            unit.calcCorrectPosition(unit.pos);
            units.add(unit);
        }
    }

    public void addTower(Tower tower){
        if(game.selectedPosition != null && balance > tower.cost){
            this.balance-= tower.cost;
            tower.pos = game.hoverPosition;
            towers.add(tower);
        }
    }

    public void setBase(Base base){
        this.base = base;
    }

    public void draw(Graphics2D g2){
        base.draw(g2);

        for(Tower t : towers) {
            t.draw(g2);
        }

        for (Unit p : units) {
            p.draw(g2);
        }
    }

    public void update(){
        for(Unit u : units) {
            u.update();
        }
    }
}
