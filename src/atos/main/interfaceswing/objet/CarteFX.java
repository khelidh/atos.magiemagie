/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.objet;

import atos.main.entity.Carte;
import atos.main.entity.Carte.TypeCarte;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Administrateur
 */
public class CarteFX extends JLabel{
    
    private ImageIcon imgICON;
    List<Long> idCartes = new ArrayList<>();
   
    public CarteFX(TypeCarte type, int witdth, int height){
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/carte_mandragore.png";
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(witdth, height, java.awt.Image.SCALE_SMOOTH);
        this.imgICON = new ImageIcon(image);
        
        this.setIcon(this.imgICON);
    }
    
    public CarteFX() {
        
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/carte_mandragore.png";
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(150, 300, java.awt.Image.SCALE_SMOOTH);
        this.imgICON = new ImageIcon(image);
        
        this.setIcon(this.imgICON);
    }
    
    public CarteFX(Carte carte, Dimension dimension) {
        //this.carte = carte;
        
        String cheminCarteMandragore = "/atos/main/interfaceswing/image/carte_mandragore.png";
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH);
        this.imgICON = new ImageIcon(image);
        
        this.setIcon(this.imgICON);
    }
    
    public CarteFX(int indice, Carte carte, Dimension dimension) {
        //this.carte = carte;
        
        String cheminCarteMandragore = determineTypeCarte(indice);
        this.imgICON = new ImageIcon(getClass().getResource(cheminCarteMandragore));
        Image image = imgICON.getImage();
        image = image.getScaledInstance(dimension.width, dimension.height, java.awt.Image.SCALE_SMOOTH);
        this.imgICON = new ImageIcon(image);
        
        this.setIcon(this.imgICON);
    }
    
    public static String determineTypeCarte(int indice){
        switch (indice){
            case 0 : return "/atos/main/interfaceswing/image/carte_mandragore.png";
            case 1 : return "/atos/main/interfaceswing/image/carte_mandragore.png";
            case 2 : return "/atos/main/interfaceswing/image/carte_mandragore.png";
            case 3 : return "/atos/main/interfaceswing/image/carte_mandragore.png";
            default : return "/atos/main/interfaceswing/image/carte_mandragore.png";
        }
    }
    
    private void add(Carte carte){
        this.idCartes.add(carte.getId());
    }
    private void remove(Carte carte){
        this.idCartes.remove(carte.getId());
    }
}
