/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.difficulty;

import com.garethc.app.config.size.SizeGenerator;
import com.garethc.model.Grid;
import javax.swing.JLabel;


public class HardDifficulty implements Difficulty {

    @Override
    public JLabel[][] populateGrid() throws Exception {
        System.out.println("Strategy: Hard");
        
        SizeGenerator sg = new SizeGenerator();
        sg.generateSize();
        
        Grid grid = Grid.getInstance();
        grid.generateGrid(sg.getHorizontal(), sg.getVertical(), 3);
        
        return grid.getGrid();
    }    
}
