package com.safetynet.safetynetalerts.servicesTest;

import com.safetynet.safetynetalerts.Model.MedicalRecord;
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
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }

    @Test
    // TODO: 06/01/2023
    public void testfindNumberOfAdult(){
        logger.info(personService.findNumberOfAdult(personService.findAll()));
    }

    @Test
    // TODO: 06/01/2023
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
        Assert.assertNotNull(personsResult);
        Assert.assertFalse(personsResult.isEmpty());
    }

    @Test
    public void testFindAllByAddress(){
        Assert.assertNotNull(personService.findAllByAddress("1509 Culver St"));
        Assert.assertFalse(personService.findAllByAddress("1509 Culver St").isEmpty());
    }

    @Test
    public void testFindAllByCity(){
        Assert.assertNotNull(personService.findAllByCity("Culver"));
        Assert.assertFalse(personService.findAllByCity("Culver").isEmpty());
    }

    @Test
    // TODO: 06/01/2023
    public void testChildAlertByAddress(){
        logger.info(personService.childAlertByAddress("1509 Culver St"));
    }

    @Test
    // TODO: 06/01/2023
    public void testChildAlertByAddressWithoutChild(){
        logger.info(personService.childAlertByAddress("29 15th St"));
    }

    @Test
    // TODO: 06/01/2023
    public void testPersonInfoByFirstNameLastName() {
        logger.info(personService.personInfoByFirstNameLastName("Brian","Stelzer"));
    }

    @Test
    // TODO: 06/01/2023
    public void testCommunityEmailByCity() {
        logger.info(personService.communityEmailByCity("Culver"));
    }
/** TODO : faire le test
    @Test
    public void infoEmailByCityFalseTest() {
        Assert.as(personService.infoEmailByCity("NameOfCityHowNotExist")=[]);
    }
**/

    @Test
    // TODO: 06/01/2023
    public void findAll(){
        logger.info("**** FIND ALL : **** \n\r");
        logger.info(personService.findAllPersons());
    }

    @Test
    // TODO: 06/01/2023
    public void saveTest(){
        Person person = new Person("Felicia", "Boyd", "841-874-6544","97451" , null,"1509 Culver St" ,"Culver" ,"jaboyd@email.com");
        personService.save(person);
    }

    @Test
    // TODO: 06/01/2023
    public void updateTest(){
        Person person = new Person("Jonanathan","Marrack","841-874-6513","97451",null,"29 15th St","Culver","drk@email.com");
        personService.update("Jonanathan","Marrack", "","THE_TEST_IS_HERE_1","THE_TEST_IS_HERE_2","","");

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
