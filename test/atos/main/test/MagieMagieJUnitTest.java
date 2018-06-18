/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.test;

import atos.main.dao.CarteDAO;
import atos.main.dao.JoueurDAO;
import atos.main.dao.PartieDAO;
import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.entity.Joueur.EtatJoueur;
import atos.main.entity.Partie;
import atos.main.service.CarteService;
import atos.main.service.JoueurService;
import atos.main.service.PartieService;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrateur
 */
public class MagieMagieJUnitTest {
    
    public MagieMagieJUnitTest() {
    }
    
    @Test
    public void demarrerJPA(){
        Persistence.createEntityManagerFactory("PU");
    }
    //@Test
    public void testInsertCarte(){
        CarteDAO carteDAO = new CarteDAO();
        
        carteDAO.insert(new Carte(TypeCarte.AILE_DE_CHAUVE_SOURIS));  
    }  
    //@Test
    public void testInsertJoueur(){
        JoueurDAO joueurDAO = new JoueurDAO();
        CarteDAO carteDAO = new CarteDAO();
        PartieDAO partieDAO = new PartieDAO();
        
        Joueur j1 = new Joueur("Maki");
        Joueur j2 = new Joueur("Hiko");
        Joueur j3 = new Joueur("Kobi");
        
        joueurDAO.insert(j1);
        joueurDAO.insert(j2);
        joueurDAO.insert(j3);
        
        Carte carte = new Carte(TypeCarte.AILE_DE_CHAUVE_SOURIS, j1);
        Carte carte2 = new Carte(TypeCarte.BAVE_DE_CRAPAUD , j3);
        Carte carte3 = new Carte(TypeCarte.MANDRAGORE, j2);
        
        carteDAO.insert(carte);
        carteDAO.insert(carte2);
        carteDAO.insert(carte3);
        
    }   
    
    @Test
    public void testRequeteFindNextDealer(){
        
        JoueurService joueurService = new JoueurService();
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        String requete = "SELECT j"
                + " FROM Joueur j"
                + " WHERE j.partie.id = :idPartie"
                + " AND j.etat in(:etatPasLaMain, :etatSommeil)"
                + " ORDER BY j.position ASC"
                + " HAVING j.position > :position"
                + " LIMIT 1";
        
        String requete2 = "SELECT j"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.position > :position"
                + "     AND j.etat in(:etatPasLaMain, :etatSommeil)"
                + "     ORDER BY j.position";
        
        Query query = em.createQuery(requete2);
        query.setMaxResults(1);
        
       
        assertEquals(new PartieService().getJoueur(4L),query.getSingleResult());
    }
    
    
    @Test
    public void testFindDealerID(){
        
        
        
        
    }
}
