/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.model;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author gconry
 */
public class WallBlock extends JLabel{
    private static WallBlock block;
    
    private WallBlock() {
        setOpaque(true);
        setBackground(Color.red);
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
    }
    
    public void scale(int size) {
        setSize(size,size);
    }
    
    public static WallBlock getInstance() {
        if (block == null) {
            block = new WallBlock();
        }
        return block;
    }
}
