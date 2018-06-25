package atos.main.service;

import atos.main.dao.CarteDAO;
import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
/**
 * @author mama
 */
public class CarteService {
    CarteDAO carteDAO = new CarteDAO();
   
    public Carte tirer(Joueur joueur){
        int indiceType = (int) (Math.random()*TypeCarte.values().length);    
        return new Carte(TypeCarte.values()[indiceType], joueur);
    }
    public void updateCarte(Carte carte) {
        carteDAO.update(carte);
    }

    public void deleteCarte(Carte carte) {
        carteDAO.delete(carte);
    }
    
    public Carte getCarte(Long idJoueur, TypeCarte type){
        return carteDAO.getCarte(idJoueur, type);
    }
    
    public Long getNombreCarte(Long idJoueur, TypeCarte type){
        return carteDAO.findNombreCarte(idJoueur, type);
    }

}
