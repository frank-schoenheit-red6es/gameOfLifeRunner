/*
 * Created on 26.10.18
 */
package de.red6.gameoflife.frank;

import de.red6.gameoflife.runner.Board;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public class ParallelStepsRunnerBoard implements Board {
    private static final long TERMINAL_SIZE = 4;
    private static final int THREAD_COUNT = 32;

    private final ExecutorService executorService;
    private int size;
    private boolean[][] cells;

    public ParallelStepsRunnerBoard() {
        this.executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

    @Override
    public void init(final int size) {
        this.cells = new boolean[size + 2][size + 2];
        this.size = size;
    }

    @Override
    public void shutdown() {
        this.executorService.shutdownNow();
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
    public void step() throws InterruptedException {
        final boolean[][] newCells = new boolean[size + 2][size + 2];
        final RunContext runContext = new RunContext();
        scheduleJob(1, 1, size, newCells, runContext);
        runContext.await();
        this.cells = newCells;
    }

    private void scheduleJob(int left, int top, int dimension, boolean[][] newCells, final RunContext runContext) {
        runContext.onJobStarting();
        this.executorService.submit(() -> doStep(left, top, dimension, newCells, runContext));
    }

    private void doStep(final int left, final int top, final int dimension, final boolean[][] newCells, final RunContext runContext) {
        try {
            if (dimension > TERMINAL_SIZE) {
                final int newDimension = dimension / 2;
                scheduleJob(left, top, newDimension, newCells, runContext);
                scheduleJob(left + newDimension, top, newDimension, newCells, runContext);
                scheduleJob(left, top + newDimension, newDimension, newCells, runContext);
                scheduleJob(left + newDimension, top + newDimension, newDimension, newCells, runContext);
            } else {
                final int right = left + dimension - 1;
                final int bottom = top + dimension - 1;
                for (int x = left; x <= right; ++x) {
                    for (int y = top; y <= bottom; ++y) {
                        if (x == 167 && y == 100) {
                            final int dummy = 0;
                        }
                        final int neighbours = neighbours(x, y);
                        if (cells[x][y]) {
                            newCells[x][y] = neighbours == 2 || neighbours == 3;
                        } else {
                            newCells[x][y] = neighbours == 3;
                        }
                    }
                }
            }
        } finally {
            runContext.onJobFinished();
        }
    }

    private int neighbours(final int xPlusOne, final int yPlusOne) {
        return activeCount(xPlusOne - 1, yPlusOne - 1)
                + activeCount(xPlusOne - 1, yPlusOne)
                + activeCount(xPlusOne - 1, yPlusOne + 1)
                + activeCount(xPlusOne, yPlusOne - 1)
                + activeCount(xPlusOne, yPlusOne + 1)
                + activeCount(xPlusOne + 1, yPlusOne - 1)
                + activeCount(xPlusOne + 1, yPlusOne)
                + activeCount(xPlusOne + 1, yPlusOne + 1);
    }

    private int activeCount(final int xPlusOne, final int yPlusOne) {
        return cells[xPlusOne][yPlusOne] ? 1 : 0;
    }

    private static class RunContext {
        private final AtomicInteger runningJobs = new AtomicInteger(0);
        private final CountDownLatch finishedLatch = new CountDownLatch(1);

        void onJobStarting() {
            this.runningJobs.incrementAndGet();
        }

        void onJobFinished() {
            if (this.runningJobs.decrementAndGet() == 0) {
                this.finishedLatch.countDown();
            }
        }
        void await() throws InterruptedException {
            this.finishedLatch.await();
        }
    }
}
