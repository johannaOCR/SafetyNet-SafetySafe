package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Util.ImportData;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.MalformedURLException;

import java.util.ArrayList;
import java.util.List;

public class FireStationServiceTest {
    FireStationService fireStationService = new FireStationService();
    ImportData importData;
    Mockito mockito;
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FireStationServiceTest");

    public FireStationServiceTest() throws MalformedURLException {
    }

    @Test
    public void testfindAll() {
        logger.info("testfindAll()");
        logger.info(fireStationService.findAll());
        Assert.assertNotNull(fireStationService.findAll());
        Assert.assertFalse(fireStationService.findAll().isEmpty());
    }

    @Test
    public void testFindByStationNumber() {
        logger.info("testFindById()");
        logger.info(fireStationService.findByStationNumber(1));
        Assert.assertNotNull(fireStationService.findByStationNumber(1));
        Assert.assertFalse(fireStationService.findByStationNumber(1).toString().isEmpty());
    }

    @Test
    public void testFindByStationNumberWithoutGoodID() {
        logger.info("testFindByWithoutGoodID()");
        logger.info(fireStationService.findByStationNumber(5));
        Assert.assertNull(fireStationService.findByStationNumber(5));
    }

    @Test
    public void testPersonByFirestation(){
        logger.info("personByFirestationTest()");
        logger.info(fireStationService.personByFirestation(1));
        Assert.assertNotNull(fireStationService.personByFirestation(1));
        Assert.assertFalse(fireStationService.personByFirestation(1).isEmpty());
        System.out.println(fireStationService.personByFirestation(1));
    }

    @Test
    public void testPhoneAlertByStationNumber(){
        logger.info(fireStationService.phoneAlertByStationNumber(1));
    }

    @Test
    public void testFloodByStationsNumbers() {
        List<Integer> firestationNumberList = new ArrayList<>();
        firestationNumberList.add(1);
        firestationNumberList.add(2);
        firestationNumberList.add(3);
        firestationNumberList.add(4);
        logger.info(fireStationService.floodByStationsNumbers(firestationNumberList));
    }
    @Test
    public void testFireByAddress(){
        String address = "908 73rd St";
        logger.info(fireStationService.fireByAddress(address));
    }

    @Test
    public void findPersonByStationNumberTest() {
            logger.info(fireStationService.findPersonByStationNumber(1));
    }
}