/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.exceptions;

/**
 *
 * @author gconry
 */
public class BoundryMoveException extends Exception{

    public BoundryMoveException() {
        super("You Crashed into the Boundry");
    }

    public BoundryMoveException(String message) {
        super(message);
    }    
}
