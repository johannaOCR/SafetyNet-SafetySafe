package com.safetynet.safetynetalerts.Controller;

import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Util.BuilderResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class FireStationController {

    private final FireStationService fireStationService = new FireStationService();
    private final BuilderResponse builderResponse = new BuilderResponse();
    private final static Logger logger = LogManager.getLogger("FirestationController") ;

    public FireStationController() throws MalformedURLException {
    }

    /*****************************
     *    C.R.U.D. Firestation
     * ***************************/

    @GetMapping("/firestations")
    public String getFirestation()
    {
        return fireStationService.findAllFirestation().toString();
    }

    @PostMapping("/firestation")
    public ResponseEntity<?> postFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        return builderResponse.responseBoolean(fireStationService.save(new FireStation(stationNumber).addAddress(address)));

    }
    @PutMapping("/firestation")
    public ResponseEntity<?> putFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        return builderResponse.responseBoolean(fireStationService.update(address,stationNumber));
    }

    @DeleteMapping("/firestation")
    public ResponseEntity<?> deleteFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        FireStation fireStation= new FireStation(stationNumber).addAddress(address);
        return builderResponse.responseBoolean(fireStationService.delete(fireStation));
    }
    /*****************************
     *          SERVICES
     * ***************************/

    @GetMapping("/firestation")
    public ResponseEntity<?> getPersonByStationNumber(@RequestParam(name="stationNumber") int station_number) {
        logger.info("Get /firestation?stationNumber="+station_number);
        return builderResponse.customResponse(fireStationService.personByFirestation(station_number));
    }

    @GetMapping("/phoneAlert")
        public ResponseEntity<?> getPhoneByStationNumber(@RequestParam(name="stationNumber") int station_number) {
            logger.info("Get /phoneAlert?stationNumber="+station_number);
            return builderResponse.customResponse(fireStationService.phoneAlertByStationNumber(station_number));
    }

    @GetMapping("/fire")
    public ResponseEntity<?> getPersonByFire(@RequestParam(name="address") String address) {
        logger.info("Get /fire?address="+address);
        return builderResponse.customResponse(fireStationService.fireByAddress(address));
    }

    @GetMapping("/flood/stations")
    public ResponseEntity<?> getPersonsByFirestationList(@RequestParam(name="stations") List<Integer> station_numbers) {
        logger.info("Get  flood/stations?stations="+station_numbers);
        return builderResponse.customResponse(fireStationService.floodByStationsNumbers(station_numbers));
    }
}


