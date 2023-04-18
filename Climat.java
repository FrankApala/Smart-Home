public class Climat {
    private int luminosite_moyenne;
    private int luminosite_reelle;
    private float temperature_moyenne;
    private float temperature_reelle;
    private float humidite_moyenne;//une bonne humidité est entre 40% et 70%
    private float humidite_reelle;
    private int meteo_actuelle;
    private final Meteo[] meteos;
    private int duree;

    public Climat(int lr, float tm, float tr, float hm, float hr, int m, int d){
        luminosite_reelle = lr;
        temperature_moyenne = tm;
        temperature_reelle = tr;
        humidite_moyenne = hm;
        humidite_reelle = hr;
        meteo_actuelle = m;
        duree = d;
        meteos = new Meteo[3];
        meteos[0] = new Meteo(0, 10, -15, 5, 0, 20); //soleil
        meteos[1] = new Meteo(-5, 5, -10, 10, -15, 5); //nuage
        meteos[2] = new Meteo(-10, 0, -5, 15, -20, 0); //pluie
    }

    public float getTemperature_moyenne() {
        return temperature_moyenne;
    }

    public float getTemperature_reelle() {
        return temperature_reelle;
    }

    public float getHumidite_moyenne() {
        return humidite_moyenne;
    }

    public float getHumidite_reelle() {
        return humidite_reelle;
    }

    public int getLuminosite_moyenne() {
        return luminosite_moyenne;
    }

    public int getLuminosite_reelle() {
        return luminosite_reelle;
    }

    public int getMeteo_actuelle() {
        return meteo_actuelle;
    }

    public int getDuree() {
        return duree;
    }

    private void aleaDuree(){
        int min_duree = (Partie.duree_journee / 3) / 20;
        duree = min_duree + (int)(Math.random() * (Partie.duree_journee - min_duree) / 20);
    }

    private void aleaMeteo(){
        meteo_actuelle = (int)(Math.random() * 3);
    }

    public void calculerClimatReel(int temps, int jours){
        if(duree == 0){
            aleaDuree();
            aleaMeteo();
        } else {
            duree --;
        }

        varierClimat(temps, jours);
        temperature_reelle = meteos[meteo_actuelle].calculerTemperature(temperature_moyenne);
        humidite_reelle = meteos[meteo_actuelle].calculerHumidite(humidite_moyenne);
        luminosite_reelle = meteos[meteo_actuelle].calculerLuminosite(luminosite_moyenne);
    }

    private void varierClimat(int temps, int jours){
        varierTemperature(temps, jours);
        varierHumidite(jours);
        varierLuminosite(temps);
    }

    private void varierTemperature(int temps, int jours){
        float milieu;
        float min = temperature_moyenne - 2;
        float max = temperature_moyenne + 2;
        float borne_max;
        float borne_min;

        if(temps % Partie.duree_journee < Partie.duree_journee/2) {
            milieu = 15 + (temps % Partie.duree_journee) * (5f/(Partie.duree_journee/2f));
        } else {
            milieu = 20 - ((temps % Partie.duree_journee) - Partie.duree_journee/2f) * (5f/(Partie.duree_journee/2f));
        }

        borne_max = milieu + jours * 1.5f;
        borne_min = milieu - jours * 1.5f;

        if (borne_max > 40){
            borne_max = 40;
        }
        if (borne_min < 10){
            borne_min = 10;
        }

        if (max > borne_max){
            max = borne_max;
        }
        if (min < borne_min) {
            min = borne_min;
        }

        temperature_moyenne = min + (float)(Math.random() * (max - min));
    }

    private void varierHumidite(int jours){
        float min = humidite_moyenne - 2;
        float max = humidite_moyenne + 2;
        float borne_max = 55 + jours * 1.5f;
        float borne_min = 55 - jours * 1.5f;

        if(borne_max > 75){
            borne_max = 75;
        }
        if(borne_min < 75){
            borne_min = 75;
        }

        if (max > borne_max){
            max = borne_max;
        }
        if (min < borne_min) {
            min = borne_min;
        }

        humidite_moyenne = min + (float)(Math.random() * (max - min));
    }

    private void varierLuminosite(int temps){
        int periode = temps % Partie.duree_journee;
        if (periode < Partie.duree_journee/2){
            luminosite_moyenne = 20 + (periode*60)/(Partie.duree_journee/2);
        } else {
            luminosite_moyenne = 80 - ((periode-(Partie.duree_journee/2))*60)/(Partie.duree_journee/2);
        }
    }
}
