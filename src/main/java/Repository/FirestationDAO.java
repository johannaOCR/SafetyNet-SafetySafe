package Repository;

import com.safetynet.safetynetalerts.Model.FireStation;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationDAO implements ObjectDAO{
    public static List<FireStation> firestations = new ArrayList<>();
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FirestationDao");

    static {
        firestations.add(new FireStation(1).addAddress("29 15th St"));
        firestations.add(new FireStation(2).addAddress("834 Binoc Ave"));
        firestations.add(new FireStation(1).addAddress("1509 Culver St"));
        firestations.add(new FireStation(3).addAddress("951 LoneTree Rd"));
        firestations.add(new FireStation(6).addAddress("112 Steppes Pl"));
        firestations.add(new FireStation(8).addAddress("489 Manchester St"));

    }

    public List<FireStation> findAll() {
        return firestations;
    }

    public FireStation findById(int stationNumber) {
        boolean isExist = false;
        for(FireStation fireStation : firestations) {
            if (fireStation.getStationNumber() == stationNumber) {
                return fireStation;
            }
        }
        return null;
    }

    public FireStation findByFirestation(int stationNumber) {
        for(FireStation fireStation : firestations){
            if(fireStation.getStationNumber() == stationNumber){
                return fireStation;
            }
        }
        return null;
    }
    public void save(FireStation newFirestation) {
        boolean isExist = false;
        for (FireStation fireS : firestations){
            if (fireS.getStationNumber() == newFirestation.getStationNumber()) {
                logger.info("Station number already exist. Do an update.");
                isExist = true;
            }
        }
        if(!isExist){
            firestations.add(newFirestation);
            logger.info("Save OK");
        }
    }

    public void update(String address, int stationNumber) {
        int i = 0;
        boolean isExist = false;
        int stationNumberToDelete=0;
        // deletion de l'adresse de la firestation
        for (FireStation fireStation : firestations) {
            if (fireStation.getAddresses().contains(address) && fireStation.getStationNumber() != stationNumber) {
                fireStation.removeAddress(address);
                if (fireStation.getAddresses().isEmpty()) {
                    stationNumberToDelete = fireStation.getStationNumber();
                    isExist = true;
                    break;
                }
            }
        }
        // Suppression de la firestation si sans adresse associée
        if(isExist){
            FireStation firestationASupp = findById(stationNumberToDelete);
            this.delete(firestationASupp);
        }
        ;
        // Ajout de l'adresse sur la nouvelle station number si inconnu, renvoi un msg pour un post au lieu d'un put
        for (FireStation fireStation : firestations){
            if (fireStation.getStationNumber() == stationNumber && i==0) {
                fireStation.addAddress(address);
                logger.info("Valid update");
                i++;
            }
        }
        if (i==0){
            logger.info("Updating failed");
        }

    }



    public void delete(FireStation fireStation) {
        try {
            firestations.remove(fireStation);
        } catch (Exception e) {
            logger.error("delete not valid" + e);
        }
    }


}
