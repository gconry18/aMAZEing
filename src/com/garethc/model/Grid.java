/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.model;

import com.garethc.app.config.exceptions.BoundryMoveException;
import com.garethc.app.config.exceptions.WallMoveException;
import static com.garethc.model.Arrow.ARROW_EAST;
import static com.garethc.model.Arrow.ARROW_NORTH;
import static com.garethc.model.Arrow.ARROW_NORTHEAST;
import static com.garethc.model.Arrow.ARROW_NORTHWEST;
import static com.garethc.model.Arrow.ARROW_SOUTH;
import static com.garethc.model.Arrow.ARROW_SOUTHEAST;
import static com.garethc.model.Arrow.ARROW_SOUTHWEST;
import static com.garethc.model.Arrow.ARROW_WEST;
import java.io.IOException;
import javax.swing.JLabel;

/**
 *
 * @author gconry
 */
public class Grid {
    private static Grid gridInstance;
    private JLabel grid [] [];
    private FinishBlock finish;
    private Arrow arrow;
    private int walls;
    private int paths;
    private int arrow_x;
    private int arrow_y;
    private int blocks_x;
    private int blocks_y;
    
    private Grid() {        
    }
    
    public static Grid getInstance() {
        if (gridInstance == null) {
            gridInstance = new Grid();
        }
        return gridInstance;
    }
    
    private void setGridSize(int x, int y) {
        grid = new JLabel[y][x];
        blocks_x = x;
        blocks_y = y;
    }
    
    public void generateGrid (int x, int y, int difficulty) throws IOException {
        setGridSize(x, y);
        
        finish = FinishBlock.getInstance();
        arrow = Arrow.getInstance();
        
        walls = 0;
        paths = 0;
        for (int i = 0; i < y; i++)
        {
            for (int j = 0; j < x; j++)
            {
                if (i == 0 && j == 0) continue;
                if (i == y-1 && j == x-1) continue;
                
                int random = (int) (Math.random() * 100);
                if (random % difficulty == 0) {
                    WallBlock wb = new WallBlock();
                    grid [i] [j] = wb;
                    walls++;
                }
                else {
                    PathBlock pb = new PathBlock();
                    grid [i] [j] = pb;
                    paths++;
                }
            }
        }
        
        grid [0] [0] = finish;
        grid [y-1] [x-1] = arrow;
        arrow_x = x-1;
        arrow_y = y-1;
        
        double ratio = (double)paths / (double)walls;
        System.out.println("Paths: " + paths + "\nWalls: " + walls + "\nRatio: " + ratio);
    }
    
    public JLabel[][] getGrid() {
        return grid;        
    }
    
    public void resetGrid() {
        for (int i = 0; i < blocks_y; i++) {
            for (int j = 0; j < blocks_x; j++) {
                if (grid [i] [j] instanceof VisitedBlock){
                    grid [i] [j] = new PathBlock();
                }
            }
        }
        grid [arrow_y] [arrow_x] = new PathBlock();
        grid [blocks_y - 1] [blocks_x - 1] = arrow;
        arrow_x = blocks_x - 1;
        arrow_y = blocks_y - 1;
        arrow.setOrientation(Arrow.ARROW_NORTH);
    }

    public boolean doMove() throws WallMoveException, BoundryMoveException {
        int orientation = arrow.getOrientation();       
        if (orientation == ARROW_NORTH) {
            canMove(arrow_x, arrow_y - 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y - 1;
        }
        else if (orientation == ARROW_NORTHEAST) {
            canMove(arrow_x + 1, arrow_y - 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y - 1;
            arrow_x = arrow_x + 1;
        }
        else if (orientation == ARROW_EAST) {
            canMove(arrow_x + 1, arrow_y);
            grid [arrow_y] [arrow_x] = new VisitedBlock();          
            arrow_x = arrow_x + 1;
        }
        else if (orientation == ARROW_SOUTHEAST) {
            canMove(arrow_x + 1, arrow_y + 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y + 1;
            arrow_x = arrow_x + 1;
        }
        else if (orientation == ARROW_SOUTH) {
            canMove(arrow_x, arrow_y + 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y + 1;
        }
        else if (orientation == ARROW_SOUTHWEST) {
            canMove(arrow_x - 1, arrow_y + 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y + 1;
            arrow_x = arrow_x - 1;
        }
        else if (orientation == ARROW_WEST) {
            canMove(arrow_x - 1, arrow_y);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_x = arrow_x - 1;
        }
        else if (orientation == ARROW_NORTHWEST) {
            canMove(arrow_x - 1, arrow_y - 1);
            grid [arrow_y] [arrow_x] = new VisitedBlock(); 
            arrow_y = arrow_y - 1;
            arrow_x = arrow_x - 1;
        }
        grid [arrow_y] [arrow_x] = arrow;
        
        if (arrow_x == 0 && arrow_y == 0) return true;
        else return false;
    }
    
    private void canMove(int x, int y) throws WallMoveException, BoundryMoveException {
        // Test For Boundry
        if (x >= blocks_x || x < 0) {
            resetGrid();
            throw new BoundryMoveException();
        }
        else if (y >= blocks_y || y < 0) {
            resetGrid();
            throw new BoundryMoveException();
        }
        
        // Test for Walls
        if(grid [y] [x] instanceof WallBlock) {
            resetGrid();
            throw new WallMoveException();
        }
    }
}
