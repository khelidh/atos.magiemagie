/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.interfaceswing.objet.CartePanel;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mama
 */
public class PanelMainJoueur extends JPanel{
    
    PartieService partieService = new PartieService();
    
    JPanel entete, main;
    CartePanel carteAile, carteBave, carteCorne, carteLapis, carteMandragore;
    
    public PanelMainJoueur(){
        super(new BorderLayout());
        
        
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
        
        this.entete.add(new JLabel("entete"));
        
        this.add(entete, BorderLayout.NORTH);
        this.add(main, BorderLayout.SOUTH);
    }
    public PanelMainJoueur(Long idJoueur){
        super(new BorderLayout());
        this.entete = new JPanel(new BoxLayout(this.entete, BoxLayout.X_AXIS));
        this.main = new JPanel(new BoxLayout(this.main, BoxLayout.X_AXIS));
        
        carteAile = new CartePanel(TypeCarte.AILE_DE_CHAUVE_SOURIS);
        carteBave = new CartePanel(TypeCarte.BAVE_DE_CRAPAUD);
        carteCorne = new CartePanel(TypeCarte.CORNE_DE_LICORNE);
        carteLapis= new CartePanel(TypeCarte.LAPIS_LAZULI);
        carteMandragore = new CartePanel(TypeCarte.MANDRAGORE);
        
        setQuantite(idJoueur);
        
        this.main.add(carteAile);
        this.main.add(carteBave);
        this.main.add(carteCorne);
        this.main.add(carteLapis);
        this.main.add(carteMandragore);
        this.add(entete, BorderLayout.NORTH);
        this.add(main, BorderLayout.SOUTH);
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
    
    
}
