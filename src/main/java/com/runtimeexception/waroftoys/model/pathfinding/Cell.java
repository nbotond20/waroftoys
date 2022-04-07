package com.runtimeexception.waroftoys.model.pathfinding;

public class Cell {
    // Cordinates
    public int i,j;
    // Parent Cell for path
    public Cell parent;
    // Heuristic cost of the current cell
    public int heuristicCost;
    // Final cost
    public int finalCost; // G + H with
    // G(n) the cost of the path from the start node to n
    // and H(n) the heuristic that estimates the cost of the cheapest path from n to the destination
    public boolean solution; // if cell is part of the solution path

    public Cell(final int i, final int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return "[" + i + ", "+j+"]";
    }


}
