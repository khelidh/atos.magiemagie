/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.panel.PanelAffichagePartie;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * @author mama
 */
public class PanelPrincipal extends JPanel {

    PartieService partieService = new PartieService();
    JoueurService joueurService = new JoueurService();
    
    JPanel container;

    public PanelPrincipal() {
        super(new BorderLayout());
        String txt_titre = "#--- Magie Magie ---#";
        
        JPanel panelEntete = new JPanel();
        JPanel container = new JPanel();
        
        JLabel titre = new JLabel(txt_titre);
        titre.setFont(new Font("Century", Font.BOLD, 30));
        
        
        
        //Ajouts des composants
        panelEntete.add(titre);
        this.add(panelEntete, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
    }
    
    
    public JPanel listerParties(){
        System.out.println("ListerPartie");
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();

        JPanel affichageListeParties = new JPanel();
        affichageListeParties.setLayout(new BoxLayout(affichageListeParties, BoxLayout.Y_AXIS));

        affichageListeParties.add(new PanelAffichagePartie());
        
        for (Partie partie : listeParties) {
            affichageListeParties.add(new PanelAffichagePartie(partie));
        }
        return affichageListeParties;
    }
    
    
    
}
