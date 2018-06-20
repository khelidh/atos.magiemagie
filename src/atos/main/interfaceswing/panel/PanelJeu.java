/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Carte;
import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.objet.CarteFX;
import atos.main.service.PartieService;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author Administrateur
 */
public class PanelJeu extends JPanel {
    PartieService partieService = new PartieService();
    JPanel joueurPrincipal = new JPanel();

    public PanelJeu(Joueur joueur) {
        this.add(initialiseMainJoueur(joueur));
    }
    
    public JPanel initialiseMainJoueur(Joueur joueur){
        JPanel panel = new JPanel();
        System.out.println("nombre de cartes du joueur : " + joueur.getCartes().size());
        for(Carte carte : joueur.getCartes())
            panel.add(new CarteFX(1, carte, new Dimension(50, 75)));
        
        return panel;
    }
    
    public void initPanelJeu(){
        
    }
    
}
