/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Administrateur
 */
public class PanelCreerPartie extends JPanel{
    
    PartieService partieService = new PartieService();
    
    JLabel nomPartie, nomCreateur, avatarCreateur;
    TextField nomPartie_txt, nomCreateur_txt, avatarCreateur_txt;
    JButton boutonReset, boutonValider;
    
    public PanelCreerPartie() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        
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
        
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(nomPartie,BorderLayout.NORTH);
        //nomPartie.setPreferredSize(new Dimension(getWidth(), getHeight()));
        panel1.add(nomPartie_txt, BorderLayout.SOUTH);
   
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(nomCreateur, BorderLayout.NORTH);
        panel2.add(nomCreateur_txt, BorderLayout.SOUTH);
   
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(avatarCreateur, BorderLayout.NORTH);
        panel3.add(avatarCreateur_txt, BorderLayout.SOUTH);
        
        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.add(boutonReset, BorderLayout.NORTH);
        panel4.add(boutonValider, BorderLayout.SOUTH);
        
        
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
   
        
        this.boutonReset.addActionListener(boutonResetListener);
        this.boutonValider.addActionListener(boutonValiderListener);
    }
    
    public void resetAll(){
        System.out.println("REST");
        this.nomPartie_txt.setText(" ");
        this.nomCreateur_txt.setText(" ");
        this.avatarCreateur_txt.setText(" ");
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
