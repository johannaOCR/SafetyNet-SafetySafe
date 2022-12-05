package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
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
        List<Person> persons;
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
        List<Person> persons;
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
    public void testChildAlertByAddress(){
        logger.info(personService.childAlertByAddress("1509 Culver St"));
    }

    @Test
    public void testChildAlertByAddressWithoutChild(){
        logger.info(personService.childAlertByAddress("29 15th St"));
    }

    @Test
    public void testPersonInfoByFirstNameLastName() {
        logger.info(personService.personInfoByFirstNameLastName("Brian","Stelzer"));
    }

    @Test
    public void testCommunityEmailByCity() {
        logger.info(personService.communityEmailByCity("Culver"));
    }
/** TODO : faire le test
    @Test
    public void infoEmailByCityFalseTest() {
        Assert.as(personService.infoEmailByCity("NameOfCityHowNotExist")=[]);
    }
**/
}
