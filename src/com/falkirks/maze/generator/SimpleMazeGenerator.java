package com.falkirks.maze.generator;

import com.falkirks.maze.graph.Cell;
import com.falkirks.maze.graph.Maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SimpleMazeGenerator extends MazeGenerator {
    public SimpleMazeGenerator(int height, int width) {
        super(height, width);
    }

    //EFFECTS returns a new randomly generated maze by running "Recursive backtracker"
    public Maze generate(){
        List<Cell> cells = buildNeighbourRelationships(buildCellList());
        List<Cell> visited = new ArrayList<Cell>();
        Stack<Cell> stack = new Stack<Cell>();
        Cell current = cells.get(0);
        visited.add(current);
        //System.out.println("Generating...");
        do{
            Cell random = getRandomUnvisited(current, visited);
            if(random != null){

                //System.out.println("Knocking wall between:" + random.getX() + ":" + random.getY() + " " + current.getX() + ":" + current.getY());
                stack.push(current);
                current.addDoor(random);
                random.addDoor(current);
                current = random;
                visited.add(current);
            }
            else if(stack.size() > 0){
                current = stack.pop();
            }
        }while(!(current.getX() == cells.get(0).getX() && current.getY() == cells.get(0).getY()));

        return new Maze(cells, getWidth(), getHeight());
    }
    //EFFECTS returns a random neighbour of cell, which is not in visited, null if none exists
    protected Cell getRandomUnvisited(Cell cell, List<Cell> visited){
        List<Cell> possible = new ArrayList<Cell>();

        for(Cell neighbour : cell.getNeighbours()){
            if(!visited.contains(neighbour)){
                possible.add(neighbour);
            }
        }
        if(possible.size() == 0){
            return null;
        }
        return possible.get(getRandomGenerator().nextInt(possible.size()));
    }
}
