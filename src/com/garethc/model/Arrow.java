/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author gconry
 */
public class Arrow extends JLabel{
    private ImageIcon image;
    private BufferedImage bi;
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
    
    private Arrow() throws IOException {
        setOpaque(true);
        setBackground(new Color(97, 212, 85));
        
        bi = ImageIO.read(new File("src/images/arrow_icon.png"));
        
        //image = new ImageIcon("src/images/arrow_icon.png");
        //setIcon(image);        
        
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBorder(new LineBorder(Color.black));
        setMinimumSize(new Dimension(16, 16));
        orientation = ARROW_NORTH;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        double rotation = 0;
        if (orientation == ARROW_NORTH) rotation = 0;
        else if (orientation == ARROW_NORTHEAST) rotation = Math.PI / 4;
        else if (orientation == ARROW_EAST) rotation = Math.PI / 2;
        else if (orientation == ARROW_SOUTHEAST) rotation = Math.PI / 1.5;
        else if (orientation == ARROW_SOUTH) rotation = Math.PI;
        else if (orientation == ARROW_SOUTHWEST) rotation = Math.PI / -1.5;
        else if (orientation == ARROW_WEST) rotation = Math.PI / -2;
        else if (orientation == ARROW_NORTHWEST) rotation = Math.PI / -4;
        
        g2.rotate(rotation, (double)arrow.getSize().width / 2, (double)arrow.getSize().height / 2);
        g2.drawImage(bi, 0, 0, arrow.getSize().width, arrow.getSize().height, null);
    }
    
    public void scale(int size) {
        setSize(size,size);
    }
    
    public static Arrow getInstance() throws IOException {
        if (arrow == null) {
            arrow = new Arrow();
        }
        return arrow;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
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
