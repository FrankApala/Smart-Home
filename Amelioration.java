public class Amelioration {
    private String nom;
    private int niveau=0;
    private int niveauMax=5;
    private int[] prix={200,300,400,500,1000,0};
    boolean est_ameliorable;
    public Amelioration(String nom,boolean est_ameliorable){
        this.nom=nom;
        this.niveau=niveau;
        this.est_ameliorable=est_ameliorable;

    }

    public int getPrix() {
        return prix[niveau];
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    public void addNiveau(){
        niveau++;
    }

    @Override
    public String toString() {
        return this.nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getNiveauMax() {
        return niveauMax;
    }

    public boolean getEstAmeliorable() {
        return est_ameliorable;
    }

    public void setEstAmeliorable(boolean est_ameliorable) {
        this.est_ameliorable = est_ameliorable;
    }
}