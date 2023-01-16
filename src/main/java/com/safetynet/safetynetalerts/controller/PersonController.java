package com.safetynet.safetynetalerts.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import com.safetynet.safetynetalerts.util.BuilderResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {
    private final static Logger logger = LogManager.getLogger("PersonController");
    private final PersonService personService = new PersonService();
    private final BuilderResponse builderResponse = new BuilderResponse();

    public PersonController() throws MalformedURLException {
    }

    /*****************************
     *     C.R.U.D. Person
     *
     * ***************************/

    @PostMapping("/person")
    public ResponseEntity<?> postPerson(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "zip") String zip,
            @RequestParam(name = "address") String address,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "email") String email) {
        Person person = new Person(firstName, lastName, phone, zip, null, address, city, email);
        return builderResponse.responseBoolean(personService.save(person));
    }

    @PutMapping("/person")
    public ResponseEntity<?> putPerson(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "zip", required = false) String zip,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "email", required = false) String email) {
        return builderResponse.responseBoolean(personService.update(firstName, lastName, phone, zip, address, city, email));
    }

    @DeleteMapping("/person")
    public ResponseEntity<?> deletePerson(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName
    ) {
        return builderResponse.responseBoolean(personService.delete(firstName, lastName));
    }

    /*****************************
     *   C.R.U.D. MedicalRecord
     *
     * ***************************/

    @PostMapping("/medicalRecord")
    public ResponseEntity<?> postMedicalRecord(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestParam(name = "medications", required = false) List<String> medications,
            @RequestParam(name = "allergies", required = false) List<String> allergies,
            @RequestParam(name = "birthdate", required = false) Date birthdate) {
        return builderResponse.responseBoolean(personService.saveMedicalRecord(firstName, lastName, medications, allergies, birthdate));
    }

    @PutMapping("/medicalRecord")
    public ResponseEntity<?> putMedicalRecord(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName,
            @RequestParam(name = "medications", required = false) List<String> medications,
            @RequestParam(name = "allergies", required = false) List<String> allergies,
            @RequestParam(name = "birthdate", required = false) Date birthdate) {
        return builderResponse.responseBoolean(personService.updateMedicalRecord(firstName, lastName, medications, allergies, birthdate));
    }

    @DeleteMapping("/medicalRecord")
    public ResponseEntity<?> deleteMedicalRecord(
            @RequestParam(name = "firstname") String firstName,
            @RequestParam(name = "lastname") String lastName
    ) {
        return builderResponse.responseBoolean(personService.deleteMedicalRecord(firstName, lastName));
    }

    /*****************************
     *          SERVICES
     * ***************************/

    @GetMapping("/childAlert")
    public ResponseEntity<String> getChildAndFamilyByAddress(@RequestParam(name = "address") String address) {
        logger.info("Get /childAlert?address=" + address);
        if (!personService.childAlertByAddress(address).isEmpty()) {
            return new ResponseEntity<>(personService.childAlertByAddress(address), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/personInfo")
    public ResponseEntity<String> getPersonByFirstnameLastname(@RequestParam(name = "lastname") String lastname, @RequestParam(name = "firstname") String firstname) {
        logger.info("Get /personInfo?firstName=" + firstname + "&lastName=" + lastname);
        if (!personService.personInfoByFirstNameLastName(firstname, lastname).isEmpty()) {
            return new ResponseEntity<>(personService.personInfoByFirstNameLastName(firstname, lastname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/communityEmail")
    public ResponseEntity<String> getEmailByCity(@RequestParam(name = "city") String city) {
        logger.info("Get communityEmail?city=" + city);
        if (!personService.communityEmailByCity(city).isEmpty()) {
            return new ResponseEntity<>(personService.communityEmailByCity(city), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
