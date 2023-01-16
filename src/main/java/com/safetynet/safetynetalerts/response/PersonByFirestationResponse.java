package com.safetynet.safetynetalerts.response;

import java.util.List;

public class PersonByFirestationResponse {
    int majeur;
    int minor;
    List<PersonByFirestationPersonInfoResponse> persons;

    public PersonByFirestationResponse(int majeur, int minor, List<PersonByFirestationPersonInfoResponse> persons) {
        this.majeur = majeur;
        this.minor = minor;
        this.persons = persons;
    }

    public int getMajeur() {
        return majeur;
    }

    public void setMajeur(int majeur) {
        this.majeur = majeur;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public List<PersonByFirestationPersonInfoResponse> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonByFirestationPersonInfoResponse> persons) {
        this.persons = persons;
    }
}
