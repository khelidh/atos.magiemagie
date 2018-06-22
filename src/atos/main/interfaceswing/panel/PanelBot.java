package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.service.PartieService;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Mamamiaaa
 */
public class PanelBot extends JPanel{
    PartieService partieService = new PartieService();
    
    Long idJoueur;
    
    JLabel carte;
    JLabel quantite;
    JLabel informations;

    public PanelBot() {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.idJoueur = null;
        
        // Dos de carte
        String cheaminDosCarte = "/atos/main/interfaceswing/image/dos_de_carte.png";
        ImageIcon imgICON = new ImageIcon(getClass().getResource(cheaminDosCarte));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(150,200, java.awt.Image.SCALE_SMOOTH);
        imgICON = new ImageIcon(image);

        this.carte = new JLabel(imgICON);
        
        String quantiteString = "0";
        this.quantite = new JLabel(quantiteString, SwingConstants.CENTER);
        
        String informationsString = "BOT";
        this.informations = new JLabel(informationsString, SwingConstants.CENTER);
        
        this.add(informations);
        this.add(carte);
        this.add(quantite);
    }
    
    public PanelBot(Long idJoueur) {
        this.idJoueur = idJoueur;
        Joueur joueur = partieService.getJoueur(idJoueur);
        
        // Dos de carte
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/dos_de_carte.png";
        ImageIcon imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(150,200, java.awt.Image.SCALE_SMOOTH);
        imgICON = new ImageIcon(image);
        
        this.carte = new JLabel(imgICON);
        
        String quantiteString = "" + joueur.getCartes().size();
        this.quantite = new JLabel(quantiteString);
        
        String informationsString = joueur.getPseudo();
        this.informations = new JLabel(informationsString);
    }
    
    public void setQuantite(Long idJoueur){
        String chaine = "" + partieService.getNombreCartesJoueur(idJoueur);
        this.quantite.setText(chaine);
    }
}
