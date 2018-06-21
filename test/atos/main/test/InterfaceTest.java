/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.test;

import atos.main.interfaceswing.panel.PanelJoueur;
import javax.swing.JFrame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mama
 */
public class InterfaceTest {
    
    @Test
    public void testPanelJoueur(){
        
        JFrame fen = new JFrame();
        fen.add(new PanelJoueur(1L));
        fen.setVisible(true);
    }
    
}
