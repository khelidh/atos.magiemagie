/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.service.PartieService;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mama
 */
public class PanelPartieAffichage extends JPanel {
    PartieService partieService = new PartieService();
    

    JLabel nomPartie, nombreJoueurs, pseudoCreateur, pseudosJoueurs;
    String nom_txt, nombre_txt, createur_txt, joueurs_txt;
    JButton boutonRejoindre;

    public PanelPartieAffichage() {
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
    
    public PanelPartieAffichage(Partie partie) {
        
        boutonRejoindre = new JButton("Rejoindre");
        boutonRejoindre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField pseudo = new JTextField(), avatar = new JTextField();

                Object[] message = {
                    "Username:", pseudo,
                    "Avatar:", avatar
                };
                
                int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION)
                     partieService.rejoindrePartie(pseudo.getText(), avatar.getText(), partie.getId());
            }
        });
        
        
        nom_txt = partie.getNom();
        nombre_txt = partie.getJoueurs().size() + "";

        createur_txt = "";
        joueurs_txt = "";

        for (Joueur joueur : partie.getJoueurs()) {
            if (joueur.getPosition() == 0L) {
                createur_txt = joueur.getPseudo();
            } else {
                joueurs_txt += joueur.getPseudo() + " - ";
            }
        }

        nomPartie = new JLabel(nom_txt);
        nombreJoueurs = new JLabel(nombre_txt);
        pseudoCreateur = new JLabel(createur_txt);
        pseudosJoueurs = new JLabel(joueurs_txt);
        
        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
        this.add(boutonRejoindre);
    }
}
