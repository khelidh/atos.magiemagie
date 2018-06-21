/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.objet;

import atos.main.entity.Carte.TypeCarte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author mama
 */
public class CartePanel extends JPanel {
    
    private JLabel carte;
    private JLabel quantite;
    private ImageIcon imgICON;
    private List<Long> idCartes = new ArrayList<>();
    private TypeCarte type;
    
    public CartePanel(TypeCarte type) {
        super(new BorderLayout());
        
        this.type = type;
        
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/carte_mandragore.png";
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
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
        this.quantite.setText("" + quantite);
    }

    public List<Long> getIdCartes() {
        return idCartes;
    }

    public void setIdCartes(List<Long> idCartes) {
        this.idCartes = idCartes;
    }

    public TypeCarte getType() {
        return type;
    }

    public void setType(TypeCarte type) {
        this.type = type;
    }
    
    
    
}
