/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.movement;

import com.garethc.app.config.exceptions.BoundryMoveException;
import com.garethc.app.config.exceptions.WallMoveException;
import com.garethc.model.Arrow;
import com.garethc.model.Grid;
import com.garethc.presentation.GameWindow;

/**
 *
 * @author Gareth
 */
public class MovementHandler {

    public static boolean handleMovement(String command) throws Exception {
        System.out.println(command);
        
        command = command.toUpperCase();
        command = command + "#";
        command = command.replaceAll("F", "#F");
        command = command.replaceAll("R", "#R");
        command = command.replaceAll("L", "#L");  
        command = command.replaceAll("F#", "F1#");
        command = command.replaceAll("R#", "R1#");
        command = command.replaceAll("L#", "L1#");
        command = command.substring(1, command.lastIndexOf("#"));
        
        System.out.println(command);
        
        String [] commandArray = command.split("#");
        
        boolean win = false;
        
        for (int i = 0; i < commandArray.length && win == false; i++) {
            if (commandArray[i].charAt(0) == 'F') {
                win = move(commandArray[i]);
            }
            else if (commandArray[i].charAt(0) == 'R' 
                    || commandArray[i].charAt(0) == 'L'){
                rotate(commandArray[i]);
            }
        }
        return win;
    }
    
    private static void rotate (String command) throws Exception {
        System.out.println("Rotate: " + command);
        Arrow arrow = Arrow.getInstance();
        
        char direction = command.charAt(0);
        int moves = Integer.parseInt(command.substring(1));
        
        for (int i = 0; i < moves; i++) {
            if (direction == 'L') arrow.rotateArrowLeft();
            else arrow.rotateArrowRight();
            System.out.println(arrow.getOrientation());
            GameWindow.paintGrid();
            Thread.sleep(250);
        }
    }
    
    private static boolean move (String command) throws WallMoveException, BoundryMoveException, InterruptedException {
        System.out.println("Moving: " + command);
        boolean win = false;
        
        Grid grid = Grid.getInstance();
        int moves = Integer.parseInt(command.substring(1));
        for (int i = 0; i < moves && win == false; i++) {
            win = grid.doMove();
            GameWindow.paintGrid();
            Thread.sleep(250);
        }
        return win;
    }
}
