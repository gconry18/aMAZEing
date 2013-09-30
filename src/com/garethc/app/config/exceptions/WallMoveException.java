/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.exceptions;

/**
 *
 * @author gconry
 */
public class WallMoveException extends Exception{

    public WallMoveException() {
        super("You Crashed into a Wall");
    }

    public WallMoveException(String message) {
        super(message);
    }    
}
