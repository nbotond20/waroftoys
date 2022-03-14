package model.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.Game;

public class MouseMovementHandler implements MouseMotionListener {
    public Position p;
    private Game game;
    public MouseMovementHandler(Game game){
        this.game = game;
        p = new Position();
    }

    private Position getPosFromIndex(int[] indexes) {
        return new Position(indexes[1] * game.tileSize, indexes[0] * game.tileSize);
    }

    private int[] getIndexFromPos(Position pos) {
        int[] arr = { (int) (pos.y / game.tileSize), (int) (pos.x / game.tileSize) };
        return arr;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /* System.out.println("mouse is dragged to point " + e.getX() + " " + e.getY());  */
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        p.x = e.getX();
        p.y = e.getY();
        game.hoverPosition = getPosFromIndex(getIndexFromPos(p));
        /* System.out.println("mouse is moved to point " + e.getX() + " " + e.getY());  */
    }
}
