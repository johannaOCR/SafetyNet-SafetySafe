package com.safetynet.safetynetalerts;

import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Util.ImportData;
import static org.mockito.Mockito.when;
import org.junit.Assert;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;


public class FireStationServiceTest {
    FireStationService fireStationService = new FireStationService();
    ImportData importData;
    Mockito mockito;


    @Test
    public void personByFirestationTest() throws IOException {
        Assert.fail(fireStationService.personByFirestation(1));
    }

}
