public class Luminosite  {
    private int Luminosite;
    private boolean etat;

    public Luminosite(int Luminosite,boolean etat) {

        this.etat=etat;
        this.Luminosite=Luminosite;



    }


    public boolean isEtat() {
        return etat;
    }

    public void setEtat( boolean etat) {
        if (etat)
            this.Luminosite = 10;
        else this.Luminosite =0;
        this.etat = etat;
    }

    // getters and setters for the instance variables
    public int getLuminosite() { return this.Luminosite; }
    public void setLuminosite(int Luminosite) { this.Luminosite = Luminosite; }
    public void lireValeur(){ };


}
