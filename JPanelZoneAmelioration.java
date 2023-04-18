import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JPanelZoneAmelioration extends JPanel {
    JPanelObjetAmelioration amelioration1;
    JPanelObjetAmelioration amelioration2;
    JPanelObjetAmelioration amelioration3;

    public JPanelZoneAmelioration(Partie partie, Zone zone){
        amelioration1 = new JPanelObjetAmelioration(partie, zone, zone.getAmelioration()[0]);
        amelioration2 = new JPanelObjetAmelioration(partie, zone, zone.getAmelioration()[1]);
        amelioration3 = new JPanelObjetAmelioration(partie, zone, zone.getAmelioration()[2]);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setPreferredSize(new Dimension(200, 700));

        JLabel titre = new JLabel(zone.toString() + " :", SwingConstants.CENTER);
        titre.setBorder(blackline);
        titre.setPreferredSize(new Dimension(200, 30));
        add(titre);

//ajout de chaque amélioration au panneau d'amélioration de cuisine
        setLayout(new GridLayout(6,1));
        add(titre, CENTER_ALIGNMENT);
        add(amelioration1, CENTER_ALIGNMENT);
        add(amelioration2, CENTER_ALIGNMENT);
        add(amelioration3, CENTER_ALIGNMENT);
    }
}
