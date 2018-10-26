/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.frank;

import de.red6.gameoflife.runner.Board;

/**
 * Eine Implementation, die auf einem zweidimensionalen Array von {@code boolean}s basiert.
 * <p>Im Gegensatz zu der straight-forward-Implementierung hat hier das Array zwei Zeilen und zwei Spalten
 * mehr als angefordert, so dass man sich bei der Nachbarbestimmung die Überprüfung des Indexes sparen kann.</p>
 *
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class EnlargedBooleaArrayBoard implements Board {
    private int size;
    private boolean[][] cells;

    @Override
    public void init(final int size) {
        this.cells = new boolean[size + 2][size + 2];
        this.size = size;
    }

    @Override
    public void shutdown() {
        // noop
    }

    @Override
    public void setAlive(final int x, final int y) {
        this.cells[x + 1][y + 1] = true;
    }

    @Override
    public boolean isAlive(final int x, final int y) {
        return this.cells[x + 1][y + 1];
    }

    @Override
    public void step() {
        final boolean[][] newCells = new boolean[size + 2][size + 2];
        for (int x = 1; x <= size; ++x) {
            for (int y = 1; y <= size; ++y) {
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

    private int neighbours(final int xPlusOne, final int yPlusOne) {
        return activeCount(xPlusOne - 1, yPlusOne - 1)
                + activeCount(xPlusOne - 1, yPlusOne)
                + activeCount(xPlusOne - 1, yPlusOne + 1)
                + activeCount(xPlusOne, yPlusOne - 1)
                + activeCount(xPlusOne, yPlusOne + 1)
                + activeCount(xPlusOne + 1, yPlusOne - 1)
                + activeCount(xPlusOne + 1, yPlusOne)
                + activeCount(xPlusOne + 1, yPlusOne + 1);
    }

    private int activeCount(final int xPlusOne, final int yPlusOne) {
        return cells[xPlusOne][yPlusOne] ? 1 : 0;
    }
}
