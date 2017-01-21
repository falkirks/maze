package com.falkirks.maze.graph;

import java.util.List;

/**
 * Raw maze data structure, with no way to mark visited
 * NOTE: width*height are only provided to render in 2d. The actual graph structure doesn't need them.
 */
public class Maze {
    public static final String WALL_STR = "██";
    public static final String EMPTY_STR = "  ";
    private List<Cell> cells;
    private int height;
    private int width;

    //EFFECTS creates a new maze using the graph cells and of width*height
    //REQUIRES cells.size() == width*height and that the maze is perfect
    public Maze(List<Cell> cells, int width, int height){
        this.cells = cells;
        this.width = width;
        this.height = height;
    }
    //EFFECTS returns a 2d representation of a maze
    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < width*2+1; i++){
            str += WALL_STR;
        }
        str += "\n";
        for(int y = 0; y < height; y++){
            str += WALL_STR;
            String after = WALL_STR;
            for(int x = 0; x < width; x++){
                str += EMPTY_STR; //CELL SPACE
                Cell cell = cells.get((y * width) + x);
                if(cell.canGoSouth()){
                    after += EMPTY_STR; //GAP IN BELOW WALL
                }
                else{
                    after += WALL_STR; //NO GAP IN BELLOW WALL
                }

                if(!cell.canGoEast()){
                    str += WALL_STR;
                }
                else{
                    str += EMPTY_STR;
                }
                after += WALL_STR;
            }
            str += "\n";
            str += after;
            str += "\n";
        }

        return str;
    }
    //FIXME
    public Cell getStartCell(){
        return cells.get(0);
    }
}
