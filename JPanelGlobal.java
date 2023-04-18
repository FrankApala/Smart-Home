import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class JPanelGlobal extends JPanel implements ItemListener {
    private final JLabel zones;
    private final JLabel zone_choisie;
    private final JButton bouton_voir_maison;
    private final JButton bouton_amelioration;              //bouton permettant d'aller sur le panneau amélioration
    private final JButton bouton_retour_amlelioration_menu;          //bouton permettant de revenir du panneau amelioration vers le panneau menu
    private final JButton bouton_retour_maison_menu;        //bouton permettant de revenir du panneau maison vers le panneau menu

    private final JPanelMenu menu;
    private final JPanelAmelioration amelioration;
    private final JPanelVueMaison vue_maison;

    private JComboBox choix_zones;

    public JPanelGlobal(Partie partie, Maison maison){
        bouton_voir_maison = new JButton("Maison");
        bouton_amelioration = new JButton("Améliorations");         //création bouton amelioration
        bouton_retour_amlelioration_menu = new JButton("Retour");
        bouton_retour_maison_menu = new JButton("Retour");


        menu = new JPanelMenu(partie, maison, bouton_voir_maison, bouton_amelioration);
        vue_maison = new JPanelVueMaison(bouton_retour_maison_menu);
        amelioration = new JPanelAmelioration(partie, maison, bouton_retour_amlelioration_menu);
        zones = new JLabel("Choisissez une zone");
        zone_choisie = new JLabel("Choix");

        // array of string containing cities
        String s1[] = { "Cuisine", "Salon", "Chambres", "Salle de bain", "Garage", "Extérieur" };

        // create checkbox
        choix_zones = new JComboBox(s1);

        // add ItemListener
        choix_zones.addItemListener(this);

        // set color of text
        zones.setForeground(Color.red);
        zone_choisie.setForeground(Color.blue);

        // create a new panel
        JPanel p = new JPanel();

        p.add(zones);

        // add combobox to panel
        p.add(choix_zones);

        p.add(zone_choisie);
/**
 public void itemStateChanged(ItemEvent e)
 {
 // if the state combobox is changed
 if (e.getSource() == choix_zones) {

 zone_choisie.setText(choix_zones.getSelectedItem() + " selected");
 }
 }
 **/
        add(menu);                           //ajoute le panel menu au panel global
        add(amelioration);                   //ajoute le panel amelioration au panel global
        add(vue_maison);                    //ajoute le panel voir_maison au panel global

        menu.setVisible(true);
        amelioration.setVisible(false);
        vue_maison.setVisible(false);

        bouton_voir_maison.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                vue_maison.setVisible(true);
            }
        });

        //Action click bouton amelioration
        bouton_amelioration.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                amelioration.setVisible(true);
            }
        });

        bouton_retour_maison_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(true);
                vue_maison.setVisible(false);
            }
        });

        bouton_retour_amlelioration_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(true);
                amelioration.setVisible(false);
            }
        });
    }

    public JPanelMenu getMenu() {
        return menu;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}