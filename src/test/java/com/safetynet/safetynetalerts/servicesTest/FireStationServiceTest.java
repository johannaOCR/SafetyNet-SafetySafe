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
    }

    @Test
    // TODO: 06/01/2023
    public void testPhoneAlertByStationNumber(){
        logger.info(fireStationService.phoneAlertByStationNumber(1));
    }

    @Test
    // TODO: 06/01/2023
    public void testFloodByStationsNumbers() {
        List<Integer> firestationNumberList = new ArrayList<>();
        firestationNumberList.add(1);
        firestationNumberList.add(2);
        firestationNumberList.add(3);
        firestationNumberList.add(4);
        logger.info(fireStationService.floodByStationsNumbers(firestationNumberList));
    }
    @Test
    // TODO: 06/01/2023
    public void testFireByAddress(){
        String address = "908 73rd St";
        logger.info(fireStationService.fireByAddress(address));
    }

    @Test
    // TODO: 06/01/2023
    public void findPersonByStationNumberTest() {
            logger.info(fireStationService.findPersonByStationNumber(1));
    }

    @Test
    // TODO: 06/01/2023
    public void findAllFirestationTest(){
        logger.info(this.fireStationService.findAllFirestation().toString());
    }

    @Test
    // TODO: 06/01/2023
    public void saveTest(){
        FireStation fireStation = new FireStation(7).addAddress("NOUVELLE ADRESSE");
        logger.info("BEFORE SAVE : " + this.fireStationService.findAllFirestation());
        this.fireStationService.save(fireStation);
        logger.info("AFTER SAVE : " + this.fireStationService.findAllFirestation());
    }

    @Test
    // TODO: 06/01/2023
    public void updateTest(){
        logger.info("BEFORE UPDATE : " + this.fireStationService.findAllFirestation());
        this.fireStationService.update("834 Binoc Ave",3);
        logger.info("AFTER UPDATE : " + this.fireStationService.findAllFirestation());
    }

    @Test
    // TODO: 06/01/2023
    public void deleteTest(){
        FireStation fireStation = new FireStation(1).addAddress("29 15th St");
        logger.info("Firestation to delete : "+fireStation);
        logger.info("BEFORE DELETE : " + this.fireStationService.findAllFirestation());
        this.fireStationService.delete(fireStation);
        logger.info("AFTER DELETE : " + this.fireStationService.findAllFirestation());
    }

    @Test
    // TODO: 06/01/2023
    public void findByIdTest(){
        logger.info("LIST : " + this.fireStationService.findAllFirestation());
        logger.info("STATION NUMBER CHOISE 3 : " + this.fireStationService.findById(3));
    }
}