/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.presentation;

import com.garethc.movement.MovementHandler;
import com.garethc.app.config.difficulty.DifficultyControl;
import com.garethc.app.config.difficulty.EasyDifficulty;
import com.garethc.app.config.difficulty.HardDifficulty;
import com.garethc.app.config.difficulty.MediumDifficulty;
import com.garethc.app.config.exceptions.BoundryMoveException;
import com.garethc.app.config.exceptions.WallMoveException;
import com.garethc.app.config.size.SizeGenerator;
import com.garethc.model.PathBlock;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.*;

/**
 *
 * @author gconry
 */
public class GameWindow extends JFrame implements ActionListener {
    private static JPanel gridPanel;
    private static JLabel [] [] gridArray;
    DifficultyControl dc = new DifficultyControl();
    private JTextField textMovement;
    private JButton buttonMove;
    private static Container container;

    public GameWindow() {
        super();
        setTitle("aMAZEing Game");
        setSize(560, 650);
        setLayout(null);
        setResizable(false);
        container = getContentPane();
        
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
        
        textMovement = new JTextField();
        textMovement.setLocation(20, 20);
        textMovement.setSize(300, 20);
        textMovement.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                key = Character.toUpperCase(key);
                //System.out.println(key);
                if (key != 'F') {
                    if (key != 'L') {
                        if (key != 'R') {
                            if (!(key>='0' && key<='9')) {
                                e.consume();
                            }
                        }
                    }
                }                            
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        buttonMove = new JButton("Move!");
        buttonMove.setLocation(340, 10);
        buttonMove.setSize(100, 40);
        buttonMove.addActionListener(this);
        
        
        gridPanel = new JPanel();
        gridPanel.setSize(500,500);
        gridPanel.setLocation(20, 60); 
        //gridPanel.setBorder(new LineBorder(Color.black));       
        
//        Arrow arrow = Arrow.getInstance();
//        arrow.setLocation(0, 0);
//        arrow.scale(20);
        
        add(textMovement);
        add(buttonMove);
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
            int scale = 500 / sgen.getHorizontal();
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
            try {
                gridArray = dc.populateGrid();
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();
        }
        
        else if (cmd.equals("Medium")) {
            dc.setStrategy(new MediumDifficulty());
            try {   
                gridArray = dc.populateGrid();
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();         
        }
        
        else if (cmd.equals("Hard")) {
            dc.setStrategy(new HardDifficulty());
            try {
                gridArray = dc.populateGrid();
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();            
        }
        
        else if (cmd.equals("Move!")) {
            System.out.println("Begin Move: ");
            try {
                MovementHandler.handleMovement(textMovement.getText());
            } 
            catch (WallMoveException ex) {
                JOptionPane.showMessageDialog(null, "You Crashed into a Wall!");
            }
            catch (BoundryMoveException ex) {
                JOptionPane.showMessageDialog(null, "You Crashed into the Boundry!");
            }
            catch (IOException ex) {
                System.out.println("IO Error: " + ex);
            } 
            catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);                
            }
            //repaint();
        }
    }
    
    public static void paintGrid() {
        gridPanel.removeAll();
        int x = gridArray[0].length;
        int y = gridArray.length;
        
        System.out.println("x: " + x + " y: " + y);
        
        int scale = 500 / x;
        
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
        container.update(container.getGraphics());
    }
}
