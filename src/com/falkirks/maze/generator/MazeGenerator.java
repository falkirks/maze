package com.falkirks.maze.generator;



import com.falkirks.maze.graph.Cell;
import com.falkirks.maze.graph.Maze;

import java.util.*;

abstract public class MazeGenerator {
    private int height;
    private int width;
    private Random randomGenerator;

    //EFFECTS creates a new maze generator
    public MazeGenerator(int height, int width) {
        this.height = height;
        this.width = width;
        this.randomGenerator = new Random();
    }
    //EFFECTS returns a new randomly generated maze
    abstract public Maze generate();

    //EFFECTS returns a list of cells in the maze, with no paths added to the graph
    protected List<Cell> buildCellList(){
        //System.out.println("Building cell list...");
        List<Cell> cells = new ArrayList<Cell>();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                cells.add(new Cell(x, y, new ArrayList<Cell>(), new ArrayList<Cell>()));
            }
        }
        return cells;
    }
    //EFFECTS adds neighbour relationships to the graph
    //REQUIRES cells.size() == height*width and all x and y values are set correctly
    protected List<Cell> buildNeighbourRelationships(List<Cell> cells){
        //System.out.println("Building neighbour list...");
        for(Cell cell : cells){
            if(cell.getX() != this.width-1)
                cell.addNeighbour(cells.get(getListOffset(cell.getX()+1, cell.getY())));
            if(cell.getX() != 0)
                cell.addNeighbour(cells.get(getListOffset(cell.getX()-1, cell.getY())));
            if(cell.getY() != this.height-1)
                cell.addNeighbour(cells.get(getListOffset(cell.getX(), cell.getY()+1)));
            if(cell.getY() != 0)
                cell.addNeighbour(cells.get(getListOffset(cell.getX(), cell.getY()-1)));
            /*System.out.println(cell.getX() + ":" + cell.getY() + " is a neighbour of:");
            for(Cell n : cell.getNeighbours()){
                System.out.println(" > " + n.getX() + ":" + n.getY());
            }*/
        }
        return cells;
    }
    //EFFECTS returns a list offset in a rectangle area
    protected int getListOffset(int x, int y){
        return (y * width) + x;
    }



    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Random getRandomGenerator() {
        return randomGenerator;
    }
}
