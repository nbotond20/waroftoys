package model.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.Game;

public class MouseMovementHandler implements MouseMotionListener {
    public Position pos;
    private final Game game;
    public MouseMovementHandler(final Game game){
        this.game = game;
        pos = new Position();
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
        /* System.out.println("mouse is dragged to point " + e.getX() + " " + e.getY());  */
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        pos.x = e.getX();
        pos.y = e.getY();
        game.hoverPosition = getPosFromIndex(getIndexFromPos(pos));
        /* System.out.println("mouse is moved to point " + e.getX() + " " + e.getY());  */
    }
}
