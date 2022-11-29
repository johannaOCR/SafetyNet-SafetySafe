package com.safetynet.safetynetalerts.Response;

import java.util.List;

public class ListPersonByStationNumberByListAddressesResponse {
    int stationNumber;
    List<PersonByAddressByFirestationResponse> listPersonsByAddress;

    public ListPersonByStationNumberByListAddressesResponse(int stationNumber, List<PersonByAddressByFirestationResponse> listPersonsByAddress) {
        this.stationNumber = stationNumber;
        this.listPersonsByAddress = listPersonsByAddress;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public List<PersonByAddressByFirestationResponse> getListPersonsByAddress() {
        return listPersonsByAddress;
    }

    public void setListPersonsByAddress(List<PersonByAddressByFirestationResponse> listPersonsByAddress) {
        this.listPersonsByAddress = listPersonsByAddress;
    }

}
