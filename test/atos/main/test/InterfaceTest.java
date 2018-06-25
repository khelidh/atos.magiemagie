/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.test;

import atos.main.interfaceswing.panel.PanelJoueurPrincipal;
import javax.swing.JFrame;
import org.junit.Test;

/**
 *
 * @author mama
 */
public class InterfaceTest {
    
    @Test
    public void testPanelJoueur(){
        
        JFrame fen = new JFrame();
        fen.add(new PanelJoueurPrincipal(1L));
        fen.setVisible(true);
    }
    
}
