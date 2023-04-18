import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Famille {

    private List<Oubli> oubli;
    private List<Demande> demande;
    private int degreeConfort;


    public Famille(int degreeConfort) {




        this.degreeConfort=degreeConfort;
        this.oubli = new ArrayList<Oubli>();
        this.demande = new ArrayList<Demande>();

        Oubli robinetOuvert = new Oubli("Robinet Ouvert",20);
        oubli.add(robinetOuvert);
        Oubli LumiereAllumee = new Oubli("Lumiere Allumee",20);
        oubli.add(LumiereAllumee);
        Oubli Fenetre_Ouverte = new Oubli("Fenetre Ouverte",20);
        oubli.add(Fenetre_Ouverte);

        Demande nouveau_Telephone = new Demande("nouveau Telephone", 200,  -10, 30,20);
        Demande table_a_manger = new Demande("Table a manger", 200,  -10, 30,20);
        Demande console_de_jeu = new Demande("Console de Jeu", 200,  -10, 30,20);
        Demande tablette = new Demande("Tablette", 200,  -10, 30,20);
        Demande chaussure = new Demande("Chaussure", 200,  -10, 30,20);
        Demande voiture = new Demande("Voiture", 2000,  -20, 60,200);



        demande.add(nouveau_Telephone);
        demande.add(table_a_manger);
        demande.add(console_de_jeu);
        demande.add(voiture);
        demande.add(tablette);
        demande.add(chaussure);


    }

    public void setDegreeConfort(int degreeConfort) {
        this.degreeConfort = degreeConfort;
    }

    public int getDegreeConfort() {
        return degreeConfort;
    }
    public void incrementDegreeConfort(){
        this.degreeConfort++;
    }
    public void decrementDegreeConfort(){
        this.degreeConfort--;
    }

    public Oubli getSpecificOubli(String name) {
        for (Oubli oubli : oubli) {
            if (oubli.toString().equals(name)) {
                return oubli;
            }
        } return null;
    }

    public Demande getSpecificDemande(int i) {
        return demande.get(i);
    }

    public List<Demande> getDemande() {
        return demande;
    }

    // Ajouter un oubli de la famille
    public void AjouterOubli(Oubli ob) {
        this.oubli.add(ob);
    }


    //generer aleatoirement un oubli de la famille
    public void oublier() {
        if (!oubli.isEmpty()) {
            Random random = new Random();
            if (random.nextInt(2) == 0) {    //remettre à 5
                int index = random.nextInt(oubli.size());
                Oubli selectedOubli = oubli.get(index);
                selectedOubli.activer(true);
            }
        }
    }

    public float calculConsoOubli(){
        float conso = 0;
        for(int i = 0; i < oubli.size(); i++){
            if(oubli.get(i).isEstActif()){
                conso += oubli.get(i).getConsommation();
            }
        }

        return conso;
    }

    public void calculConfortOubli(){
        for(int i = 0; i < oubli.size(); i++){
            if(oubli.get(i).isEstActif()){
                degreeConfort -= 2;
            }
        }
    }

    // Ajouter une demande de la famille
    public void Ajouterdemande(Demande demande) {
        this.demande.add(demande);

    }

    // generer aleatoirement une demande de la famille
    public void demander(Partie partie) {
        if (!demande.isEmpty()) {
            Random random = new Random();
            if (random.nextInt(3) == 0) {   //remettre à 5
                int index = random.nextInt(demande.size());
                if(demande.get(index).getEstActif()) {
                    demande.get(index).setEstVisible(true);
                    partie.setJouer_jeu(false);
                }
            }
        }
    }

    public void reglerConfortTemperature(float temperature_ressenti) {
        if (temperature_ressenti <= 4 || temperature_ressenti >= 36) {
            degreeConfort -= 4;
        } else if (temperature_ressenti <= 12 || temperature_ressenti >= 28) {
            degreeConfort -= 3;
        } else if (temperature_ressenti <= 16 || temperature_ressenti >= 24) {
            degreeConfort -= 2;
        } else if (temperature_ressenti <= 18 || temperature_ressenti >= 22) {
            degreeConfort -= 1;
        } else {
            degreeConfort += 1;

        }
    }


    public void calculConfortDemande(Demande demande) {
        if (demande.getEstAccepte()) {
            degreeConfort += demande.getConfortPositif();
        } else {
            degreeConfort += demande.getConfortNegatif();
        }

    }

}