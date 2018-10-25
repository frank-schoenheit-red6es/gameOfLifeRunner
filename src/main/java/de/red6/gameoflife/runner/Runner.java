/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.runner;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class Runner {
    private final Board board;
    private final int boardSize;
    private final long seed;
    private final int initialFillingLevel;
    private final long steps;

    public Runner(final Board board, final int boardSize, final long seed, final int initialFillingLevel, final long steps) {
        this.board = Objects.requireNonNull(board, "|board| darf nicht null sein!");
        this.boardSize = boardSize;
        this.seed = seed;
        this.initialFillingLevel = initialFillingLevel;
        this.steps = steps;
    }

    public static void main(final String[] argv) throws Exception {
        final String implementationName = argv[0];
        final int boardSize = Integer.valueOf(argv[1]);
        final long seed = Long.valueOf(argv[2]);
        final int fillingLevel = Integer.valueOf(argv[3]);
        final long steps = Long.valueOf(argv[4]);

        @SuppressWarnings("unchecked") final Class<? extends Board> boardClass = (Class<? extends Board>) Class.forName(implementationName);
        final Board board = boardClass.getDeclaredConstructor().newInstance();
        new Runner(board, boardSize, seed, fillingLevel, steps).run();
    }

    private void run() {
        initializeBoard();
        final long start = System.currentTimeMillis();
        try {
            doRun();
        } finally {
            final long end = System.currentTimeMillis();
            System.out.println(String.format("miliseconds elapsed: %s", end - start));
        }
    }

    private void doRun() {
        for (long i = 0; i < steps; ++i) {
            this.board.step();
        }
    }

    private void initializeBoard() {
        final Random random = new Random(seed);
        final BigInteger livingCells = BigInteger.valueOf(boardSize).pow(2).multiply(BigInteger.valueOf(initialFillingLevel)).divide(BigInteger.valueOf(100));
        for (long i = 0; i < livingCells.longValue(); ++i) {
            this.board.setAlive(random.nextInt(boardSize), random.nextInt(boardSize), true);
        }
    }
}
