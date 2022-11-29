package com.safetynet.safetynetalerts.Controller;
import com.safetynet.safetynetalerts.Service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.MalformedURLException;

public class PersonController {
    private PersonService personService = new PersonService();
    private final static Logger logger = LogManager.getLogger("PersonController") ;

    public PersonController() throws MalformedURLException {
    }


}
