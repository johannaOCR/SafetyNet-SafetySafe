package com.safetynet.safetynetalerts.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.safetynetalerts.Model.MedicalRecord;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Response.ChildFamilyByAddressResponse;
import com.safetynet.safetynetalerts.Response.PersonInfoByFirstnameLastnameResponse;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.util.*;

public class PersonService {
    private final static Logger logger = LogManager.getLogger("PersonService");
    private final ImportData importData = new ImportData();
    private final GsonBuilder builder = new GsonBuilder();
    public static List<Person> personsList = new ArrayList<>();
    static {
        List<String> m1 = new ArrayList<>(); m1.add(""); List<String> a1 = new ArrayList<>(); a1.add("");
        List<String> m2 = new ArrayList<>(); m2.add(""); List<String> a2 = new ArrayList<>(); a2.add("");
        List<String> m3 = new ArrayList<>(); m3.add(""); List<String> a3 = new ArrayList<>(); a3.add("shellfish");
        List<String> m4 = new ArrayList<>(); m4.add(""); List<String> a4 = new ArrayList<>(); a4.add("");

        personsList.add(new Person("Jonanathan","Marrack","841-874-6513","97451",new MedicalRecord("Jonanathan","Marrack",m1,a1,new Date("01/03/1989")),"29 15th St","Culver","drk@email.com"));
        personsList.add(new Person("Tessa","Carman","841-874-6512","97451",new MedicalRecord("Tessa","Carman",m2,a2,new Date("02/18/2012")),"834 Binoc Ave","Culver","tenz@email.com"));
        personsList.add(new Person("Peter","Duncan","841-874-6512","97451",new MedicalRecord("Peter","Duncan",m3,a3,new Date("09/06/2000")),"644 Gershwin Cir","Culver","jaboyd@email.com"));
        personsList.add(new Person("Foster","Shepard","841-874-6544","97451",new MedicalRecord("Foster","Shepard",m4,a4,new Date("01/08/1980")),"748 Townings Dr","Culver","jaboyd@email.com"));
        personsList.add(new Person("Toto","Toto","841-874-6544","97451",null,"748 Toto Dr","Toto","toto@email.com"));
    }

    public PersonService() throws MalformedURLException {
    }

    /*****************************
     *          TOOLS
     * ***************************/

    /**
     * TODO Use a BDD then rewrite this comment :p
     * @return a list of all the Person objects
     */
    public List<Person> findAll() {
        return  importData.loadPerson();
    }

    /**
     * Find all the person living at the given address
     * @param address
     * @return a List of Person object
     */
    public List<Person> findAllByAddress(String address) {
        List<Person> persons = this.findAll();
        List<Person> personsResult = new ArrayList<>();
        for (Person person : persons) {
            if (Objects.equals(address, person.getAddress())) {
                personsResult.add(person);
            }
        }
        return personsResult;
    }

    /**
     * Find all the person living at the given list of addresses
     * @param addresses a list of addresses
     * @return a list of Person Object
     */
    public List<Person> findAllByAddresses(Set<String> addresses) {
        List<Person> personsResult = new ArrayList<>();
        for (String address : addresses) {
            personsResult.addAll(findAllByAddress(address));
        }
        return personsResult;
    }

    /**
     * Find the number of adult in the given list of Person object
     * @param persons a list of person object
     * @return the number of adult
     */
    public int findNumberOfAdult(List<Person> persons) {
        int adulte = 0;
        for (Person person : persons) {
            if (person.isMajeur()) {
                adulte++;
            }
        }
        return adulte;
    }

    /**
     * Find the number of child in the given list of Person object
     * @param persons a list of person object
     * @return the number of child
     */
    public int findNumberOfChild(List<Person> persons) {
        int child = 0;
        for (Person person : persons) {
            if (!person.isMajeur()) {
                child++;
            }
        }
        return child;
    }

    /**
     * Find all the person living in the given city
     * @param city the city name
     * @return a list of Person object
     */
    public List<Person> findAllByCity(String city) {
        List<Person> persons = this.findAll();
        List<Person> personsResult = new ArrayList<>();
        for (Person person : persons) {
            if (city.equals(person.getCity())) {
                personsResult.add(person);
            }
        }
        return personsResult;
    }

    /*****************************
     *      C.R.U.D. Person
     * ***************************/

    public List<Person> findAllPersons(){
        return personsList;
    }

    public void save(Person person) {
        if(!personsList.contains(person)){
            personsList.add(person);
            logger.info("Person save : " + person);
        } else {
            logger.info("Person already exist. Do an update for modification");
        }
    }

    public void update(String firstname, String lastname, String phone, String zip, String address, String city, String email){
        for (Person person : personsList){
            if (Objects.equals(person.getFirstName(), firstname) && Objects.equals(person.getLastName(), lastname)){
                if(!Objects.equals(phone, null)){
                    logger.info("Phone update : " + phone);
                    person.setPhone(phone);
                }
                if (!Objects.equals(zip, null)){
                    logger.info("Zip update : " + zip);
                    person.setZip(zip);
                }
                if (!Objects.equals(address,null)) {
                    logger.info("Address update : " + address);
                    person.setAddress(address);
                }
                if (!Objects.equals(city, null)) {
                    logger.info("City update : " + city);
                    person.setCity(city);
                }
                if (!Objects.equals(email, null)) {
                    logger.info("Email update : " + email);
                    person.setEmail(email);
                }
            }
        }
    }

    public Person findPersonByFirstnameLastname(String firstname, String lastname){
        Person personResult = null;

        for (Person person : personsList){
            if (Objects.equals(person.getFirstName(), firstname) && Objects.equals(person.getLastName(), lastname)){
                personResult = person;
                break;
            }
        }
        if (personResult == null) {
            logger.error(firstname + " " + lastname + " not found");
        }
        return personResult;
    }

    public void delete(String firstname, String lastname) {
        Person personToDelete = null;

        for (Person person : personsList){
            if (Objects.equals(person.getFirstName(), firstname) && Objects.equals(person.getLastName(), lastname)){
                personToDelete = person;
            }
        }
        if (personToDelete != null) {
            logger.info("Delete Person : " + personToDelete);
            personsList.remove(personToDelete);
        } else {
            logger.info("invalid delete");
        }
    }

    /*****************************
     *   C.R.U.D. MedicalRecord
     * ***************************/

    public void saveMedicalRecord(String firstname,String lastname, List<String> medication, List<String> allergie, Date birthdate){
        Person personResult = findPersonByFirstnameLastname(firstname,lastname);
        if (personResult != null) {
            MedicalRecord medicalRecord = new MedicalRecord(firstname, lastname, medication, allergie, birthdate);
            logger.info("MedicalRecord save : " + medicalRecord);
            personResult.setMedicalrecord(medicalRecord);
        } else {
            logger.error("this person not exist");
        }
    }

    public void updateMedicalRecord(String firstname,String lastname, List<String> medication, List<String> allergie, Date birthdate){
        Person personResult = findPersonByFirstnameLastname(firstname,lastname);
        if (personResult != null) {
            if(medication.isEmpty() && allergie.isEmpty() && birthdate==null){
                logger.error("Nothing to update on medical record");
            }
            if (!medication.isEmpty()){
                logger.info("medication update : "+medication);
                personResult.getMedicalrecord().setMedications(medication);
            }
            if (!allergie.isEmpty()){
                logger.info("allergie update : "+allergie);
                personResult.getMedicalrecord().setAllergies(allergie);
            }
            if (birthdate!=null){
                logger.info("birthdate update : "+birthdate);
                personResult.getMedicalrecord().setBirthdate(birthdate);
            }
        } else {
            logger.error("this person not exist");
        }
    }

    public void deleteMedicalRecord(String firstname, String lastname){
        Person personResult = findPersonByFirstnameLastname(firstname,lastname);
        if (personResult != null) {
            for (Person person : personsList) {
                if (person == personResult) {
                    logger.info("Medical record delete : " + person.getMedicalrecord());
                    person.setMedicalrecord(null);

                }
            }
        }
    }

    /*****************************
     *          SERVICES
     * ***************************/

    /**
     * childAlert?address=<address>
     * Build a json formatted string of a list a child living at the given address
     * @param address : the address to look at
     * @return Return a json formatted string of a list a child living at the given address
     */
    public String childAlertByAddress(String address) {
        Gson gson = builder.create();
        List<Person> persons = this.findAll();
        ArrayList<ChildFamilyByAddressResponse> result = new ArrayList<>();
        List<Person> family = new ArrayList<>();
        // Pour chaque person de la liste
        for (Person person : persons) {
            // Si la personne a la même adresse et est mineure
            if (address.equals(person.getAddress())) {
                family.add(person);
            }
        }
        for (Person member : family) {
            if (member.isEighteenOrLess()) {
                List<Person> fam = new ArrayList<>();
                for (Person famillyMember : family) {
                    if (famillyMember != member) {
                        fam.add(famillyMember);
                    }
                }
                ChildFamilyByAddressResponse child = new ChildFamilyByAddressResponse(
                        member.getLastName(),
                        member.getFirstName(),
                        member.getAge(member.medicalrecord.getBirthdate()),
                        fam
                );
                result.add(child);
            }
        }
        return gson.toJson(result);
    }

    /**
     * personInfo?firstName=<firstName>&lastName=<lastName>
     * Build a json formatted string with the name, age, mail and medical information of the person matching the given
     * firstname/lastname
     * @param firstname the person firstame
     * @param lastname the person lastname
     * @return a json formatted string with the name, age, mail and medical information
     */
    public String personInfoByFirstNameLastName(String firstname, String lastname) {
        List<Person> persons = this.findAll();
        List<PersonInfoByFirstnameLastnameResponse> person = new ArrayList<>();
        Gson gson = builder.create();

        for (Person member : persons) {
            if (member.getLastName().equals(lastname) && member.getFirstName().equals(firstname)) {
                PersonInfoByFirstnameLastnameResponse personInfo = new PersonInfoByFirstnameLastnameResponse(
                    member.getLastName(),
                    member.getAddress(),
                    member.getAge(member.medicalrecord.getBirthdate()),
                    member.getEmail(),
                    member.medicalrecord.getMedications(),
                    member.medicalrecord.getAllergies()
                );
                person.add(personInfo);
            }
        }
        return gson.toJson(person);
    }

    /**
     * communityEmail?city=<city>
     * Build a json formatted string of a list of email for all the person who live in the given city
     * @param city the city name
     * @return a list of email as a json formatted string
     */
    public String communityEmailByCity(String city) {
        Gson gson = builder.create();
        List<String> emails = new ArrayList<>();
        List<Person> persons = this.findAll();
        for (Person person : persons) {
            if (person.getCity().equals(city)) {
                emails.add(person.getEmail());
            }
        }
        return gson.toJson(emails);
    }

}