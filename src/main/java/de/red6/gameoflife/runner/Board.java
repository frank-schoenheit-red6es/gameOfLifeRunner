package de.red6.gameoflife.runner;/*
 * Created on 25.10.18
 */

public interface Board {
    void init(final int size);
    void shutdown();

    void setAlive(final int x, final int y);
    boolean isAlive(final int x, final int y);
    void step() throws InterruptedException;
}
