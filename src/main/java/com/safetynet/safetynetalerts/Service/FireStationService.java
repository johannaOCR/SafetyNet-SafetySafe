package com.safetynet.safetynetalerts.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Response.PersonByFirestationPersonInfoResponse;
import com.safetynet.safetynetalerts.Response.PersonByFirestationResponse;
import com.safetynet.safetynetalerts.Util.Filter;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public  class FireStationService {

    private final static Logger logger = LogManager.getLogger("FirestationService");
    private List<Person> personsList = new ArrayList<>();
    private ImportData importData = new ImportData();
    private PersonService personService = new PersonService();
    private Filter filter = new Filter();
    private GsonBuilder builder = new GsonBuilder();

    public FireStationService() throws MalformedURLException {}

    /*****************************
     *          TOOLS
     * ***************************/

    /**
     *
     * @return
     * @throws IOException
     */
    public List<FireStation> findAll() throws IOException {
        List<FireStation> listFirestations = importData.loadFirestation();
        return listFirestations;
    }

    /**
     *
     * @param stationNumber
     * @return
     * @throws IOException
     */
    public FireStation findByStationNumber(int stationNumber) throws IOException {
        List<FireStation> fireStationList = this.findAll();
        FireStation fireStationResult = null;

        for (FireStation fireStation : fireStationList) {
            if (fireStation.getStationNumber() == stationNumber){
                fireStationResult = fireStation;
            }
        }
        if(fireStationResult == null){
            logger.error("Pas de FireStation trouvée avec cet index");
        }
        return fireStationResult;
    }

    /**
     * Retourne l'age pour une date donnée
     * @param birthDate
     * @return
     */
    public static int getAge(Date birthDate) {
        LocalDate birthdate = convertToLocalDateViaMilisecond(birthDate);
        Date dateNow = new Date(System.currentTimeMillis());
        LocalDate localDateNow = convertToLocalDateViaMilisecond(dateNow);
        if ((birthDate != null) && (dateNow != null)) {
            return Period.between(birthdate, localDateNow).getYears();
        } else {
            return 0;
        }
    }
    public static LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /*****************************
     *          SERVICES
     * ***************************/



    /**
     * firestation?stationNumber=<station_number>
     @return Json d'une liste de personnes triées en fonction de l'adresse de la firestation
      * @param numberfirestation
     */

    public String personByFirestation(int numberfirestation) throws IOException {
        Gson gson = builder.create();

        // Récupération de la firestation
        FireStation firestation = this.findByStationNumber(numberfirestation);
        List<Person> persons;
        // Recupération des personnes ayant la même adresse que celle de la firestation
        persons = personService.findAllByAddresses(firestation.getAddresses());
        int adulte = personService.findNumberOfAdult(persons);
        int child = personService.findNumberOfChild(persons);
        List<PersonByFirestationPersonInfoResponse> personsResultList = new ArrayList<>();

        for (Person person : persons){
            PersonByFirestationPersonInfoResponse personDetail =
                new PersonByFirestationPersonInfoResponse(person.getFirstName(),person.getLastName(),person.getAddress(), person.getPhone());
            personsResultList.add(personDetail);
        }
        PersonByFirestationResponse result =
            new PersonByFirestationResponse(adulte,child,personsResultList);
        return(gson.toJson(result));
    }

    /**
     * Fonction permettant l'extraction d'une liste de personne et du numéro de la station desservi par une adresse donnée.
     * @param address
     * @return
     */
    public String personsAndStationNumberByAddress(String address) throws IOException {
        Map<String, String> result = new HashMap<>();
        List<Person> persons = new ArrayList<>();

        int stationNumber;
        persons = personService.findAllByAddress(address);
        stationNumber = findNumberStationByAdress(address);
        List<JSONObject> personsResultList = new ArrayList<>();
        for (Person person : persons){
            Map<String, String> personDetail = new HashMap<>();
            personDetail.put("lastname", person.getLastName());
            personDetail.put("phone", person.getPhone());
            personDetail.put("age",String.valueOf(getAge(person.medicalrecord.getBirthdate())));
            personDetail.put("medications",person.medicalrecord.getMedications().toString());
            personDetail.put("allergies",person.medicalrecord.getAllergies().toString());
            personsResultList.add(new JSONObject(personDetail));
        }
        if (!personsResultList.isEmpty() & stationNumber != 0){
            result.put("persons",personsResultList.toString());
            result.put("stationNumber",String.valueOf(stationNumber));
            return(new JSONObject(result).toString().replace("\\", ""));
        } else {
            return null;
        }
    }
    public HashMap<String, List<JSONObject>> personsAndInformationsByStationNumberBrut(int stationNumber) throws IOException, JSONException {
        // On recupère toutes les personnes du fichier
        List<Person> persons = personService.findAll();
        //On recupère la firestation qui porte le numéro donné
        FireStation fireStation = this.findByStationNumber(stationNumber);

        List<Person> personsResult= new ArrayList<>();
        // On recupère les personnes portant les adresses desservie par la caserne
        for (Person person : persons) {
            if (fireStation.getAddresses().contains(person.getAddress())){
                personsResult.add(person);
            }
        }
        // On tri les personnes par adresse et on ajoute les infos nécessaires
        HashMap<String, List<JSONObject>> personsByAddress = new HashMap<>();
        List<JSONObject> list = new ArrayList<>();
        // pour toutes les personnes du résultat
        for (Person person : personsResult){
            // si la personne n'a pas encore son adresse dans le fichier final
            if(!personsByAddress.containsKey(person.getAddress())){
                // alors pour chaque personne des personnes du résultat
                list.clear();
                for (Person personR : personsResult) {
                    // si l'adresse est égale à celle de la personne retenue
                    if (Objects.equals(personR.getAddress(), person.getAddress())) {
                        // on créer une MAP avec les infos que l'on souhaite
                        Map<String, String> personDetail = new HashMap<>();
                        personDetail.put("lastname", personR.getLastName());
                        personDetail.put("phone", personR.getPhone());
                        personDetail.put("address", personR.getAddress()); /// a supprimer
                        personDetail.put("age", String.valueOf(getAge(personR.medicalrecord.getBirthdate())));
                        personDetail.put("medications", personR.medicalrecord.getMedications().toString());
                        personDetail.put("allergies", personR.medicalrecord.getAllergies().toString());
                        // On ajoute ce résultat au format JSON
                        list.add(new JSONObject(personDetail));
                    }
                }
                logger.info(list);
                personsByAddress.put(person.getAddress(),list);

            }
        }
        return personsByAddress;
    }

    public String personsAndInformationsByStationNumber(int stationNumber) throws IOException, JSONException {
        return new JSONObject(this.personsAndInformationsByStationNumberBrut(stationNumber)).toString();
    }

    /**
     * Retourne le numéro de caserne de pompier pour une adresse donnée
     * @param  address
     * @return
     * @throws IOException
     */
    public int findNumberStationByAdress(String address) throws IOException {
        int stationNumber = 0;
        List<FireStation> fireStations = this.findAll();
        for (FireStation firestation : fireStations){
            if(firestation.getAddresses().contains(address)){
                stationNumber = firestation.getStationNumber();
            }
        }
        return stationNumber;
    }

    public String findPhoneByStationNumber(int stationNumber) throws IOException {
        Gson gson = builder.create();
        FireStation fireStations = this.findByStationNumber(stationNumber);
        List<String> phoneList = new ArrayList<>();
        for (String address : fireStations.getAddresses()){
            List<Person> persons = personService.findAllByAddress(address);
            for (Person person : persons) {
                phoneList.add(person.getPhone());
            }
        }

        return gson.toJson(phoneList);
    }

}


