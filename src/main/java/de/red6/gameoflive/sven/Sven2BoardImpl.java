package de.red6.gameoflive.sven;

import de.red6.gameoflife.runner.Board;


public class Sven2BoardImpl implements Board {


    private boolean[][] board;
    private int size;

    public void init(int size) {
        this.size = size;
        board = new boolean[size][size];
    }


    public boolean isAlive(int x, int y) {
        return board[x][y];
    }

    public boolean willBeAlive(int x, int y) {
        int anzahl = countNeighbours(x, y);

        return (board[x][y] && anzahl == 2) || anzahl == 3;
    }

    private int countNeighbours(int x, int y) {
        int count = 0;

        for (int ax = x - 1; ax <= x + 1; ax++) {
            for (int ay = y - 1; ay <= y + 1; ay++) {
                try {
                    if ((ax != x || ay != y) && board[ax][ay]) {
                        count++;
                        if (count == 4) {
                            return count;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }
        return count;
    }


    public void setAlive(int x, int y) {
        this.board[x][y] = true;
    }

    public void step() {
        boolean[][] nextBoard = new boolean[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (willBeAlive(x, y)) {
                    nextBoard[x][y] = true;
                }
            }
        }
        board = nextBoard;
    }
}
