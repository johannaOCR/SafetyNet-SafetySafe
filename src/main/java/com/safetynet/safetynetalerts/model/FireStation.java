package com.safetynet.safetynetalerts.model;

import java.util.HashSet;
import java.util.Set;

public class FireStation {

    private final Set<String> addresses = new HashSet<>();
    private final int stationNumber;

    public FireStation(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public FireStation addAddress(String address) {
        addresses.add(address);
        return this;
    }

    public FireStation removeAddress(String address) {
        addresses.remove(address);
        return this;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "addresses=" + addresses +
                ", stationNumber=" + stationNumber +
                '}';
    }
}