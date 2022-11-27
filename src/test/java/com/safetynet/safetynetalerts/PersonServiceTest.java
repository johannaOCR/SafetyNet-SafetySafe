package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.*;

public class PersonServiceTest {
    PersonService personService = new PersonService();
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("PersonServiceTest") ;
    public PersonServiceTest() throws MalformedURLException {
    }

    @Test
    public void testFindAll() {
        List<Person> persons = new ArrayList<>();
        persons = personService.findAll();
        logger.info(persons);
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }

    @Test
    public void testfindNumberOfAdult(){
        logger.info(personService.findNumberOfAdult(personService.findAll()));
    }

    @Test
    public void testFindNumberOfChild(){
        logger.info(personService.findNumberOfChild(personService.findAll()));
    }

    @Test
    public void testfindAllByAdresses(){
        Set<String> adresses = new HashSet<>();
        List<Person> persons = personService.findAll();
        for (Person person : persons){
            adresses.add(person.getAddress());
        }
        List<Person> personsResult = personService.findAllByAddresses(adresses);
        logger.info(personsResult);
        Assert.assertNotNull(personsResult);
        Assert.assertFalse(personsResult.isEmpty());
    }
    @Test
    public void testFindAllByAddresses(){
        List<Person> persons= new ArrayList<>();
        Set<String> addresses= new HashSet<>();
        persons = personService.findAll();
        for(Person person : persons) {
            if(person.getAddress().contains("a")){
                addresses.add(person.getAddress());
            }
        }
        List<Person> personsResult = personService.findAllByAddresses(addresses);
        logger.info(personsResult);
        Assert.assertNotNull(personsResult);
        Assert.assertFalse(personsResult.isEmpty());

    }

    @Test
    public void testFindAllByAddress(){
        logger.info(personService.findAllByAddress("1509 Culver St"));
        Assert.assertNotNull(personService.findAllByAddress("1509 Culver St"));
        Assert.assertFalse(personService.findAllByAddress("1509 Culver St").isEmpty());
    }

    @Test
    public void testFindAllByCity(){
        logger.info(personService.findAllByCity("Culver").size());
        logger.info(personService.findAllByCity("Culver"));
        Assert.assertNotNull(personService.findAllByCity("Culver"));
        Assert.assertFalse(personService.findAllByCity("Culver").isEmpty());
    }

    @Test
    public void testFindEmailByCity() throws JSONException {
        logger.info(personService.findMailByCity("Culver"));
        logger.info(personService.findMailByCity("Culver"));
        Assert.assertNotNull(personService.findMailByCity("Culver"));
        Assert.assertFalse(personService.findMailByCity("Culver").isEmpty());
    }
    @Test
    public void testFindEmailByCityFalse() throws JSONException {
        Assert.assertNull(personService.findMailByCity("NameOfCityHowNotExist"));
    }

    @Test
    public void TestfindAllChildByAddress(){
        logger.info(personService.childFamilyByAddress("1509 Culver St"));
    }

    @Test
    public void findPersByFirstnameLastnameTest() throws JSONException {
        logger.info(personService.findPersByFirstnameLastname("Brian","Stelzer"));
    }

}
