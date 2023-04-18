import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelAmelioration extends JPanel {
    private final JPanelZoneAmelioration cuisine;
    private final JPanelZoneAmelioration salon;
    private final JPanelZoneAmelioration salle_de_bain;
    private final JPanelZoneAmelioration chambres;
    private final JPanelZoneAmelioration garage;
    private final JPanelZoneAmelioration exterieur;


    public JPanelAmelioration(Partie partie, Maison maison, JButton bouton_retour_menu){
        cuisine = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Cuisine"));
        salon = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Salon"));
        salle_de_bain = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Salle de bain"));
        chambres = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Chambres"));
        garage = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Garage"));
        exterieur = new JPanelZoneAmelioration(partie, maison.getSpecificZone("Exterieur"));

        setLayout(new BorderLayout(15, 10));

        JPanel panel_central = new JPanel();
        panel_central.setLayout(new GridLayout(1,6,10, 10));

        add(bouton_retour_menu, BorderLayout.NORTH);
        add(panel_central, BorderLayout.CENTER);

        panel_central.add(cuisine);
        panel_central.add(salon);
        panel_central.add(salle_de_bain);
        panel_central.add(chambres);
        panel_central.add(garage);
        panel_central.add(exterieur);
    }
}
