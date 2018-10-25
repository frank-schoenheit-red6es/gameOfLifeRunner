package de.red6.gameoflife.board;/*
 * Created on 25.10.18
 */

import de.red6.gameoflife.runner.Board;

import java.util.Objects;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public abstract class AbstractBoardTest {
    private final Board testee;

    AbstractBoardTest(final Board testee) {
        this.testee = Objects.requireNonNull(testee, "|testee| darf nicht null sein!");
    }
}
