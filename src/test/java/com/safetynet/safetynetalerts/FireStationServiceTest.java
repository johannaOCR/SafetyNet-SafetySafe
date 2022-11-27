package com.safetynet.safetynetalerts;

import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Util.ImportData;
import static org.mockito.Mockito.when;

import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


public class FireStationServiceTest {
    FireStationService fireStationService = new FireStationService();
    ImportData importData;
    Mockito mockito;
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FireStationServiceTest");

    public FireStationServiceTest() throws MalformedURLException {
    }

    @Test
    public void testfindAll() throws IOException {
        logger.info("testfindAll()");
        logger.info(fireStationService.findAll());
        Assert.assertNotNull(fireStationService.findAll());
        Assert.assertFalse(fireStationService.findAll().isEmpty());
    }

    @Test
    public void testFindByStationNumber() throws IOException {
        logger.info("testFindById()");
        logger.info(fireStationService.findByStationNumber(1));
        Assert.assertNotNull(fireStationService.findByStationNumber(1));
        Assert.assertFalse(fireStationService.findByStationNumber(1).toString().isEmpty());
    }

    @Test
    public void testFindByWithoutGoodID() throws IOException {
        logger.info("testFindByWithoutGoodID()");
        logger.info(fireStationService.findByStationNumber(5));
        Assert.assertNull(fireStationService.findByStationNumber(5));
    }

    @Test
    public void personByFirestationTest() throws IOException, JSONException {
        logger.info("personByFirestationTest()");
        logger.info(fireStationService.personByFirestation(1));
        Assert.assertNotNull(fireStationService.personByFirestation(1));
        Assert.assertFalse(fireStationService.personByFirestation(1).isEmpty());
        System.out.println(fireStationService.personByFirestation(1));
    }

    @Test
    public void personsAndStationNumberByAddress() throws IOException {
        String result = fireStationService.personsAndStationNumberByAddress("1509 Culver St");
        logger.info(fireStationService.personsAndStationNumberByAddress("1509 Culver St"));
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void personsAndInformationsByStationNumber() throws IOException, JSONException {
        String ANY = fireStationService.personsAndInformationsByStationNumber(2);
        logger.info(fireStationService.personsAndInformationsByStationNumber(2));
    }

    @Test
    public void findPhoneByStationNumberTest() throws JSONException, IOException {
        logger.info(fireStationService.findPhoneByStationNumber(1));
    }
}