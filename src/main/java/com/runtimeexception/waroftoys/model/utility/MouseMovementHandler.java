package com.runtimeexception.waroftoys.model.utility;

import com.runtimeexception.waroftoys.model.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMovementHandler implements MouseMotionListener {
    public Position pos;
    public Position dragPos;

    private final Game game;

    public MouseMovementHandler(final Game game) {
        this.game = game;
        this.pos = new Position();
        this.dragPos = new Position();
    }

    private Position getPosFromIndex(final int[] indexes) {
        return new Position(indexes[1] * game.tileSize, indexes[0] * game.tileSize);
    }

    private int[] getIndexFromPos(final Position pos) {
        final int[] arr = { (int) (pos.y / game.tileSize), (int) (pos.x / game.tileSize) };
        return arr;
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        /* System.out.println("Dragged: x:" + e.getX() + ", y: " + e.getY()); */
        dragPos.x = e.getX();
        dragPos.y = e.getY();
        final Position tempTile = getPosFromIndex(getIndexFromPos(dragPos));

        if (game.dragPosition == null) {
            game.dragPosition = tempTile;
            game.prevDragPosition = tempTile;
        }

        if (tempTile.x != game.dragPosition.x || tempTile.y != game.dragPosition.y) {
            game.prevDragPosition = game.dragPosition;
            game.dragPosition = tempTile;

            if (game.selectedUnit != null) {
                Position temp = new Position();
                Position temp2 = new Position();
                Position temp3 = null;
                for (final Position p : game.selectedUnit.destinations) {
                    temp = getPosFromIndex(getIndexFromPos(p));
                    final int[] indexes = getIndexFromPos(game.dragPosition);
                    if (temp.x == game.prevDragPosition.x && temp.y == game.prevDragPosition.y && indexes[0] >= 0
                            && indexes[0] < game.tileM.maxRowNumber && indexes[1] >= 0
                            && indexes[1] < game.tileM.maxColNumber
                            && !game.tileM.grid[indexes[0]][indexes[1]].collision) {
                        for (final Position p2 : game.selectedUnit.fixPositions) {
                            temp2 = getPosFromIndex(getIndexFromPos(p2));
                            if ((temp2.x == game.prevDragPosition.x && temp2.y == game.prevDragPosition.y)
                                    || temp2.x == game.dragPosition.x && temp2.y == game.dragPosition.y) {
                                temp3 = p2;
                            }
                        }
                        if (temp3 != null) {
                            game.selectedUnit.fixPositions.remove(temp3);
                        }
                        game.selectedUnit.addFixPosition(game.dragPosition);
                    }
                }
                game.selectedUnit.updatePath();
            }
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        pos.x = e.getX();
        pos.y = e.getY();
        game.hoverPosition = getPosFromIndex(getIndexFromPos(pos));

        dragPos.x = -1;
        dragPos.y = -1;
        game.dragPosition = null;
        game.prevDragPosition = null;
        /*
         * System.out.println("mouse is moved to point " + e.getX() + " " + e.getY());
         */
    }
}
