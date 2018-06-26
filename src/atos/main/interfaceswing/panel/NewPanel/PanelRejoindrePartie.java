/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel.NewPanel;

import atos.main.entity.Partie;
import atos.main.interfaceswing.panel.PanelAffichageRejoindrePartie;
import atos.main.service.PartieService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Administrateur
 */
public class PanelRejoindrePartie extends JPanel{

    private PartieService partieService = new PartieService();
    public JButton boutonRafraichir, boutonRejoindre;
    private JPanel panelAffichage;
    private Long partieSelectionnee = null;
    private PanelAffichagePartie panelSectionne = null;
    
    ////////////////
    //      Constructor 
    ///////////////////////////
    public PanelRejoindrePartie() {
        super(new BorderLayout());
        
        boutonRafraichir = new JButton("- Rafraîchir la page -");
        boutonRafraichir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        
        boutonRejoindre = new JButton("- Rejoindre la partie sélectionnée -");
        
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage = new JPanel();
        panelAffichage.setLayout(new BoxLayout(panelAffichage, BoxLayout.Y_AXIS));
        
        panelAffichage.add(new PanelAffichagePartie());
        
        for (Partie partie : listeParties){
            PanelAffichagePartie panel = new PanelAffichagePartie(partie);
            panel.addMouseListener(panelAffichageListener);
            
            panelAffichage.add(panel);
        }
        this.add(boutonRafraichir, BorderLayout.NORTH);
        this.add(panelAffichage, BorderLayout.CENTER);
        this.add(boutonRejoindre, BorderLayout.SOUTH);
    }
    
    ////////////////
    //      Méthodes
    ///////////////////////////
    public void update() {
        panelAffichage.removeAll();
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage.add(new PanelAffichageRejoindrePartie());
        
        for (Partie partie : listeParties){
            PanelAffichagePartie panel = new PanelAffichagePartie(partie);
            panel.addMouseListener(panelAffichageListener);
            panelAffichage.add(panel);
        }
        
        revalidate();
        repaint();
    }
    
    ////////////////
    //      Mouse Listener
    ///////////////////////////
    MouseListener panelAffichageListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
            PanelAffichagePartie panel = (PanelAffichagePartie) e.getSource();
            if (panelSectionne == null){
                panelSectionne = panel;
                panelSectionne.setBackground(Color.yellow);
            } else if (panelSectionne == panel){
                panelSectionne.setBackground(null);
                panelSectionne = null;
            } else {
                panelSectionne.setBackground(null);
                panelSectionne = panel;
                panelSectionne.setBackground(Color.yellow);
            }
            try {
                System.out.println("CLICK - panelAffichageListener" + panelSectionne.getIdPartie());
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            
            
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    ////////////////
    //      GET & SET
    ///////////////////////////
    public JButton getBoutonRafraichir() {
        return boutonRafraichir;
    }
    public void setBoutonRafraichir(JButton boutonRafraichir) {
        this.boutonRafraichir = boutonRafraichir;
    }
    public JButton getBoutonRejoindre() {
        return boutonRejoindre;
    }
    public void setBoutonRejoindre(JButton boutonRejoindre) {
        this.boutonRejoindre = boutonRejoindre;
    }
    public Long getPartieSelectionnee() {
        return partieSelectionnee;
    }
    public void setPartieSelectionnee(Long partieSelectionnee) {
        this.partieSelectionnee = partieSelectionnee;
    }

    public PanelAffichagePartie getPanelSectionne() {
        return panelSectionne;
    }

    public void setPanelSectionne(PanelAffichagePartie panelSectionne) {
        this.panelSectionne = panelSectionne;
    }
}
