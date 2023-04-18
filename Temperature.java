public class Temperature  {
    private float temperature;
    private float temperature_ressentie;
    private boolean etat;
    public Temperature( float temperature,boolean etat) {
        super();
        this.temperature = temperature;
        this.etat=etat;
    }

    public void calculTemperatureRessentie(float humid_reelle){
        temperature_ressentie = temperature * (1 + (humid_reelle / 500));
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public float getTemperature() { return this.temperature; }

    public float getTemperature_ressentie() {
        return temperature_ressentie;
    }

    public void setTemperature(float temp, float temp_exterieur, int isolation) {
        if(isEtat()) {
            if(temperature > temp){
                temperature -= 1f / (7f - isolation);
                if(temperature <= temp){
                    temperature = temp;
                }
            } else if (temperature < temp) {
                temperature += 1f / (7f - isolation);
                if(temperature >= temp){
                    temperature = temp;
                }
            }
        } else {
            if(temperature > temp_exterieur){
                temperature -= 1f / (2f + isolation);
                if(temperature <= temp_exterieur){
                    temperature = temp_exterieur;
                }
            } else if (temperature < temp_exterieur) {
                temperature += 1f / (2 + isolation);
                if(temperature >= temp_exterieur){
                    temperature = temp_exterieur;
                }
            }
        }
    }

}