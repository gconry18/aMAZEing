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
    
    private Arrow() {
        setOpaque(true);
        setBackground(Color.green);
        image = new ImageIcon("src/images/arrow_icon.png");
        setIcon(image);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
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
}
