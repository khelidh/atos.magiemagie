/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.service;

import atos.main.dao.PartieDAO;
import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.interfaceswing.frame.InterfaceGraphique;
import atos.main.service.CarteService;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mama
 */
public class Main {
    
    public static void main(String[] args) throws HeadlessException, IOException {
        PartieService servicePartie = new PartieService();
        JoueurService serviceJoueur = new JoueurService();
        CarteService serviceCarte = new CarteService();

        

//        Partie partie = servicePartie.creer("Partie TESQT22", "Miyamotooo", "M");
//        Long idPartie = partie.getId();
//        
//        servicePartie.rejoindrePartie("Kobi", "K", idPartie);
//        servicePartie.rejoindrePartie("Faramir", "F", idPartie);
//        servicePartie.rejoindrePartie("Hobbit", "H", idPartie);
//        servicePartie.demarrer(idPartie);
//        servicePartie.distribuer(idPartie);
//        servicePartie.volerCarteAleatoireFromJoueur(1L, 2L);
          new InterfaceGraphique("- Magie Magie -");

        //servicePartie.application();
    }
}
