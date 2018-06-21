/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.interfaceswing.objet.CarteFX;
import atos.main.interfaceswing.objet.DosCarte;
import atos.main.service.CarteService;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Administrateur
 */
public class PanelJoueur extends JPanel{
    
    CarteService carteService = new CarteService();
    PartieService partieService = new PartieService();
    
    JPanel main, entete;
    //JPanel carteMandragore, carteBave, carteAile, carteLipi, carteCorne;
    
    public PanelJoueur(Long idJoueur) {
        super(new BorderLayout());
        
        this.entete = new JPanel();
        this.entete.setLayout(new BoxLayout(this.entete, BoxLayout.X_AXIS));
        
        this.main = new JPanel();
        this.main.setLayout(new BoxLayout(this.main, BoxLayout.X_AXIS));
        
        
        initMainJoueur(idJoueur);
        
        this.add(entete, BorderLayout.NORTH);
        this.add(main, BorderLayout.SOUTH);
    }
    
    private void initMainJoueur(Long idJoueur){
        this.main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        
        for (TypeCarte type : TypeCarte.values()) {
            JPanel panel = new JPanel(new BorderLayout());
            
            panel.add(new CarteFX(type, 125, 175), BorderLayout.NORTH);

            String nombreTypeCarte = "" + type + " : " + carteService.getNombreCarte(idJoueur, type);
            
            // - centrer : SwingConstants.CENTER ;
            JLabel label = new JLabel(nombreTypeCarte, SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(25, 25));
            
            panel.add(label, BorderLayout.SOUTH);
            panel.setPreferredSize(new Dimension(150,200));
            
            this.main.add(panel);
        }
    }
    
    
    
}
