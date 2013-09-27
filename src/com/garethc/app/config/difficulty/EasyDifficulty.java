/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.difficulty;

import com.garethc.app.config.size.SizeGenerator;
import com.garethc.model.Grid;

/**
 *
 * @author gconry
 */
public class EasyDifficulty implements Difficulty {

    @Override
    public void populateGrid() {
        SizeGenerator sg = new SizeGenerator();
        sg.generateSize();
        
        Grid grid = Grid.getInstance();
    }
    
}
