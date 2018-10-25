/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.frank;

import de.red6.gameoflife.runner.Board;

import java.util.BitSet;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class BitSetBoard implements Board {
    private int size;
    private BitSet[] rows;

    @Override
    public void init(final int size) {
        this.size = size;
        this.rows = createRows(size);
    }

    @Override
    public void setAlive(int x, int y) {
        this.rows[y].set(x);
    }

    @Override
    public boolean isAlive(int x, int y) {
        return this.rows[y].get(x);
    }

    @Override
    public void step() {
        final BitSet[] newRows = createRows(size);

        for (int x = 0; x < size; ++x) {
            for (int y = 0; y < size; ++y) {
                final int neighbours = neighbours(x, y);
                if (rows[y].get(x)) {
                    newRows[y].set(x, neighbours == 2 || neighbours == 3);
                } else {
                    newRows[y].set(x, neighbours == 3);
                }
            }
        }

        this.rows = newRows;
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
        try {
            return this.rows[y].get(x) ? 1 : 0;
        } catch (final IndexOutOfBoundsException e) {
            return 0;
        }
    }

    private static BitSet[] createRows(final int size) {
        final BitSet[] rows = new BitSet[size];
        for (int i = 0; i < size; ++i) {
            rows[i] = new BitSet(size);
        }
        return rows;
    }
}
