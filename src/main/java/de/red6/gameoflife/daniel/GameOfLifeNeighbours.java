package de.red6.gameoflife.daniel;

import de.red6.gameoflife.runner.Board;

public class GameOfLifeNeighbours implements Board {

    private boolean[][] grid;
    private int dimension;

    public void init(int size) {
        dimension = size;
        grid = new boolean[dimension][dimension];
    }

    @Override
    public void shutdown() {
        // noop
    }

    public void setAlive(int x, int y) {
        grid[x][y] = true;
    }

    public boolean isAlive(int x, int y) {
        return grid[x][y];
    }

    public void step() {
        boolean[][] iteratedGrid = new boolean[dimension][dimension];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                iteratedGrid[i][j] = evaluateCellState(i, j, grid[i][j]);
            }
        }
        grid = iteratedGrid;
    }

    private boolean evaluateCellState(int x, int y, boolean oldState) {
        int neighbours = countNeighboursFor(x, y);
        if (neighbours<2 || neighbours>3) {
            return false;
        } else {
            return neighbours != 2 || oldState;
        }
    }

    private int countNeighboursFor(int x, int y) {
        int neighbourCount = 0;
        for (int i = x-1; i<=x+1; i++) {
            for (int j = y-1; j<=y+1; j++) {
                if (isAliveNeighbour(i,j) && !(i == x && j==y)) {
                    neighbourCount += 1;
                }
            }
        }
        return neighbourCount;
    }

    private boolean isAliveNeighbour(int x, int y) {
        try {
            return grid[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j]) {
                    System.out.printf(String.format("%s","1"));
                } else {
                    System.out.printf(String.format("%s","0"));

                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

}
