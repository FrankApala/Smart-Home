public class Oubli {
    private String nom;

    private float consommation;
    private boolean estActif;
    public Oubli(String nom,float consommation){
        this.consommation=consommation;
        this.nom=nom;


    }

    public void activer(boolean b){
        this.estActif=b;
    }

    @Override
    public String toString() {
        return this.nom;
    }


    public float getConsommation(){
        return this.consommation;
    }

    public boolean isEstActif() {
        return estActif;
    }
}