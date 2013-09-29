/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.model;

import javax.swing.JLabel;

/**
 *
 * @author gconry
 */
public class Grid {
    private static Grid gridInstance;
    private JLabel grid [] [];
    private int walls;
    private int paths;
    
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
    }
    
    public void generateGrid (int x, int y, int difficulty) {
        setGridSize(x, y);
        
        FinishBlock finish = FinishBlock.getInstance();
        Arrow arrow = Arrow.getInstance();
        
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
        
        double ratio = (double)paths / (double)walls;
        System.out.println("Paths: " + paths + "\nWalls: " + walls + "\nRatio: " + ratio);
    }
    
    public JLabel[][] getGrid() {
        return grid;        
    }
}
