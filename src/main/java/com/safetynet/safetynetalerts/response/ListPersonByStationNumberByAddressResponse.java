package com.safetynet.safetynetalerts.response;

public class ListPersonByStationNumberByAddressResponse {
    int stationNumber;
    PersonByAddressByFirestationResponse listPersons;

    public ListPersonByStationNumberByAddressResponse(int stationNumber, PersonByAddressByFirestationResponse listPersons) {
        this.stationNumber = stationNumber;
        this.listPersons = listPersons;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public PersonByAddressByFirestationResponse getListPersons() {
        return listPersons;
    }

    public void setListPersons(PersonByAddressByFirestationResponse listPersons) {
        this.listPersons = listPersons;
    }


}
