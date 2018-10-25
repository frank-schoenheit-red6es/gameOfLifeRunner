package de.red6.gameoflive.sven;

import de.red6.gameoflife.runner.Board;

import java.util.Objects;


public class SvensFastestBoardImpl implements Board {


    private int[][] board1;
    private int[][] board2;

    private int size;

    public void init(int size) {
        this.size = size;
        board1 = new int[size][size];
        board2 = new int[size][size];
    }


    public boolean isAlive(int x, int y) {
        return board1[x][y] == 1;
    }

    public int willBeAlive(int x, int y) {
        return (board1[x][y] | countNeighbours(x, y)) == 3 ? 1 : 0;
    }

    private int countNeighbours(int x, int y) {
        int c =0;
        try {c+=board1[x][y + 1] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x][y - 1]  ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x + 1][y + 1] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x + 1][y] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x + 1][y - 1] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x - 1][y + 1] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x - 1][y] ;}catch (ArrayIndexOutOfBoundsException e){}
        try {c+=board1[x - 1][y - 1] ;}catch (ArrayIndexOutOfBoundsException e){}
        return c;
    }


    public void setAlive(int x, int y) {
        this.board1[x][y] = 1;
    }

    public void step() {
        try {
            for (int x = 0; ; x++) {
                int[] ints = board2[x];

                try {
                    for (int y = 0; ; y++) {
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
