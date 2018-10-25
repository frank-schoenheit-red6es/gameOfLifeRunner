package de.red6.gameoflive.sven;

import de.red6.gameoflife.runner.Board;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {
    @Test
    public void testSetAlive(){
        Board board = new Sven0BoardImpl();
        board.init(10);
        board.setAlive(4,5);
        board.setAlive(4,4);
        assertTrue(board.isAlive(4,4));
        assertTrue(board.isAlive(4,5));
    }

    @Test
    public void testRule1(){
        Board board = new Sven1BoardImpl();
        board.init(10);
        board.setAlive(4,4);
        board.step();
        assertFalse(board.isAlive(4,4));
    }

    @Test
    public void testRule2(){
        Board board = new Sven1BoardImpl();
        board.init(10);
        board.setAlive(4,5);
        board.setAlive(4,4);
        board.setAlive(4,3);

        board.step();
        assertTrue(board.isAlive(4,4));
    }

    @Test
    public void testRule3(){
        Board board = new Sven1BoardImpl();
        board.init(10);
        board.setAlive(4,4);
        board.setAlive(4,5);
        board.setAlive(4,3);
        board.setAlive(3,3);
        board.setAlive(3,4);

        board.step();
        assertFalse(board.isAlive(4,4));
    }

    @Test
    public void testRule4(){
        Board board = new Sven1BoardImpl();
        board.init(10);
        board.setAlive(4,4);
        board.setAlive(4,5);
        board.setAlive(4,3);

        board.step();
        assertTrue(board.isAlive(3,4));
    }

    @Test
    public void testOuterBorders(){
        Board board = new Sven1BoardImpl();
        board.init(10);
        board.setAlive(9,4);
        board.setAlive(9,5);
        board.setAlive(9,3);
        board.step();
    }
}
