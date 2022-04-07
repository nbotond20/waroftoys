package com.runtimeexception.waroftoys.model.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {

    // Cost for diagonal and vertical/horizontal moves
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;
    // Cells of our grid
    private Cell[][] grid;
    // Priority Queue for open Cells
    // Open Cells: the set of nodes to be evaluated (lowest cost first)
    private PriorityQueue<Cell> openCells;
    // Closed Cells: the set of nodes already evaluated
    public boolean[][] closedCells;
    // Start cell
    private int startI, startJ;
    // End cell
    private int endI, endJ;

    public AStar(final int width, final int height, final int si, final int sj, final int ei, final int ej,
                 final int[][] blocks) {
        grid = new Cell[width][height];
        closedCells = new boolean[width][height];
        openCells = new PriorityQueue<Cell>((final Cell c1, final Cell c2) -> {
            return c1.finalCost < c2.finalCost ? -1 : c1.finalCost > c2.finalCost ? 1 : 0;
        });

        startCell(si, sj);
        endCell(ei, ej);

        // init heuristic and cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i - endI) + Math.abs(j - endJ);
                grid[i][j].solution = false;
            }
        }

        grid[startI][startJ].finalCost = 0;

        for (int i = 0; i < blocks.length; i++) {
            addBlockOnCell(blocks[i][0], blocks[i][1]);
        }
    }

    public void addBlockOnCell(final int i, final int j) {
        if (i >= 0 && j >= 0) {
            grid[i][j] = null;
        }
    }

    public void startCell(final int i, final int j) {
        startI = i;
        startJ = j;
    }

    public void endCell(final int i, final int j) {
        endI = i;
        endJ = j;
    }

    public void updateCostIfNeeded(final Cell current, final Cell t, final int cost) {
        if (t == null || closedCells[t.i][t.j]) {
            return;
        }

        final int tFinalCost = t.heuristicCost + cost;
        final boolean isOpen = openCells.contains(t);

        if (!isOpen || tFinalCost < t.finalCost) {
            t.finalCost = tFinalCost;
            t.parent = current;

            if (!isOpen) {
                openCells.add(t);
            }
        }
    }

    public void process() {
        // Add start location to the open list
        if (grid[startI][startJ] == null) {
            System.out.println("Standing on a block!");
            return;
        }
        openCells.add(grid[startI][startJ]);
        Cell current;

        while (true) {
            current = openCells.poll();

            if (current == null) {
                break;
            }

            closedCells[current.i][current.j] = true;

            if (current.equals(grid[endI][endJ])) {
                return;
            }

            Cell t;

            if (current.i - 1 >= 0) {
                t = grid[current.i - 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

                /*
                 * if (current.j - 1 >= 0) {
                 * t = grid[current.i - 1][current.j - 1];
                 * updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                 * }
                 * if (current.j + 1 < grid[0].length) {
                 * t = grid[current.i - 1][current.j + 1];
                 * updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                 * }
                 */
            }

            if (current.j - 1 >= 0) {
                t = grid[current.i][current.j - 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            if (current.j + 1 < grid[0].length) {
                t = grid[current.i][current.j + 1];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);
            }

            if (current.i + 1 < grid.length) {
                t = grid[current.i + 1][current.j];
                updateCostIfNeeded(current, t, current.finalCost + V_H_COST);

                /*
                 * if (current.j - 1 >= 0) {
                 * t = grid[current.i + 1][current.j - 1];
                 * updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                 * }
                 *
                 * if (current.j + 1 < grid[0].length) {
                 * t = grid[current.i + 1][current.j + 1];
                 * updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                 * }
                 */
            }
        }
    }

    public void display() {
        System.out.println("Grid : ");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (i == startI && j == startJ) {
                    System.out.print("SO  "); // Source cell
                } else if (i == endI && j == endJ) {
                    System.out.print("DE  "); // Destination cell
                } else if (grid[i][j] != null) {
                    System.out.printf("%-3d ", 0); //
                } else {
                    System.out.print("BL  "); // Block cell
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void displayScores() {
        System.out.println("\nScores for cells : ");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.printf("%-3d ", grid[i][j].finalCost); //
                } else {
                    System.out.print("BL  "); //
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public ArrayList<Cell> displaySolution(final boolean b) {
        final ArrayList<Cell> result = new ArrayList<Cell>();
        if (closedCells[endI][endJ]) {
            // Track back the path
            Cell current = grid[endI][endJ];
            grid[current.i][current.j].solution = true;
            result.add(current);
            if (b) {
                System.out.print("Path : ");
                System.out.print(current);
            }
            while (current.parent != null) {
                if (b) {
                    System.out.print(" -> " + current.parent);
                }
                grid[current.parent.i][current.parent.j].solution = true;
                current = current.parent;
                result.add(current);
            }

            if (b) {
                System.out.println("\n");

                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        if (i == startI && j == startJ) {
                            System.out.print("SO  "); // Source cell
                        } else if (i == endI && j == endJ) {
                            System.out.print("DE  "); // Destination cell
                        } else if (grid[i][j] != null) {
                            System.out.printf("%-3s ", grid[i][j].solution ? "X" : "0"); //
                        } else {
                            System.out.print("BL  "); // Block cell
                        }
                    }
                    System.out.println();
                }
                System.out.println();
            }

            return result;
        } else {
            if (b) {
                System.out.println("No possible path");
            }
            return null;
        }
    }

    // For testing
    /*
     * public static void main(String[] args) {
     * AStar aStar = new AStar(9, 16, 5, 5, 3, 2,
     * new int[][] {
     * { 0, 4 }, { 2, 2 }, { 3, 1 }, { 3, 3 }, { 2, 1 }, { 2, 3 }
     * });
     *
     * aStar.display();
     * aStar.process(); // Apply A* Algorithm
     * aStar.displayScores(); // Display Scores on grid
     * aStar.displaySolution(true); // Display Solution
     * }
     */
}
