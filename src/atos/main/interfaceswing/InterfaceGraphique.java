/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;

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
        setVisible(true);
        
        this.getContentPane().add(new PanelPrincipal());
        
    }
    
        
    
                          // add the label to the JFrame
    
    
    
    
}
