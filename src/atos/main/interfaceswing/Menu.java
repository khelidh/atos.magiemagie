/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing;

import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author mama
 */
public class Menu extends JMenuBar {

    public JMenu menuJouer, menuProfil, menuOptions, menuQuitter;
    public JMenuItem itemJouer1, itemJouer2;
    
    public Menu() {
        menuJouer = new JMenu("Jouer");
        menuProfil = new JMenu("Profil");
        menuOptions = new JMenu("Options");
        menuQuitter = new JMenu("Quitter");
        
        String txt_itemJouer1 = "Créer une partie";
        itemJouer1 = new JMenuItem(txt_itemJouer1);
        String txt_itemJouer2 = "Rejoindre une partie";
        itemJouer2 = new JMenuItem(txt_itemJouer2);
        String txt_itemJouer3 = "Charger une partie";
        JMenuItem itemJouer3 = new JMenuItem(txt_itemJouer3);
        
        
        String txt_itemProfil1 = "Voir son profil";
        JMenuItem itemProfil1 = new JMenuItem(txt_itemProfil1);
        String txt_itemProfil2 = "Chercher profil";
        JMenuItem itemProfil2 = new JMenuItem(txt_itemProfil2);
        
        
        String txt_itemQuitter1 = "Relancer l'application";
        JMenuItem itemQuitter1 = new JMenuItem(txt_itemQuitter1);
        String txt_itemQuitter2 = "Sauvegarder et quitter";
        JMenuItem itemQuitter2 = new JMenuItem(txt_itemQuitter2);
        String txt_itemQuitter3 = "Quitter";
        JMenuItem itemQuitter3 = new JMenuItem(txt_itemQuitter3);
        
        menuJouer.add(itemJouer1);
        menuJouer.add(itemJouer2);
        menuJouer.add(itemJouer3);
        
        menuProfil.add(itemProfil1);
        menuProfil.add(itemProfil2);
        
        menuQuitter.add(itemQuitter1);
        menuQuitter.add(itemQuitter2);
        menuQuitter.add(itemQuitter3);
        
        this.add(menuJouer);
        this.add(menuProfil);
        this.add(menuOptions);
        this.add(menuQuitter);
    }

    
}
