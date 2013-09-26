/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.presentation;

import com.garethc.model.Arrow;
import com.garethc.model.PathBlock;
import com.garethc.model.WallBlock;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author gconry
 */
public class GameWindow extends JFrame implements ActionListener{

    public GameWindow() {
        super();
        setTitle("aMAZEing Game");
        setSize(800, 600);
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu Bar
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);
        
        JMenu menuFile = new JMenu("File");
        menuFile.add(menuItemExit);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        
        setJMenuBar(menuBar);
        //--       
        Arrow arrow = Arrow.getInstance();
        arrow.setLocation(20, 20);
        arrow.scale(20);
        PathBlock pb = PathBlock.getInstance();
        pb.setLocation(60, 40);
        pb.scale(20);
        WallBlock wb = WallBlock.getInstance();
        wb.setLocation(80, 60);
        wb.scale(20);
        
        add(arrow);
        add(pb);
        add(wb);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        String cmd = e.getActionCommand();
        
        if (cmd.equals("Exit")) {
            System.exit(0);
        }
    }
}
