package com.runtimeexception.waroftoys.model.pathfinding;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

public class PathfindingTest {
    @Test
    public void testNoRoute(){
        Boolean route = true;
        final AStar aStar = new AStar(1, 3, 0, 0, 0, 2, new int [][] {{0, 1}});
        aStar.process();
        final ArrayList<Cell> destCells = aStar.displaySolution(false);
        if (destCells == null || destCells.size() == 0) {
            route = false;
        }

        // as there should be no route, assertFalse(route) should work.
        assertFalse(route);
    }

    @Test
    public void testShortRoute(){
            Boolean route = true;
            final AStar aStar = new AStar(1, 3, 0, 0, 0, 2, new int [][]{});
            aStar.process();
            final ArrayList<Cell> destCells = aStar.displaySolution(false);
            if (destCells == null || destCells.size() == 0) {
                route = false;
            }

            // as there should be no route, assertFalse(route) should work.
            assertTrue(route);
            assertEquals(destCells.size(), 3);
    }

    @Test
    public void testNormalRoute(){
        Boolean route = true;
        final AStar aStar = new AStar(5, 5, 1, 1, 4, 3, new int [][]{});
        aStar.process();
        final ArrayList<Cell> destCells = aStar.displaySolution(false);
        if (destCells == null || destCells.size() == 0) {
            route = false;
        }

        // as there should be no route, assertFalse(route) should work.
        assertTrue(route);
        assertEquals(destCells.size(), 6);
    }
    @Test
    public void testBlockyRoute(){
        Boolean route = true;
        final AStar aStar = new AStar(5, 5, 2, 1, 0, 4, new int [][]{{0,2},{1,2},{2,2},{3,2}});
        aStar.process();
        final ArrayList<Cell> destCells = aStar.displaySolution(false);
        if (destCells == null || destCells.size() == 0) {
            route = false;
        }

        // as there should be no route, assertFalse(route) should work.
        assertTrue(route);
        assertEquals(destCells.size(), 10);
    }
}