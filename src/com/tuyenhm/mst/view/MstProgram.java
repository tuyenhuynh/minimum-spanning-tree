/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuyenhm.mst.view;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author tuyenhuynh
 */
public class MstProgram {
    
    public static void main(String[] argv) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame app = new MstFrame();
                app.setVisible(true);
                app.setLocationRelativeTo(null);
                app.setTitle("MST Visualization");
            }
        });
    }
}
