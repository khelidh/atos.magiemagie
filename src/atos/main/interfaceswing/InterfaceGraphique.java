/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import atos.main.interfaceswing.panel.PanelAffichagePartie;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

/**
 *
 * @author mama
 */
public class InterfaceGraphique extends JFrame {

    public InterfaceGraphique(String title) throws HeadlessException {
        super(title);

        //Calcul de la taille effective de l'écran utilisateur
        //Dimension de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Hauteur de la barre des taches linux (ou mac)
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        int height = screenSize.height - taskBarSize;
        int width = screenSize.width;
        
        // Initialisation de la fenêtre
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        
        Menu menu = new Menu();
        
        JMenuItem itemRejoindre = (JMenuItem) menu.menuJouer.getItem(1);
        
        itemRejoindre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    panelPrincipal.container.removeAll();
                    panelPrincipal.container.add(panelPrincipal.listerParties());
                } catch (NullPointerException ex) {
                }
                
                repaint();
            }
        });
        
        
        // Personnalisation
        this.add(panelPrincipal);
        this.setJMenuBar(menu);
        
        setVisible(true);
    }
}
