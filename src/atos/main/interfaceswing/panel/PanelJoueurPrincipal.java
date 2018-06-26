package atos.main.interfaceswing.panel;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.objet.CartePanel;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author mama
 */
public class PanelJoueurPrincipal extends JPanel {
    
    PartieService partieService = new PartieService();
    
    Long idJoueur;
    JPanel entete, main;
    CartePanel carteAile, carteBave, carteCorne, carteLapis, carteMandragore;
    JLabel pseudoLabel, tourLabel, nombreJoueursRestantsLabel;
    
    ////////////////
    //      CONSTRUCTEUR
    ///////////////////////////
    public PanelJoueurPrincipal(Long idJoueur){
        super(new BorderLayout());
        this.idJoueur = idJoueur; 
        
        entete = new JPanel();
        entete.setLayout(new BoxLayout(entete, BoxLayout.X_AXIS));
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        
        carteAile = new CartePanel(TypeCarte.AILE_DE_CHAUVE_SOURIS);
        carteBave = new CartePanel(TypeCarte.BAVE_DE_CRAPAUD);
        carteCorne = new CartePanel(TypeCarte.CORNE_DE_LICORNE);
        carteLapis= new CartePanel(TypeCarte.LAPIS_LAZULI);
        carteMandragore = new CartePanel(TypeCarte.MANDRAGORE);
        
        main.add(carteAile);
        main.add(carteBave);
        main.add(carteCorne);
        main.add(carteLapis);
        main.add(carteMandragore);
        
        
        this.pseudoLabel = new JLabel();
        this.tourLabel = new JLabel();
        this.nombreJoueursRestantsLabel = new JLabel();
        
        
        setQuantite(this.idJoueur);
        setEntete(this.idJoueur);
        
        this.entete.add(pseudoLabel);
        this.entete.add(nombreJoueursRestantsLabel);
        this.entete.add(tourLabel);
        
        this.add(entete, BorderLayout.NORTH);
        this.add(main, BorderLayout.SOUTH);
    }
    
    public void changementJoueur(Long idJoueur){
        setIdJoueur(idJoueur);
        setEntete();
        setQuantite();
        revalidate();
        repaint();
    }
    
    ////////////////
    //      GET & SET - ERS
    ///////////////////////////
    public void setQuantite(){
        Joueur joueur = partieService.getJoueur(this.idJoueur);
        int nbAile = 0, nbBave = 0, nbCorne = 0, nbLapis = 0, nbMandragore = 0;
        for (Carte carte : joueur.getCartes()){
            switch (carte.getType()){
                case AILE_DE_CHAUVE_SOURIS :
                    nbAile++;
                    break;
                case BAVE_DE_CRAPAUD :
                    nbBave++;
                    break;
                case CORNE_DE_LICORNE :
                    nbCorne++;
                    break;
                case LAPIS_LAZULI :
                    nbLapis++;
                    break;
                case MANDRAGORE :
                    nbMandragore++;
                    break;
            }
            
            carteAile.setQuantite(nbAile);
            carteBave.setQuantite(nbBave);
            carteCorne.setQuantite(nbCorne);
            carteLapis.setQuantite(nbLapis);
            carteMandragore.setQuantite(nbMandragore);
        }

        
    }
    public void setQuantite(Long idJoueur){
        Joueur joueur = partieService.getJoueur(idJoueur);
        int nbAile = 0, nbBave = 0, nbCorne = 0, nbLapis = 0, nbMandragore = 0;
        for (Carte carte : joueur.getCartes()){
            switch (carte.getType()){
                case AILE_DE_CHAUVE_SOURIS :
                    nbAile++;
                    break;
                case BAVE_DE_CRAPAUD :
                    nbBave++;
                    break;
                case CORNE_DE_LICORNE :
                    nbCorne++;
                    break;
                case LAPIS_LAZULI :
                    nbLapis++;
                    break;
                case MANDRAGORE :
                    nbMandragore++;
                    break;
            }
            
            carteAile.setQuantite(nbAile);
            carteBave.setQuantite(nbBave);
            carteCorne.setQuantite(nbCorne);
            carteLapis.setQuantite(nbLapis);
            carteMandragore.setQuantite(nbMandragore);
        }

        
    }
    public void setEntete(){
        Joueur joueur = partieService.getJoueur(idJoueur);
        Partie partie = joueur.getPartie();
        
        this.pseudoLabel.setText(joueur.getPseudo());
        
        String nombreJoueurs = " " + partieService.getJoueurs(partie.getId()).size();
        this.nombreJoueursRestantsLabel.setText(nombreJoueurs);
        
        String tourJoueur = partieService.getJoueurALaMain(partie.getId()).getPseudo();
        this.tourLabel.setText(tourJoueur);
    }
    public void setEntete(Long idJoueur){
        Joueur joueur = partieService.getJoueur(idJoueur);
        Partie partie = joueur.getPartie();
        
        this.pseudoLabel.setText(joueur.getPseudo());
        
        String nombreJoueurs = " " + partieService.getJoueurs(partie.getId()).size();
        this.nombreJoueursRestantsLabel.setText(nombreJoueurs);
        
        String tourJoueur = partieService.getJoueurALaMain(partie.getId()).getPseudo();
        this.tourLabel.setText(tourJoueur);
    }

    public void setTourLabel(String nomJoueurQuiALeTour) {
        this.tourLabel.setText(nomJoueurQuiALeTour);
    }
    public void setNombreJoueursRestantsLabel(int nombre) {
        this.nombreJoueursRestantsLabel.setText("" + nombre);
    }
    public Long getIdJoueur() {
        return idJoueur;
    }
    public void setIdJoueur(Long idJoueur) {
        this.idJoueur = idJoueur;
    }
}
