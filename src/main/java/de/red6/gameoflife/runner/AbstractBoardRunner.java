/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.runner;

import java.math.BigInteger;
import java.util.Random;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
class AbstractBoardRunner {
    static Board createBoard(final String implementationName, int boardSize) throws ClassNotFoundException, InstantiationException, IllegalAccessException, java.lang.reflect.InvocationTargetException, NoSuchMethodException {
        @SuppressWarnings("unchecked")
        final Class<? extends Board> boardClass = (Class<? extends Board>) Class.forName(implementationName);
        final Board board = boardClass.getConstructor().newInstance();
        board.init(boardSize);
        return board;
    }

    static void initializeBoard(final Board board, final int boardSize, final int fillLevel, final Random random) {
        final BigInteger livingCells = BigInteger.valueOf(boardSize).pow(2).multiply(BigInteger.valueOf(fillLevel)).divide(BigInteger.valueOf(100));
        for (long i = 0; i < livingCells.longValue(); ++i) {
            board.setAlive(random.nextInt(boardSize), random.nextInt(boardSize));
        }
    }
}
