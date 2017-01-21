package com.falkirks.maze.graph;


import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int x;
    private int y;
    private List<Cell> doors;
    private List<Cell> neighbours;

    public Cell(int x, int y, List<Cell> doors, List<Cell> neighbours) {
        this.x = x;
        this.y = y;
        this.doors = doors;
        this.neighbours = neighbours;
    }
    public Cell(int x, int y){
        this.x = x;
        this.y = y;
        this.doors = new ArrayList<Cell>();
        this.neighbours = new ArrayList<Cell>();
    }
    public void addDoor(Cell cell){
        doors.add(cell);
    }
    public void addNeighbour(Cell cell){
        neighbours.add(cell);
    }
    public boolean canGoNorth(){
        for(Cell c : getDoors()){
            if(c.getY() == y-1){
                return true;
            }
        }
        return false;
    }
    public boolean canGoSouth(){
        for(Cell c : getDoors()){
            if(c.getY() == y+1){
                return true;
            }
        }
        return false;
    }
    public boolean canGoEast(){
        for(Cell c : getDoors()){
            if(c.getX() == x+1){
                return true;
            }
        }
        return false;
    }
    public boolean canGoWest(){
        for(Cell c : getDoors()){
            if(c.getX() == x-1){
                return true;
            }
        }
        return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Cell> getDoors() {
        return doors;
    }

    public List<Cell> getNeighbours() {
        return neighbours;
    }
}
