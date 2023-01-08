package com.safetynet.safetynetalerts.servicesTest;

import com.safetynet.safetynetalerts.Model.MedicalRecord;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.util.*;

@SpringBootTest
public class PersonServiceTest {
    PersonService personService = new PersonService();
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("PersonServiceTest") ;

    public PersonServiceTest() throws MalformedURLException {
    }


    @Test
    public void testFindAll() {
        logger.info("testfindAll()");

        List<Person> persons;
        persons = personService.findAll();

        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }

    @Test
    public void testfindNumberOfAdult(){
        logger.info("testfindNumberOfAdult()");
        Assert.assertNotEquals(0,personService.findNumberOfAdult(personService.findAll()));
    }

    @Test
    public void testFindNumberOfChild(){
        logger.info("testFindNumberOfChild()");
        Assert.assertNotEquals(0,personService.findNumberOfChild(personService.findAll()));
    }

    @Test
    public void testfindAllByAdresses(){
        logger.info("testfindAllByAdresses()");

        Set<String> adresses = new HashSet<>();
        List<Person> persons = personService.findAll();
        for (Person person : persons){
            adresses.add(person.getAddress());
        }
        List<Person> personsResult = personService.findAllByAddresses(adresses);

        Assert.assertNotNull(personsResult);
        Assert.assertFalse(personsResult.isEmpty());
    }
    @Test
    public void testFindAllByAddresses(){
        logger.info("testFindAllByAddresses()");

        List<Person> persons;
        Set<String> addresses= new HashSet<>();
        persons = personService.findAll();
        for(Person person : persons) {
            if(person.getAddress().contains("a")){
                addresses.add(person.getAddress());
            }
        }
        List<Person> personsResult = personService.findAllByAddresses(addresses);

        Assert.assertNotNull(personsResult);
        Assert.assertFalse(personsResult.isEmpty());
    }

    @Test
    public void testFindAllByAddress(){
        logger.info("testFindAllByAddress()");

        Assert.assertNotNull(personService.findAllByAddress("1509 Culver St"));
        Assert.assertFalse(personService.findAllByAddress("1509 Culver St").isEmpty());
    }

    @Test
    public void testFindAllByCity(){
        logger.info("testFindAllByCity()");

        Assert.assertNotNull(personService.findAllByCity("Culver"));
        Assert.assertFalse(personService.findAllByCity("Culver").isEmpty());
    }

    @Test
    public void testChildAlertByAddress(){
        logger.info("testChildAlertByAddress()");

        Assert.assertNotNull(personService.childAlertByAddress("1509 Culver St"));
        Assert.assertFalse(personService.childAlertByAddress("1509 Culver St").isEmpty());
    }

    @Test
    public void testChildAlertByAddressWithoutChild(){
        logger.info("testChildAlertByAddressWithoutChild()");

        Assert.assertNotNull(personService.childAlertByAddress("29 15th St"));
        Assert.assertFalse(personService.childAlertByAddress("29 15th St").isEmpty());
    }

    @Test
    public void testPersonInfoByFirstNameLastName() {
        logger.info("testPersonInfoByFirstNameLastName()");

        Assert.assertNotNull(personService.personInfoByFirstNameLastName("Brian","Stelzer"));
        Assert.assertFalse(personService.personInfoByFirstNameLastName("Brian","Stelzer").isEmpty());
    }

    @Test
    // TODO: 06/01/2023
    public void testCommunityEmailByCity() {
        logger.info(personService.communityEmailByCity("Culver"));

        logger.info("testFindAllByCity()");

        Assert.assertNotNull(personService.findAllByCity("Culver"));
        Assert.assertFalse(personService.findAllByCity("Culver").isEmpty());
    }

    @Test
    public void findAllPersonsTest(){
        logger.info("findAllPersonsTest()");
        Assert.assertNotNull(personService.findAllPersons());
    }

    @Test
    public void saveTest(){
        logger.info("saveTest()");
        Person person = new Person("Felicia", "Boyd", "841-874-6544","97451" , null,"1509 Culver St" ,"Culver" ,"jaboyd@email.com");
        personService.save(person);
        Assert.assertTrue(personService.findAllPersons().contains(person));
    }

    @Test
    // TODO: 06/01/2023
    public void updateTest(){
        logger.info("updateTest()");
        List<String> m2 = new ArrayList<>(); m2.add(""); List<String> a2 = new ArrayList<>(); a2.add("");
        MedicalRecord medicalRecord = new MedicalRecord("Jonanathan","Marrack",m2,a2,new Date("01/03/1989"));
        Person person = new Person("Jonanathan","Marrack","841-874-6513","97451",medicalRecord,"29 15th St","Culver","drk@email.com");
        personService.update("Jonanathan","Marrack", "","THE_TEST_IS_HERE_1","THE_TEST_IS_HERE_2","","");
        Person personNew = new Person("Jonanathan","Marrack","","THE_TEST_IS_HERE_1",null,"THE_TEST_IS_HERE_2","","");
        logger.info(personService.findAllPersons());
        Assert.assertTrue(personService.findAllPersons().contains(personNew));
    }

    @Test
    // TODO: 06/01/2023
    public void deleteTest(){
        Person person = new Person("Jonanathan","Marrack","841-874-6513","97451",null,"29 15th St","Culver","drk@email.com");
        personService.delete("Jonanathan","Marrack");
    }

    @Test
    // TODO: 06/01/2023
    public void findPersonByFirstnameLastname(){
        logger.info(personService.findPersonByFirstnameLastname("Foster","Shepard"));
    }

    @Test
    // TODO: 06/01/2023
    public void updateMedicalRecordTest(){
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        Date birthdate = new Date("01/06/2011");
        medication.add("testMedication");
        allergie.add("testAllergie");
        MedicalRecord medicalRecord = new MedicalRecord("Foster","Shepard",medication,allergie,birthdate);
        personService.updateMedicalRecord(medicalRecord.getFirstname(),medicalRecord.getLastname(),medication,allergie,birthdate);
    }

    @Test
    // TODO: 06/01/2023
    public void saveMedicalRecordTest(){
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        Date birthdate = new Date("10/10/2010");
        medication.add("testMedication");
        allergie.add("testAllergie");
        personService.saveMedicalRecord("Toto","Toto",medication,allergie,birthdate);
    }

    @Test
    // TODO: 06/01/2023
    public void deleteMedicalRecordTest(){
        personService.deleteMedicalRecord("Peter","Duncan");
        Assert.assertNull(personService.findPersonByFirstnameLastname("Peter","Duncan").getMedicalrecord());
    }

}
