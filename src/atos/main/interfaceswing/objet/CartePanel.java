package atos.main.interfaceswing.objet;

import atos.main.entity.Carte.TypeCarte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author mama
 */
public class CartePanel extends JPanel{
    
    private JLabel carte;
    private JLabel quantite;
    private ImageIcon imgICON;
    private TypeCarte type;
    
    public CartePanel(TypeCarte type) {
        super(new BorderLayout());
        this.type = type;
        
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/carte_mandragore.png";
        String cheminCarteCorneDeLicorne = "/atos/main/interfaceswing/image/carte_corne_de_licorne.png";
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteCorneDeLicorne));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(150,200, java.awt.Image.SCALE_SMOOTH);
        this.imgICON = new ImageIcon(image);
        
        this.carte = new JLabel(imgICON);
        this.carte.setPreferredSize(new Dimension(150, 200));
        this.quantite = new JLabel("0", SwingConstants.CENTER);
        this.setPreferredSize(new Dimension(150, 50));
        
        this.add(this.carte, BorderLayout.CENTER);
        this.add(this.quantite, BorderLayout.SOUTH);
        this.setPreferredSize(new Dimension(150, 250));
    }
    
    public void setQuantite(int quantite){
        String qte = Integer.toString(quantite);
        this.quantite.setText(qte);
    }
    
    
    
    
    
    
    ///////////
    // GET & SET -ERS
    /////////////////////
    public TypeCarte getType() {
        return type;
    }
    public void setType(TypeCarte type) {
        this.type = type;
    }
    public JLabel getCarte() {
        return carte;
    }
    public void setCarte(JLabel carte) {
        this.carte = carte;
    }
    public JLabel getQuantite() {
        return quantite;
    }

    public void setQuantite(JLabel quantite) {
        this.quantite = quantite;
    }

    public ImageIcon getImgICON() {
        return imgICON;
    }

    public void setImgICON(ImageIcon imgICON) {
        this.imgICON = imgICON;
    }
}
