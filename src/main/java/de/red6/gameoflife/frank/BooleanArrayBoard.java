/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.frank;

import de.red6.gameoflife.runner.Board;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class BooleanArrayBoard implements Board {
    private int size;
    private boolean[][] cells;

    @Override
    public void init(final int size) {
        this.cells = new boolean[size][size];
        this.size = size;
    }

    @Override
    public void setAlive(final int x, final int y) {
        this.cells[x][y] = true;
    }

    @Override
    public boolean isAlive(final int x, final int y) {
        return this.cells[x][y];
    }

    @Override
    public void step() {
        final boolean[][] newCells = new boolean[size][size];
        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                final int neighbours = neighbours(x, y);
                if (cells[x][y]) {
                    newCells[x][y] = neighbours == 2 || neighbours == 3;
                } else {
                    newCells[x][y] = neighbours == 3;
                }
            }
        }
        this.cells = newCells;
    }

    private int neighbours(final int x, final int y) {
        return activeCount(x - 1, y - 1)
                + activeCount(x - 1, y)
                + activeCount(x - 1, y + 1)
                + activeCount(x, y - 1)
                + activeCount(x, y + 1)
                + activeCount(x + 1, y - 1)
                + activeCount(x + 1, y)
                + activeCount(x + 1, y + 1);
    }

    private int activeCount(final int x, final int y) {
        if (inRange(x) && inRange(y))
            return cells[x][y] ? 1 : 0;
        return 0;
    }

    private boolean inRange(final int c) {
        return c >= 0 && c < size;
    }
}
