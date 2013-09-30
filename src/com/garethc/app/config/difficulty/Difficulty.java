/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garethc.app.config.difficulty;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author gconry
 */
public interface Difficulty {
    public JLabel [] [] populateGrid() throws Exception;
}
