package com.runtimeexception.waroftoys.model.entity.unit;

import static org.junit.Assert.*;
import com.runtimeexception.waroftoys.model.utility.Position;

public class PathfindingTest {
    @Test
    public void testNoRoute(){
        Boolean route = true;
        Position start = new Position(0,0);
        Position end = new Position(0,2);
        final int[][] temp = new int[1][2];
        temp[0][0] = 0;
        temp[0][1] = 1;
        final AStar aStar = new AStar(3, 1, start.x, start.y, end.x, 
        end.y, temp);
        aStar.process();
        final ArrayList<Cell> destCells = aStar.displaySolution(false);
        if (destCells == null || destCells.size() == 0) {
            route = false;
        }

        // as there should be no route, assertFalse(route) should work.
        assertFalse(route);
    }

    public void testShortRoute(){
        
    }
}