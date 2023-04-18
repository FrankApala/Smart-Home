import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Partie implements ActionListener, KeyListener {
    private boolean jouer_joueur = true;
    private boolean jouer_jeu = true;
    private Timer horloge;
    public static final int duree_journee = 240;
    private int temps = 0;
    private int compteur_seconde = 0;
    private int jours = 1;      //jours de la partie
    private int argent = 1000;
    private int argent_temp = 0;
    private int confort = 100;
    private int temp_conf = 0;
    private int consommation = 0;   //valeur à récupérer de la classe maison (addition conso chaque zone)
    private int conso_jour = 0;
    private int gain_jour = 0;
    private int argent_sec = 8;
    private int score = 0;  //incrémenté selon temps et actions joueur, affiché à la fin de la partie
    private final Climat climat;
    private Maison maison;
    public Interface interface_;

    public static void main(String[] args) {
        Partie partie = new Partie();
        partie.start_();
    }

    public Partie(){
        maison = new Maison(2);
        interface_ = new Interface(this, maison);
        climat = new Climat(0,20, 20, 55, 55, 0, 0);
        horloge = new Timer(25, this); //40 images par secondes
    }

    public void reduireArgent(int perte){
        argent -= perte;
    }

    //méthode qui permet d'ajouter de l'xp
    public void augmenterScore(int xp){
        score = score + xp;
    }

    @Override
    public void actionPerformed(ActionEvent e)  //boucle de jeu
    {
        horloge.start();

        if (compteur_seconde == 10) {       //remettre à 40
            if (jouer_joueur && jouer_jeu) {
                maison.actualiser(interface_.getInterface_globale().getMenu().getControle_maison().getSlider(), interface_.getInterface_globale().getMenu().getControle_maison().getThermostat(), climat.getTemperature_reelle(), climat.getHumidite_reelle(), climat.getLuminosite_reelle());
                confort = maison.getFamille().getDegreeConfort();
                temps++;
                conso_jour = (int) (conso_jour + maison.getConsommation()/1000);
                argent_temp += argent_sec;

                if(confort <= 0) {
                    temp_conf++;
                } else {
                    temp_conf = 0;
                }
                if(argent <= 0 || temp_conf >= 10){
                    System.exit(1);
                    System.out.println("Game Over");
                    System.out.println("Score : " + score);
                }
                if(temps % 20 == 0){
                    climat.calculerClimatReel(temps, jours);
                }
                if(temps % 50 == 0){
                    maison.getFamille().oublier();
                    maison.getFamille().demander(this);
                }
                if (jours * duree_journee <= temps) {
                    jours++;                                //jour +1
                    //argent = argent + argent_temp;          //donne au joueur l'argent de la journée
                    gain_jour = argent_temp - conso_jour;
                    argent_temp=0;                          //cumul de l'argent du jour remis à 0 en fin de journée
                    argent = argent + gain_jour;           //chaque fin de journée le joueur perd l'argent de la consommation accumulée cette journée
                    conso_jour = 0;                        //cumul de conso de la journée remis à 0
                }
                if(temps%10== 0){
                    score = score + 10;

                }
            }
            compteur_seconde = 0;
        } else {
            compteur_seconde++;
        }
        interface_.actualiser(this, maison, climat);
    }

    public void start_(){
        horloge.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(jouer_jeu) {
                jouer_joueur = !jouer_joueur;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public int getTemps() {
        return temps;
    }

    public int getScore(){
        return score;
    }

    public int getArgent() {
        return argent;
    }
    public int getArgentSec() {
        return argent_sec;
    }

    public int getConsoJour() {
        return conso_jour;
    }

    public float getPerte(){
        return maison.getConsommation();
    }

    public int getConfort() {
        return confort;
    }

    public int getConsommation() {
        return consommation;
    }

    public int getJours() {
        return jours;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void setJouer_jeu(boolean jouer_jeu) {
        this.jouer_jeu = jouer_jeu;
    }
}