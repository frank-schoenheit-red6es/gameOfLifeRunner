/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.runner;

import java.util.Objects;
import java.util.Random;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class Runner extends AbstractBoardRunner {
    private final Board board;

    public Runner(final Board board) {
        this.board = Objects.requireNonNull(board, "|board| darf nicht null sein!");
    }

    public static void main(final String[] argv) throws Exception {
        final String implementationName = argv[0];
        final int boardSize = Integer.valueOf(argv[1]);
        final long seed = Long.valueOf(argv[2]);
        final int fillLevel = Integer.valueOf(argv[3]);
        final long steps = Long.valueOf(argv[4]);

        final Board board = createBoard(implementationName, boardSize);
        initializeBoard(board, boardSize, fillLevel, new Random(seed));
        new Runner(board).run(steps);
    }

    private void run(final long steps) throws InterruptedException {
        final long start = System.currentTimeMillis();
        try {
            doRun(steps);
        } finally {
            final long end = System.currentTimeMillis();
            System.out.println(end - start);
        }
    }

    private void doRun(final long steps) throws InterruptedException {
        for (long i = 0; i < steps; ++i) {
            this.board.step();
        }
        board.shutdown();
    }
}
