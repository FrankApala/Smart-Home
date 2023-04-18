import java.util.ArrayList;
import java.util.List;

public class Zone {
    private String nom;

    private boolean lum;

    private Amelioration [] amelioration=new Amelioration[3];

    private float consommation_lum = 0;
    private float consommation_amelioration = 1000;






    public Zone(String nom,Amelioration[] amelioration) {
        this.amelioration=amelioration;


        this.nom = nom;

        this.lum = false;



    }




    public void addAmelioration(int i, Amelioration amelioration) {
        this.amelioration[i]=amelioration;
    }

    public boolean getLum() {
        return lum;
    }

    public void setLum(boolean lum) {
        this.lum = lum;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public float getConsommation() {
        return consommation_amelioration + consommation_lum;
    }

    public Amelioration[] getAmelioration() {
        return amelioration;
    }


    public void ameliorer(Amelioration amelioration,Partie partie){
        if(amelioration.getNiveau()<5){

            amelioration.addNiveau();
            partie.setArgent(partie.getArgent() - amelioration.getPrix());
            calculConsommationZoneAmelioration(amelioration.getNiveau());
            partie.setScore(partie.getScore()+50);
        }
        else{
            amelioration.setEstAmeliorable(false);
        }
    }

    public void calculConsommationZoneAmelioration(int niveauAmelioration){
        if(niveauAmelioration==1){
            consommation_amelioration-=20;
        }
        else if(niveauAmelioration==2){
            consommation_amelioration-=30;
        }
        else if(niveauAmelioration==3){
            consommation_amelioration-=40;
        }
        else if(niveauAmelioration==4){
            consommation_amelioration-=50;
        }
        else if(niveauAmelioration==5){
            consommation_amelioration-=60;
        }

    }

    public void calculConsommationLum() {
        if(lum){
            consommation_lum = 100;
        } else {
            consommation_lum = 0;
        }
    }


}