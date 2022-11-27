package com.safetynet.safetynetalerts.Controller;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.MalformedURLException;

public class PersonController {
    private PersonService personService = new PersonService();
    private final static Logger logger = LogManager.getLogger("PersonController") ;

    public PersonController() throws MalformedURLException {
    }


    @GetMapping(value = "/communityEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetCommunityEmail(@RequestParam String city) throws IOException, JSONException {
        logger.info("get /communityEmail?city=<city> with param "+city);
        return personService.findMailByCity(city);
    }

    @GetMapping(value = "/childAlert", produces = MediaType.APPLICATION_JSON_VALUE)
    public String GetChildByAddress(@RequestParam String address) throws IOException, JSONException {
        logger.info("get /childAlert?address=<address> with param "+address);
        return personService.childFamilyByAddress(address);
    }

//    @GetMapping(value = "/personInfo", produces = MediaType.APPLICATION_JSON_VALUE)
//    public String GetPersonInfo(@RequestParam String firstname, @RequestParam String lastname)  {
//        logger.info("/personInfo?firstName=<firstName>&lastName=<lastName> with param "+firstname + "& " + lastname);
//        return personService.findPersByFirstnameLastname(firstname, lastname);
//    }

}
