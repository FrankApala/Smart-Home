import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPanelObjetAmelioration extends JPanel {
    private JLabel nom;
    private JLabel niveau;
    private JLabel prix;
    private JButton ameliorer;

    public JPanelObjetAmelioration(Partie partie, Zone zone, Amelioration amelioration) {
        nom = new JLabel(amelioration.toString());
        niveau = new JLabel("Niveau : " + amelioration.getNiveau());
        prix = new JLabel("Prix :" + amelioration.getPrix());
        ameliorer = new JButton("Améliorer");

        setLayout(new GridLayout(4, 1));
        add(nom, CENTER_ALIGNMENT);
        add(niveau, CENTER_ALIGNMENT);
        add(prix, CENTER_ALIGNMENT);
        add(ameliorer, CENTER_ALIGNMENT);

        ameliorer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zone.ameliorer(amelioration, partie);
                niveau.setText("Niveau : " + amelioration.getNiveau());
                if(amelioration.getNiveau() < 5) {
                    prix.setText("Prix : " + amelioration.getPrix());
                } else {
                    prix.setText("Amélioration maximum");
                }
            }
        });
    }
}