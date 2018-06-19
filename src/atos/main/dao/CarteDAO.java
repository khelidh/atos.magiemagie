/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.dao;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


/**
 * @author mama
 */
public class CarteDAO {
    public EntityManager makeEM(){
        return Persistence.createEntityManagerFactory("PU").createEntityManager(); 
    }
    public void insert(Carte carte){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.persist(carte);
        em.getTransaction().commit();  
    }
    public void delete(Carte carte) {
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.remove(em.merge(carte));
        em.getTransaction().commit(); 
    }
    public void update(Carte carte){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.merge(carte);
        em.getTransaction().commit();  
    }
    public void update(List<Carte> cartes){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        for(Carte carte : cartes)
            em.merge(carte);
        em.getTransaction().commit();
    }
    public List<Carte> findByJoueurID(Joueur joueur){
        EntityManager em = makeEM();
        long id = joueur.getId();
        String requete = ""
                + " SELECT carte"
                + " FROM Carte carte"
                + " WHERE carte.joueur.id = " + id;
        
        Query query = em.createQuery(requete);
        
        return query.getResultList();
    }
    public Carte findById(Long id){
        return makeEM().find(Carte.class, id);
    }
}
