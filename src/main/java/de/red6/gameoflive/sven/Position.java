package de.red6.gameoflive.sven;

public class Position {
    private int neighbours;
    private boolean alive;


    public Position(int neighbours, boolean alive) {
        this.neighbours = neighbours;
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean willBeAlive() {
        return (alive && neighbours == 2) || neighbours == 3;
    }

    public void setAlive(boolean alive) {
        this.alive=alive;
    }

    public void increment() {
        neighbours++;
    }
}
