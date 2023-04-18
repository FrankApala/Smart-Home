import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class JPanelControleMaison extends JPanel {
    private JPanelZone interface_cuisine;
    private JPanelZone interface_chambres;
    private JPanelZone interface_salon;
    private JPanelZone interface_garage;
    private JPanelZone interface_salle_de_bain;
    private JPanelZone interface_exterieur;
    private JLabel consommation;
    private JLabel temperature;
    private JLabel luminosite;
    private JLabel temperature_ressentie;
    private JSlider slider_temperature;
    private JCheckBox allumer_thermostat;
    public JPanelControleMaison(Maison maison){
        interface_cuisine = new JPanelZone(maison.getSpecificZone("Cuisine"));
        interface_chambres = new JPanelZone(maison.getSpecificZone("Chambres"));
        interface_salon = new JPanelZone(maison.getSpecificZone("Salon"));
        interface_garage = new JPanelZone(maison.getSpecificZone("Garage"));
        interface_salle_de_bain = new JPanelZone(maison.getSpecificZone("Salle de bain"));
        interface_exterieur = new JPanelZone(maison.getSpecificZone("Exterieur"));
        consommation = new JLabel("Consommation totale : 0.00");
        temperature = new JLabel("Température : 20.00");
        luminosite = new JLabel("Luminosite : 0%");
        temperature_ressentie = new JLabel("Température ressentie : 20.00");
        slider_temperature = new JSlider();
        allumer_thermostat = new JCheckBox("Allumer le thermostat");

        setLayout(new GridLayout(10,1));
        setPreferredSize(new Dimension(600, 0));

        // Paramétrage slider
        slider_temperature.setPaintTrack(true);
        slider_temperature.setPaintTicks(true);
        slider_temperature.setPaintLabels(true);

        slider_temperature.setMinimum(10);
        slider_temperature.setMaximum(30);
        slider_temperature.setValue(20);
        slider_temperature.setMajorTickSpacing(10);
        slider_temperature.setMinorTickSpacing(1);

        JPanel global1 = new JPanel();
        JPanel global2 = new JPanel();
        global1.setLayout(new GridLayout(1, 3));
        global2.setLayout(new GridLayout(1, 2));

        global1.add(slider_temperature);
        global1.add(temperature);
        global1.add(allumer_thermostat);
        global2.add(temperature_ressentie);
        global2.add(luminosite);
        global2.add(consommation);

        Border redline = BorderFactory.createLineBorder(Color.red);
        global2.setBorder(redline);

        add(global2);
        add(global1);
        add(interface_cuisine);
        add(interface_chambres);
        add(interface_salon);
        add(interface_garage);
        add(interface_salle_de_bain);
        add(interface_exterieur);

        slider_temperature.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                temperature.setText("Température : " + slider_temperature.getValue() + ".00");
            }
        });
    }

    public void actualiser(Maison maison){
        interface_cuisine.actualiser(maison.getSpecificZone("Cuisine"));
        interface_chambres.actualiser(maison.getSpecificZone("Chambres"));
        interface_salon.actualiser(maison.getSpecificZone("Salon"));
        interface_garage.actualiser(maison.getSpecificZone("Garage"));
        interface_salle_de_bain.actualiser(maison.getSpecificZone("Salle de bain"));
        interface_exterieur.actualiser(maison.getSpecificZone("Exterieur"));
        consommation.setText("Consommation totale : " + String.format("%.2f", maison.getConsommation()));
        temperature_ressentie.setText("Température ressentie : " + String.format("%.2f", maison.getTemp().getTemperature_ressentie()));
        luminosite.setText("Luminosite : " + maison.getLum() + "%");
    }

    public float getSlider(){
        return (float)slider_temperature.getValue();
    }

    public boolean getThermostat(){
        return allumer_thermostat.isSelected();
    }
}
