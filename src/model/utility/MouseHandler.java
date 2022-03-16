package model.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.entity.tower.Tower;
import model.entity.tower.TowerType1;
import model.entity.tower.TowerType2;
import model.entity.tower.TowerType3;
import model.entity.unit.Unit;
import model.entity.unit.UnitType1;
import model.entity.unit.UnitType2;
import model.entity.unit.UnitType3;

public class MouseHandler implements MouseListener {
    public boolean isClicked = false;
    public Position pos = new Position();
    private Game game;

    public MouseHandler(Game game) {
        this.game = game;
    };

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = true;
        /* System.out.println("Clicked at: (X: "+pos.x+", Y: "+pos.y+")"); */
        pos.x = (int) e.getPoint().getX();
        pos.y = (int) e.getPoint().getY();

        switch (game.selectedButtonNum) {
            case 0:
                Tower t1 = new TowerType1(new Position(), game, this);
                game.players.get(game.activePlayer).addTower(t1);
                break;
            case 1:
                Tower t2 = new TowerType2(new Position(), game, this);
                game.players.get(game.activePlayer).addTower(t2);
                break;
            case 2:
                Tower t3 = new TowerType3(new Position(), game, this);
                game.players.get(game.activePlayer).addTower(t3);
                break;
            case 3:
                Unit u1 = new UnitType1(new Position(), game, this);
                game.players.get(game.activePlayer).addUnit(u1);
                break;
            case 4:
                Unit u2 = new UnitType2(new Position(), game, this);
                game.players.get(game.activePlayer).addUnit(u2);
                break;
            case 5:
                Unit u3 = new UnitType3(new Position(), game, this);
                game.players.get(game.activePlayer).addUnit(u3);
                break;
        }
        game.selectedPosition = game.hoverPosition;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
