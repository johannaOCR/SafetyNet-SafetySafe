package com.safetynet.safetynetalerts.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private ImportData importData = new ImportData();
    private GsonBuilder builder = new GsonBuilder();

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
        List<Person> listPersons = importData.loadPerson();
        return listPersons;
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


