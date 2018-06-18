/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.service;

import atos.main.dao.PartieDAO;
import atos.main.entity.Joueur;
import atos.main.service.CarteService;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.util.ArrayList;

/**
 *
 * @author mama
 */
public class Main {
    
    public static void main(String[] args) {
        PartieService servicePartie = new PartieService();
        JoueurService serviceJoueur = new JoueurService();
        CarteService serviceCarte = new CarteService();
        
        
        Long idPartie = 1L;
        //ArrayList<Joueur> joueurs = new ArrayList<>();
        
        //Joueur j = new Joueur("Miyamoto");
        servicePartie.creer("Miyamoto", "M");

        serviceJoueur.rejoindrePartie("Kobi", "K", idPartie);
        serviceJoueur.rejoindrePartie("Faramir", "F", idPartie);
        serviceJoueur.rejoindrePartie("Hobbit", "H", idPartie);
             
        servicePartie.demarrer(idPartie);
        servicePartie.distribuer(idPartie);
        System.out.println("NOMBRE DE JOUEUR EN LICE = " + new PartieDAO().findNombreJoueurEnLice(idPartie));
        //servicePartie.updatePartie(1L);
        servicePartie.affichage(idPartie);
        servicePartie.commencer(idPartie);
//        servicePartie.lancerSortInvisibilite(1L);
//        servicePartie.lancerSortHypnose(1L,2L);
//        servicePartie.lancerSortHypnose(1L,2L);
//        servicePartie.lancerSortHypnose(1L,2L);
        //servicePartie.jouerTour(serviceJoueur.findDealerID(idPartie));
        //servicePartie.jouerTour(1L);
        //servicePartie.affichage(idPartie);
        servicePartie.affichage(idPartie);
        System.out.println("NOMBRE DE JOUEUR EN LICE = " + new PartieDAO().findNombreJoueurEnLice(idPartie));
        //servicePartie.jouerTour(servicePartie.getDealerID(1L));
    }
}
