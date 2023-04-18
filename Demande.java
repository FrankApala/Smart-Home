public class Demande {
    private String nom;
    private int prix;
    private float consommation;
    private int confortPositif;
    private int confortNegatif;
    private boolean estActif = true;
    private boolean estVisible = false;
    private boolean estAccepte = false;
    public Demande(String nom,int prix,int confortNegatif,int confortPositif,float consommation){
        this.prix=prix;
        this.nom=nom;
        this.confortPositif=confortPositif;
        this.confortNegatif=confortNegatif;
        this.consommation=consommation;

    }

    public void payer(Partie partie){
        partie.setArgent(partie.getArgent() - prix);
    }

    public boolean isEstVisible() {
        return estVisible;
    }

    public void setEstVisible(boolean estVisible) {
        this.estVisible = estVisible;
    }

    public boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(boolean estActif) {
        this.estActif = estActif;
    }

    public float getConsommation() {
        return consommation;
    }
    public boolean getEstAccepte(){
        return this.estAccepte;
    }
    public int getConfortPositif(){
        return this.confortPositif;
    }
    public int getConfortNegatif(){
        return this.confortNegatif;
    }

    public int getPrix() {
        return prix;
    }

    public void accepter(boolean b) {
        this.estAccepte=b;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}