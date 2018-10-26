package de.red6.gameoflife.sven;

import de.red6.gameoflife.runner.Board;


public class IntArrayBoardWithEnlargmentImpl implements Board {


    private int[][] board1;
    private int[][] board2;

    private int size;

    public void init(int size) {
        this.size = size;
        board1 = new int[size+2][size+2];
        board2 = new int[size+2][size+2];
    }

    public void shutdown() {
        // noop
    }

    public boolean isAlive(int x, int y) {
        return board1[x+1][y+1] == 1;
    }

    public int willBeAlive(int x, int y) {
        return (board1[x][y] | countNeighbours(x, y)) == 3 ? 1 : 0;
    }

    private int countNeighbours(int x, int y) {
        int c =0;
        c+=board1[x + 1][y + 1];
        c+=board1[x][y + 1];
        c+=board1[x + 1][y];
        c+=board1[x + 1][y - 1] ;
        c+=board1[x][y - 1]  ;
        c+=board1[x - 1][y + 1] ;
        c+=board1[x - 1][y] ;
        c+=board1[x - 1][y - 1] ;
        return c;
    }


    public void setAlive(int x, int y) {
        this.board1[x+1][y+1] = 1;
    }

    public void step() {
        try {
            for (int x = 1; ; x++) {
                int[] ints = board2[x];

                try {
                    for (int y = 1; ; y++) {
                        ints[y] = willBeAlive(x, y);
                    }
                } catch (ArrayIndexOutOfBoundsException e1) {
                }
            }
        } catch (ArrayIndexOutOfBoundsException e2) {
        }
        int[][] temp = board1;
        board1 = board2;
        board2 = temp;
    }
}
