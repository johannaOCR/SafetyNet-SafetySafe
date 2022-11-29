package com.safetynet.safetynetalerts.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Response.*;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class FireStationService {
    private final static Logger logger = LogManager.getLogger("FirestationService");
    private ImportData importData = new ImportData();
    private PersonService personService = new PersonService();
    private GsonBuilder builder = new GsonBuilder();

    public FireStationService() throws MalformedURLException {
    }

    /*****************************
     *          TOOLS
     * ***************************/

    /**
     * TODO Use a BDD then rewrite this comment
     * @return a list of Firestation object
     */
    public List<FireStation> findAll() {
        List<FireStation> listFirestations = new ArrayList<>();
        try {
            listFirestations.addAll(importData.loadFirestation());
        } catch (IOException e) {
            logger.error(e);
        }
        return listFirestations;
    }

    /**
     * Find a firstation based on the given station number
     * @param stationNumber the station number
     * @return a Firestation Object
     */
    public FireStation findByStationNumber(int stationNumber) {
        List<FireStation> fireStationList = this.findAll();
        FireStation fireStationResult = null;

        for (FireStation fireStation : fireStationList) {
            if (fireStation.getStationNumber() == stationNumber) {
                fireStationResult = fireStation;
            }
        }
        if (fireStationResult == null) {
            logger.error("Pas de FireStation trouvée avec cet index");
        }
        return fireStationResult;
    }

    /*****************************
     *          SERVICES
     * ***************************/

    /**
     * firestation?stationNumber=<station_number>
     * Build a json formatted string of all the person covered by a firestation with the given number
     * @param numberfirestation the firestation number
     * @return A json formatted string of all the person, and the number of child and adult
     */
    public String personByFirestation(int numberfirestation) {
        Gson gson = builder.create();
        FireStation firestation = this.findByStationNumber(numberfirestation);
        List<Person> persons;
        persons = personService.findAllByAddresses(firestation.getAddresses());
        int adulte = personService.findNumberOfAdult(persons);
        int child = personService.findNumberOfChild(persons);
        List<PersonByFirestationPersonInfoResponse> personsResultList = new ArrayList<>();

        for (Person person : persons) {
            PersonByFirestationPersonInfoResponse personDetail = new PersonByFirestationPersonInfoResponse(
                person.getFirstName(),
                person.getLastName(),
                person.getAddress(),
                person.getPhone()
            );
            personsResultList.add(personDetail);
        }
        PersonByFirestationResponse result = new PersonByFirestationResponse(
            adulte,
            child,
            personsResultList
        );
        return (gson.toJson(result));
    }

    /**
     * Find all the phone numbers of the persons covered by the firestation based on the given station number
     *
     * @param stationNumber the station number
     * @return a json formatted string of all the phone numbers
     */
    public String phoneAlertByStationNumber(int stationNumber) {
        Gson gson = builder.create();
        FireStation fireStations = this.findByStationNumber(stationNumber);
        List<String> phoneList = new ArrayList<>();
        for (String address : fireStations.getAddresses()) {
            List<Person> persons = personService.findAllByAddress(address);
            for (Person person : persons) {
                phoneList.add(person.getPhone());
            }
        }
        return gson.toJson(phoneList);
    }

    /**
     * Build a json formatted string of all the persons covered by the Firestation based on the given
     * list of station number
     * @param firestationNumbers a list of station number
     * @return a json formatted string of all the persons covered by the Firestation
     */
    public String floodByStationsNumbers(List<Integer> firestationNumbers){
        Gson gson = builder.create();
        List<ListPersonByStationNumberByAddressResponse> response = new ArrayList<>();
        for (int number : firestationNumbers) {
            response.add(new ListPersonByStationNumberByAddressResponse(number, this.findPersonByStationNumber(number)));
        }
        return gson.toJson(response);
    }

    /**
     * Build a list of all the persons covered by the Firestation based on the given station number
     * @param stationNumber a station number
     * @return a list of all the persons covered by the Firestation as a PersonByAdressByFirestationResponse Object
     */
    public List<PersonByAdressByFirestationResponse> findPersonByStationNumber(int stationNumber) {
        FireStation firestation = this.findByStationNumber(stationNumber);
        List<Person> persons = personService.findAll();
        List<PersonByAdressByFirestationResponse> response = new ArrayList<>();
        for (String address : firestation.getAddresses()) {
            List<PersonInfoByaddressByStationNumber> personAtAddress = new ArrayList<>();
            for (Person person : persons) {
                if (person.getAddress().equals(address)) {
                    PersonInfoByaddressByStationNumber personInfoByFirstnameLastnameResponse =
                            new PersonInfoByaddressByStationNumber(
                                person.getLastName(),
                                person.getAge(person.medicalrecord.getBirthdate()),
                                person.getPhone(),
                                person.medicalrecord.getMedications(),
                                person.medicalrecord.getAllergies()
                            );
                    personAtAddress.add(personInfoByFirstnameLastnameResponse);
                }
            }
            if (!persons.isEmpty()) {
                PersonByAdressByFirestationResponse personByAdressByFirestationResponse =
                        new PersonByAdressByFirestationResponse(
                            address,
                            personAtAddress
                        );
                response.add(personByAdressByFirestationResponse);
            }
        }
        return response;
    }
}


