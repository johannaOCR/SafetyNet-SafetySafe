package com.safetynet.safetynetalerts.Controller;
import com.safetynet.safetynetalerts.Model.MedicalRecord;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PersonController {
    private PersonService personService = new PersonService();
    private final static Logger logger = LogManager.getLogger("PersonController") ;
    private List<String> listNullDefault = new ArrayList<String>();

    public PersonController() throws MalformedURLException {
    }


    /*****************************
     *     C.R.U.D. Person
     * ***************************/

    @PostMapping("/person")
    public void postPerson(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName,
            @RequestParam(name="phone") String phone,
            @RequestParam(name="zip") String zip,
            @RequestParam(name="address") String address,
            @RequestParam(name="city") String city,
            @RequestParam(name="email") String email) {
        Person person = new Person(firstName,lastName,phone,zip,null,address,city,email);
        personService.save(person);
    }

    @PutMapping("/person")
    public void putPerson(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName,
            @RequestParam(name="phone", required = false) String phone,
            @RequestParam(name="zip",required = false) String zip,
            @RequestParam(name="address",required = false) String address,
            @RequestParam(name="city",required = false) String city,
            @RequestParam(name="email",required = false) String email) {

        personService.update(firstName,lastName,phone,zip,address,city,email);
    }

    @DeleteMapping("/person")
    public void deletePerson(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName
    )
    {
        personService.delete(firstName,lastName);
    }

    /*****************************
     *   C.R.U.D. MedicalRecord
     * ***************************/

    @PostMapping("/medicalRecord")
    public void postMedicalRecord(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName,
            @RequestParam(name="medications", required = false) List<String> medications,
            @RequestParam(name="allergies",required = false) List<String> allergies,
            @RequestParam(name="birthdate",required = false) Date birthdate)
    {
        personService.saveMedicalRecord(firstName,lastName,medications,allergies,birthdate);
    }

    @PutMapping("/medicalRecord")
    public void putMedicalRecord(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName,
            @RequestParam(name="medications", required = false) List<String> medications,
            @RequestParam(name="allergies",required = false) List<String> allergies,
            @RequestParam(name="birthdate",required = false) Date birthdate)
     {
        personService.updateMedicalRecord(firstName,lastName,medications,allergies,birthdate);
    }

    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(
            @RequestParam(name="firstname") String firstName,
            @RequestParam(name="lastname") String lastName
    )
    {
        personService.deleteMedicalRecord(firstName,lastName);
    }

    /*****************************
     *          SERVICES
     * ***************************/

    @GetMapping("/childAlert")
    public String getChildAndFamilyByAddress(@RequestParam(name="address", required = true) String address) {
        logger.info("Get /childAlert?address="+address);
        return personService.childAlertByAddress(address);
    }

    @GetMapping("/personInfo")
    public String getPersonByFirstnameLastname(@RequestParam(name="lastname", required = true) String lastname, @RequestParam(name="firstname", required = true) String firstname) {
        logger.info("Get /personInfo?firstName="+firstname+"&lastName="+lastname);
        return personService.personInfoByFirstNameLastName(firstname,lastname);
    }

    @GetMapping("/communityEmail")
    public String getEmailByCity(@RequestParam(name="city", required = true) String city) {
        logger.info("Get communityEmail?city="+city);
        return personService.communityEmailByCity(city);
    }


}
