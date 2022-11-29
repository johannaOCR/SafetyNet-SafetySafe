package com.safetynet.safetynetalerts.Response;

import java.util.List;

public class PersonByAddressByFirestationResponse {
    String address;
    List<PersonInfoByAddressByStationNumber> persons;

    public PersonByAddressByFirestationResponse(String address, List<PersonInfoByAddressByStationNumber> persons) {
        this.address = address;
        this.persons = persons;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonInfoByAddressByStationNumber> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonInfoByAddressByStationNumber> persons) {
        this.persons = persons;
    }
}
