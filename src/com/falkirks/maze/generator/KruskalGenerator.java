package com.falkirks.maze.generator;

import com.falkirks.maze.graph.Cell;
import com.falkirks.maze.graph.Maze;
import com.falkirks.maze.graph.Wall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a rough and inefficient implantation.
 * I am mainly interested in looking at how the results differ.
 */
public class KruskalGenerator extends MazeGenerator {
    public KruskalGenerator(int height, int width) {
        super(height, width);
    }

    //EFFECTS generates a maze using Kruskal's algorithm
    @Override
    public Maze generate() {
        List<Cell> cells = buildNeighbourRelationships(buildCellList());
        List<List<Cell>> sets = new ArrayList<List<Cell>>();
        List<Wall> list = new ArrayList<Wall>();
        for(Cell cell : cells){
            for(Cell n : cell.getNeighbours()){
                Wall w = new Wall(cell, n);
                list.add(w);
            }
            List<Cell> set = new ArrayList<Cell>();
            set.add(cell);
            sets.add(set);
        }

        // Randomize the order of visits
        Collections.shuffle(list, getRandomGenerator());

        for(Wall w : list){
            int setA = getSet(w.getA(), sets);
            int setB = getSet(w.getB(), sets);
            if(setA != setB){ // Belong to distinct sets
                // BREAK DOWN THE WALL
                w.getA().addDoor(w.getB());
                w.getB().addDoor(w.getA());

                // JOIN THE SETS
                sets.get(setA).addAll(sets.get(setB));
                sets.remove(setB);
            }
        }
        return new Maze(cells, getWidth(), getHeight());
    }

    //EFFECTS returns the index of the set which cell belongs to
    //REQUIRES each cell belongs to at most one set
    private int getSet(Cell cell, List<List<Cell>> sets){
        for(List<Cell> set : sets){
            for(Cell current : set){
                if(current == cell){
                    return sets.indexOf(set);
                }
            }
        }
        return -1;
    }

}
