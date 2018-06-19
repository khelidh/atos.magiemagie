/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author mama
 */
public class PanelPrincipal extends JPanel {

    public PanelPrincipal(LayoutManager layout) {
        super(layout);
    }

    public PanelPrincipal() {
        
        this.setLayout(new BorderLayout());
        JPanel panelEntete = new JPanel();
        
        panelEntete.add(new JButton("COMMENCER"));
        panelEntete.add(new JLabel("Salut"));
        
        this.add(panelEntete, BorderLayout.CENTER);
        
        
        
        
        
    }
    
    
    
    
    
    
}
