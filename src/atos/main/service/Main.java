/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.service;

import atos.main.dao.PartieDAO;
import atos.main.entity.Joueur;
import atos.main.interfaceswing.InterfaceGraphique;
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
        
        //Long idPartie = 1L;
       
//        servicePartie.creer("Miyamoto", "M");
//        serviceJoueur.rejoindrePartie("Kobi", "K", idPartie);
//        serviceJoueur.rejoindrePartie("Faramir", "F", idPartie);
//        serviceJoueur.rejoindrePartie("Hobbit", "H", idPartie);
          new InterfaceGraphique("- Magie Magie -");
          //servicePartie.application();
    }
}
