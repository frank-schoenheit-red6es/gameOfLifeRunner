package de.red6.gameoflife.impl.saraj2;

import de.red6.gameoflife.runner.Board;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BoardImpl implements Board {
    Set<Cell> aliveCells = new HashSet<>();
    Set<Cell> aliveCellsCache = new HashSet<>();
    Set<Cell> cells = new HashSet<>();
    private int size;


    @Override
    public void init(int x) {
        size = x;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells.add(new Cell(i, j));
            }
        }
    }

    @Override
    public void shutdown() {
        // noop
    }

    @Override
    public void setAlive(int x, int y) {
        final Cell cell = new Cell(x, y);
        aliveCells.add(cell);
    }

    @Override
    public boolean isAlive(int x, int y) {
        return aliveCells.contains(new Cell(x, y));
    }

    @Override
    public void step() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Cell cell = new Cell(i, j);
                int numberOfAliveNeighbours = getNumberOfAliveNeighbours(cell);
                boolean alive = aliveCells.contains(cell);
                boolean aliveInNextStep = isAliveInNextStep(numberOfAliveNeighbours, alive);
                if (aliveInNextStep) {
                    aliveCellsCache.add(cell);
                }
            }
        }
        aliveCells = aliveCellsCache;
        aliveCellsCache = new HashSet<>();
    }
    
    boolean isAliveInNextStep(int numberOfAliveNeighbours, boolean alive) {
        if (alive && numberOfAliveNeighbours < 2) {
            return false;
        }
        if (alive && (numberOfAliveNeighbours == 2 || numberOfAliveNeighbours == 3)) {
            return true;
        }
        if (alive && numberOfAliveNeighbours > 3) {
            return false;
        }
        if (!alive && numberOfAliveNeighbours == 3) {
            return true;
        }
        return false;
    }

    int getNumberOfAliveNeighbours(Cell cell){
        Set<Cell> neighbours = computeNeighbours(cell);
        return (int) aliveCells.stream()
                .filter(currentCell -> neighbours.contains(currentCell))
                .count();
    }

    Set<Cell> computeNeighbours(final Cell cell) {
        return cells.stream()
                .filter(cell1 -> Math.abs(cell1.getX() - cell.getX()) <= 1)
                .filter(cell1 -> Math.abs(cell1.getY() - cell.getY()) <= 1)
                .filter(cell1 -> !cell.equals(cell1))
                .collect(Collectors.toSet());
    }
}
