/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.presentation;

import com.garethc.app.config.difficulty.DifficultyControl;
import com.garethc.app.config.difficulty.EasyDifficulty;
import com.garethc.app.config.difficulty.HardDifficulty;
import com.garethc.app.config.difficulty.MediumDifficulty;
import com.garethc.app.config.size.SizeGenerator;
import com.garethc.model.PathBlock;
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
    private JLabel [] [] gridArray;
    DifficultyControl dc = new DifficultyControl();

    public GameWindow() {
        super();
        setTitle("aMAZEing Game");
        setSize(1000, 1000);
        setLayout(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu Bar
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);        
        JMenu menuFile = new JMenu("File");
        menuFile.add(menuItemExit);
        
        JMenuItem menuItemGenerate = new JMenuItem("Generate");
        menuItemGenerate.addActionListener(this);
        JMenuItem menuItemEasy = new JMenuItem("Easy");
        menuItemEasy.addActionListener(this);
        JMenuItem menuItemMedium = new JMenuItem("Medium");
        menuItemMedium.addActionListener(this);
        JMenuItem menuItemHard = new JMenuItem("Hard");
        menuItemHard.addActionListener(this);
        JMenu menuGame = new JMenu("Game");
        menuGame.add(menuItemGenerate);
        menuGame.add(menuItemEasy);
        menuGame.add(menuItemMedium);
        menuGame.add(menuItemHard);
        
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
        
        else if (cmd.equals("Easy")) {
            dc.setStrategy(new EasyDifficulty());
            gridArray = dc.populateGrid();
            paintGrid();
        }
        
        else if (cmd.equals("Medium")) {
            dc.setStrategy(new MediumDifficulty());
            gridArray = dc.populateGrid();   
            paintGrid();         
        }
        
        else if (cmd.equals("Hard")) {
            dc.setStrategy(new HardDifficulty());
            gridArray = dc.populateGrid();
            paintGrid();            
        }
    }
    
    private void paintGrid() {
        gridPanel.removeAll();
        int x = gridArray[0].length;
        int y = gridArray.length;
        
        System.out.println("x: " + x + " y: " + y);
        
        int scale = 700 / x;
        
        System.out.println("scale: " + scale);
        
        gridPanel.setSize(scale*x,scale*y);
        int posx = 0;
        int posy = 0;
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                //System.out.println(gridArray[i][j]);
                JLabel block = gridArray[i][j];
                block.setSize(scale,scale);
                block.setLocation(posx, posy);                
                gridPanel.add(block);
                
                posx += scale;
            }
            posx = 0;
            posy += scale;
        }
        this.repaint();
    }
}
