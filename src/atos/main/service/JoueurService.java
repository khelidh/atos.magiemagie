/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.service;

import atos.main.dao.CarteDAO;
import atos.main.dao.JoueurDAO;
import atos.main.dao.PartieDAO;
import atos.main.entity.Carte;
import atos.main.entity.Joueur;
import atos.main.entity.Joueur.EtatJoueur;
import atos.main.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mama
 */
public class JoueurService {  
    JoueurDAO joueurDAO = new JoueurDAO();
    CarteService serviceCarte = new CarteService();
    PartieDAO partieDAO = new PartieDAO();
       
    public String mainToString(Joueur joueur){
        
        int indice = 1;
        String chaine = "Main de " + joueur.getAvatar() + "  :";
        for (Carte carte : joueur.getCartes()){
             chaine += "  " + indice + " - " + carte.getType() + "  |";
             indice++;
        }
        return chaine;
    }
    public void afficher(Joueur joueur){
        String chaine = ""
                + "Avatar : " + joueur.getAvatar()
                + "     Pseudo : " + joueur.getPseudo()
                + "\n Main : " + mainToString(joueur);
        
        System.out.println(chaine);
    }
    public void insertJoueur(Joueur joueur){
        joueurDAO.insert(joueur);
    }
    public void updateJoueur(Joueur joueur){
        joueurDAO.update(joueur);
    }
    public void deleteJoueur(Joueur joueur) {
        //joueur.getPartie().getJoueurs().remove(joueur);
        //partieDAO.update(joueur.getPartie());
        for (Carte carte : joueur.getCartes())
            serviceCarte.deleteCarte(carte);
        joueurDAO.delete(joueur);
    }
    public void supprimerCartes(Joueur joueur, Carte carte1, Carte carte2){

        joueur.getCartes().remove(carte1);
        joueur.getCartes().remove(carte2);
        
        carte1.setJoueur(null);
        carte2.setJoueur(null);
      
        serviceCarte.deleteCarte(carte1);
        serviceCarte.deleteCarte(carte2);
        updateJoueur(joueur);
    }
    
    public String selectionPseudo(){
        String txt;
        do {
        System.out.println("Veuillez choisir un pseudonyme !");
        Scanner scanner = new Scanner(System.in);
        txt = scanner.nextLine();    
        } while (txt.length() < 3);
            
        
        System.out.println("Votre pseudo est " + txt);
        return txt;
        
    }
    public String selectionAvatar(){ 
        int avatar;
        Scanner scanner = new Scanner(System.in);
        do{
        System.out.println("Choississez un avatar :");
        System.out.println("1 - Avatar 1");
        System.out.println("2 - Avatar 2");
        System.out.println("3 - Avatar 3");
            try {
                avatar = scanner.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Vous avez surement fait une faute de frappe ! On vous remet ça ;)");
                avatar = 0;
            }
        
        } while (avatar < 1 && avatar > 3);
        
        switch (avatar){
            case 1 :
                return "Avatar 1";
            case 2 :
                return "Avatar 2";
            case 3:
                return "Avatar 3";
        }
        return "Avatar inconnu";
    }
    
    
}
