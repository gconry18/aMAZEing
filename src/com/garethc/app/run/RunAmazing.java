/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.run;

import com.garethc.app.config.SizeGenerator;
import com.garethc.presentation.GameWindow;

/**
 *
 * @author gconry
 */
public class RunAmazing {
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        
        SizeGenerator size = new SizeGenerator();
        System.out.println(size.getVertical());
        System.out.println(size.getHorizontal());
    }
}
