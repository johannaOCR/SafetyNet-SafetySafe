package com.safetynet.safetynetalerts.servicesTest;

import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FireStationServiceTest {
    FireStationService fireStationService = new FireStationService();

    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FireStationServiceTest");

    public FireStationServiceTest() throws MalformedURLException {
    }

    @Test
    public void testfindAll() {
        logger.info("testfindAll()");

        Assert.assertNotNull(fireStationService.findAll());
        Assert.assertFalse(fireStationService.findAll().isEmpty());
    }

    @Test
    public void testFindByStationNumber() {
        logger.info("testFindById()");

        Assert.assertNotNull(fireStationService.findByStationNumber(1));
        Assert.assertFalse(fireStationService.findByStationNumber(1).toString().isEmpty());
    }

    @Test
    public void testFindByStationNumberWithoutGoodID() {
        logger.info("testFindByWithoutGoodID()");

        Assert.assertNull(fireStationService.findByStationNumber(5));
    }

    @Test
    public void testPersonByFirestation(){
        logger.info("personByFirestationTest()");

        logger.info(fireStationService.personByFirestation(1));
        Assert.assertNotNull(fireStationService.personByFirestation(1));
        Assert.assertFalse(fireStationService.personByFirestation(1).isEmpty());
    }

    @Test
    public void testPhoneAlertByStationNumber(){
        logger.info("testPhoneAlertByStationNumber()");

        Assert.assertNotNull(fireStationService.phoneAlertByStationNumber(1));
        Assert.assertFalse(fireStationService.phoneAlertByStationNumber(1).isEmpty());
    }

    @Test
    public void testFloodByStationsNumbers() {
        logger.info("testFloodByStationsNumbers()");

        List<Integer> firestationNumberList = new ArrayList<>();
        firestationNumberList.add(1);
        firestationNumberList.add(2);
        firestationNumberList.add(3);
        firestationNumberList.add(4);

        Assert.assertNotNull(fireStationService.floodByStationsNumbers(firestationNumberList));
        Assert.assertFalse(fireStationService.floodByStationsNumbers(firestationNumberList).isEmpty());
    }

    @Test
    public void testFireByAddress(){
        logger.info("testFireByAddress()");

        String address = "908 73rd St";

        Assert.assertNotNull(fireStationService.fireByAddress(address));
        Assert.assertFalse(fireStationService.fireByAddress(address).isEmpty());
    }

    @Test
    public void testFindPersonByStationNumber() {
        logger.info("testFindPersonByStationNumber()");

        Assert.assertNotNull(fireStationService.findPersonByStationNumber(1));
        Assert.assertFalse(fireStationService.findPersonByStationNumber(1).isEmpty());
    }

    @Test
    public void testFindAllFirestation(){
        logger.info("testFindAllFirestation()");

        Assert.assertNotNull(fireStationService.findAllFirestation().toString());
        Assert.assertFalse(fireStationService.findAllFirestation().toString().isEmpty());
    }

    @Test
    public void testSave(){
        logger.info("testSave()");

        FireStation fireStation = new FireStation(7).addAddress("NOUVELLE ADRESSE");
        boolean result = fireStationService.save(fireStation);
        Assert.assertTrue(result);
        Assert.assertTrue(fireStationService.findAllFirestation().contains(fireStation));
    }

    @Test
    public void testUpdate(){
        logger.info("testUpdate()");

        String address = "834 Binoc Ave";
        boolean result = fireStationService.update(address,3);
        Assert.assertTrue(result);
        Assert.assertTrue(fireStationService.findByStationNumber(3).getAddresses().contains(address));
    }

    @Test
    public void testDelete(){
        logger.info("testDelete()");

        String address = "29 15th St";
        FireStation fireStation = new FireStation(1).addAddress(address);

        boolean result = fireStationService.delete(fireStation);
        Assert.assertTrue(result);
        Assert.assertFalse(fireStationService.findByStationNumber(1).getAddresses().contains(address));
    }

    @Test
    public void testFindByID(){
        logger.info("testFindByID()");

        Assert.assertNotNull(fireStationService.findById(3));
    }
}