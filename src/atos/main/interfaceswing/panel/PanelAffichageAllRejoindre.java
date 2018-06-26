/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atos.main.interfaceswing.panel;

import atos.main.entity.Partie;
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
 * @author mama
 */
public class PanelAffichageAllRejoindre extends JPanel implements MouseListener{

    private PartieService partieService = new PartieService();
    public JButton boutonRafraichir, boutonRejoindre;
    private JPanel panelAffichage;
    private Long partieSelectionnee = null;

    public PanelAffichageAllRejoindre() {
        super(new BorderLayout());
        
        boutonRafraichir = new JButton("- Rafraîchir la page -");
        
        boutonRafraichir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maj();
            }
        });
        boutonRejoindre = new JButton("- Rejoindre la partie sélectionnée -");
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage = new JPanel();
        panelAffichage.setLayout(new BoxLayout(panelAffichage, BoxLayout.Y_AXIS));
        
        panelAffichage.add(new PanelAffichageRejoindrePartie());
        
        for (Partie partie : listeParties)
            panelAffichage.add(new PanelAffichageRejoindrePartie(partie));
        
        
        this.add(boutonRafraichir, BorderLayout.NORTH);
        this.add(panelAffichage, BorderLayout.CENTER);
        this.add(boutonRejoindre, BorderLayout.SOUTH);
        
    }

    public void maj() {
        
        panelAffichage.removeAll();
        
        List<Partie> listeParties = partieService.getPartiesEnPrepapration();
        
        panelAffichage.add(new PanelAffichageRejoindrePartie());
        
        for (Partie partie : listeParties)
            panelAffichage.add(new PanelAffichageRejoindrePartie(partie));
        
        revalidate();
        repaint();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        
        PanelAffichageRejoindrePartie panel = (PanelAffichageRejoindrePartie) e.getSource();
        panel.setBackground(Color.red);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
}
