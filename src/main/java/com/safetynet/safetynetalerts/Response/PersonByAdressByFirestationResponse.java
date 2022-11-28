package com.safetynet.safetynetalerts.Response;

import java.util.List;

public class PersonByAdressByFirestationResponse {


    String address;
    List<PersonInfoByaddressByStationNumber> persons;

    public PersonByAdressByFirestationResponse(String address, List<PersonInfoByaddressByStationNumber> persons) {
        this.address = address;
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonInfoByaddressByStationNumber> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonInfoByaddressByStationNumber> persons) {
        this.persons = persons;
    }
}
