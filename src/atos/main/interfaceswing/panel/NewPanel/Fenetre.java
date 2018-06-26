/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel.NewPanel;

import atos.main.entity.Partie;
import atos.main.interfaceswing.menu.Menu;
import atos.main.interfaceswing.panel.PanelAffichageAllRejoindre;
import atos.main.interfaceswing.panel.PanelCreerPartie;
import atos.main.interfaceswing.panel.PanelTable;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Administrateur
 */
public class Fenetre extends JFrame {

    PartieService partieService = new PartieService();
    JoueurService joueurService = new JoueurService();

    JPanel container, entete;
    JPanel tableDeJeu, affichageRejoindre, affichageCreer;
    Menu menu;
    
     ////////////////
    //      Constructor 
    ///////////////////////////
    public Fenetre(String title) throws HeadlessException, IOException {
        super(title);
        //Calcul de la taille effective de l'écran utilisateur
        //Dimension de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Hauteur de la barre des taches linux (ou mac)
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
        int height = screenSize.height - taskBarSize;
        int width = screenSize.width;

        // Initialisation de la fenêtre
        setSize(700, 8000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        entete = new JPanel();
        container = new JPanel();
        menu = new Menu();

        affichageCreer = new PanelCreerPartie();
        affichageRejoindre = new PanelRejoindrePartie();
        ((PanelRejoindrePartie) affichageRejoindre).getBoutonRejoindre().addActionListener(boutonRejoindreTableListener);

        initialisationMenu();
        initEntete();
        initContainer();

        container.setBackground(new Color(237, 50, 50));
        entete.setBackground(new Color(255, 255, 255));
        // Personnalisation
        this.add(entete, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        this.setJMenuBar(menu);

        setVisible(true);
    }

     ////////////////
    //      ActionListner 
    ///////////////////////////
    ActionListener boutonRejoindreTableListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Long id = ((PanelRejoindrePartie) affichageRejoindre).getPanelSectionne().getIdPartie();
            System.out.println("boutonRejoindreTableListener id = " + id);
            Partie partie = partieService.getPartie(id);
            
            JTextField pseudo = new JTextField(), avatar = new JTextField();
            Object[] message = {
                "Username:", pseudo,
                "Avatar:", avatar
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Rejoindre " + partie.getNom(), JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                partieService.rejoindrePartie(pseudo.getText(), avatar.getText(), partie.getId());
                String mess = "Voulez-vous commencer maintenant ?";

                int optionCommencer = JOptionPane.showConfirmDialog(null, mess, "Commencer " + partie.getNom(), JOptionPane.YES_NO_OPTION);

                if (optionCommencer == JOptionPane.YES_OPTION) {
                    partieService.demarrer(partie.getId());
                    partieService.distribuer(partie.getId());
                    Long idJoueur = partieService.getJoueurFirstPosition(id).getId();

                    PanelTable table = new PanelTable(idJoueur);
                    container.removeAll();
                    container.add(table);
                    container.revalidate();
                    container.repaint();
                }
            }

        }
    };
    ActionListener boutonCreationPartieListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() != affichageCreer) {
                container.removeAll();
                ((PanelCreerPartie) affichageCreer).resetAll();
                container.add(affichageCreer);
                container.revalidate();
                container.repaint();
            }
        }
    };
    ActionListener boutonAffichageRejoindrePartie = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            ((PanelRejoindrePartie) affichageRejoindre).update();
            container.add(affichageRejoindre);
            container.revalidate();
            container.repaint();
        }
    };
    ActionListener boutonQuitterApplicationListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    };
    ////////////////
    //      Initialisation 
    ///////////////////////////

    private void initialisationMenu() {
        this.menu.getItemCreationPartie().addActionListener(boutonCreationPartieListener);
        this.menu.getItemRejoindrePartie().addActionListener(boutonAffichageRejoindrePartie);
        this.menu.getItemQuitterApplication().addActionListener(boutonQuitterApplicationListener);
    }

    private void initEntete() {
        JLabel labelEntete = new JLabel("*-_Magie Magie _-*");
        Font font = new Font("Century", Font.BOLD, 36);
        labelEntete.setFont(font);
        entete.add(labelEntete);
    }

    private void initContainer() {
        String chaine = "<html>Bien à vous sorcières et sorciers !<br>Vous entrez dans"
                + " le monde de Magie Magie ! Où vos grimoires, votre aptitude aux sortilèges et votre passion pour la magie"
                + " seront mis à rude épreuve !<br><br><b>Préparez vos baguette, avec le regard tranchant, et devenez MAGIE !</b></html>";
        JLabel label = new JLabel(chaine);
        label.setPreferredSize(new Dimension(500, 500));
        Font font = new Font("Century", Font.CENTER_BASELINE, 25);
        label.setFont(font);
        this.container.add(label);
    }

}
