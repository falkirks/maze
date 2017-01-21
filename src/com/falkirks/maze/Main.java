package com.falkirks.maze;

import com.falkirks.maze.generator.KruskalGenerator;
import com.falkirks.maze.generator.MazeGenerator;
import com.falkirks.maze.generator.SimpleMazeGenerator;
import com.falkirks.maze.graph.Maze;

public class Main {

    public static void main(String[] args) {
        MazeGenerator kruskalGenerator = new KruskalGenerator(15, 15);
        Maze maze = kruskalGenerator.generate();
        System.out.print(maze);

        MazeGenerator mazeGenerator = new SimpleMazeGenerator(15, 15);
        Maze simpleMaze = mazeGenerator.generate();
        System.out.print(simpleMaze);
    }
}
