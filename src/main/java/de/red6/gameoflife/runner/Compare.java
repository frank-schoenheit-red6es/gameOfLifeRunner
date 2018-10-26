/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.runner;

import de.red6.gameoflife.printer.BoardPrinter;

import java.util.Objects;
import java.util.Random;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class Compare extends AbstractBoardRunner {
    private final Board board1;
    private final Board board2;

    private Compare(final Board board1, final Board board2) {
        this.board1 = Objects.requireNonNull(board1, "|board1| darf nicht null sein!");
        this.board2 = Objects.requireNonNull(board2, "|board2| darf nicht null sein!");
    }

    public static void main(final String[] argv) throws Exception {
        final String implementationName1 = argv[0];
        final String implementationName2 = argv[1];
        final int boardSize = 16;
        final int fillLevel = 50;
        final long steps = 200;

        final long seed = 234754576586L;

        final Board board1 = createBoard(implementationName1, boardSize);
        initializeBoard(board1, boardSize, fillLevel, new Random(seed));

        final Board board2 = createBoard(implementationName2, boardSize);
        initializeBoard(board2, boardSize, fillLevel, new Random(seed));

        new Compare(board1, board2).run(boardSize, steps);
    }

    private void run(final int boardSize, final long steps) throws InterruptedException {
        final BoardPrinter printer = new BoardPrinter();
        printer.print(4, 1, board1, boardSize);
        printer.print(22, 1, board2, boardSize);

        for (long i = 0; i < steps; ++i) {
            board1.step();
            board2.step();
            printer.print(4, 1, board1, boardSize);
            printer.print(22, 1, board2, boardSize);
            Thread.sleep(100);
        }
        board1.shutdown();
        board2.shutdown();
    }
}
