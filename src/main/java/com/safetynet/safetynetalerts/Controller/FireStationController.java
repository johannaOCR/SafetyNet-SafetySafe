package com.safetynet.safetynetalerts.Controller;

import com.safetynet.safetynetalerts.Service.FireStationService;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {

//    http://localhost:8080/firestation?stationNumber=<station_number>
//    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
//            Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
//    doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
//    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
//            moins) dans la zone desservie.
@GetMapping(value = "/firestation", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> firestation(@RequestParam int stationNumber) {

}
//    http://localhost:8080/fire?address=<address>
//    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
//    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
//    médicaux (médicaments, posologie et allergies) de chaque personne.
@GetMapping(value = "/fire", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> fire(@RequestParam String adresse) {

}
//    http://localhost:8080/flood/stations?stations=<a list of station_numbers>
//    Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
//    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
//    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
@GetMapping(value = "/flood/stations", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> flood(@RequestParam List<Integer> stationNumber) {

}



}
