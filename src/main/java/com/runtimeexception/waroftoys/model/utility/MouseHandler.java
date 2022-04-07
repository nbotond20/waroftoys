package com.runtimeexception.waroftoys.model.utility;

import com.runtimeexception.waroftoys.model.Game;
import com.runtimeexception.waroftoys.model.entity.tower.Tower;
import com.runtimeexception.waroftoys.model.entity.tower.TowerType1;
import com.runtimeexception.waroftoys.model.entity.tower.TowerType2;
import com.runtimeexception.waroftoys.model.entity.tower.TowerType3;
import com.runtimeexception.waroftoys.model.entity.unit.Unit;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {
    public Position pos = new Position();
    private final Game game;

    public MouseHandler(final Game game) {
        this.game = game;
    };

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        /* System.out.println("Clicked at: (X: "+pos.x+", Y: "+pos.y+")"); */
        pos.x = (int) e.getPoint().getX();
        pos.y = (int) e.getPoint().getY();

        if(SwingUtilities.isRightMouseButton(e)){
            boolean isSet = false;
            for(final Unit u : game.players.get(game.activePlayer).units){
                if(u.isInSameTile(pos)){
                    game.selectedUnit = u;
                    isSet = true;
                }
            }

            if(game.selectedUnit != null && game.selectedUnit.fixPositions != null){
                Position temp = null;
                for(final Position p : game.selectedUnit.fixPositions){
                    if((int) (p.x / game.tileSize) == (int) (pos.x / game.tileSize) && (int) (pos.y / game.tileSize) == (int) (p.y / game.tileSize)){
                        temp = p;
                    }
                }
                if(temp != null){
                    game.selectedUnit.fixPositions.remove(temp);
                    isSet = true;
                }
            }

            if(!isSet){
                game.selectedUnit = null;
            }
        }else{
            if(game.selectedUnit == null){
                switch (game.selectedButtonNum) {
                    case 0:
                        final Tower t1 = new TowerType1(new Position(), game, this);
                        game.players.get(game.activePlayer).addTower(t1);
                        break;
                    case 1:
                        final Tower t2 = new TowerType2(new Position(), game, this);
                        game.players.get(game.activePlayer).addTower(t2);
                        break;
                    case 2:
                        final Tower t3 = new TowerType3(new Position(), game, this);
                        game.players.get(game.activePlayer).addTower(t3);
                        break;
                }
            }
        }
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

}
