package com.falkirks.maze.graph;

// Wall that divides two cells
public class Wall {
    private Cell a;
    private Cell b;

    //EFFECTS creates new wall that represents division between a and b
    //REQUIRES a and b are adjacent cells (neighbours of each-other)
    public Wall(Cell a, Cell b) {
        this.a = a;
        this.b = b;
    }

    public Cell getA() {
        return a;
    }

    public Cell getB() {
        return b;
    }
}
