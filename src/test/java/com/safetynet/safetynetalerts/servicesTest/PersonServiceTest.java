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
    public void testCommunityEmailByCity() {
        logger.info(personService.communityEmailByCity("Culver"));

        logger.info("testFindAllByCity()");

        Assert.assertNotNull(personService.findAllByCity("Culver"));
        Assert.assertFalse(personService.findAllByCity("Culver").isEmpty());
    }

    @Test
    public void testFindAllPersons(){
        logger.info("testFindAllPersons()");
        Assert.assertNotNull(personService.findAllPersons());
    }

    @Test
    public void testSave(){
        logger.info("testSave()");
        Person person = new Person("Felicia", "Boyd", "841-874-6544","97451" , null,"1509 Culver St" ,"Culver" ,"jaboyd@email.com");
        personService.save(person);
        Assert.assertTrue(personService.findAllPersons().contains(person));
    }

    @Test
    public void testUpdate(){
        logger.info("testUpdate()");
        Person personResult = this.personService.findPersonByFirstnameLastname("Toto","Toto");
        personResult.setZip("test");
        personResult.setAddress("test");
        personResult.setCity("test");
        personResult.setEmail("");
        personResult.setPhone("");
        personService.update(personResult.getFirstName(),
                personResult.getLastName(),
                personResult.getPhone(),
                personResult.getZip(),
                personResult.getAddress(),
                personResult.getCity(),
                personResult.getEmail());
        Assert.assertTrue(personService.findAllPersons().contains(personResult));
    }

    @Test
    public void testDelete(){
        logger.info("testDelete()");
        Person person = personService.findPersonByFirstnameLastname("Jonanathan","Marrack");
        personService.delete("Jonanathan","Marrack");
        Assert.assertFalse(personService.findAllPersons().contains(person));
    }

    @Test
    public void testFindPersonByFirstnameLastname(){
        logger.info("testFindPersonByFirstnameLastname()");

        Person person = new Person("test", "test","000000000","test",null,"test","test","test");
        personService.save(person);
        Person personResult = personService.findPersonByFirstnameLastname("test","test");

        Assert.assertEquals(person,personResult);
    }

    @Test
    public void testUpdateMedicalRecord(){
        logger.info("testUpdateMedicalRecord()");

        MedicalRecord medicalRecordResult = this.personService.findPersonByFirstnameLastname("Foster","Shepard").getMedicalrecord();
        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        medication.add("testMedication");
        allergie.add("testAllergie");
        medicalRecordResult.setBirthdate(new Date("01/09/2011"));
        medicalRecordResult.setMedications(medication);
        medicalRecordResult.setAllergies(allergie);

        personService.updateMedicalRecord(
                medicalRecordResult.getFirstname(),
                medicalRecordResult.getLastname(),
                medicalRecordResult.getMedications(),
                medicalRecordResult.getAllergies(),
                medicalRecordResult.getBirthdate()
        );

        Assert.assertEquals(personService.findPersonByFirstnameLastname("Foster","Shepard").getMedicalrecord(),medicalRecordResult);
    }

    @Test
    public void testSaveMedicalRecord(){
        logger.info("testSaveMedicalRecord()");

        List<String> medication = new ArrayList<>();
        List<String> allergie = new ArrayList<>();
        Date birthdate = new Date("10/10/2010");
        medication.add("testMedication");
        allergie.add("testAllergie");
        personService.saveMedicalRecord("Toto","Toto",medication,allergie,birthdate);

        Assert.assertNotNull(personService.findPersonByFirstnameLastname("Toto","Toto").getMedicalrecord());
    }

    @Test
    public void testDeleteMedicalRecord(){
        logger.info("testDeleteMedicalRecord()");

        personService.deleteMedicalRecord("Peter","Duncan");

        Assert.assertNull(personService.findPersonByFirstnameLastname("Peter","Duncan").getMedicalrecord());
    }

}
