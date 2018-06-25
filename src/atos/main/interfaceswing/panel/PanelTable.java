package atos.main.interfaceswing.panel;

import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.objet.CartePanel;
import atos.main.service.CarteService;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Administrateur
 */
public class PanelTable extends JPanel {
    // SERVICES
    PartieService partieService = new PartieService();
    CarteService carteService = new CarteService();
    JoueurService joueurService = new JoueurService();
    
    // Variables d'instance
    List<PanelBot> listeBots;
    JPanel panelBots;
    PanelMainJoueur panelJoueur;
    JPanel panelBoutons;
    TypeCarte selection1 = null, selection2 = null;
    
    ////////////////
    //      CONSTRUCTEUR(s)
    ///////////////////////////
    public PanelTable(Long idJoueur){
        super(new BorderLayout());
        
        Joueur joueurPrincipal = partieService.getJoueur(idJoueur);
        Partie partie = joueurPrincipal.getPartie();
        
        panelBots = new JPanel();
        panelBots.setLayout(new BoxLayout(panelBots, BoxLayout.X_AXIS));
        
        //test avec grid : effet " table poker"
        panelBots.setLayout(new GridLayout(3, 3, 2, 2));
        
        
    // Initialisation des bots et du panel BOT
       for (Joueur j : partie.getJoueurs())
           if (j != joueurPrincipal)
               panelBots.add(new PanelBot(j.getId()));
           
    // Initialisation du panel des boutons de jeu
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.X_AXIS));
        
        JButton boutonLancerSort = new JButton("Lancer sort"), boutonPasserTour = new JButton("Piocher une carte");
        
        boutonLancerSort.addActionListener(boutonLancerListener);
        boutonPasserTour.addActionListener(boutonPasserListener);
        
        panelBoutons.add(boutonLancerSort);
        panelBoutons.add(boutonPasserTour);
                
    // Initialisation du panel Joueur principal
        panelJoueur = new PanelMainJoueur(2L);
        panelJoueur.carteAile.addMouseListener(carteSelectionListener);
        panelJoueur.carteBave.addMouseListener(carteSelectionListener);
        panelJoueur.carteCorne.addMouseListener(carteSelectionListener);
        panelJoueur.carteLapis.addMouseListener(carteSelectionListener);
        panelJoueur.carteMandragore.addMouseListener(carteSelectionListener);
                
        
    // Ajout à this (PanelTable)
        this.add(panelBots, BorderLayout.NORTH);
        this.add(panelJoueur, BorderLayout.CENTER);
        this.add(panelBoutons, BorderLayout.SOUTH);
    }
    
    ////////////////
    //      ActionListener
    ///////////////////////////
    
    ActionListener boutonPasserListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            partieService.piocherCarte(panelJoueur.getIdJoueur());
            panelJoueur.setQuantite();
        }
    };
    ActionListener boutonLancerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selection1 != null && selection2 != null) {
                partieService.jouerSortPANEL(panelJoueur.getIdJoueur(), selection1, selection2);
                
                // SET PANEL 
                //partieService.jouer(partieService.getNextDealer(idJoueur).getId());
            }
        }
    };
    
    ////////////////
    //      Mouse Listener
    ///////////////////////////
    MouseListener carteSelectionListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            setSelection((CartePanel) e.getSource());
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };
    
    ////////////////
    //     Gérance de la sélection des cartes du panel Joueur
    ///////////////////////////
    public void setSelection(CartePanel carte) {
        // UPDATE CARTE DESIGN
        TypeCarte type = carte.getType();
        if (carteService.getNombreCarte(panelJoueur.getIdJoueur(), type) > -1) {
            if (selection1 == null){ 
                if (selection2 != type){
                selection1 = type;
                carte.setBackground(Color.red);
                }
            } else if (selection1 == type) {
                selection1 = null;
                carte.setBackground(null);
            } else if (selection2 == null) {
                if (selection1 != type) {
                    carte.setBackground(Color.yellow);
                    selection2 = type;
                }
            } else if (selection2 == type) {
                selection2 = null;
                carte.setBackground(null);
            }
        }
    }
}
