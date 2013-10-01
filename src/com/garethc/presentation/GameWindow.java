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
import com.garethc.model.Grid;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
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
    private JLabel labelCommand;
    private JButton buttonMove;
    private static Container container;

    public GameWindow() {
        super();
        setTitle("aMAZEing Game");
        setSize(550, 650);
        setLayout(null);
        setResizable(false);
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-560/2, dim.height/2-650/2);
                
        container = getContentPane();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menu Bar
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(this);        
        JMenu menuFile = new JMenu("File");
        menuFile.add(menuItemExit);
        
        JMenuItem menuItemEasy = new JMenuItem("Easy");
        menuItemEasy.addActionListener(this);
        JMenuItem menuItemMedium = new JMenuItem("Medium");
        menuItemMedium.addActionListener(this);
        JMenuItem menuItemHard = new JMenuItem("Hard");
        menuItemHard.addActionListener(this);
        JMenu menuGame = new JMenu("New Game");
        menuGame.add(menuItemEasy);
        menuGame.add(menuItemMedium);
        menuGame.add(menuItemHard);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuFile);
        menuBar.add(menuGame);
        
        setJMenuBar(menuBar);
        //--
        
        textMovement = new JTextField();
        textMovement.setLocation(20, 10);
        textMovement.setSize(400, 20);
        textMovement.setEnabled(false);
        textMovement.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                key = Character.toUpperCase(key);
                System.out.println(key);
                if (key == '\n') buttonMove.doClick();
                else if (key != 'F') {
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
        
        labelCommand = new JLabel();
        labelCommand.setText("<html>L(n) (Rotate Left) | R(n) (Rotate Right) | F(n) (Move Forward)"
                                + "<br>(n) is any whole number</html>");
        labelCommand.setLocation(20, 30);
        labelCommand.setSize(400, 40);
        
        buttonMove = new JButton("Move!");
        buttonMove.setLocation(440, 10);
        buttonMove.setSize(100, 40);
        buttonMove.addActionListener(this);
        
        
        gridPanel = new JPanel();
        gridPanel.setSize(500,500);
        gridPanel.setLocation(20, 80); 
        //gridPanel.setBorder(new LineBorder(Color.black));       
                
        add(textMovement);
        add(labelCommand);
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
        
        else if (cmd.equals("Easy")) {
            dc.setStrategy(new EasyDifficulty());
            try {
                gridArray = dc.populateGrid();
                textMovement.setEnabled(true);
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();
        }
        
        else if (cmd.equals("Medium")) {
            dc.setStrategy(new MediumDifficulty());
            try {   
                gridArray = dc.populateGrid();
                textMovement.setEnabled(true);
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();         
        }
        
        else if (cmd.equals("Hard")) {
            dc.setStrategy(new HardDifficulty());
            try {
                gridArray = dc.populateGrid();
                textMovement.setEnabled(true);
            } catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);   
            }
            paintGrid();            
        }
        
        else if (cmd.equals("Move!")) {
            System.out.println("Begin Move: ");
            try {
                if (textMovement.getText().length() > 0) {
                    if(MovementHandler.handleMovement(textMovement.getText())) {
                       JOptionPane.showMessageDialog(container, "You WIN ! ! !");
                       textMovement.setEnabled(false);
                    }
                }
            } 
            catch (WallMoveException ex) {
                JOptionPane.showMessageDialog(container, "You Crashed into a Wall!");
                Grid grid = Grid.getInstance();
                grid.resetGrid();
                paintGrid();
            }
            catch (BoundryMoveException ex) {
                JOptionPane.showMessageDialog(container, "You Crashed into the Boundry!");
                Grid grid = Grid.getInstance();
                grid.resetGrid();
                paintGrid();
            }
            catch (IOException ex) {
                System.out.println("IO Error: " + ex);
            } 
            catch (Exception ex) {
                System.out.println("Unknown Error: " + ex);                
            }
            finally {
                textMovement.setText("");
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
