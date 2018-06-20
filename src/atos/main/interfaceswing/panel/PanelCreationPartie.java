/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.service.PartieService;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Administrateur
 */
public class PanelCreationPartie extends JPanel{
    
    PartieService partieService = new PartieService();
    
    JLabel nomPartie, nomCreateur, avatarCreateur;
    TextField nomPartie_txt, nomCreateur_txt, avatarCreateur_txt;
    JButton boutonReset, boutonValider;
    
    public PanelCreationPartie() {
        String nomPartie_string = "Nom de la partie";
        String nomCreateur_string = "Nom de votre personnage";
        String avatarCreateur_string = "Sélectionnez un avatar pour cette partie";
        
        this.nomPartie = new JLabel(nomPartie_string);
        this.nomCreateur = new JLabel(nomCreateur_string);
        this.avatarCreateur = new JLabel(avatarCreateur_string);
        
        this.nomPartie_txt = new TextField();
        this.nomCreateur_txt = new TextField();
        this.avatarCreateur_txt = new TextField();
        
        this.boutonReset = new JButton("Reset");
        this.boutonValider = new JButton("Valider");
        
        this.add(nomPartie);
        this.add(nomPartie_txt);
   
        this.add(nomCreateur);
        this.add(nomCreateur_txt);
   
        this.add(avatarCreateur);
        this.add(avatarCreateur_txt);
        
        this.add(boutonReset);
        this.add(boutonValider);
   
        
        this.boutonReset.addActionListener(boutonResetListener);
        this.boutonValider.addActionListener(boutonValiderListener);
    }
    
    public void resetAll(){
        System.out.println("REST");
        this.nomPartie_txt.setText("");
        this.nomCreateur_txt.setText("");
        this.avatarCreateur_txt.setText("");
        this.repaint();
    }
    
    public void valider(){
        String nomPartie = this.nomPartie_txt.getText();
        String nomCreateur = this.nomCreateur_txt.getText();
        String avatar = this.avatarCreateur_txt.getText();
        
        partieService.creer(nomPartie, nomCreateur, avatar);
        JOptionPane.showMessageDialog(null,"Partie créée.");
    }
    
    ActionListener boutonResetListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            resetAll();
        }
    };
    ActionListener boutonValiderListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            valider();
        }
    };
}
