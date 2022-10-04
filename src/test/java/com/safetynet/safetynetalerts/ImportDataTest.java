package com.safetynet.safetynetalerts;

import ch.qos.logback.core.CoreConstants;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.junit.Test;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class ImportDataTest {
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("ImportDataTest") ;
    ImportData importData = new ImportData();

    public ImportDataTest() throws MalformedURLException {
    }

    @Test
    public void testLoadFileByUrl() {
        Assert.assertNotNull(importData.loadFileByUrl());
        Assert.assertFalse(importData.loadFileByUrl().toString().isEmpty());
    }

    @Test
    public void testLoadFirestation() throws IOException {
        logger.info(importData.loadFirestation());
        Assert.assertNotNull(importData.loadFirestation());
        Assert.assertFalse(importData.loadFirestation().isEmpty());
    }

    @Test
    public void testLoadMedicalRecord() {
        logger.info(importData.loadMedicalRecord(importData.loadFileByUrl()).toString());
        Assert.assertNotNull(importData.loadMedicalRecord(importData.loadFileByUrl()));
        Assert.assertFalse(importData.loadMedicalRecord(importData.loadFileByUrl()).isEmpty());
    }

    @Test
    public void testLoadPerson() {
        ArrayList<Person> persons = (ArrayList<Person>) importData.loadPerson();
        logger.info(persons);
        Assert.assertNotNull(persons);
        Assert.assertFalse(persons.isEmpty());
    }
}