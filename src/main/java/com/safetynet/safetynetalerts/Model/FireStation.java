package com.safetynet.safetynetalerts.Model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FireStation {
    private Set<String> addresses = new HashSet<>();
    private String stationNumber;

    public FireStation(Set<String> addresses, String stationNumber) {
        this.addresses = addresses;
        this.stationNumber = stationNumber;
    }

    public FireStation addAddress(String address) {
        addresses.add(address);
        return this;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public Set<String> getAddresses() {
        return new HashSet<>(addresses);
    }

    @Override
    public String toString() {
        return stationNumber.concat(": ") + String.join(", ", addresses);
    }
}
