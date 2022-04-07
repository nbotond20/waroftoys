package com.runtimeexception.waroftoys.model.pathfinding;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class PathfindingTest {
    @Test
    public void testNoRoute(){
        Boolean route = true;
        final AStar aStar = new AStar(3, 2, 0, 0, 0, 2, new int [][] {{0, 1}});
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