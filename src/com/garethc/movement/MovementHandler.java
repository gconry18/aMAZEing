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

    public static void handleMovement(String command) throws Exception {
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
        
        for (int i = 0; i < commandArray.length; i++) {
            if (commandArray[i].charAt(0) == 'F') {
                move(commandArray[i]);
            }
            else {
                rotate(commandArray[i]);
            }
        }
    }
    
    private static void rotate (String command) throws Exception {
        System.out.println("Rotating: " + command);
        Arrow arrow = Arrow.getInstance();
        
        char direction = command.charAt(0);
        int moves = Integer.parseInt(command.substring(1));
        
        for (int i = 0; i < moves; i++) {
            if (direction == 'L') arrow.rotateArrowLeft();
            else arrow.rotateArrowRight();
            System.out.println(arrow.getOrientation());
            Thread.sleep(500);
            GameWindow.paintGrid();
        }
    }
    
    private static void move (String command) throws WallMoveException, BoundryMoveException, InterruptedException {
        System.out.println("Moving: " + command);
        
        Grid grid = Grid.getInstance();
        int moves = Integer.parseInt(command.substring(1));
        for (int i = 0; i < moves; i++) {
            grid.doMove();
            Thread.sleep(500);
            GameWindow.paintGrid();
        }
    }
}
