/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.service;

import atos.main.dao.CarteDAO;
import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author mama
 */
public class CarteService {
    CarteDAO carteDAO = new CarteDAO();
    
    public Carte tirer(){
        return new Carte();
    }
    public Carte tirer(Joueur joueur){
        int indiceType = (int) (Math.random()*TypeCarte.values().length);    
        return new Carte(TypeCarte.values()[indiceType], joueur);
    }
    public void piocher(Joueur joueur){
        int indiceType = (int) (Math.random()*TypeCarte.values().length);
        carteDAO.insert(new Carte(TypeCarte.values()[indiceType], joueur));
    }   
    public List<Carte> getCartesFromJoueur(Joueur joueur){
        return carteDAO.findByJoueurID(joueur);
    }

    public void updateCarte(Carte carte) {
        carteDAO.update(carte);
    }

    void deleteCarte(Carte carte) {
        carteDAO.delete(carte);
    }
    
    public Long getNombreCarte(Long idJoueur, TypeCarte type){
        return carteDAO.findNombreCarte(idJoueur, type);
    }

}
