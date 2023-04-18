import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame{
    private JPanelBarreMenu interface_barre_menu;
    private JPanelGlobal interface_globale;

    public Interface(Partie partie, Maison maison){
        interface_barre_menu = new JPanelBarreMenu();
        interface_globale= new JPanelGlobal(partie, maison);

        setTitle("Smart Home");
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        addKeyListener(partie);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setLayout(new BorderLayout());

        add(interface_barre_menu, BorderLayout.NORTH);
        add(interface_globale, BorderLayout.CENTER);
    }

    public void actualiser(Partie partie, Maison maison, Climat climat){
        interface_barre_menu.actualiser(partie);
        interface_globale.getMenu().actualiser(maison, climat);
    }

    public JPanelGlobal getInterface_globale() {
        return interface_globale;
    }
}