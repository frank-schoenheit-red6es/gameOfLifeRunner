package benjamin;

import java.util.HashSet;
import java.util.Set;

public class Board implements de.red6.gameoflife.runner.Board {

    private Set<Cell> livingCells;
    private int size;

    public void init(int size) {
        this.livingCells = new HashSet<Cell>();
        this.size = size;
    }

    @Override
    public void shutdown() {
        // noop
    }

    public void setAlive(int x, int y) {
        livingCells.add(new Cell(x, y));
    }

    public boolean isAlive(int x, int y) {
        return livingCells.contains(new Cell(x, y));
    }

    public void step() {
        Set<Cell> newLivingCells = new HashSet<Cell>();
        for(Cell testingCell : livingCells) {
            if (cellCanStayTrue(testingCell)) {
                newLivingCells.add(testingCell);
            }
            for (int x = testingCell.getX() - 1; x <= (testingCell.getX() + 1); x++) {
                for (int y = testingCell.getY() - 1; y <= (testingCell.getY() + 1); y++) {
                    if (x != testingCell.getX() && y != testingCell.getY()) {
                        if (livingCellsAround(new Cell(x, y)) == 3) {
                            newLivingCells.add(testingCell);
                        }
                    }
                }
            }
        }
        this.livingCells = newLivingCells;
    }

    private int livingCellsAround(Cell cell) {
        int countLivingCells = 0;
        for (int x = cell.getX() - 1; x <= (cell.getX() + 1); x++) {
            for (int y = cell.getY() - 1; y <= (cell.getY() + 1); y++) {
                if (x != cell.getX() && y != cell.getY()) {
                    if (livingCells.contains(new Cell(x, y))) {
                        countLivingCells++;
                    }
                }
            }
        }
        return countLivingCells;
    }

    private boolean cellCanStayTrue(Cell cell) {
        int livingCellsCount = livingCellsAround(cell);
        if (livingCellsCount < 2 || livingCellsCount > 3) {
            return false;
        } else {
            return true;
        }
    }
}
