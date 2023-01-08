package com.safetynet.safetynetalerts.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Response.*;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class FireStationService {
    private final static Logger logger = LogManager.getLogger("FirestationService");
    private final ImportData importData = new ImportData();
    private final PersonService personService = new PersonService();
    private final GsonBuilder builder = new GsonBuilder();
    public static List<FireStation> firestations = new ArrayList<>();
    static {
        firestations.add(new FireStation(1).addAddress("29 15th St"));
        firestations.add(new FireStation(2).addAddress("834 Binoc Ave"));
        firestations.add(new FireStation(1).addAddress("1509 Culver St"));
        firestations.add(new FireStation(3).addAddress("951 LoneTree Rd"));
        firestations.add(new FireStation(6).addAddress("112 Steppes Pl"));
        firestations.add(new FireStation(8).addAddress("489 Manchester St"));
    }

    public FireStationService() throws MalformedURLException {
    }

    /*****************************
     *          TOOLS
     * ***************************/

    /**
     * Find all firestations
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

    /**
     * Find the Station number from the given address
     * @param address
     * @return
     */
    public int findStationNumberByAddress(String address){
        List<FireStation> fireStationList = this.findAll();
        for (FireStation firestation : fireStationList){
            if (firestation.getAddresses().contains(address)){
                return firestation.getStationNumber();
            }
        }
        return -1;
    }

    /*****************************
     *    C.R.U.D. Firestation
     * ***************************/

    public List<FireStation> findAllFirestation() {
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
            logger.info("Firestation save : " + newFirestation);
            firestations.add(newFirestation);

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
                logger.info("Firestation update : " +fireStation);
                i++;
            }
        }
        if (i==0){
            logger.info("Updating failed");
        }

    }
/*
    Suppression d'une adresse sur une firestation
    Une firestation peut avoir plusieurs adresses
 */
    public void delete(FireStation fireStationToDelete) {
        Iterator<FireStation> iteratorFirestation = firestations.iterator();
        while (iteratorFirestation.hasNext()){
            FireStation fireStation = iteratorFirestation.next();
            if(fireStation.getStationNumber() == fireStationToDelete.getStationNumber()){
                Iterator<String> iteratorAddress = fireStation.getAddresses().iterator();
                while (iteratorAddress.hasNext()){
                    String address = iteratorAddress.next();
                    for (String addressToDelete : fireStationToDelete.getAddresses()){
                        if(Objects.equals(addressToDelete, address)){
                            iteratorAddress.remove();
                            logger.info("Address delete : " + address);
                            if(fireStation.getAddresses().size() == 0){
                                iteratorFirestation.remove();
                                logger.info("Firestation delete : " +fireStation);
                            }
                        }
                    }
                }
            }
        }
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
     * phoneAlert?firestation=<firestation_number>
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
     * /fire?address=<address>
     * Build a json formatted string of persons living at given address and the corresponding station number
     * @param address the address to look at
     * @return a json formatted string of persons living at given address and the corresponding station number
     */
    public String fireByAddress(String address) {
        Gson gson = builder.create();
        int stationNumber = findStationNumberByAddress(address);
        List<Person> persons = personService.findAllByAddress(address);
        List<PersonInfoByAddressByStationNumber> personInfo = new ArrayList<>();
        for (Person member : persons) {
            if (member.getAddress().equals(address)) {
                PersonInfoByAddressByStationNumber info = new PersonInfoByAddressByStationNumber(
                        member.getLastName(),
                        member.getAge(member.medicalrecord.getBirthdate()),
                        member.getPhone(),
                        member.medicalrecord.getMedications(),
                        member.medicalrecord.getAllergies()
                );
                personInfo.add(info);
            }
        }
        PersonByAddressByFirestationResponse result = new PersonByAddressByFirestationResponse(
                address,
                personInfo
        );
        ListPersonByStationNumberByAddressResponse response =  new ListPersonByStationNumberByAddressResponse(
                stationNumber,
                result
        );
        return gson.toJson(response);
    }

    /**
     * flood/stations?stations=<a list of station_numbers>
     * Build a json formatted string of all the persons covered by the Firestation based on the given
     * list of station number
     * @param firestationNumbers a list of station number
     * @return a json formatted string of all the persons covered by the Firestation
     */
    public String floodByStationsNumbers(List<Integer> firestationNumbers){
        Gson gson = builder.create();
        List<ListPersonByStationNumberByListAddressesResponse> response = new ArrayList<>();
        for (int number : firestationNumbers) {
            response.add(new ListPersonByStationNumberByListAddressesResponse(number, this.findPersonByStationNumber(number)));
        }
        return gson.toJson(response);
    }

    /**
     * Build a list of all the persons covered by the Firestation based on the given station number
     * @param stationNumber a station number
     * @return a list of all the persons covered by the Firestation as a PersonByAddressByFirestationResponse Object
     */
    public List<PersonByAddressByFirestationResponse> findPersonByStationNumber(int stationNumber) {
        FireStation firestation = this.findByStationNumber(stationNumber);
        List<Person> persons = personService.findAll();
        List<PersonByAddressByFirestationResponse> response = new ArrayList<>();
        for (String address : firestation.getAddresses()) {
            List<PersonInfoByAddressByStationNumber> personAtAddress = new ArrayList<>();
            for (Person person : persons) {
                if (person.getAddress().equals(address)) {
                    PersonInfoByAddressByStationNumber personInfoByFirstnameLastnameResponse =
                            new PersonInfoByAddressByStationNumber(
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
                PersonByAddressByFirestationResponse personByAddressByFirestationResponse =
                        new PersonByAddressByFirestationResponse(
                            address,
                            personAtAddress
                        );
                response.add(personByAddressByFirestationResponse);
            }
        }
        return response;
    }
}


