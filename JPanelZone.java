import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelZone extends JPanel {
    private boolean test = false;
    private JLabel titre;
    private JLabel consommation;
    private JCheckBox switch_lum;

    public JPanelZone(Zone zone){
        setLayout(new GridLayout(1, 3));
        setPreferredSize(new Dimension(getPreferredSize().width, 30));

        //Créer
        titre = new JLabel(zone.toString() + " :");
        consommation = new JLabel("Consommation :");
        switch_lum = new JCheckBox("Allumer la lumière");

        // Ajouter le slider au panneau
        add(titre);
        add(switch_lum);
        add(consommation);
    }

    public void actualiser(Zone zone){
        consommation.setText("Consommation : " + String.format("%.2f", zone.getConsommation()));
        zone.setLum(switch_lum.isSelected());
    }
}
