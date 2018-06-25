package atos.main.interfaceswing.panel;

import atos.main.entity.Joueur;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
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
    
    Long idBot;
    JLabel carte;
    JLabel quantite;
    JLabel pseudo;

    public PanelBot(Long idBot) {
        super(new BorderLayout());
        this.idBot = idBot;
        
        // Dos de carte
        String cheaminDosCarte = "/atos/main/interfaceswing/image/dos_de_carte.png";
        ImageIcon imgICON = new ImageIcon(getClass().getResource(cheaminDosCarte));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(150,200, java.awt.Image.SCALE_SMOOTH);
        imgICON = new ImageIcon(image);

        this.carte = new JLabel(imgICON, SwingConstants.CENTER);
        this.quantite = new JLabel("", SwingConstants.CENTER);
        this.pseudo = new JLabel("", SwingConstants.CENTER);
        
        this.setInformations(idBot);
        
        this.add(pseudo, BorderLayout.NORTH);
        this.add(carte, BorderLayout.CENTER);
        this.add(quantite, BorderLayout.SOUTH);
    }
    
    public void setInformations(Long idBot){
        Joueur bot = partieService.getJoueur(idBot);
        
        this.quantite.setText("" + bot.getCartes().size());
        this.pseudo.setText(bot.getPseudo());
        
        System.out.println("Nom : " + bot.getPseudo());
        System.out.println("Quantité cartes : " + bot.getCartes().size());
        
        
    }
}
