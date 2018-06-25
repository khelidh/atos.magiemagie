/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mama
 */
public class PanelAffichageAllRejoindre extends JPanel{

    private PartieService partieService = new PartieService();
    private JButton boutonRafraichir;
    private JPanel panelAffichage;

    public PanelAffichageAllRejoindre() {
        super(new BorderLayout());
        
        boutonRafraichir = new JButton("- Rafraîchir la page -");
        
        boutonRafraichir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maj();
            }
        });
        
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage = new JPanel();
        panelAffichage.setLayout(new BoxLayout(panelAffichage, BoxLayout.Y_AXIS));
        
        panelAffichage.add(new PanelAffichageRejoindrePartie());
        
        for (Partie partie : listeParties)
            panelAffichage.add(new PanelAffichageRejoindrePartie(partie));
        
        
        this.add(boutonRafraichir, BorderLayout.NORTH);
        this.add(panelAffichage, BorderLayout.CENTER);
    }

    public void maj() {
        
        panelAffichage.removeAll();
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage.add(new PanelAffichageRejoindrePartie());
        
        for (Partie partie : listeParties)
            panelAffichage.add(new PanelAffichageRejoindrePartie(partie));
        
        revalidate();
        repaint();
    }

    
    
    
    
}
