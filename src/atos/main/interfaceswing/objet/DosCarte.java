/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.objet;

import atos.main.entity.Carte;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Administrateur
 */
public class DosCarte extends JLabel {
    private ImageIcon imgICON;
    
    public DosCarte(Carte.TypeCarte type, int witdth, int height){
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/dos_de_carte.png";
        
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        // Obliger de faire pour rescale l'image
        Image image = imgICON.getImage();
        image = image.getScaledInstance(witdth, height, java.awt.Image.SCALE_SMOOTH);
        
        this.imgICON = new ImageIcon(image);
        this.setIcon(this.imgICON);
    }
}
