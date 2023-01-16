package com.safetynet.safetynetalerts.model;

import java.util.Date;
import java.util.List;

public class MedicalRecord {
    public List<String> medications;
    public List<String> allergies;
    public Date birthdate;
    private String firstname;
    private String lastname;

    public MedicalRecord(String firstname, String lastname, List<String> medications, List<String> allergies, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.medications = medications;
        this.allergies = allergies;
        this.birthdate = birthdate;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", medications=" + medications +
                ", allergies=" + allergies +
                ", birthdate=" + birthdate +
                '}';
    }
}
