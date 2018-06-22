/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.service.PartieService;
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
 * @author Administrateur
 */
public class PanelAffichagePartie extends JPanel {

    PartieService partieService = new PartieService();

    public PanelAffichagePartie() {
        String nom_txt = "Partie";
        String nombre_txt = "Nombre de joueurs";
        String createur_txt = "Créateur de la partie";
        String joueurs_txt = "Autres joueurs dans la partie";

        JLabel nomPartie = new JLabel(nom_txt);
        JLabel nombreJoueurs = new JLabel(nombre_txt);
        JLabel pseudoCreateur = new JLabel(createur_txt);
        JLabel pseudosJoueurs = new JLabel(joueurs_txt);

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
    
    public PanelAffichagePartie(Partie partie) {
        JLabel nomPartie = new JLabel(partie.getNom());
        JLabel nombreJoueurs = new JLabel(partie.getJoueurs().size() + "");
        System.out.println("Partie ID = " + partie.getId());
        JLabel pseudoCreateur = new JLabel(partieService.getJoueurFirstPosition(partie.getId()).getPseudo());
        
        String chaineNomsJoueurs = "";
            for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() != 0L)
                    chaineNomsJoueurs += joueur.getPseudo() + " - ";
            
        JLabel pseudosJoueurs = new JLabel(chaineNomsJoueurs);

        // Ajout bouton rejoindre avec boite de dialogue pour récupérer pseudo & avatar du joueur 
        JButton boutonRejoindre = new JButton("Rejoindre");
        boutonRejoindre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField pseudo = new JTextField(), avatar = new JTextField();

                Object[] message = {
                    "Username:", pseudo,
                    "Avatar:", avatar
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Rejoindre " + partie.getNom(), JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    partieService.rejoindrePartie(pseudo.getText(), avatar.getText(), partie.getId());
                }
            }
        });

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
        this.add(boutonRejoindre);
    }
}
