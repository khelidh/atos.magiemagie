/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.dao;

import atos.main.entity.Joueur;
import atos.main.entity.Joueur.EtatJoueur;
import atos.main.entity.Partie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mama
 */
public class JoueurDAO {
    
    public EntityManager makeEM(){
        return Persistence.createEntityManagerFactory("PU").createEntityManager();
    }
    
    public void insert(Joueur joueur){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.persist(joueur);
        em.getTransaction().commit();
    }
    
    public void delete(Joueur joueur){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.remove(em.merge(joueur));
        em.getTransaction().commit();
    }
    
    public Long findPartieID(Long idJoueur){
        String requete = "SELECT j.partie.id"
                + "     FROM Joueur j"
                + "     WHERE j.id = :idJoueur";
        Query query = makeEM().createQuery(requete);
        query.setParameter("idJoueur", idJoueur);
        return (Long) query.getSingleResult();
    }
    
    public Joueur findById(long id){
        return (Joueur) makeEM().find(Joueur.class, id);
    }
    public List<Joueur> findAllFromPartie(Partie partie){
        EntityManager em = makeEM();
        String requete = ""
                + " SELECT j"
                + " FROM Joueur j"
                + " WHERE j.partie.id = :idPartie";

        Query query = em.createQuery(requete);
        
        query.setParameter("idPartie", partie.getId());
        
        return query.getResultList();
    }
    
    public List<Joueur> findAllFromPartieExecptOne(Joueur joueur){
        EntityManager em = makeEM();
        String requete = ""
                + " SELECT j"
                + " FROM Joueur j"
                + " WHERE j.partie.id = :idPartie"
                + " AND j.id != :idJoueur"
                + " AND j.etat in(:etatJoueur,:etatJoueur2, :etatJoueur3)";

        Query query = em.createQuery(requete);
        query.setParameter("idPartie", joueur.getPartie().getId());
        query.setParameter("idJoueur", joueur.getId());
        query.setParameter("etatJoueur", EtatJoueur.PAS_LA_MAIN);
        query.setParameter("etatJoueur2", EtatJoueur.EN_SOMMEIL);
        query.setParameter("etatJoueur3", EtatJoueur.A_LA_MAIN);
        return query.getResultList();
    }
    public void update(Joueur joueur){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.merge(joueur);
        em.getTransaction().commit();
    }
    public void update(ArrayList<Joueur> joueurs){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        for (Joueur joueur : joueurs)
            em.merge(joueur);
        em.getTransaction().commit();
    }
    
    public Joueur findJoueurByPosition(Long position, Long idPartie){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        String requete = "SELECT j"
                + " FROM Joueur j"
                + " WHERE j.position = :position"
                + " AND j.partie.id = :idPartie";
       
        Query query = em.createQuery(requete);
        
        query.setParameter("position", position);
        query.setParameter("idPartie", idPartie);
        
        return (Joueur) query.getSingleResult();
    }
    
    /**
     * Permet de trouver un joueur dans la BDD via son pseudo.
     * S'il n'existe pas, on renvoie un nouvel objet Joueur avec le pseudo.
     * @param String pseudo
     * @return Joueur joueur
 
     */
    public Joueur findJoueurByPseudo(String pseudo){
        EntityManager em = makeEM();
        String requete = "SELECT j"
                + " FROM Joueur j"
                + " WHERE j.pseudo = :pseudo";
        
        Query query = em.createQuery(requete);
        query.setParameter("pseudo", pseudo);
        
        Joueur joueur;
        
        try {
            joueur = (Joueur) query.getSingleResult();
        } catch (Exception e) {
            joueur = new Joueur(pseudo);
        }
        
        return joueur;
    }
    
    
}
