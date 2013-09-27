/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.difficulty;

/**
 *
 * @author gconry
 */
public class DifficultyControl {
    private Difficulty strategy;

    public void setStrategy(Difficulty strategy) {
        this.strategy = strategy;
    }
    
    public void populateGrid(){
        strategy.populateGrid();
    }
}
