package com.safetynet.safetynetalerts.Controller;

import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class FireStationController {

    private FireStationService fireStationService = new FireStationService();
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
    public void postFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        fireStationService.save(new FireStation(stationNumber).addAddress(address));
    }
    @PutMapping("/firestation")
    public void putFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        fireStationService.update(address,stationNumber);
    }

    @DeleteMapping("/firestation")
    public void deleteFirestation(
            @RequestParam(name="stationNumber") int stationNumber,
            @RequestParam(name="address") String address)
    {
        FireStation fireStation= new FireStation(stationNumber).addAddress(address);
        fireStationService.delete(fireStation);
    }
    /*****************************
     *          SERVICES
     * ***************************/

    @GetMapping("/firestation")
    public String getPersonByStationNumber(@RequestParam(name="stationNumber", required = true) int station_number) {
        logger.info("Get /firestation?stationNumber="+station_number);
        return fireStationService.personByFirestation(station_number);
    }

    @GetMapping("/phoneAlert")
        public String getPhoneByStationNumber(@RequestParam(name="stationNumber", required = true) int station_number) {
            logger.info("Get /phoneAlert?stationNumber="+station_number);
            return fireStationService.phoneAlertByStationNumber(station_number);
    }

    @GetMapping("/fire")
    public String getPersonByFire(@RequestParam(name="address", required = true) String address) {
        logger.info("Get /fire?address="+address);
        return fireStationService.fireByAddress(address);
    }

    @GetMapping("/flood/stations")
    public String getPersonsByFirestationList(@RequestParam(name="stations", required = true) List<Integer> station_numbers) {
        logger.info("Get  flood/stations?stations="+station_numbers);
        return fireStationService.floodByStationsNumbers(station_numbers);
    }
}


