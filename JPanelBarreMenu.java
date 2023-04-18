import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JPanelBarreMenu extends JPanel {
    private final JLabel label_temps;
    private final JLabel label_argent;
    private final JLabel label_confort;
    private final JLabel label_gain;
    private final JLabel label_date;
    private final JProgressBar jb;

    public JPanelBarreMenu(){
        Border blackline = BorderFactory.createLineBorder(Color.black);

        //label_temps
        label_temps = new JLabel("0", JLabel.CENTER);
        label_temps.setForeground(Color.YELLOW);
        label_temps.setBorder(blackline);                                   //bords noirs
        label_temps.setBackground(Color.darkGray);                         //fond gris
        label_temps.setOpaque(true);

        //label_argent
        label_argent = new JLabel("0", JLabel.CENTER);
        label_argent.setBorder(blackline);                                      //bords noirs
        label_argent.setBackground(Color.darkGray);                            //fond gris
        label_argent.setOpaque(true);


        //label_confort
        label_confort = new JLabel("confort :", JLabel.CENTER);
        label_confort.setText("Confort : ");                        //contenu du labem confort
        label_confort.setForeground(Color.WHITE);                   //texte confort en blanc
        label_confort.setPreferredSize(new Dimension(50, 30));
        label_confort.setBorder(blackline);                                 //bords noirs
        label_confort.setBackground(Color.darkGray);                        //fond gris
        label_confort.setOpaque(true);

        jb=new JProgressBar(0,100);                                         //création de la jauge
        //jb.setBounds(40,40,160,30);
        jb.setValue(0);
        jb.setStringPainted(true);

        //label_conso
        label_gain = new JLabel("0", JLabel.CENTER);
        label_gain.setForeground(Color.ORANGE);
        label_gain.setBorder(blackline);                                    //bords noirs
        label_gain.setBackground(Color.darkGray);                           //fond gris
        label_gain.setOpaque(true);

        //label_date
        label_date = new JLabel("0", JLabel.CENTER);
        label_date.setForeground(Color.WHITE);
        label_date.setBorder(blackline);                                     //bords noirs
        label_date.setBackground(Color.darkGray);                           //fond gris
        label_date.setOpaque(true);

        setPreferredSize(new Dimension(getMinimumSize().width, 30));
        setLayout(new GridLayout(1,3));                            //creation layout differents label barre menu
        add(label_date);                                //ajout du label date à la barre de menu
        add(label_temps);                               //ajout du label temps à la barre de menu
        add(label_argent);                              //ajout du label argent à la barre de menu
        add(label_confort);                             //ajout du label confort à la barre de menu
        add(jb);                                        //ajout de la jauge confort à la barre de menu
        add(label_gain);                               //ajout du label gain à la barre menu

    }

    /**
     *
     * @param partie objet de type partie
     */
    public void actualiser(Partie partie){
        label_temps.setText("Score : " + partie.getScore());                                    //affiche le score du joueur
        label_gain.setText("prochains gains : " + ((partie.getArgentSec()*Partie.duree_journee) - partie.getConsoJour()) + "  perte : -" + String.format("%.2f", partie.getPerte()/1000));

        //label argent
        label_argent.setText("Argent : " + partie.getArgent());     //mise a jour argent joueur
        if (partie.getArgent() < 500) {                             //si le joueur a moins de 500 pieces, apparait en rouge
            label_argent.setForeground(Color.RED);
        } else if (partie.getArgent() > 1500) {                     //si plus de 1500 argent apparait en vert
            label_argent.setForeground(Color.green);
        }else{
            label_argent.setForeground(Color.YELLOW);               //si >500 et <1500  argent apparait en jaune
        }

        //jauge confort
        jb.setValue(partie.getConfort());                           //si confort >75 on met la jauge en vert
        if (partie.getConfort() > 75) {
            jb.setForeground(Color.green);                          //si confort < 30 on met la jauge en rouge
        } else if (partie.getConfort() < 30) {
            jb.setForeground(Color.RED);
        } else {                                                    //si confort >30 et <75 on met la jauge en orange
            jb.setForeground(Color.ORANGE);
        }

        //label_date                                                //mise a jour de la date du jeu
        label_date.setText("JOUR : " + partie.getJours() + "      "  + (partie.getTemps()/10)%24 + " heures");
    }
}