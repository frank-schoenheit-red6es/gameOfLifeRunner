/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.frank;

import de.red6.gameoflife.runner.Board;

/**
 * Ein Board, welches auf einem erweiterten zweidimensionalen Array von {@code boolean}s arbeitet.
 * <p>Im Gegensatz zu der straight-forward-Implementierung wird für jedes Feld nur exakt einmal die Anzahl der Nachbarn
 * bestimmt.</p>
 *
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class CalcNeighboursOnceBoard implements Board {
    private int size;
    private boolean[][] cells;

    @Override
    public void init(final int size) {
        this.cells = new boolean[size + 2][size + 2];
        this.size = size;
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

        byte[] previousRowNeighbourCount = new byte[size + 2];
        byte[] currentRowNeighbourCount = new byte[size + 2];
        byte[] nextRowNeighbourCount = new byte[size + 2];

        for (int y = 1; y <= size+1; ++y) {
            for (int x = 1; x <= size+1; ++x) {
                // update count of neighbours in all neighbour cells
                if (this.cells[x][y]) {
                    ++previousRowNeighbourCount[x - 1];
                    ++previousRowNeighbourCount[x];
                    ++previousRowNeighbourCount[x + 1];
                    ++currentRowNeighbourCount[x - 1];
                    ++currentRowNeighbourCount[x + 1];
                    ++nextRowNeighbourCount[x - 1];
                    ++nextRowNeighbourCount[x];
                    ++nextRowNeighbourCount[x + 1];
                }
                // now there's exactly one cell whose count cannot change anymore: our upper-left neighbour cell
                final int neighbours = previousRowNeighbourCount[x - 1];
                if (cells[x - 1][y - 1]) {
                    newCells[x - 1][y - 1] = neighbours == 2 || neighbours == 3;
                } else {
                    newCells[x - 1][y - 1] = neighbours == 3;
                }
            }
            previousRowNeighbourCount = currentRowNeighbourCount;
            currentRowNeighbourCount = nextRowNeighbourCount;
            nextRowNeighbourCount = new byte[size + 2];
        }
        this.cells = newCells;
    }
}
