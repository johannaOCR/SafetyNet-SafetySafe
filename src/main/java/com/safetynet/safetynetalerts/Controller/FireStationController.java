package com.safetynet.safetynetalerts.Controller;

import com.safetynet.safetynetalerts.Service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;
import java.net.MalformedURLException;

@RestController
public class FireStationController {
    private FireStationService fireStationService = new FireStationService();
    private final static Logger logger = LogManager.getLogger("FirestationController") ;

    public FireStationController() throws MalformedURLException {
    }


}
