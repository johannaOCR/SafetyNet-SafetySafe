package com.safetynet.safetynetalerts.Service;
import com.jsoniter.output.JsonStream;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public  class FireStationService {

    private final static Logger logger = LogManager.getLogger("PersonService");
    private List<Person> personsList = new ArrayList<>();
    private static List<FireStation> listFirestations = new ArrayList<>();
    private ImportData importData;

    public FireStationService() {}

    int adulte = 0;
    int child = 0;
    private class Result {
        int adultes;
        int mineurs;
        List<Person> persons;
    }


    public List<FireStation> findAll() throws IOException {
        listFirestations = importData.loadFirestation();
        return listFirestations;
    }

    public FireStation findById(int id) throws IOException {
        List<FireStation> fireStationList = this.findAll();
        FireStation fireStationResult = null;

        for (FireStation fireStation : fireStationList) {
            if (fireStation.getStationNumber() == id){
                fireStationResult = fireStation;
                break;
            } else {
                fireStationResult = null;
            }
        }
        return fireStationResult;
    }



    /**
     * firestation?stationNumber=<station_number>
     @return Json d'une liste de personnes triées en fonction de l'adresse de la firestation
      * @param numberfirestation
     */

    public String personByFirestation(int numberfirestation) throws IOException {

        // Récupération de la firestation
        FireStation firestation = this.findById(numberfirestation);

        // Recupération des personnes ayant la même adresse que celle de la firestation
        List<Person> personFirestation = personsList.stream()
                .filter(persons -> firestation.getAddresses().contains(persons.getAddress()))
                .collect(Collectors.toList());

        // Recherche si majeur ou non et ajout de la mention
        if (personFirestation.size() != 0) {
            for (Person pers : personFirestation) {
                if (pers.isMajeur() ==null) {
                    logger.info(pers.getFirstName()+ " "+ pers.getLastName() + "ne possède pas de date de naissance");
                } else if (pers.isMajeur()) {
                    adulte++;
                }else{
                    child++;
                }
            }

            // Retourne les résutats au format Json
            Result result = null;
            result.adultes = adulte;
            result.mineurs = child;
            result.persons = personsList;
            return(JsonStream.serialize(result));
        } else {
            return null;
        }
    }

}
