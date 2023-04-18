public class Meteo {
    private final int min_temperature;
    private final int max_temperature;
    private final int min_humidite;
    private final int max_humidite;
    private final int min_luminosite;
    private final int max_luminosite;

    public Meteo(int min_t, int max_t, int min_h, int max_h, int min_l, int max_l){
        min_temperature = min_t;
        max_temperature = max_t;
        min_humidite = min_h;
        max_humidite = max_h;
        min_luminosite = min_l;
        max_luminosite = max_l;
    }

    public float calculerTemperature(float t_moyenne) {
        float min = t_moyenne - 5;
        float max = t_moyenne + 5;

        if (max > t_moyenne + max_temperature){
            max = t_moyenne + max_temperature;
        }
        if (min < t_moyenne + min_temperature) {
            min = t_moyenne + min_temperature;
        }

        return min + (float)(Math.random() * (max - min));
    }

    public float calculerHumidite(float h_moyenne) {
        float min = h_moyenne - 5;
        float max = h_moyenne + 5;

        if (max > h_moyenne + max_humidite){
            max = h_moyenne + max_humidite;
        }
        if (min < h_moyenne + min_humidite) {
            min = h_moyenne + min_humidite;
        }

        return min + (float)(Math.random() * (max - min));
    }

    public int calculerLuminosite(int l_moyenne) {
        int min = l_moyenne - 10;
        int max = l_moyenne + 10;

        if (max > l_moyenne + max_luminosite){
            max = l_moyenne + max_luminosite;
        }
        if (min < l_moyenne + min_luminosite) {
            min = l_moyenne + min_luminosite;
        }

        return min + (int)(Math.random() * (max - min + 1));
    }
}
