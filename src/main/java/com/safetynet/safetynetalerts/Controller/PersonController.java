package com.safetynet.safetynetalerts.Controller;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
@RestController
public class PersonController {
    private PersonService personService = new PersonService();
    private final static Logger logger = LogManager.getLogger("PersonController") ;

    public PersonController() throws MalformedURLException {
    }



    /**
     * services
     */
    @GetMapping("/childAlert")
    public String getChildAndFamilyByAddress(@RequestParam(name="address", required = true) String address) {
        logger.info("Get /childAlert?address="+address);
        return personService.childAlertByAddress(address);
    }

    @GetMapping("/personInfo")
    public String getPersonByFirstnameLastname(@RequestParam(name="lastname", required = true) String lastname, @RequestParam(name="firstname", required = true) String firstname) {
        logger.info("Get /personInfo?firstName="+firstname+"&lastName="+lastname);
        return personService.personInfoByFirstNameLastName(firstname,lastname);
    }

    @GetMapping("/communityEmail")
    public String getEmailByCity(@RequestParam(name="city", required = true) String city) {
        logger.info("Get communityEmail?city="+city);
        return personService.communityEmailByCity(city);
    }


}
