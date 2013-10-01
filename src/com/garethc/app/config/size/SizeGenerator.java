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
    
    private final int MIN = 10;
    private final int MAX = 25;

    public SizeGenerator() {
        this.generateSize();
    }

//    public SizeGenerator(int vertical, int horizontal) {
//        this.vertical = vertical;
//        this.horizontal = horizontal;
//    }    
    
    public void generateSize() {
        generateHorizontalSize();
        generateVerticalSize();
    }

    private void generateHorizontalSize() {
        double h = 0;
        horizontal = (int) (h*100);
        
        while ((horizontal < MIN || horizontal > MAX)) {
            h = Math.random();
            horizontal = (int) (h*100);        
        }
    }

    private void generateVerticalSize() {
        double v = 0;
        vertical = (int) (v*100);
        
        while ((vertical < MIN || vertical > MAX) 
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
