/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.presentation;

import com.garethc.app.config.SizeGenerator;
import com.garethc.model.Arrow;
import com.garethc.model.PathBlock;
import com.garethc.model.WallBlock;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author gconry
 */
public class GameWindow extends JFrame implements ActionListener{
    private JPanel gridPanel;

    public GameWindow() {
        super();
        setTitle("aMAZEing Game");
        setSize(800, 700);
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu Bar
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);        
        JMenu menuFile = new JMenu("File");
        menuFile.add(menuItemExit);
        
        JMenuItem menuItemGenerate = new JMenuItem("Generate");
        menuItemGenerate.addActionListener(this);
        JMenu menuGame = new JMenu("Game");
        menuGame.add(menuItemGenerate);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuGame);
        
        setJMenuBar(menuBar);
        //--
        
        gridPanel = new JPanel();
        gridPanel.setSize(700,500);
        gridPanel.setLocation(20, 20); 
        gridPanel.setBorder(new LineBorder(Color.black));       
        
//        Arrow arrow = Arrow.getInstance();
//        arrow.setLocation(0, 0);
//        arrow.scale(20);
        
        add(gridPanel);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        
        if (cmd.equals("Exit")) {
            System.exit(0);
        }
        
        else if (cmd.equals("Generate")) {
            gridPanel.removeAll();
            SizeGenerator sgen = new SizeGenerator();
            // Decide on scale
            int scale = 700 / sgen.getHorizontal();
            gridPanel.setSize(scale*sgen.getHorizontal(),scale*sgen.getVertical());
            int x = 0;
            int y = 0;
            for (int j = 0; j < sgen.getVertical(); j++)
            {
                for (int i = 0; i < sgen.getHorizontal(); i++)
                {
                    PathBlock pb = new PathBlock();
                    pb.setLocation(x, y);
                    x=x+scale;
                    pb.scale(scale);
                    gridPanel.add(pb);
                }
                x = 0;
                y = y + scale;
            }
            this.repaint();
        }
    }
}
