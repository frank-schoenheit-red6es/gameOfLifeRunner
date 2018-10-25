/*
 * Created on 25.10.18
 */
package de.red6.gameoflife.impl.dummy;

import de.red6.gameoflife.runner.Board;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class DummyBoard implements Board {
    @Override
    public void init(int size) {
        // nope
    }

    @Override
    public void setAlive(int x, int y, boolean alive) {
        // nope
    }

    @Override
    public boolean isAlive(int x, int y) {
        // nope
        return false;
    }

    @Override
    public void step() {
        // nope
    }
}
