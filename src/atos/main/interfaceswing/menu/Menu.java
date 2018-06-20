/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.menu;

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

    //public JMenu menuJouer, menuProfil, menuOptions, menuQuitter;
    private JMenuItem itemCreationPartie, itemRejoindrePartie, itemRelancerApplication, itemQuitterApplication;
    
    public Menu() {
        JMenu menuJouer, menuProfil, menuOptions, menuQuitter;
        menuJouer = new JMenu("Jouer");
        menuProfil = new JMenu("Profil");
        menuOptions = new JMenu("Options");
        menuQuitter = new JMenu("Quitter");
        
        String txt_itemJouer1 = "Créer une partie";
        itemCreationPartie = new JMenuItem(txt_itemJouer1);
        String txt_itemJouer2 = "Rejoindre une partie";
        itemRejoindrePartie = new JMenuItem(txt_itemJouer2);
        String txt_itemJouer3 = "Charger une partie";
        JMenuItem itemJouer3 = new JMenuItem(txt_itemJouer3);
        
        
        String txt_itemProfil1 = "Voir son profil";
        JMenuItem itemProfil1 = new JMenuItem(txt_itemProfil1);
        String txt_itemProfil2 = "Chercher profil";
        JMenuItem itemProfil2 = new JMenuItem(txt_itemProfil2);
        
        
        String txt_itemQuitter1 = "Relancer l'application";
        itemRelancerApplication = new JMenuItem(txt_itemQuitter1);
        String txt_itemQuitter2 = "Sauvegarder et quitter";
        JMenuItem itemQuitter2 = new JMenuItem(txt_itemQuitter2);
        String txt_itemQuitter3 = "Quitter";
        itemQuitterApplication = new JMenuItem(txt_itemQuitter3);
        
        menuJouer.add(itemCreationPartie);
        menuJouer.add(itemRejoindrePartie);
        menuJouer.add(itemJouer3);
        
        menuProfil.add(itemProfil1);
        menuProfil.add(itemProfil2);
        
        menuQuitter.add(itemRelancerApplication);
        menuQuitter.add(itemQuitter2);
        menuQuitter.add(itemQuitterApplication);
        
        this.add(menuJouer);
        this.add(menuProfil);
        this.add(menuOptions);
        this.add(menuQuitter);
    }

    public JMenuItem getItemCreationPartie() {
        return itemCreationPartie;
    }

    public void setItemCreationPartie(JMenuItem itemCreationPartie) {
        this.itemCreationPartie = itemCreationPartie;
    }

    public JMenuItem getItemRejoindrePartie() {
        return itemRejoindrePartie;
    }

    public void setItemRejoindrePartie(JMenuItem itemRejoindrePartie) {
        this.itemRejoindrePartie = itemRejoindrePartie;
    }

    public JMenuItem getItemRelancerApplication() {
        return itemRelancerApplication;
    }

    public void setItemRelancerApplication(JMenuItem itemRelancerApplication) {
        this.itemRelancerApplication = itemRelancerApplication;
    }

    public JMenuItem getItemQuitterApplication() {
        return itemQuitterApplication;
    }

    public void setItemQuitterApplication(JMenuItem itemQuitterApplication) {
        this.itemQuitterApplication = itemQuitterApplication;
    }

    
}
