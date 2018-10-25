package de.red6.gameoflife.board;/*
 * Created on 25.10.18
 */

import de.red6.gameoflife.runner.Board;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import java.util.Objects;

/**
 * @author <a href="mailto:frank.schoenheit@red6-es.de">Frank Scho&ouml;nheit</a>
 */
public abstract class AbstractBoardTest {
    private final Class<? extends Board> testeeClass;

    protected AbstractBoardTest(final Class<? extends Board> testeeClass) {
        this.testeeClass = Objects.requireNonNull(testeeClass, "|testeeClass| darf nicht null sein!");
    }

    @Test
    public void cell_is_initially_dead() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 166;
        final int y = 571;

        // when

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    @Test
    public void cell_is_alive_after_setting_alive() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 165;
        final int y = 653;

        // when
        objectUnderTest.setAlive(x, y);

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertTrue(alive);
    }

    @Test
    public void alive_cell_without_neighbours_is_dead_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 916;
        final int y = 572;
        objectUnderTest.setAlive(x, y);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    @Test
    public void alive_cell_with_one_neighbour_is_dead_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 176;
        final int y = 16;
        objectUnderTest.setAlive(x, y);
        objectUnderTest.setAlive(x - 1, y - 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    @Test
    public void alive_cell_with_two_neighbours_is_alive_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 981;
        final int y = 643;
        objectUnderTest.setAlive(x, y);
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x + 1, y);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertTrue(alive);
    }

    @Test
    public void alive_cell_with_three_neighbours_is_alive_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 166;
        final int y = 99;
        objectUnderTest.setAlive(x, y);
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x + 1, y);
        objectUnderTest.setAlive(x + 1, y + 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertTrue(alive);
    }

    @Test
    public void alive_cell_with_four_neighbours_is_dead_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 991;
        final int y = 723;
        objectUnderTest.setAlive(x, y);
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x - 1, y);
        objectUnderTest.setAlive(x + 1, y);
        objectUnderTest.setAlive(x + 1, y + 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    @Test
    public void dead_cell_with_two_neighbours_is_still_dead_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 845;
        final int y = 394;
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x + 1, y - 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    @Test
    public void dead_cell_with_three_neighbours_is_alive_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 157;
        final int y = 943;
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x - 1, y);
        objectUnderTest.setAlive(x + 1, y - 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertTrue(alive);
    }

    @Test
    public void dead_cell_with_four_neighbours_is_dead_after_evolution_step() throws Exception {
        // given
        final Board objectUnderTest = createObjectUnderTest();
        final int x = 157;
        final int y = 943;
        objectUnderTest.setAlive(x - 1, y - 1);
        objectUnderTest.setAlive(x - 1, y);
        objectUnderTest.setAlive(x, y - 1);
        objectUnderTest.setAlive(x + 1, y - 1);

        // when
        objectUnderTest.step();

        // then
        final boolean alive = objectUnderTest.isAlive(x, y);
        assertFalse(alive);
    }

    private Board createObjectUnderTest() throws Exception {
        final Board board = testeeClass.getDeclaredConstructor().newInstance();
        board.init(1024);
        return board;
    }

}
