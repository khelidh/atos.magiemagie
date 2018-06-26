/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel.NewPanel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.service.PartieService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Administrateur
 */
public class PanelAffichagePartie extends JPanel{
    JLabel nomPartie, nombreJoueurs, pseudoCreateur, pseudosJoueurs;
    
    PartieService partieService = new PartieService();
    private Long idPartie;
    
    ////////////////
    //      Constructor
    ///////////////////////////
    
    public PanelAffichagePartie() {
        String nom_txt = "Partie";
        String nombre_txt = "Nombre de joueurs";
        String createur_txt = "Créateur de la partie";
        String joueurs_txt = "Autres joueurs dans la partie";

        nomPartie = new JLabel(nom_txt);
        nombreJoueurs = new JLabel(nombre_txt);
        pseudoCreateur = new JLabel(createur_txt);
        pseudosJoueurs = new JLabel(joueurs_txt);

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
    
    public PanelAffichagePartie(Partie p) {
        
        this.idPartie = p.getId();
        Partie partie = partieService.getPartie(idPartie);
        
        nomPartie = new JLabel(partie.getNom());
        nombreJoueurs = new JLabel(partie.getJoueurs().size() + "");
        pseudoCreateur = new JLabel(partieService.getJoueurFirstPosition(partie.getId()).getPseudo());
        
        String chaineNomsJoueurs = "";
            for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() != 0L)
                    chaineNomsJoueurs += joueur.getPseudo() + " - ";
            
        pseudosJoueurs = new JLabel(chaineNomsJoueurs);

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
    
    ////////////////
    //      Méthodes
    ///////////////////////////
    
    public void update(){
        Partie partie = partieService.getPartie(idPartie);
        
        nomPartie.setText(partie.getNom());
        nombreJoueurs.setText(partie.getJoueurs().size() + "");
        
        String chaineNomsJoueurs = "";
            for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() != 0L)
                    chaineNomsJoueurs += joueur.getPseudo() + " - ";
            
        pseudosJoueurs.setText(chaineNomsJoueurs);
    }

    ////////////////
    //      Get & Set
    ///////////////////////////
    public Long getIdPartie() {
        return idPartie;
    }
    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }
    
}
