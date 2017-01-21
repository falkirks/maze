package com.falkirks.maze.solver;

import com.falkirks.maze.graph.Cell;

// A walker in a 2d maze
public class Walker {
    // THESE ARE ABSOLUTE DIRECTIONS
    public static final int DIRECTION_NORTH = 0;
    public static final int DIRECTION_WEST = 1;
    public static final int DIRECTION_SOUTH = 2;
    public static final int DIRECTION_EAST = 3;

    private Cell loc;

    public Walker(Cell loc) {
        this.loc = loc;

    }
    //REQUIRES dir is a valid direction constant
    //MODIFIES this
    //EFFECTS if possible, move in provided direction and return true, otherwise return false
    public boolean move(int dir){
        Cell c = getCell(dir);
        if(c == null || !loc.getDoors().contains(c)){
            return false;
        }
        loc = c;
        return true;
    }
    //REQUIRES dir is a valid direction constant
    //EFFECTS returns true if move is possible, false otherwise
    public boolean canMove(int dir){
        switch (dir){
            case DIRECTION_NORTH:
                return loc.canGoNorth();
            case DIRECTION_SOUTH:
                return loc.canGoSouth();
            case DIRECTION_WEST:
                return loc.canGoWest();
            case DIRECTION_EAST:
                return loc.canGoEast();
            default:
                return false;
        }
    }

    public Cell getLoc() {
        return loc;
    }

    protected Cell getCell(int dir){
        Cell c;
        switch (dir){
            case DIRECTION_NORTH:
                c = getNeighbourAt(loc.getX(), loc.getY()-1);
                break;
            case DIRECTION_SOUTH:
                c = getNeighbourAt(loc.getX(), loc.getY()+1);
                break;
            case DIRECTION_EAST:
                c = getNeighbourAt(loc.getX()+1, loc.getY());
                break;
            case DIRECTION_WEST:
                c = getNeighbourAt(loc.getX()-1, loc.getY());
                break;
            default:
                c = null;
                break;
        }
        return c;
    }

    protected Cell getNeighbourAt(int x, int y){
        for(Cell n : loc.getNeighbours()){
            if(n.getX() == x && n.getY() == y){
                return n;
            }
        }
        return null;
    }

}
