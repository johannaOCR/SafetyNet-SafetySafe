package com.safetynet.safetynetalerts.Response;

import java.util.List;

public class ListPersonByStationNumberByAddressResponse {
    int stationNumber;
    List<PersonByAdressByFirestationResponse> listPersonsByAddress;

    public ListPersonByStationNumberByAddressResponse(int stationNumber, List<PersonByAdressByFirestationResponse> listPersonsByAddress) {
        this.stationNumber = stationNumber;
        this.listPersonsByAddress = listPersonsByAddress;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<PersonByAdressByFirestationResponse> getListPersonsByAddress() {
        return listPersonsByAddress;
    }

    public void setListPersonsByAddress(List<PersonByAdressByFirestationResponse> listPersonsByAddress) {
        this.listPersonsByAddress = listPersonsByAddress;
    }

}
