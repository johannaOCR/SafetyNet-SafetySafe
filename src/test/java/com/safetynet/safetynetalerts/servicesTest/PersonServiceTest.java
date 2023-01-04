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

    @Test
    public void findAll(){
        logger.info("**** FIND ALL : **** \n\r");
        logger.info(personService.findAllPersons());
    }

    @Test
    public void saveTest(){
        Person person = new Person("Felicia", "Boyd", "841-874-6544","97451" , null,"1509 Culver St" ,"Culver" ,"jaboyd@email.com");
        logger.info("**** BEFORE SAVE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");
        personService.save(person);
        logger.info("**** PERSON TO SAVE **** \n\r" + person + " \n\r **** END **** \r\n");
        logger.info("**** AFTER SAVE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");
    }

    @Test
    public void updateTest(){
        Person person = new Person("Jonanathan","Marrack","841-874-6513","97451",null,"29 15th St","Culver","drk@email.com");
        logger.info("**** BEFORE UPDATE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");
        personService.update("Jonanathan","Marrack", "","THE_TEST_IS_HERE_1","THE_TEST_IS_HERE_2","","");
        logger.info("**** AFTER UPDATE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");

    }

    @Test
    public void deleteTest(){
        Person person = new Person("Jonanathan","Marrack","841-874-6513","97451",null,"29 15th St","Culver","drk@email.com");
        logger.info("**** BEFORE DELETE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");
        personService.delete("Jonanathan","Marrack");
        logger.info("**** PERSON TO DELETE **** \n\r" + person + " \n\r **** END **** \r\n");
        logger.info("**** AFTER DELETE **** \n\r" + personService.findAllPersons() + " \n\r **** END **** \r\n");
    }

    @Test
    public void findPersonByFirstnameLastname(){
        logger.info(personService.findPersonByFirstnameLastname("Foster","Shepard"));
    }

    @Test
    public void updateMedicalRecordTest(){
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        Date birthdate = new Date("01/06/2011");
        medication.add("testMedication");
        allergie.add("testAllergie");
        MedicalRecord medicalRecord = new MedicalRecord("Foster","Shepard",medication,allergie,birthdate);
        logger.info("**** BEFORE MR UPDATE **** \n\r" + personService.findPersonByFirstnameLastname("Foster","Shepard") + " \n\r **** END **** \r\n");
        personService.updateMedicalRecord(medicalRecord.getFirstname(),medicalRecord.getLastname(),medication,allergie,birthdate);
        logger.info("**** AFTER MR UPDATE **** \n\r" + personService.findPersonByFirstnameLastname("Foster","Shepard") + " \n\r **** END **** \r\n");
    }

    @Test
    public void saveMedicalRecordTest(){
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        Date birthdate = new Date("10/10/2010");
        medication.add("testMedication");
        allergie.add("testAllergie");
        logger.info("**** BEFORE MR SAVE **** \n\r" + personService.findPersonByFirstnameLastname("Toto","Toto") + " \n\r **** END **** \r\n");
        personService.saveMedicalRecord("Toto","Toto",medication,allergie,birthdate);
        logger.info("**** AFTER MR SAVE **** \n\r" + personService.findPersonByFirstnameLastname("Toto","Toto") + " \n\r **** END **** \r\n");
    }

    @Test
    public void deleteMedicalRecordTest(){
        logger.info("**** BEFORE MR DELETE **** \n\r" + personService.findPersonByFirstnameLastname("Peter","Duncan") + " \n\r **** END **** \r\n");
        personService.deleteMedicalRecord("Peter","Duncan");
        logger.info("**** AFTER MR DELETE **** \n\r" + personService.findPersonByFirstnameLastname("Peter","Duncan") + " \n\r **** END **** \r\n");
        Assert.assertNull(personService.findPersonByFirstnameLastname("Peter","Duncan").getMedicalrecord());
    }

}
