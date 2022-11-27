package com.safetynet.safetynetalerts.Controller;

import com.safetynet.safetynetalerts.Service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@RestController
public class FireStationController {
    private FireStationService fireStationService = new FireStationService();
    private final static Logger logger = LogManager.getLogger("FirestationController") ;

    public FireStationController() throws MalformedURLException {
    }

    /**
     * @Param firestation?stationNumber=<station_number>
     * @return une liste des personnes par caserne de pompiers correspondantes (prénom, nom, adresse, numéro de téléphone) et indique le nombre de majeur et de mineur
     * */

    @GetMapping(value = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetFirestation(@RequestParam int stationNumber) throws IOException, JSONException {
        logger.info("get /firestation?stationNumber with param "+stationNumber);
        return fireStationService.personByFirestation(stationNumber);
    }

    /*
        http://localhost:8080/fire?address=<address>
        Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
        de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
        médicaux (médicaments, posologie et allergies) de chaque personne.
         */
    @GetMapping(value = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetFire(@RequestParam String adresse) throws IOException {
        return fireStationService.personsAndStationNumberByAddress(adresse);
    }

    @GetMapping(value = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetPhone(@RequestParam int stationNumber) throws IOException, JSONException {
        logger.info("get /phoneAlert?firestation=<firestation_number>stationNumber with param "+stationNumber);
        return fireStationService.findPhoneByStationNumber(stationNumber);
    }
}
