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
import atos.main.entity.Carte.TypeCarte;
import atos.main.entity.Joueur;
import atos.main.entity.Joueur.EtatJoueur;
import atos.main.entity.Partie;
import atos.main.entity.Partie.EtatPartie;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author mama
 */
public class PartieService {

    //////////////////////
    //  CONSTANTES
    /////////////////////
    public static final String SORT_INVISIBILITE = "INVISIBILITE";
    public static final String SORT_HYPSNOSE = "HYPSNOSE";
    public static final String SORT_DIVINATION = "DIVINATION";
    public static final String SORT_SOMMEIL_PROFOND = "SOMMEIL PROFOND";
    public static final String SORT_FILTRE_AMOUR = "FILTRE D'AMOUR";
    public static final String SORT_FAILED = "SORT FAILED";

    public static final int POSITION_DE_DEPART = 0;

    public static final int NOMBRE_MINIMAL_JOUEURS_PAR_PARTIE = 2;

//////////////////////
    //  DAO & Services utilisés 
    /////////////////////
    PartieDAO partieDAO = new PartieDAO();
    JoueurDAO joueurDAO = new JoueurDAO();
    CarteDAO carteDAO = new CarteDAO();
    CarteService serviceCarte = new CarteService();
    JoueurService serviceJoueur = new JoueurService();
    CarteService carteService = new CarteService();

    ///////////////////////
    //  Gérance de la création & de l'état de la partie
    /////////////////////
    public Partie creer(String pseudo, String avatar) {
        Partie partie = new Partie("Partie speciale");
        Joueur joueur = joueurDAO.findJoueurByPseudo(pseudo);

        joueur.setAvatar(avatar);
        joueur.setEtat(EtatJoueur.EN_ATTENTE);
        joueur.setPosition(0);

        partieDAO.insert(partie);

        partie.getJoueurs().add(joueur);
        joueur.setPartie(partie);

        if (joueur.getId() == null) {
            serviceJoueur.insertJoueur(joueur);
        } else {
            serviceJoueur.updateJoueur(joueur);
        }

        return partie;
    }

    public void demarrer(Long idPartie) {
        Partie partie = getPartie(idPartie);
        if ((partieDAO.findNombreJoueurEnAttente(idPartie) > 1) && (partie.getEtat() == EtatPartie.EN_PREPARATION)) {
            partie.setEtat(EtatPartie.EN_COURS);
            updatePartie(partie);

            List<Joueur> joueurs = partieDAO.findJoueursOrderByPosition(idPartie);
            joueurs.get(POSITION_DE_DEPART).setEtat(EtatJoueur.A_LA_MAIN);
            serviceJoueur.updateJoueur(joueurs.get(0));

            for (int i = 1; i < joueurs.size(); i++) {
                joueurs.get(i).setEtat(EtatJoueur.PAS_LA_MAIN);
                serviceJoueur.updateJoueur(joueurs.get(i));
            }
        }
    }

    public void terminerPartie(Long idPartie) {
        Partie partie = getPartie(idPartie);
        System.out.println("La partie " + partie.getNom() + " est terminée !");
        //MAJ partie gagnées etc....
    }

    ///////////////////////
    //  Gérance des joueurs pendant la partie
    ///////////////////// 
    /**
     * A partir du dealer actuel, cherche le prochaine joueur toujours en jeu.
     * S'il est en sommeil, on modifie son état et on cherche le prochain joueur
     * jusqu'à trouver un joueur avec un etatJoueur = PAS_LA_MAIN
     *
     * @param Long
     * @return Joueur
     */
    public Joueur getNextDealer(Long idJoueur) {
        Joueur dealer = getJoueur(idJoueur);
        dealer.setEtat(EtatJoueur.PAS_LA_MAIN);
        serviceJoueur.updateJoueur(dealer);

        Joueur nextDealer = partieDAO.findNextDealer(dealer);
        do {

            if (nextDealer.getEtat() == EtatJoueur.EN_SOMMEIL) {
                nextDealer.setEtat(EtatJoueur.PAS_LA_MAIN);
                serviceJoueur.updateJoueur(nextDealer);
            } else if (nextDealer.getEtat() == EtatJoueur.PAS_LA_MAIN) {
                nextDealer.setEtat(EtatJoueur.A_LA_MAIN);
                serviceJoueur.updateJoueur(nextDealer);
                return nextDealer;
            }
            nextDealer = partieDAO.findNextDealer(nextDealer);
        } while (true);
    }

    public void eliminerJoueur(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);
        joueur.setEtat(EtatJoueur.ELIMINE);
        //partie.getJoueurs().remove(joueur);

        System.out.println("Le joueur " + joueur.getPseudo() + " est éliminé ! Dommage ! ");
        System.out.println("Il reste " + partieDAO.findNombreJoueurEnLice(idJoueur) + " dans la partie !");

        if (joueur.getCartes().size() > 0) {
            for (Carte carte : joueur.getCartes()) {
                carte.setJoueur(null);
                serviceCarte.deleteCarte(carte);
            }
        }
        serviceJoueur.updateJoueur(joueur);
    }

    ///////////////////////
    //  Gérance des cartes
    /////////////////////
    public void distribuer(Long idPartie) {
        for (Joueur joueur : getJoueurs(idPartie)) {
            for (int i = 0; i < Partie.PARTIE_NOMBRE_CARTE_DEBUT; i++) {
                //carteDAO.insert(new Carte(joueur));    
                Carte carte = carteService.tirer(joueur);
                carteDAO.insert(carte);
                joueur.getCartes().add(carte);
                serviceJoueur.updateJoueur(joueur);
            }
        }
    }

    public void volerCarteAleatoireFromJoueur(Long idJoueur, Long idCible) {
        Joueur cible = getJoueur(idCible);
        Joueur joueur = getJoueur(idJoueur);

        List<Carte> cartes = cible.getCartes();
        int nombreCartes = cartes.size();
        int indiceCarteVolee = (int) (Math.random() * nombreCartes);

        Carte carteVolee = cartes.get(indiceCarteVolee);
        carteVolee.setJoueur(joueur);

        joueur.getCartes().add(carteVolee);
        cartes.remove(carteVolee);

        System.out.println("Le joueur " + joueur.getPseudo() + " a volé une carte " + carteVolee.getType()
                + " au joueur " + cible.getPseudo() + " !");

        serviceCarte.updateCarte(carteVolee);
        serviceJoueur.updateJoueur(joueur);
        serviceJoueur.updateJoueur(cible);
    }

    ///////////////////////
    //  Gérance du tour
    /////////////////////
    public void commencer(Long idPartie) {
        Partie partie = getPartie(idPartie);
        if (partie.getEtat() == EtatPartie.EN_COURS) {
            Long idPremierJoueur = partieDAO.findDealerID(idPartie);
            jouer(idPremierJoueur);
        } else {
            System.out.println("La partie " + partie.getNom() + " a déjà commencée !");
        }
    }

    //OK - passer tour = piocher carte
    public void piocherCarte(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);
        Carte carte = serviceCarte.tirer(joueur);
        carteDAO.insert(carte);
        joueur.getCartes().add(carte);
        joueur.setEtat(EtatJoueur.PAS_LA_MAIN);
        serviceJoueur.updateJoueur(joueur);
    }

    public void jouer(Long idJoueur) {

        try {
            Long idPartie = joueurDAO.findPartieID(idJoueur);
            affichage(idPartie);
            if (isPartieTerminee(idPartie)) {
                terminerPartie(idPartie);
            } else {
                jouerTour(idJoueur);
            }
        } catch (NullPointerException e) {
            System.out.println("La partie n'a pas commencé ou est terminée ! Ou peut-être un bug qui n'est pas de notre faute...");
        }
    }

    public void jouerTour(Long idJoueur) {
        System.out.println("");
        System.out.println("A vous de jouer " + getJoueur(idJoueur).getPseudo());
        System.out.println("");
        int action = selectionAction();

        if (action == 1) {
            System.out.println("Vous êtes chaude comme la lave du Mont Blanc ! Morse & Odeur à vous !");
            jouerSort(idJoueur);
            isJoueurStillAlive(idJoueur);
            jouer(getNextDealer(idJoueur).getId());
        } else if (action == 2) {
            System.out.println("Vous avez choisi de passer votre et donc de piocher une carte !");
            piocherCarte(idJoueur);
            // récupère prochain joueur en état de jouer (en faisant les mofifications 
            //nécessaires pour les joueurs en sommeil) -> puis le fait jouer
            jouer(getNextDealer(idJoueur).getId());
        } else if (action == 3) {
            System.out.println("Ce n'était pas très clair, désolé... Voici une meilleure explication !");
            jouer(idJoueur);
        } else {
            System.out.println("Vous avez rippé(e), accrochez-vous et donner une réponse valide ! Vous pouvez le faire !");
            jouerTour(idJoueur);
        }
    }

    public void jouerSort(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);

        System.out.println("Préparez vous à mourir ! Lancez un sort pour votre survie !");
        //System.out.println(serviceJoueur.mainToString(joueur));

        int indiceCarte1 = selectionCarte(idJoueur);
        int indiceCarte2 = selectionCarte(idJoueur);

        if (indiceCarte1 == indiceCarte2) {
            System.out.println("Vous avez sélectionné la même carte. Choississez en une autre !");
            indiceCarte2 = selectionCarte(idJoueur);
        }

        Carte carte1 = joueur.getCartes().get(indiceCarte1);
        Carte carte2 = joueur.getCartes().get(indiceCarte2);

        TypeCarte type1 = carte1.getType();
        TypeCarte type2 = carte2.getType();

        System.out.println("Vous avez sélectionné les cartes suivantes : ");
        System.out.println("Carte " + type1);
        System.out.println("Carte " + type2);

        if (selectionVerification()) {
            String sort = determinerSort(type1, type2);
            serviceJoueur.supprimerCartes(joueur, carte1, carte2);
            lancerSort(idJoueur, sort);
        } else {
            jouerSort(idJoueur);
        }
    }

    public String determinerSort(TypeCarte type1, TypeCarte type2) {
        String sort = SORT_FAILED;

        if (type1.equals(TypeCarte.AILE_DE_CHAUVE_SOURIS)) {
            if (type2.equals(TypeCarte.MANDRAGORE)) {
                sort = SORT_SOMMEIL_PROFOND;
            } else if (type2.equals(TypeCarte.LAPIS_LAZULI)) {
                sort = SORT_DIVINATION;
            }
        } else if ((type1.equals(TypeCarte.MANDRAGORE))) {
            if (type2.equals(TypeCarte.AILE_DE_CHAUVE_SOURIS)) {
                sort = SORT_SOMMEIL_PROFOND;
            } else if (type2.equals(TypeCarte.CORNE_DE_LICORNE)) {
                sort = SORT_FILTRE_AMOUR;
            }
        } else if ((type1.equals(TypeCarte.CORNE_DE_LICORNE))) {
            if (type2.equals(TypeCarte.MANDRAGORE)) {
                sort = SORT_FILTRE_AMOUR;
            } else if (type2.equals(TypeCarte.BAVE_DE_CRAPAUD)) {
                sort = SORT_INVISIBILITE;
            }
        } else if ((type1.equals(TypeCarte.BAVE_DE_CRAPAUD))) {
            if (type2.equals(TypeCarte.CORNE_DE_LICORNE)) {
                sort = SORT_INVISIBILITE;
            } else if (type2.equals(TypeCarte.LAPIS_LAZULI)) {
                sort = SORT_HYPSNOSE;
            }
        } else if ((type1.equals(TypeCarte.LAPIS_LAZULI))) {
            if (type2.equals(TypeCarte.BAVE_DE_CRAPAUD)) {
                sort = SORT_HYPSNOSE;
            } else if (type2.equals(TypeCarte.AILE_DE_CHAUVE_SOURIS)) {
                sort = SORT_DIVINATION;
            }
        }
        return sort;
    }

    public void lancerSort(Long idJoueur, String sort) {
        switch (sort) {
            case (SORT_FAILED): {
                System.out.println("Malheureusement le combo n'a rien donné ! Bien essayé tout de même ;)");
                break;
            }
            case (SORT_INVISIBILITE): {
                lancerSortInvisibilite(idJoueur);
                break;
            }
            case (SORT_HYPSNOSE): {
                System.out.println("Vous avez lancé un sort d'HYPNOSE !");
                Long idCible = selectionCible(idJoueur);
                lancerSortHypnose(idJoueur, idCible);
                break;
            }
            case (SORT_DIVINATION): {
                System.out.println("Un sort de DIVINATION SUPREME a été déclenché ! Quel est l'utilisateur de cette magie noire ?!");
                System.out.println("Les cartes des victimes sont dévoilées ! Quel manque de fair-play. C'pas sport ça !");
                lancerSortDivination(idJoueur);
                break;
            }
            case (SORT_SOMMEIL_PROFOND): {
                System.out.println("Vous avez lancé un sort de SOMMEIL PROFOND !");
                Long idCible = selectionCible(idJoueur);

                System.out.println("Un sortitlège de SOMMEIL PROFOND a été lancé"
                        + " par la fabuleuse sorcière " + getJoueur(idJoueur).getPseudo()
                        + " sur le faiblard cerveau de l'hérétique " + getJoueur(idCible).getPseudo());

                lancerSortSommeil(idCible);
                break;
            }
            case (SORT_FILTRE_AMOUR): {
                System.out.println("Vous vous apprétez à amorcer un puissant filtre d'amour !");
                Long idCible = selectionCible(idJoueur);
                lancerSortFiltreAmour(idJoueur, idCible);

                break;
            }
        }
    }

    public void lancerSortInvisibilite(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);
        System.out.println("Sort d'invisibilité lancé par " + joueur.getPseudo());

        List<Joueur> listeJoueurs = joueurDAO.findAllFromPartieExecptOne(joueur);

        for (Joueur cible : listeJoueurs) {
            volerCarteAleatoireFromJoueur(idJoueur, cible.getId());
        }
    }

    public void lancerSortDivination(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);
        List<Joueur> listeJoueurs = joueurDAO.findAllFromPartieExecptOne(joueur);
        for (Joueur cible : listeJoueurs) {
            System.out.println(serviceJoueur.mainToString(cible));
        }
    }

    public void lancerSortSommeil(Long idCible) {
        Joueur cible = getJoueur(idCible);
        cible.setEtat(EtatJoueur.EN_SOMMEIL);
        serviceJoueur.updateJoueur(cible);
    }

    public void lancerSortHypnose(Long idJoueur, Long idCible) {

        final int NOMBRE_CARTES_VOLEES = 3;
        boolean cibleEliminee = false;

        Joueur joueur = getJoueur(idJoueur);
        Joueur cible = getJoueur(idCible);
        System.out.println(joueur.getPseudo() + " vient d'hypnotiser " + cible.getPseudo()
                + " pour lui échanger 1 de ses cartes contre 3 ! Quel action de la sorcière française ! Chapeau bas !");

        long indiceCarteJoueur = (long) selectionCarte(idJoueur);
        Carte carteJoueur = joueur.getCartes().get((int) indiceCarteJoueur);
        joueur.getCartes().remove(carteJoueur);

        for (int i = 0; i < NOMBRE_CARTES_VOLEES; i++) {
            int nombreCarteCible = cible.getCartes().size();

            if (nombreCarteCible > 0) {
                int indiceCarte = (int) Math.random() * nombreCarteCible;

                Carte carteVolee = cible.getCartes().remove(indiceCarte);
                carteVolee.setJoueur(joueur);
                joueur.getCartes().add(carteVolee);
                System.out.println("La carte subtilisée n°" + (i + 1) + " :" + carteVolee.getType());
            } else {
                cibleEliminee = true;
                break;
            }
        }

        if (cibleEliminee) {
            //modifier la desctruction de la carte
            carteJoueur.setJoueur(null);
            serviceCarte.deleteCarte(carteJoueur);
            eliminerJoueur(idCible);
            System.out.println("La carte de " + carteJoueur.getType() + " de " + joueur.getPseudo()
                    + " a donc été détruite ! Quel gaspillage !");
        } else {
            System.out.println("La carte de " + carteJoueur.getType() + " de " + joueur.getPseudo()
                    + " est donc cédée à la pauvre sorcière " + cible.getPseudo() + " !");
            carteJoueur.setJoueur(cible);
            cible.getCartes().add(carteJoueur);
            serviceCarte.updateCarte(carteJoueur);
        }

        serviceJoueur.updateJoueur(cible);
        serviceJoueur.updateJoueur(joueur);
    }

    public void lancerSortFiltreAmour(Long idJoueur, Long idCible) {

        Joueur joueur = getJoueur(idJoueur);
        Joueur cible = getJoueur(idCible);

        int moitieCarteCible = (int) (cible.getCartes().size() / 2);

        if (moitieCarteCible == 0) {
            System.out.println("Un filtre d'amour surgit et conquit la belle " + cible.getPseudo() + " !"
                    + " Elle n'a qu'une pauvre carte à offrir"
                    + " à " + joueur.getPseudo() + ". Quelle tristesse ! Elle est morte d'amour et de honte "
                    + " en entrainant son faible bien ! Dommage !");
            eliminerJoueur(cible.getId());
        } else {
            System.out.println("Un filtre d'amour surgit et conquit la belle " + cible.getPseudo() + " !"
                    + " Elle donne " + moitieCarteCible + " de ses cartes afin de dévoiler son coeur "
                    + " à " + joueur.getPseudo() + ". Fabuleux !");
            for (int i = 0; i < moitieCarteCible; i++) {
                int indice = (int) Math.random() * cible.getCartes().size();

                Carte carteVolee = cible.getCartes().get(indice);
                System.out.println("La carte " + carteVolee.getType() + " est donnée à " + joueur.getPseudo() + " par " + cible.getPseudo());
                cible.getCartes().remove(carteVolee);
                carteVolee.setJoueur(joueur);
                joueur.getCartes().add(carteVolee);
                serviceCarte.updateCarte(carteVolee);
            }
            serviceJoueur.updateJoueur(joueur);
            serviceJoueur.updateJoueur(cible);
        }
    }

    ///////////////////////
    //  CHECKs
    /////////////////////
    public boolean isPartieTerminee(Long idPartie) {
        return partieDAO.findNombreJoueurEnLice(idPartie) == 1;
    }

    public void isJoueurStillAlive(Long idJoueur) {
        Joueur joueur = getJoueur(idJoueur);
        if (joueur.getCartes().size() == 0) {
            eliminerJoueur(idJoueur);
        }
    }

    ///////////////////////
    //  SELECTIONS
    /////////////////////
    public Long selectionCible(Long idJoueur) {
        System.out.println("Vous devez choisir votre cible entre :");
        Joueur player = getJoueur(idJoueur);
        int numero = 0;
        List<Joueur> joueurs = joueurDAO.findAllFromPartieExecptOne(player);

        for (Joueur joueur : joueurs) {
            numero++;

            System.out.println(numero + "- Joueur " + joueur.getPseudo()
                    + " avec " + joueur.getCartes().size() + " cartes !");
        }

        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();

        int indice;
        try {
            indice = Integer.parseInt(txt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return selectionCible(idJoueur);
        }

        if (indice >= 1 && indice <= numero) {
            return joueurs.get(indice - 1).getId();
        }
        return selectionCible(idJoueur);
    }

    public int selectionAction() {
        System.out.println("C'est à votre tour de jouer ! Que voulez-vous faire");
        System.out.println("SELECTION ACTION : 1- Jouer Combo");
        System.out.println("SELECTION ACTION : 2- Piocher & passer");
        System.out.println("SELECTION ACTION : 3- C'est pas faux !");

        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();
        int indice = 0;
        try {
            indice = Integer.parseInt(txt);
        } catch (java.lang.NumberFormatException e) {
            System.out.println("Soyez attentif !");
            return selectionAction();
        }
        return indice;
    }

    public int selectionCarte(Long idJoueur) {

        Joueur joueur = getJoueur(idJoueur);
        int nombreCarte = joueur.getCartes().size();

        System.out.println("- Choississez une carte !");
        System.out.println(serviceJoueur.mainToString(joueur));
        System.out.println("Entre 1 et " + nombreCarte);

        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();
        int indice = 0;
        try {
            indice = Integer.parseInt(txt);
            indice--;
        } catch (java.lang.NumberFormatException e) {
            return selectionCarte(idJoueur);
        }

        if (indice >= 0 && indice < nombreCarte) {
            return indice;
        }
        System.out.println("Indice de carte invalide. Veuillez essayer à nouveau !");
        return selectionCarte(idJoueur);
    }

    public boolean selectionVerification() {
        System.out.println("Vous êtes sûr de votre choix ? - yes/y - no");
        Scanner scanner = new Scanner(System.in);
        String txt = scanner.nextLine();

        if (txt.equals("yes") || (txt.equals("y"))) {
            return true;
        }
        return false;
    }

    ///////////////////////
    //  Affichage
    /////////////////////
    public void affichage(Long idPartie) {
        Partie partie = getPartie(idPartie);
        String chaine = "Partie " + partie.getNom() + "\n";
        List<Joueur> joueurs;
        if (partie.getEtat() == EtatPartie.EN_PREPARATION) {
            joueurs = getAllJoueurs(idPartie);
        } else {
            joueurs = getJoueurs(idPartie);
        }

        for (Joueur joueur : joueurs) {
            chaine += serviceJoueur.mainToString(joueur) + "\n";
        }
        System.out.println(chaine);
    }

    ///////////////////////
    //  GET
    /////////////////////
    public Partie getPartie(Long idPartie) {
        return partieDAO.findById(idPartie);
    }

    public List<Joueur> getAllJoueurs(Long idPartie) {
        Partie partie = getPartie(idPartie);
        return partie.getJoueurs();
    }

    public List<Joueur> getJoueurs(Long idPartie) {
        return partieDAO.findJoueurEnLice(idPartie);
    }

    public Joueur getJoueur(Long idJoueur) {
        return joueurDAO.findById(idJoueur);
    }

    ///////////////////////
    //  UPDATE
    /////////////////////
    public void updatePartie(Partie partie) {
        partieDAO.update(partie);
    }

    /////////////////////////
    // FONCTION MAIN SERVICE
    public int selectionActionDebutPartie() {

        System.out.println("Bienvenue sur Magie Magie !");
        System.out.println("Nous somme heureux de vous avoir avec nous !"
                + "\nQue voulez-vous faire ?");
        System.out.println("1 - Créer une partie"
                + "\n2 - Rejoindre une partie"
                + "\n3 - Aller dans son espace personnel");

        Scanner scanner = new Scanner(System.in);
        int indice;
        String txt;

        try {
            txt = scanner.nextLine();
            indice = Integer.parseInt(txt);

        } catch (IllegalArgumentException e) {
            return selectionActionDebutPartie();
        }
        return indice;
    }

    public void application() {
        while (true) {
            int indice = selectionActionDebutPartie();

            switch (indice) {
                case 1:
                    String pseudo = serviceJoueur.selectionPseudo();
                    String avatar = serviceJoueur.selectionAvatar();

                    Partie partie = creer(pseudo, avatar);
                    System.out.println(pseudo + " vient de créer la partie " + partie.getNom() + " !");
                    //updatePartie(partie);
                    break;

                case 2:
                    Scanner scanner = new Scanner(System.in);
                    String txt;
                    int i = 1;

                    List<Partie> parties = partieDAO.findAllPartieEnPreparation();

                    //List<Partie> parties = partieDAO.findAllPartieEnPreparation();
                    if (parties.size() != 0) {
                        System.out.println("Partie =///= null");
                        for (Partie p : parties) {
                            System.out.println(i + " - La partie " + p.getNom() + " est en préparation. Il y a " + p.getJoueurs().size()
                                    + " joueur(s) pour le moment !");
                            indice++;
                        }
                        System.out.println("Quelle partie souhaitez-vous rejoindre ?");
                        txt = scanner.nextLine();

                        try {
                            indice = Integer.parseInt(txt);
                        } catch (IllegalArgumentException e) {
                            indice = 1;
                        }

                        Partie partieChoisie = parties.get(indice - 1);
                        Long idPartie = partieChoisie.getId();
                        serviceJoueur.rejoindrePartie(serviceJoueur.selectionPseudo(), serviceJoueur.selectionAvatar(), idPartie);

                        System.out.println("Vous avez rejoint la partie " + partieChoisie.getNom());
                        System.out.println("Voulez-vous faire débuter la partie à votre entrée ? y/n");

                        try {
                            txt = scanner.nextLine();
                        } catch (IllegalArgumentException e) {
                            txt = "non";
                        }

                        if (txt.equals("y") || txt.equals("yes")) {
                            demarrer(idPartie);
                            distribuer(idPartie);
                            commencer(idPartie);
                        } else {
                            System.out.println("Vous êtes en attente dans une partie. Elle commencera lorque un des joueurs lancera celle-ci !"
                                    + "\nATTENTE !");
                        }
                    } else {
                        System.out.println("Il n'y a pas de partie disponible ! Créez-en une si vous voulez jouer ! ");
                    }
                    break;
                case 3:
                    System.out.println("ESPACE PERSONNEL ! SUPER !");
                    break;
                    
                default:
                    return;
            }

        }

    }

    public void listerPartieEnPreparation() {
        int indice = 0;
        for (Partie partie : partieDAO.findAllPartieEnPreparation()) {
            indice++;
            System.out.println(indice + " - La partie " + partie.getNom() + " est en préparation. Il y a " + partie.getJoueurs().size()
                    + " joueurs pour le moment !");
        }
    }

}
