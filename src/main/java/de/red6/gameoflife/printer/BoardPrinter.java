/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.printer;

import de.red6.gameoflife.runner.Board;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class BoardPrinter {
    public void print(final int row, final int col, final Board board, final int boardSize) {
        char escCode = 0x1B;
        System.out.print(String.format("%c[%d;%dH", escCode, row, col));
        System.out.println(String.format("+%s+", repeat('-', boardSize)));
        for (int y = 0; y < boardSize; ++y) {
            System.out.print('|');
            for (int x = 0; x < boardSize; ++x) {
                System.out.print(board.isAlive(x, y) ? 'X' : ' ');
            }
            System.out.println('|');
        }
        System.out.println(String.format("+%s+", repeat('-', boardSize)));
        System.out.println();
    }

    private Object repeat(final char c, final int count) {
        final StringBuilder composer = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            composer.append(c);
        }
        return composer.toString();
    }
}
