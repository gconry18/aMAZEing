/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.size;

/**
 *
 * @author gconry
 */
public class SizeGenerator {
    private int vertical;
    private int horizontal;

    public SizeGenerator() {
        this.generateSize();
    }

    public SizeGenerator(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }    
    
    public void generateSize() {
        generateHorizontalSize();
        generateVerticalSize();
    }

    private void generateHorizontalSize() {
        double h = 0;
        horizontal = (int) (h*100);
        
        while ((horizontal < 10 || horizontal > 50) || vertical % 2 != 0 ) {
            h = Math.random();
            horizontal = (int) (h*100);        
        }
    }

    private void generateVerticalSize() {
        double v = 0;
        vertical = (int) (v*100);
        
        while ((vertical < 10 || vertical > 50) 
                || (vertical % 2 != 0) 
                || vertical > horizontal) {
            v = Math.random();
            vertical = (int) (v*100);        
        }
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizontal() {
        return horizontal;
    }    
}
