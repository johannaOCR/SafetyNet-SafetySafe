package com.safetynet.safetynetalerts.daoTest;

import Repository.FirestationDAO;
import com.safetynet.safetynetalerts.Model.FireStation;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

public class FirestationDaoTest {
    FirestationDAO firestationDao = new FirestationDAO();
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FirestationDaoTest");
    @Test
    public void findAllTest(){
        logger.info(this.firestationDao.findAll().toString());
    }

    @Test
    public void saveTest(){
        FireStation fireStation = new FireStation(7).addAddress("NOUVELLE ADRESSE");
        logger.info("BEFORE SAVE : " + this.firestationDao.findAll());
        this.firestationDao.save(fireStation);
        logger.info("AFTER SAVE : " + this.firestationDao.findAll());
    }

    @Test
    public void updateTest(){
        logger.info("BEFORE UPDATE : " + this.firestationDao.findAll());
        this.firestationDao.update("834 Binoc Ave",3);
        logger.info("AFTER UPDATE : " + this.firestationDao.findAll());
    }

    @Test
    public void deleteTest(){
        FireStation fireStation = new FireStation(7).addAddress("NOUVELLE ADRESSE");
        this.firestationDao.save(fireStation);
        logger.info("BEFORE DELETE : " + this.firestationDao.findAll());
        this.firestationDao.delete(fireStation);
        logger.info("AFTER DELETE : " + this.firestationDao.findAll());
    }

    @Test
    public void findByIdTest(){
        logger.info("LIST : " + this.firestationDao.findAll());
        logger.info("STATION NUMBER CHOISE 3 : " + this.firestationDao.findById(3));
    }
}
