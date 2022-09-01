package com.safetynet.safetynetalerts.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PersonController {


//    http://localhost:8080/childAlert?address=<address>
//
//    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
//    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
//    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
@GetMapping(value = "/childAlert",produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> childAlert(@RequestParam String address)  {
    return ;
}


//    http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
//    Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
//    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
//    toutes apparaître.
@GetMapping(value = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> personInfoName(@RequestParam String firstName, String lastName) {
    return ;
}
//    http://localhost:8080/communityEmail?city=<city>
//    Cette url doit retourner les adresses mail de tous les habitants de la ville
@GetMapping(value = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> communityEmail(@RequestParam String city) {
    return ;
}

//    http://localhost:8080/phoneAlert?firestation=<firestation_number>
//    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
//    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
@GetMapping(value = "/phoneAlert", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> phoneAlert(@RequestParam int firestation_number)  {
    return ;
}

}
