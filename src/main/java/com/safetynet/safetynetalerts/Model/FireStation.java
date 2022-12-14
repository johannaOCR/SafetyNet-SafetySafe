package com.safetynet.safetynetalerts.Model;

import java.util.HashSet;
import java.util.Set;

public class FireStation {

    private Set<String> addresses = new HashSet<>();
    private int stationNumber;

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