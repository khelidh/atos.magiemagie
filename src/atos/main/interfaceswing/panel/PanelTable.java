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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
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
    // List<PanelBot> listeBots;
    JPanel panelBots;
    PanelJoueurPrincipal panelJoueur;
    JPanel panelBoutons;
    TypeCarte selection1 = null, selection2 = null;
    Long idPartie;
    boolean selectionCible;
    Long idCible = null;
    private JLabel aide;
    
    ////////////////
    //      CONSTRUCTEUR(s)
    ///////////////////////////
    public PanelTable(Long idJoueur){
        super(new BorderLayout());
        
        Joueur joueurPrincipal = partieService.getJoueur(idJoueur);
        Partie partie = joueurPrincipal.getPartie();
        
        this.idPartie = partie.getId();
        
        panelBots = new JPanel();
        panelBots.setLayout(new BoxLayout(panelBots, BoxLayout.X_AXIS));
        
        //test avec grid : effet " table poker"
        //panelBots.setLayout(new GridLayout(3, 3, 2, 2));
        
        
    // Initialisation des bots et du panel BOT
       for(Joueur j : partie.getJoueurs())
           if (j != joueurPrincipal){
               PanelBot panel = new PanelBot(j.getId());
               panel.addMouseListener(new MouseListener() {
                   @Override
                   public void mouseClicked(MouseEvent e) {
                       if (true){
                            if (idCible == null){
                               idCible = panel.getIdBot();
                               panel.setBackground(Color.red);
                            } else if (idCible.equals(panel.getIdBot())){
                                idCible = null;
                                panel.setBackground(null);
                            }
                       }
                   }

                   @Override
                   public void mousePressed(MouseEvent e) {
                   }

                   @Override
                   public void mouseReleased(MouseEvent e) {
                   }

                   @Override
                   public void mouseEntered(MouseEvent e) {
                   }

                   @Override
                   public void mouseExited(MouseEvent e) {
                   }
               });
               
               panelBots.add(panel);
           }
               
           
    // Initialisation du panel des boutons de jeu
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.X_AXIS));
        
        JButton boutonLancerSort = new JButton("Lancer sort"), boutonPasserTour = new JButton("Piocher une carte");
        
        boutonLancerSort.addActionListener(boutonLancerListener);
        boutonPasserTour.addActionListener(boutonPasserListener);
        
        panelBoutons.add(boutonLancerSort);
        panelBoutons.add(boutonPasserTour);
                
    // Initialisation du panel Joueur principal
        panelJoueur = new PanelJoueurPrincipal(idJoueur);
        panelJoueur.carteAile.addMouseListener(carteSelectionListener);
        panelJoueur.carteBave.addMouseListener(carteSelectionListener);
        panelJoueur.carteCorne.addMouseListener(carteSelectionListener);
        panelJoueur.carteLapis.addMouseListener(carteSelectionListener);
        panelJoueur.carteMandragore.addMouseListener(carteSelectionListener);
    
    // Initialisation de l'affichage aide
        aide = new JLabel("aide");
        
        
        
        
    // Ajout à this (PanelTable)
        this.add(panelBots, BorderLayout.NORTH);
        this.add(panelJoueur, BorderLayout.CENTER);
        
        JPanel south = new JPanel(new BorderLayout());
        
        south.add(aide, BorderLayout.NORTH);
        south.add(panelBoutons, BorderLayout.SOUTH);
        this.add(south, BorderLayout.SOUTH);
    }
    
    ////////////////
    //      Méthode(s)
    ///////////////////////////
    public void setAide(String aideString){
        aide.setText(aideString);
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
            if (selection1 != null && selection2 != null){
                partieService.jouerSortPANEL(panelJoueur.getIdJoueur(), selection1, selection1);
                
                String sort = partieService.determinerSort(selection1, selection1);
                partieService.supprimerDeuxCartes(panelJoueur.getIdJoueur(), selection1, selection1);
                
                if (sort.equals(PartieService.SORT_HYPSNOSE) || sort.equals(PartieService.SORT_HYPSNOSE)){
                    setSelectionCible(true);
                    //partieService.lancerSortPANEL(, idCible, 5L ,sort);
                    
                }
                
                partieService.lancerSort(panelJoueur.getIdJoueur(), sort);
            }
        }
 };
    
    
    ////////////////
    //      Mouse Listener
    ///////////////////////////
    MouseListener carteSelectionListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("dzlql");
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
    ////////////////////
    //                  Get     
    //////////////////////////////////////////////////////////////////

    public TypeCarte getSelection1() {
        return selection1;
    }

    public void setSelection1(TypeCarte selection1) {
        this.selection1 = selection1;
    }

    public TypeCarte getSelection2() {
        return selection2;
    }

    public void setSelection2(TypeCarte selection2) {
        this.selection2 = selection2;
    }

    public Long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }

    public boolean isSelectionCible() {
        return selectionCible;
    }

    public void setSelectionCible(boolean selectionCible) {
        this.selectionCible = selectionCible;
    }

    public Long getIdCible() {
        return idCible;
    }

    public void setIdCible(Long idCible) {
        this.idCible = idCible;
    }
    
    
     
    
}
