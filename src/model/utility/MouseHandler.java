package model.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener{
    public boolean isClicked = false;
    public Position pos = new Position();
    
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
        pos.x = (int)e.getPoint().getX();
        pos.y = (int)e.getPoint().getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
