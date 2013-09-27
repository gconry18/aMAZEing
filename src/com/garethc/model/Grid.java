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
    
    private Grid() {        
    }
    
    public static Grid getInstance() {
        if (gridInstance == null) {
            gridInstance = new Grid();
        }
        return gridInstance;
    }
    
    private void setGridSize(int x, int y) {
        grid = new JLabel[x][y];
    }
    
    public void generateGrid (int x, int y) {
        setGridSize(x, y);
        
        FinishBlock finish = FinishBlock.getInstance();
        Arrow arrow = Arrow.getInstance();
        
        int scale = 700 / x;
        int posx = 0;
        int posy = 0;
        for (int j = 0; j < x; j++)
        {
            for (int i = 0; i < y; i++)
            {
                PathBlock pb = new PathBlock();
                pb.setLocation(x, y);
                posx = posx + scale;
                pb.scale(scale);
            }
            posx = 0;
            posy = posy + scale;
        }
        
        grid [0] [0] = finish;
        grid [x-1] [y-1] = arrow;
    }
}
