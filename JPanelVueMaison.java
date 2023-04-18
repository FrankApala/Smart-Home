import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JPanelVueMaison extends JPanel{
    private JLabel image_maison;

    public JPanelVueMaison(JButton bouton_retour_maison_menu){
        //ajout image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("src\\maison.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        image_maison = new JLabel(new ImageIcon(image));
        image_maison.setPreferredSize(new Dimension(541, 514));        //dimensions de l'image

        setLayout(new BorderLayout());
        add(bouton_retour_maison_menu, BorderLayout.NORTH);
        add(image_maison, BorderLayout.CENTER);
    }
}