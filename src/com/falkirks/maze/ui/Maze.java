package com.falkirks.maze.ui;

import com.falkirks.maze.generator.SimpleMazeGenerator;
import com.falkirks.maze.solver.FacingWalker;
import com.falkirks.maze.solver.Walker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Represents the main window in which the maze is rendered
 * game is played
 */
@SuppressWarnings("serial")
public class Maze extends JFrame {

	private GamePanel gp;

	// EFFECTS: sets up maze and window will be played
	public Maze() {
		super("Maze");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		com.falkirks.maze.graph.Maze maze = (new SimpleMazeGenerator(25, 25)).generate();
		System.out.println(maze);

		gp = new GamePanel(new FacingWalker(maze.getStartCell(), Walker.DIRECTION_SOUTH));
		add(gp);
		addKeyListener(new KeyHandler());
		pack();
		centreOnScreen();
		setVisible(true);
	}

	// MODIFIES: this
	// EFFECTS:  location of frame is set so frame is centred on desktop
	private void centreOnScreen() {
		Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
	}
	
	/*
	 * A key handler to respond to key events
	 */
	private class KeyHandler extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			gp.keyPressed(e.getKeyCode());
		}
	}

	// Play the game
	public static void main(String[] args) {
		new Maze();
	}
}
