/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.panel.PanelCreationPartie;
import atos.main.interfaceswing.panel.PanelJeu;
import atos.main.interfaceswing.panel.PanelPartieAffichage;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author mama
 */
public class PanelPrincipal extends JPanel{

    PartieService partieService = new PartieService();
    JPanel container, panelEntete;

    public PanelPrincipal() throws IOException {
        super(new BorderLayout());
        String txt_titre = "#--- Magie Magie ---#";
        
        this.panelEntete = new JPanel();
        this.container = new JPanel();
        
        JLabel titre = new JLabel(txt_titre);
        titre.setFont(new Font("Century", Font.BOLD, 30));
        
        //Ajouts des composants
        panelEntete.add(titre);
        
        
        container.add(new PanelJeu(partieService.getJoueur(1L)));
       
        
        this.add(panelEntete, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
    }
    
    public void addPanelAffichageParties(){
        this.container.removeAll();
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();

        JPanel affichageListeParties = new JPanel();
        affichageListeParties.setLayout(new BoxLayout(affichageListeParties, BoxLayout.Y_AXIS));

        affichageListeParties.add(new PanelPartieAffichage());
        
        for (Partie partie : listeParties)
            affichageListeParties.add(new PanelPartieAffichage(partie));
        
        //Ajout dans le container
        this.container.add(affichageListeParties);
        validate();
        repaint();
    }
    
    public void addPanelCreationPartie(){
        this.container.removeAll();
        
        PanelCreationPartie panel = new PanelCreationPartie();
        this.container.add(panel);
        
        validate();
        repaint();
    }

    
   
}
