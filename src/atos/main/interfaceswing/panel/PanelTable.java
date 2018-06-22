/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.interfaceswing.objet.CartePanel;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
    
    PartieService partieService = new PartieService();
    
    List<PanelBot> listeBots;
    JPanel panelBots;
    PanelMainJoueur panelJoueur;
    JPanel panelBoutons;
    TypeCarte selection1 = null, selection2 = null;
    
    public PanelTable(int nombreBots){
        super(new BorderLayout());
        
    // Initialisation des bots et du panel BOT
        listeBots = new ArrayList<>();
        for (int i = 0; i < nombreBots; i++)
            listeBots.add(new PanelBot());
        
        panelBots = new JPanel();
        panelBots.setLayout(new BoxLayout(panelBots, BoxLayout.X_AXIS));
        
        for (PanelBot bot : listeBots)
            panelBots.add(bot);
        
    // Initialisation du panel des boutons de jeu
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.X_AXIS));
        JButton boutonLancerSort = new JButton("Lancer sort"), boutonPasserTour = new JButton("Piocher une carte");
        
        boutonLancerSort.addActionListener(boutonLancerListener);
        boutonPasserTour.addActionListener(boutonPasserListener);
        
        panelBoutons.add(boutonLancerSort);
        panelBoutons.add(boutonPasserTour);
                
        // Initialisation du panel Joueur principal
        panelJoueur = new PanelMainJoueur();
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
    
    public void setInformations(Long idPartie){
        
    }
    
    
    ActionListener boutonPasserListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            partieService.piocherCarte(panelJoueur.getIdJoueur());
        }
    };
    ActionListener boutonLancerListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Long idJoueur = panelJoueur.getIdJoueur();
            
            partieService.jouerSort(idJoueur);
            partieService.isJoueurStillAlive(idJoueur);
            partieService.jouer(partieService.getNextDealer(idJoueur).getId());
            
        /* 
            String sort = determinerSort(selection1, selection2);
            serviceJoueur.supprimerCartes(joueur, carte1, carte2);
            lancerSort(idJoueur, sort);
        */
        
        }
    };
    
    MouseListener carteSelectionListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            CartePanel carte = (CartePanel) e.getSource();
            TypeCarte type = carte.getType();
            
            setSelection(carte);
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
    };
    
    public void setSelection(CartePanel carte) {
        // UPDATE CARTE DESIGN
        TypeCarte type = carte.getType();
        
        if (selection1 == null){
            selection1 = type;
            carte.setBackground(Color.red);
        }
        else if (selection1 == type){
            selection1 = null;
            carte.setBackground(null);
        }
         else 
            if (selection2 == type) 
                selection2 = null;
            else 
                selection2 = type;
        
    }
    
}
