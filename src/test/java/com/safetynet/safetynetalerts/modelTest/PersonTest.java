package com.safetynet.safetynetalerts.modelTest;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PersonTest {
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("PersonTest");
    Person person;
    Mockito mockito;
    PersonService personService;

    @Test
    public void testIsMajeur() throws ParseException {
        logger.info("testIsMajeur");
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        Date dateMajeur = new SimpleDateFormat("dd/MM/yyyy").parse("04/10/2004");
        MedicalRecord medicalRecordMajeur = new MedicalRecord("test", "test", medication, allergies, dateMajeur);
        Person personTestMajeur = new Person("test", "test", "test", "test", medicalRecordMajeur, "test", "test", "test");
        Assert.assertTrue(personTestMajeur.isMajeur());
    }

    @Test
    public void testIsMajeurWhereIsMinor() throws ParseException {
        logger.info("testIsMajeurWhereIsMinor");
        List<String> medication = new ArrayList<>();
        List<String> allergies = new ArrayList<>();

        //GIVEN
        Date dateMineur = new SimpleDateFormat("dd/MM/yyyy").parse("22/11/2005");
        MedicalRecord medicalRecordMineur = new MedicalRecord("test", "test", medication, allergies, dateMineur);
        Person personTestMineur = new Person("test", "test", "test", "test", medicalRecordMineur, "test", "test", "test");
        // THEN
        Assert.assertFalse(personTestMineur.isMajeur());
    }
}
