/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.frame;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.menu.Menu;
import atos.main.interfaceswing.panel.PanelAffichagePartie;
import atos.main.interfaceswing.panel.PanelCreerPartie;
import atos.main.interfaceswing.panel.PanelJoueur;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mama
 */
public class InterfaceGraphique extends JFrame {
    
    PartieService partieService = new PartieService();
    JoueurService joueurService = new JoueurService();
    
    JPanel container, entete;
    Menu menu;

    public InterfaceGraphique(String title) throws HeadlessException, IOException {
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
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        entete = new JPanel();
        container = new JPanel();
        menu = new Menu();

        initialisationMenu();
        initEntete();
        initContainer();
        
        // Personnalisation
        this.add(entete, BorderLayout.NORTH);
        this.add(container, BorderLayout.CENTER);
        this.setJMenuBar(menu);
        
        setVisible(true);
    }
    
    ActionListener boutonCreationPartieListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            container.add(new PanelCreerPartie());
            container.revalidate();
            container.repaint();
        }
    };
    
    ActionListener boutonRejoindrePartieListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            affichageParties();
        }
    };
    
    ActionListener boutonRelancerApplicationListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            container.removeAll();
            container.add(new PanelJoueur(1L));
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

    private void initialisationMenu() {
        this.menu.getItemCreationPartie().addActionListener(boutonCreationPartieListener);
        this.menu.getItemRejoindrePartie().addActionListener(boutonRejoindrePartieListener);
        this.menu.getItemRelancerApplication().addActionListener(boutonRelancerApplicationListener);
        this.menu.getItemQuitterApplication().addActionListener(boutonQuitterApplicationListener);
    }
    private void initEntete() {
        JLabel labelEntete = new JLabel("*-_Magie Magie _-*");
        Font font = new Font("Century", Font.BOLD, 30);
        labelEntete.setFont(font);
        entete.add(labelEntete);
    }
    private void initContainer() {
        String chaine = "Bien à vous sorcières et sorciers ! Vous entrez dans"
                + "le monde de Magie Magie ! Où vos grimoires, votre aptitude aux sortilèges et votre passion pour la magie"
                + " seront mis à rude épreuve ! Préparez vos baguette, avec le regard tranchant, et devenez MAGIE !";
        JLabel label = new JLabel(chaine);
        label.setPreferredSize(new Dimension(getWidth()/2, getHeight()/2));
        Font font = new Font("Century", Font.CENTER_BASELINE, 18);
        label.setFont(font);
        this.container.add(label);
        
    }
    
    public void affichePartiesEnPreparation(){
        this.container.removeAll();
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();

        JPanel affichageListeParties = new JPanel();
        affichageListeParties.setLayout(new BoxLayout(affichageListeParties, BoxLayout.X_AXIS));
        
        String nom_txt = "Partie";
        String nombre_txt = "Nombre de joueurs";
        String createur_txt = "Créateur de la partie";
        String joueurs_txt = "Autres joueurs dans la partie";

        JLabel nomPartie = new JLabel(nom_txt);
        JLabel nombreJoueurs = new JLabel(nombre_txt);
        JLabel pseudoCreateur = new JLabel(createur_txt);
        JLabel pseudosJoueurs = new JLabel(joueurs_txt);
        
        JPanel panelPartieNom = new JPanel();
        panelPartieNom.setLayout(new BoxLayout(panelPartieNom, BoxLayout.Y_AXIS));
        panelPartieNom.add(nomPartie);
        
        JPanel panelNombreJoueur = new JPanel();
        panelNombreJoueur.setLayout(new BoxLayout(panelNombreJoueur, BoxLayout.Y_AXIS));
        panelNombreJoueur.add(nombreJoueurs);
        
        JPanel panelCreateurNom = new JPanel();
        panelCreateurNom.setLayout(new BoxLayout(panelCreateurNom, BoxLayout.Y_AXIS));
        panelCreateurNom.add(pseudoCreateur);
        
        JPanel panelJoueursNom = new JPanel();
        panelJoueursNom.setLayout(new BoxLayout(panelJoueursNom, BoxLayout.Y_AXIS));
        panelJoueursNom.add(pseudosJoueurs);
        
        JPanel panelBoutons= new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        
        
        for (Partie partie : listeParties) {
            
            panelPartieNom.add(new JLabel(partie.getNom()));
            panelNombreJoueur.add(new JLabel(partie.getJoueurs().size() + ""));
            try {
                panelCreateurNom.add(new JLabel(partieService.getJoueurByPosition(partie.getId(),0L).getPseudo()+ ""));        
            } catch (Exception e) {
                System.out.println("EXEVOTIN");
            }
                
            
            String chaineNomsJoueurs = "";
            for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() != 0L)
                    chaineNomsJoueurs += joueur.getPseudo() + " - ";
            
            panelJoueursNom.add(new JLabel(chaineNomsJoueurs));
            
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
                if (option == JOptionPane.OK_OPTION)
                     partieService.rejoindrePartie(pseudo.getText(), avatar.getText(), partie.getId());
            }
            });
            
            
            panelBoutons.add(boutonRejoindre);
        }
        
        affichageListeParties.add(panelPartieNom);
        affichageListeParties.add(panelNombreJoueur);
        affichageListeParties.add(panelCreateurNom);
        affichageListeParties.add(panelJoueursNom);
        affichageListeParties.add(panelBoutons);
        
        //Ajout dans le container
        this.container.add(affichageListeParties);
        revalidate();
        repaint();
    }
    
    public void affichageParties(){
        this.container.removeAll();
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        
        JPanel panelAffichage = new JPanel();
        panelAffichage.setLayout(new BoxLayout(panelAffichage, BoxLayout.Y_AXIS));
        
        panelAffichage.add(new PanelAffichagePartie());
        
        for (Partie partie : listeParties)
            panelAffichage.add(new PanelAffichagePartie(partie));
        
        this.container.add(panelAffichage);
        revalidate();
        repaint();
    }
}
