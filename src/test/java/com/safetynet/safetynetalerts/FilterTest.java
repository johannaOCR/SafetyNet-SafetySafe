package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.Util.Filter;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilterTest {
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("FilterTest");
    Filter filtre = new Filter();

    @Test
    public void listStringToJsonTest(){
        List list = new ArrayList<String>();
        list.add("tutu");
        list.add("toto");
        list.add("titi");
        list.add("tata");
        logger.info(filtre.listStringToJson(list));
    }
}
