/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.model;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author gconry
 */
public class Arrow extends JLabel{
    private ImageIcon image;
    private static Arrow arrow;
    
    public static final int ARROW_NORTH = 0;
    public static final int ARROW_NORTHEAST = 1;
    public static final int ARROW_EAST = 2;
    public static final int ARROW_SOUTHEAST = 3;
    public static final int ARROW_SOUTH = 4;
    public static final int ARROW_SOUTHWEST = 5;
    public static final int ARROW_WEST = 6;
    public static final int ARROW_NORTHWEST = 7;
    
    private int orientation;
    
    private Arrow() {
        setOpaque(true);
        setBackground(Color.green);
        image = new ImageIcon("src/images/arrow_icon.png");
        setIcon(image);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
        orientation = ARROW_NORTH;
    }
    
    public void scale(int size) {
        setSize(size,size);
    }
    
    public static Arrow getInstance() {
        if (arrow == null) {
            arrow = new Arrow();
        }
        return arrow;
    }
    
    public void rotateArrowLeft() {
        if (orientation != 0) {
            orientation = orientation - 1;
        }
        else {
            orientation = 7;
        }
    }
    
    public void rotateArrowRight() {
        if (orientation != 7) {
            orientation = orientation + 1;
        }
        else {
            orientation = 0;
        }
    }
}
