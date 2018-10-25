package de.red6.gameoflive.sven;

import de.red6.gameoflife.runner.Board;


public class BoardImpl implements Board {


    private Position[][] board;
    private int size;

    public void init(int size) {
        this.size = size;
        board = new Position[size][size];
    }


    public boolean isAlive(int x, int y) {
        return board[x][y] != null && (board[x][y]).isAlive();
    }

    public boolean willBeAlive(int x, int y) {
        return board[x][y] != null && (board[x][y]).willBeAlive();
    }


    public void setAlive(int x, int y) {
        setAlive(x, y, this.board);
    }

    private void setAlive(int x, int y, Position[][] board) {
        if (x < size && y < size && x > 0 && y > 0) {

            Position position = board[x][y];
            if (position == null) {
                board[x][y] = new Position(0, true);
            } else {
                position.setAlive(true);
            }
            incrementNeighbours(x + 1, y + 1, board);
            incrementNeighbours(x + 1, y, board);
            incrementNeighbours(x + 1, y - 1, board);
            incrementNeighbours(x, y + 1, board);
            incrementNeighbours(x, y - 1, board);
            incrementNeighbours(x - 1, y + 1, board);
            incrementNeighbours(x - 1, y, board);
            incrementNeighbours(x - 1, y - 1, board);
        }
    }


    public void incrementNeighbours(int x, int y, Position[][] board) {
        if (x < size && y < size && x > 0 && y > 0) {
            Position position = board[x][y];
            if (position == null) {
                board[x][y] = new Position(1, false);
            } else {
                position.increment();
            }
        }
    }

    public void step() {
        Position[][] nextBoard = new Position[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (willBeAlive(x, y)) {
                    setAlive(x, y, nextBoard);
                }
            }
        }
        board = nextBoard;
    }
}
