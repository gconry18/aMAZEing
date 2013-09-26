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
public class VisitedBlock extends JLabel{
    
    public VisitedBlock() {
        setOpaque(true);
        setBackground(Color.green);
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
    }
    
    public void scale(int size) {
        setSize(size,size);
    }
}
