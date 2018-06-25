package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.entity.Partie;
import atos.main.service.PartieService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author Administrateur
 */
public class PanelAffichageDemarragePartie extends JPanel {
    
    PartieService partieService = new PartieService();

    public PanelAffichageDemarragePartie() {
        String nom_txt = "Partie";
        String nombre_txt = "Nombre de joueurs";
        String createur_txt = "Créateur de la partie";
        String joueurs_txt = "Autres joueurs dans la partie";

        JLabel nomPartie = new JLabel(nom_txt);
        JLabel nombreJoueurs = new JLabel(nombre_txt);
        JLabel pseudoCreateur = new JLabel(createur_txt);
        JLabel pseudosJoueurs = new JLabel(joueurs_txt);

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
    }
    
    public PanelAffichageDemarragePartie(Partie partie) {
        JLabel nomPartie = new JLabel(partie.getNom());
        JLabel nombreJoueurs = new JLabel(partie.getJoueurs().size() + "");
        System.out.println("Partie ID = " + partie.getId());
        JLabel pseudoCreateur = new JLabel(partieService.getJoueurFirstPosition(partie.getId()).getPseudo());
        
        String chaineNomsJoueurs = "";
            for (Joueur joueur : partie.getJoueurs())
                if (joueur.getPosition() != 0L)
                    chaineNomsJoueurs += joueur.getPseudo() + " - ";
            
        JLabel pseudosJoueurs = new JLabel(chaineNomsJoueurs);

        // Ajout bouton rejoindre avec boite de dialogue pour récupérer pseudo & avatar du joueur 
        JButton boutonDemarrer = new JButton("Démarrer");
        boutonDemarrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String mess = "Voulez-vous commencer maintenant ?";
                int optionCommencer = JOptionPane.showConfirmDialog(null, mess, "Commencer " + partie.getNom(), JOptionPane.YES_NO_OPTION);
                if (optionCommencer == JOptionPane.YES_OPTION) {
                    partieService.commencer(partie.getId());
                    // A FAIRE : ON rejoint la partie et si oui on commence directement
                    partieService.demarrer(partie.getId());
                    partieService.distribuer(partie.getId());
                }
            }
        });

        this.add(nomPartie);
        this.add(nombreJoueurs);
        this.add(pseudoCreateur);
        this.add(pseudosJoueurs);
        this.add(boutonDemarrer);
    }
    
    
}
