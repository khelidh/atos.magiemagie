/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mama
 */
public class PanelAffichagePartie extends JPanel {

    JLabel nomPartie, nombreJoueurs, pseudoCreateur, pseudosJoueurs;
    String nom_txt, nombre_txt, createur_txt, joueurs_txt;

    public PanelAffichagePartie() {
        nom_txt = "Partie";
        nombre_txt = "Nombre de joueurs";
        createur_txt = "Créateur de la partie";
        joueurs_txt = "Autres joueurs dans la partie";

        nomPartie = new JLabel(nom_txt);
        nombreJoueurs = new JLabel(nombre_txt);
        pseudoCreateur = new JLabel(createur_txt);
        pseudosJoueurs = new JLabel(joueurs_txt);
        
        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
    
    public PanelAffichagePartie(Partie partie) {
        nom_txt = partie.getNom();
        nombre_txt = partie.getJoueurs().size() + "";
        
        createur_txt = "";
        joueurs_txt = "";
        
        for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() == 0L)
                    createur_txt = joueur.getPseudo();
                else
                    joueurs_txt += joueur.getPseudo() + " - ";
        
        nomPartie = new JLabel(nom_txt);
        nombreJoueurs = new JLabel(nombre_txt);
        pseudoCreateur = new JLabel(createur_txt);
        pseudosJoueurs = new JLabel(joueurs_txt);
        
        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
}
