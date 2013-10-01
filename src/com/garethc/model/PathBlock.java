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
public class PathBlock extends JLabel{
    
    public PathBlock() {
        setOpaque(true);
        setBackground(new Color(85, 148, 212));
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
    }
    
//    public void scale(int size) {
//        setSize(size,size);
//    }
}
