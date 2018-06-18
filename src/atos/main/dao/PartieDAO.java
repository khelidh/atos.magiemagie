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
public class PartieDAO {
    
    public EntityManager makeEM(){
        return Persistence.createEntityManagerFactory("PU").createEntityManager();
    }
    
    public void insert(Partie partie){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.persist(partie);
        em.getTransaction().commit();
    }
    
    public void update(Partie partie){
        EntityManager em = makeEM();
        em.getTransaction().begin();
        em.merge(partie);
        em.getTransaction().commit();
    }
    
    public Partie findById(Long id){
        return (Partie) makeEM().find(Partie.class, id);
    }
    
    public List<Joueur> findJoueurEnLice(Long idPartie) {
        String requete = "SELECT j"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.etat in (:etatPasLaMain, :etatALaMain, :etatEnSommeil)";
        Query query = makeEM().createQuery(requete);
        
        query.setParameter("idPartie", idPartie);
        query.setParameter("etatPasLaMain", EtatJoueur.PAS_LA_MAIN);
        query.setParameter("etatALaMain", EtatJoueur.A_LA_MAIN);
        query.setParameter("etatEnSommeil", EtatJoueur.EN_SOMMEIL);
    
        return query.getResultList();
    }
    public Long findNombreJoueurEnLice(Long idPartie){
        String requete = "SELECT COUNT(j)"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.etat in (:etatPasLaMain, :etatALaMain, :etatEnSommeil)";
        
        Query query = makeEM().createQuery(requete);
        
        query.setParameter("idPartie", idPartie);
        query.setParameter("etatPasLaMain", EtatJoueur.PAS_LA_MAIN);
        query.setParameter("etatALaMain", EtatJoueur.A_LA_MAIN);
        query.setParameter("etatEnSommeil", EtatJoueur.EN_SOMMEIL);
        
        return (Long) query.getSingleResult();
    }
 
    public Long findLastPositionWithMax(Long idPartie){
        EntityManager em = makeEM();
        
        String requete = "SELECT MAX(j.position)"
                + " FROM Joueur j"
                + " WHERE j.partie.id = " + idPartie;
        
        long position;
        try {
            position = (long) em.createQuery(requete).getSingleResult();
        } catch (NullPointerException e) {
            position = 1;
        }
        return position;
    }   
    public Long findNombreJoueurEnAttente(Long idPartie){
        
        String requete = "SELECT count(j) FROM Joueur j"
                + "     WHERE j.etat = :etat"
                + "     AND j.partie.id = :id";
        
        Query query = makeEM().createQuery(requete);
        query.setParameter("etat", Joueur.EtatJoueur.EN_ATTENTE);
        query.setParameter("id", idPartie);
        
        long result = 0;
        try {
            result = (long) query.getSingleResult();
        } catch (IllegalArgumentException e) {
            
        }
        return result;
        
    }
    public Long findDealerID(Long idPartie){
        String requete = "SELECT j.id"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.etat = :etatALaMain";
        
        Query query = makeEM().createQuery(requete);
        query.setParameter("idPartie", idPartie);
        query.setParameter("etatALaMain", EtatJoueur.A_LA_MAIN);
        
        return (Long) query.getSingleResult();
    }
    public Long findNextDealerPosition(Joueur dealer){
        String requete = "SELECT MIN(j.position)"
                + " FROM Joueur j"
                + " WHERE j.partie.id = :idPartie"
                + " AND j.etat in(:etatPasLaMain, :etatSommeil)"
                + " AND j.position > :position";
        
        Query query = makeEM().createQuery(requete);
        
        query.setParameter("position", dealer.getPosition());
        query.setParameter("idPartie", dealer.getPartie().getId());
        query.setParameter("etatPasLaMain", Joueur.EtatJoueur.PAS_LA_MAIN);
        query.setParameter("etatSommeil", Joueur.EtatJoueur.EN_SOMMEIL);
        
        return (Long) query.getSingleResult();   
    }
    
    /**
     *  Renvoies le prochain joueur toujours actif dans le jeu : PAS_LA_MAIN ou EN_SOMMEIL
     * @param dealer
     * @return (Joueur) nextDealer
     */
    public Joueur findNextDealer(Joueur dealer){
        String requete = ""
                + "     SELECT j"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.position > :position"
                + "     AND j.etat in(:etatPasLaMain, :etatSommeil)"
                + "     ORDER BY j.position ASC";
        
        Query query = makeEM().createQuery(requete);
        
        query.setParameter("position", dealer.getPosition());
        query.setParameter("idPartie", dealer.getPartie().getId());
        query.setParameter("etatPasLaMain", Joueur.EtatJoueur.PAS_LA_MAIN);
        query.setParameter("etatSommeil", Joueur.EtatJoueur.EN_SOMMEIL);
        
        query.setMaxResults(1);
        
        Joueur nextDealer;
        try {
            nextDealer = (Joueur) query.getSingleResult();
        } catch (Exception e) {
            query.setParameter("position", -1);
            nextDealer = (Joueur) query.getSingleResult();
        }
        
        return nextDealer;
    }
    
    public List<Joueur> findJoueursOrderByPosition(Long idPartie){
        
        String requete = "SELECT j"
                + "     FROM Joueur j"
                + "     WHERE j.partie.id = :idPartie"
                + "     AND j.etat = :etatJoueur"
                + "     ORDER BY j.position ASC";
        
        Query query = makeEM().createQuery(requete);
        
        query.setParameter("idPartie", idPartie);
        query.setParameter("etatJoueur", EtatJoueur.EN_ATTENTE);
        
        return query.getResultList();
    }
    
}