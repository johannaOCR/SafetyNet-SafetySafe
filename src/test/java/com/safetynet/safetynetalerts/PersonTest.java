package com.safetynet.safetynetalerts;

import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.Model.MedicalRecord;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PersonTest {
    Person person;
    Mockito mockito;
    PersonService personService;
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("PersonTest");

    @Test
    public void testIsMajeur() throws ParseException {
        logger.info("testIsMajeur");
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        Date dateMajeur = new SimpleDateFormat("dd/MM/yyyy").parse("04/10/2004");
        MedicalRecord medicalRecordMajeur = new MedicalRecord("test", "test", medication, allergies, dateMajeur);
        Person personTestMajeur = new Person.PersonBuilder().medicalRecord(medicalRecordMajeur).lastName("test").firstName("test").city("test").address("test").email("test").phone("test").zip("test").build();
        logger.info("Result : " + personTestMajeur.isMajeur());
        Assert.assertTrue(personTestMajeur.isMajeur());
    }

    @Test
    public void testIsMajeurWhereIsMinor() throws ParseException {
        logger.info("testIsMajeurWhereIsMinor");
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();
        Date dateMineur = new SimpleDateFormat("dd/MM/yyyy").parse("22/11/2005");
        MedicalRecord medicalRecordMineur = new MedicalRecord("test", "test", medication, allergies, dateMineur);
        Person personTestMineur = new Person.PersonBuilder().medicalRecord(medicalRecordMineur).lastName("test").firstName("test").city("test").address("test").email("test").phone("test").zip("test").build();
        logger.info("Result : " + personTestMineur.isMajeur());
        Assert.assertFalse(personTestMineur.isMajeur());
    }
    /**
     * TODO: Test getAge(), test isEighteenOrLess(), test dateInMillisecond()
     */

}
