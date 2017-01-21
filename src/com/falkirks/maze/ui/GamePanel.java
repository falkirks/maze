package com.falkirks.maze.ui;

import com.falkirks.maze.solver.FacingWalker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/*
 * The panel in which the game is rendered.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel{

    private FacingWalker walker;
    public static final Color FLOOR = new Color(0, 0, 0);
    public static final double SCALE_FACTOR = 0.9;
    public static final double Y_COEF = 0.75;
    public static final int START_XY_DIST = 125;
    public static final int TOP_Y = 0;
    public static final int LEFT_X = 0;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Color SOLID_WALL = new Color(104, 104, 104);
    public static final Color OPEN_WALL = new Color(219, 219, 219);

    // EFFECTS:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(FacingWalker walker) {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.GRAY);
        this.walker = walker;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGame(g);
    }

    // MODIFIES: g
    // EFFECTS:  draws the game onto g
    private void drawGame(Graphics g) {
        drawCell(g);

    }

    //MODIFIES g
    //EFFECTS: draws maze cell onto g
    private void drawCell(Graphics g) {
        int depth = 0;
        g.setColor(FLOOR);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        int rightX = LEFT_X + WIDTH;
        int topY = TOP_Y;

        int leftX = LEFT_X;
        int bottomY = TOP_Y + HEIGHT;
        int dist = START_XY_DIST;


        FacingWalker tempWalker = new FacingWalker(walker.getLoc(), walker.getAbsFacing());
        do {
            dist *= SCALE_FACTOR;
            if (!tempWalker.canMove(FacingWalker.RIGHT)) {

                Polygon p = new Polygon();
                p.addPoint(rightX, topY);
                p.addPoint(rightX - dist, topY + 2*(dist/3));
                p.addPoint(rightX - dist, bottomY - 2*(dist/3));
                p.addPoint(rightX, bottomY);
                g.fillPolygon(p);

            }
            if (!tempWalker.canMove(FacingWalker.LEFT)) {
                Polygon p = new Polygon();
                p.addPoint(leftX, topY);
                p.addPoint(leftX + dist, topY + 2*(dist/3));
                p.addPoint(leftX + dist, bottomY - 2*(dist/3));
                p.addPoint(leftX, bottomY);
                g.fillPolygon(p);

            }
            dist *= SCALE_FACTOR;
            rightX -= dist;
            leftX += dist;
            topY += 2*(dist/3);
            bottomY -= 2*(dist/3);
            depth++;
        }
        while (tempWalker.move());
        g.fillRect(leftX, topY, rightX-leftX, bottomY-topY);

    }
    //MODIFIES this
    //EFFECTS moves the walker according to the code passed, and repaints
    public void keyPressed(int code){
        switch(code){
            case KeyEvent.VK_UP:
                walker.move();
                break;
            case KeyEvent.VK_RIGHT:
                walker.rotateRight();
                break;
            case KeyEvent.VK_LEFT:
                walker.rotateLeft();
                break;
        }
        repaint();

    }

}
