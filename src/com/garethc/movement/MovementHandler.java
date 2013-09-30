/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.movement;

/**
 *
 * @author Gareth
 */
public class MovementHandler {

    public static void handleMovement(String command) {
        System.out.println(command);
        
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
    
    private static void rotate (String command) {
        System.out.println("Rotating: " + command);
    }
    
    private static void move (String command) {
        System.out.println("Moving: " + command);
    }
}
