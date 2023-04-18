import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelMenu extends JPanel {
    private Demande demande_actuelle;
    private final JPanel north_panel;
    private final JPanel west_panel;
    private JPanelControleMaison controle_maison;

    private final JPanel panneau_meteo;                 //panneau météo
    private final JPanel panneau_oublis;                //panneau des oublis
    private final JPanel panneau_demande;               //panneau des demandes
    //private final JPanelMaison controle_maison;
    JLabel etat_meteo;
    JLabel temperature_meteo;
    JLabel humidite_meteo;
    JLabel luminosite_meteo;
    JCheckBox checkbox_fenetre;
    JCheckBox checkbox_eau;
    JCheckBox checkbox_ampoule;
    JLabel titre_demande;
    JButton bouton_accepter;
    JButton bouton_refuser;

    public JPanelMenu(Partie partie, Maison maison, JButton bouton_voir_maison, JButton bouton_amelioration) {

        Border blackline = BorderFactory.createLineBorder(Color.black);

        panneau_meteo = new JPanel();                       //création du panneau météo

        panneau_demande = new JPanel();                     //création du panneau demande

        panneau_oublis = new JPanel();                      //création du panneau oublis

        controle_maison = new JPanelControleMaison(maison);

        etat_meteo = new JLabel("Meteo", SwingConstants.CENTER);
        temperature_meteo = new JLabel("Température : " + "°C", SwingConstants.CENTER);   //création du label temp
        humidite_meteo = new JLabel("Humidité : " + "%", SwingConstants.CENTER);          //création du label humidité
        luminosite_meteo = new JLabel("Luminosité : " + "%", SwingConstants.CENTER);
        //ajout image
        JLabel image_ampoule;
        JLabel image_fenetre;
        JLabel image_eau;

        //ajout image ampoule
        BufferedImage image_amp = null;
        try {
            image_amp = ImageIO.read(new File("src\\ampoule.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ajout image goutte
        BufferedImage image_e = null;
        try {
            image_e = ImageIO.read(new File("src\\eau.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //ajout image fenetre
        BufferedImage image_fen = null;
        try {
            image_fen = ImageIO.read(new File("src\\fenetre.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //image electricite
        image_ampoule = new JLabel(new ImageIcon(image_amp));                     //ajout  de l'image dans le label
        image_ampoule.setPreferredSize(new Dimension(40, 40));        //dimensions de l'image

        //image eau
        image_eau = new JLabel(new ImageIcon(image_e));                           //ajout  de l'image dans le label
        image_eau.setPreferredSize(new Dimension(40, 40));            //dimensions de l'image

        //image fenetre
        image_fenetre =  new JLabel(new ImageIcon(image_fen));                    //ajout  de l'image dans le label
        image_fenetre.setPreferredSize(new Dimension(40,40));         //dimensions de l'image

        setLayout(new BorderLayout());                                          //création du layout

        //panneau oublis
        JLabel titre_oublis = new JLabel("Oublis", SwingConstants.CENTER);        //création du label oublis
        JPanel image_oublis = new JPanel();                                            //création du panneau pour les images d'oublis
        JPanel checkbox_oublis = new JPanel();                                         //création du panneau pour les checkbox d'oublis
        checkbox_oublis.setLayout(new BorderLayout(60, 0));         //création du layout panneau images oublis
        image_oublis.setLayout(new BorderLayout(20,0));             //création du layout panneau checkbox oublis

        titre_oublis.setBorder(blackline);                                             //bords noirs
        titre_oublis.setPreferredSize(new Dimension(100, 50));             //dimensions du titre oublis

        panneau_oublis.setLayout(new BorderLayout(20,0));            //création du layout des du panneau oublis
        panneau_oublis.setBorder(blackline);                                    //bords en noir
        panneau_oublis.setPreferredSize(new Dimension(200, 200));   //dimensions panneau oublis
        panneau_oublis.add(titre_oublis, BorderLayout.NORTH);                   //ajout du titre oublis au panneau_oubli
        panneau_oublis.add(image_oublis, BorderLayout.CENTER);
        panneau_oublis.add(checkbox_oublis, BorderLayout.SOUTH);

        //ajout des images au panneau oublis
        image_oublis.add(image_fenetre, BorderLayout.WEST);                  //image ampoule à gauche
        image_oublis.add(image_ampoule, BorderLayout.CENTER);                //image ampoule au cetntre
        image_oublis.add(image_eau, BorderLayout.EAST);                      //image ampoukle à droite


        //ajout des checkbox oublis
        checkbox_fenetre = new JCheckBox("", false);       //création checkbox fenetre
        checkbox_fenetre.setPreferredSize(new Dimension(30, 30));
        checkbox_fenetre.setVisible(false);
        checkbox_ampoule = new JCheckBox("", false);       //création checkbox ampoule
        checkbox_ampoule.setPreferredSize(new Dimension(30, 30));
        checkbox_ampoule.setVisible(false);
        checkbox_eau = new JCheckBox("", false);           //création checkbox eau
        checkbox_eau.setPreferredSize(new Dimension(30, 30));
        checkbox_eau.setVisible(false);

        /** Listener à ajouter pour action + actualisation
         *
         if(checkbox_fenetre.isSelected()){              //désactive la checkbox de l'oubli fenetre s'il est sélectionné
         checkbox_fenetre.setVisible(false);
         }

         if(checkbox_ampoule.isSelected()){              //désactive la checkbox de l'oubli ampoule s'il est sélectionné
         checkbox_ampoule.setVisible(false);
         }

         if(checkbox_eau.isSelected()){              //désactive la checkbox de l'oubli eau s'il est sélectionné
         checkbox_eau.setVisible(false);
         }**/

        checkbox_oublis.add(checkbox_fenetre, BorderLayout.WEST);               //positionnement checkbox à gauche du panneau
        checkbox_oublis.add(checkbox_ampoule, BorderLayout.CENTER);             //positionnement checkbox au centre du panneau
        checkbox_oublis.add(checkbox_eau, BorderLayout.EAST);                   //positionnement checkbox à droite du panneau


        //panneau demandes
        titre_demande = new JLabel("Demandes de la famille", SwingConstants.CENTER);    //création du label demande
        titre_demande.setPreferredSize(new Dimension(100, 100));    //dimensions du titre demandes

        bouton_accepter = new JButton("Accepter");              //création du bouton accepter
        bouton_refuser = new JButton("Refuser");                //création du bouton refuser

        bouton_accepter.setPreferredSize(new Dimension(100, 100));  //dimensions du bouton accepter
        bouton_refuser.setPreferredSize(new Dimension(100, 100));   //dimensiosn du bouton refuser
        bouton_accepter.setVisible(false);
        bouton_refuser.setVisible(false);

        panneau_demande.setLayout(new BorderLayout(0,1));            //création layout panneau_demande
        panneau_demande.setBorder(blackline);                                   //bords noirs
        panneau_demande.setPreferredSize(new Dimension(200,200));   //dimensions
        panneau_demande.add(titre_demande, BorderLayout.NORTH);                     //ajout du label demandes au panneau_demande
        panneau_demande.add(bouton_accepter, BorderLayout.WEST);                    //ajout du bouton accepter à panneau_demande
        panneau_demande.add(bouton_refuser, BorderLayout.EAST);                     //ajout du bouton refuser à panneau_demande


        //panneau meteo
        panneau_meteo.setLayout(new BorderLayout(0, 20));
        JLabel label_meteo = new JLabel("Météo", SwingConstants.CENTER);                         //création du label météo
        label_meteo.setBorder(blackline);                                                             //bords noirs
        label_meteo.setPreferredSize(new Dimension(100, 50));                 //dimension du label meteo
        JPanel valeurs_meteo = new JPanel();

        //titre panneau meteo
        panneau_meteo.setBorder(blackline);                                     //bords noirs
        panneau_meteo.add(valeurs_meteo, BorderLayout.SOUTH);
        panneau_meteo.setPreferredSize(new Dimension(200, 200));    //dimensions du panneau de titre panneau meteo
        panneau_meteo.add(label_meteo, BorderLayout.NORTH);
        valeurs_meteo.setLayout(new GridLayout(4, 1, 0, 20));

        //panneau affichage valeurs meteo
        valeurs_meteo.add(etat_meteo, CENTER_ALIGNMENT);
        valeurs_meteo.add(temperature_meteo, CENTER_ALIGNMENT);
        valeurs_meteo.add(humidite_meteo, CENTER_ALIGNMENT);
        valeurs_meteo.add(luminosite_meteo, CENTER_ALIGNMENT);


        //condiguration panneau menu global avec les layout
        north_panel = new JPanel();                         //création du panel
        //controle_maison = new JPanelMaison();

        setLayout(new BorderLayout());                      //création du layout

        north_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 200, 0));   //création du layout north_panel

        north_panel.add(bouton_amelioration);              //ajoute le bouton amelioration à la fenetre menu
        north_panel.add(bouton_voir_maison);               //ajoute le bouton voirmaison à la fenetre menu

        add(north_panel, BorderLayout.NORTH);               //ajout du panel north au panel menu
        //add(controle_maison, BorderLayout.CENTER);

        west_panel = new JPanel();                          //création de west_panel

        add(west_panel, BorderLayout.WEST);                 //ajout du panel west au panel menu

        west_panel.setLayout(new BorderLayout(0, 20));           //création du layout de west_panel et ajout d'un vertical gap

        west_panel.add(panneau_meteo, BorderLayout.NORTH);       //ajout du panneau météo au panneau de gauche
        west_panel.add(panneau_oublis, BorderLayout.CENTER);     //ajout du panneau oublis à panneau de gauche
        west_panel.add(panneau_demande, BorderLayout.SOUTH);     //ajout du panneau demande à panneau de gauche

        add(controle_maison, BorderLayout.EAST);

        checkbox_fenetre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maison.getFamille().getSpecificOubli("Fenetre Ouverte").activer(false);
                checkbox_fenetre.setVisible(false);
                checkbox_fenetre.setSelected(false);
            }
        });

        checkbox_eau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maison.getFamille().getSpecificOubli("Robinet Ouvert").activer(false);
                checkbox_eau.setVisible(false);
                checkbox_eau.setSelected(false);
            }
        });

        checkbox_ampoule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maison.getFamille().getSpecificOubli("Lumiere Allumee").activer(false);
                checkbox_ampoule.setVisible(false);
                checkbox_ampoule.setSelected(false);
            }
        });

        bouton_accepter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demande_actuelle.accepter(true);
                demande_actuelle.setEstActif(false);
                demande_actuelle.setEstVisible(false);
                bouton_accepter.setVisible(false);
                bouton_refuser.setVisible(false);
                titre_demande.setText("Demandes de la famille");
                demande_actuelle.payer(partie);
                partie.setScore(50);
                maison.getFamille().calculConfortDemande(demande_actuelle);
                partie.setJouer_jeu(true);
            }
        });

        bouton_refuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                demande_actuelle.accepter(false);
                demande_actuelle.setEstVisible(false);
                bouton_accepter.setVisible(false);
                bouton_refuser.setVisible(false);
                titre_demande.setText("Demandes de la famille");
                maison.getFamille().calculConfortDemande(demande_actuelle);
                partie.setJouer_jeu(true);
            }
        });
    }

    public void actualiser(Maison maison, Climat climat){
        controle_maison.actualiser(maison);

        switch (climat.getMeteo_actuelle()) {
            case 0 -> etat_meteo.setText("Soleil");
            case 1 -> etat_meteo.setText("Nuage");
            default -> etat_meteo.setText("Pluie");
        }

        temperature_meteo.setText("Temperature : " + String.format("%.2f", climat.getTemperature_reelle()) + "C°");
        humidite_meteo.setText("Humidité : " + String.format("%.2f", climat.getHumidite_reelle()) + "%");
        luminosite_meteo.setText("Luminosité : " + climat.getLuminosite_reelle() + "%");

        if(maison.getFamille().getSpecificOubli("Fenetre Ouverte").isEstActif()){
            checkbox_fenetre.setVisible(true);
        }
        if(maison.getFamille().getSpecificOubli("Robinet Ouvert").isEstActif()){
            checkbox_eau.setVisible(true);
        }
        if(maison.getFamille().getSpecificOubli("Lumiere Allumee").isEstActif()){
            checkbox_ampoule.setVisible(true);
        }

        int i = 0;
        boolean stop = false;
        while(i < 6 && stop == false) {
            if(maison.getFamille().getSpecificDemande(i).isEstVisible()){
                demande_actuelle = maison.getFamille().getSpecificDemande(i);
                titre_demande.setText(demande_actuelle.toString() + " : " + demande_actuelle.getPrix());
                bouton_accepter.setVisible(true);
                bouton_refuser.setVisible(true);
                stop = true;
            }
            i++;
        }
    }

    public JPanelControleMaison getControle_maison() {
        return controle_maison;
    }
}