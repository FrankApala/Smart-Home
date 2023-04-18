import java.util.ArrayList;
import java.util.List;

public class Maison {
    private List<Zone> zones;
    private Temperature temp;
    private float consommation;
    private Famille famille;

    private int isolation;

    private int lum = 0;


    // constructeur de la classe maison
    public Maison(int isolation) {

        temp = new Temperature(20, false);

        Amelioration lave_vaisselle=new Amelioration("Lave_vaisselle",true);
        Amelioration refrigerateur=new Amelioration("Refrigeteur",true);
        Amelioration plaque_electrique=new Amelioration("plaque electrique",true);

        Amelioration Ampoules1=new Amelioration("Ampoules",true);
        Amelioration Ampoules2=new Amelioration("Ampoules",true);
        Amelioration Ampoules3=new Amelioration("Ampoules",true);
        Amelioration Ampoules4=new Amelioration("Ampoules",true);
        Amelioration Ampoules5=new Amelioration("Ampoules",true);
        Amelioration TV=new Amelioration("TV",true);
        Amelioration Chauffage=new Amelioration("Chauffage electrique",true);
        Amelioration mirroir_led=new Amelioration("Mirroir",true);



        Amelioration robinet=new Amelioration("robinet",true);
        Amelioration fer_a_repasser=new Amelioration("Fer a repasser",true);
        Amelioration fenetres=new Amelioration("fenetres",true);
        Amelioration toit=new Amelioration("Toit",true);
        Amelioration portes=new Amelioration("portes",true);


        Amelioration Installer_panneau_solaire=new Amelioration("Panneau solaire",true);

        Amelioration [] a_Cuisine={lave_vaisselle,refrigerateur,plaque_electrique};
        Amelioration [] a_Salon={Ampoules1,TV,Chauffage};
        Amelioration [] a_Salle_De_Bain={Ampoules2,mirroir_led,robinet};
        Amelioration [] a_Chambres={Ampoules3,fer_a_repasser,fenetres};
        Amelioration [] a_Exterieur={Ampoules4,Installer_panneau_solaire,toit};
        Amelioration [] a_Garage={Ampoules5,Chauffage,portes};


        //parametres en entrée
        this.famille=new Famille(50);
        this.consommation=0;
        this.isolation = isolation;
        this.zones = new ArrayList<Zone>();
        Zone Cuisine = new Zone("Cuisine",a_Cuisine);
        addZone(Cuisine);
        Zone Salon = new Zone("Salon",a_Salon);
        addZone(Salon);
        Zone SalleDeBain= new Zone("Salle de bain",a_Salle_De_Bain);
        addZone(SalleDeBain);
        Zone Chambres= new Zone("Chambres",a_Chambres);
        addZone(Chambres);
        Zone Garage= new Zone("Garage",a_Garage);
        addZone(Garage);
        Zone Exterieur= new Zone("Exterieur",a_Exterieur);
        addZone(Exterieur);



    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Temperature getTemp() {
        return temp;
    }

    public int getLum() {
        return lum;
    }

    public float getConsommation() {
        return consommation;
    }

    public void setConsommation(float consommation) {
        this.consommation = consommation;
    }

    public Famille getFamille(){
        return this.famille;
    }

    public Zone getSpecificZone(String name) {
        for (Zone zone : zones) {
            if (zone.toString().equals(name)) {
                return zone;
            }
        }

        return null;
    }

    public void setZones(List<Zone> zones) { this.zones = zones; }

    // methode pour ajouter une zone dans la maison
    public void addZone(Zone zone) {
        this.zones.add(zone);
    }
    // methode pour ajouter une categories d'equipement dans la maison


    // methode pour enlever une zone de la maison
    public void removeZone(Zone zone) {
        this.zones.remove(zone);
    }



    public void calculConsommation(float temperature){
        float consommation=0.0f;

        for(int i=0; i<zones.size();i++){
            Zone zone = zones.get(i);
            zone.calculConsommationLum();
            consommation+=zone.getConsommation();
        }
        if(temp.isEtat()){
            consommation += (Math.abs(temp.getTemperature() - temperature)) * (5 - isolation) * 10;
        }
        consommation += famille.calculConsoOubli();

        for(int i = 0; i < famille.getDemande().size(); i++){
            if(famille.getSpecificDemande(i).getEstAccepte()){
                consommation += famille.getSpecificDemande(i).getConsommation();
            }
        }

        this.consommation=consommation;
    }

    public void calculLuminosite(int luminosite_exterieur){
        int lum_zones = 0;
        for (int i = 0; i < zones.size(); i++) {
            Zone zone = zones.get(i);
            if(zone.getLum()){
                lum_zones += 50;
            }
        }
        lum = luminosite_exterieur + ((lum_zones) / 5);
    }

    public void reglerConfortLumiere(int luminosite_ext) {
        calculLuminosite(luminosite_ext);
        if (lum < 50) {
            famille.decrementDegreeConfort();
        } else {
            famille.incrementDegreeConfort();
        }
    }

    public void actualiser(float temperature_maison, boolean allumer_thermostat, float temperature_exrerieur, float humidité_exterieur, int luminosite_ext){
        temp.setEtat(allumer_thermostat);
        temp.setTemperature(temperature_maison, temperature_exrerieur, isolation);
        calculConsommation(temperature_exrerieur);
        temp.calculTemperatureRessentie(humidité_exterieur);
        reglerConfortLumiere(luminosite_ext);
        famille.reglerConfortTemperature(temp.getTemperature_ressentie());
        famille.calculConfortOubli();
        if(famille.getDegreeConfort() > 100){
            famille.setDegreeConfort(100);
        }
    }

}






