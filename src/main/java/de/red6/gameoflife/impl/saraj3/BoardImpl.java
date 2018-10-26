package de.red6.gameoflife.impl.saraj3;

import de.red6.gameoflife.runner.Board;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Set;

public class BoardImpl implements Board {
    Set<Cell> aliveCells = new HashSet<>();
    Set<Cell> aliveCellsCache = new HashSet<>();
    Set<AbstractMap.SimpleEntry> points = new HashSet<>();
    private int size;


    @Override
    public void init(int x) {
        size = x;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                points.add(new AbstractMap.SimpleEntry<Integer, Integer>(i, j));
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
        points.stream()
                .parallel()
                .forEach(simpleEntry -> {
                    final Cell cell = new Cell((int) simpleEntry.getKey(), (int) simpleEntry.getValue());
                    int numberOfAliveNeighbours = getNumberOfAliveNeighbours(cell);
                    boolean alive = aliveCells.contains(cell);
                    boolean aliveInNextStep = isAliveInNextStep(numberOfAliveNeighbours, alive);
                    if (aliveInNextStep) {
                        aliveCellsCache.add(cell);
                    }
                });
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
                .filter(neighbours::contains)
                .count();
    }

    Set<Cell> computeNeighbours(final Cell cell) {
        Cell cell1 = new Cell(cell.getX()-1, cell.getY()-1);
        Cell cell2 = new Cell(cell.getX()-1, cell.getY());
        Cell cell3 = new Cell(cell.getX()-1, cell.getY()+1);
        Cell cell4 = new Cell(cell.getX(), cell.getY()-1);
        Cell cell5 = new Cell(cell.getX(), cell.getY()+1);
        Cell cell6 = new Cell(cell.getX()+1, cell.getY()-1);
        Cell cell7 = new Cell(cell.getX()+1, cell.getY());
        Cell cell8 = new Cell(cell.getX()+1, cell.getY()+1);

        Set<Cell> cells = new HashSet<>();
        cells.add(cell1);
        cells.add(cell2);
        cells.add(cell3);
        cells.add(cell4);
        cells.add(cell5);
        cells.add(cell6);
        cells.add(cell7);
        cells.add(cell8);
        return cells;
    }
}
