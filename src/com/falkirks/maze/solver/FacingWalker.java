package com.falkirks.maze.solver;

import com.falkirks.maze.graph.Cell;

// A walker that faces in a certain direction
public class FacingWalker extends Walker {
    public static final int FORWARD = 0;
    public static final int LEFT = 1;
    public static final int BACKWARD = 2;
    public static final int RIGHT = 3;

    private int absFacing;
    public FacingWalker(Cell loc, int facing) {
        super(loc);
        absFacing = facing;
    }
    public boolean move(){
        return super.move(absFacing);
    }

    public boolean canMove(int direction){
        switch(direction){
            case FORWARD:
                return super.canMove(absFacing);
            case BACKWARD:
                return super.canMove(rotateLeft(rotateLeft(absFacing)));
            case LEFT:
                return super.canMove(rotateLeft(absFacing));
            case RIGHT:
                return super.canMove(rotateRight(absFacing));
        }
        return false;
    }


    public void rotateRight(){
        absFacing = rotateRight(absFacing);
    }
    public void rotateLeft(){
        absFacing = rotateLeft(absFacing);
    }

    private int rotateRight(int absFacing){
        if(absFacing == DIRECTION_NORTH){
            return DIRECTION_EAST;
        }
        else{
            return absFacing-1;
        }
    }
    private int rotateLeft(int absFacing){
        if(absFacing == DIRECTION_EAST){
            return DIRECTION_NORTH;
        }
        else{
            return absFacing+1;
        }
    }

    public int getAbsFacing() {
        return absFacing;
    }
}
