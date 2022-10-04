package com.safetynet.safetynetalerts.Model;

import com.jsoniter.any.Any;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicalRecord {
    private String firstname;
    private String lastname;
    public List<Any> medications;
    public List<Any> allergies;
    public Date birthdate;

    public MedicalRecord(String firstname, String lastname, List<Any> medications, List<Any> allergies, Date birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.medications = medications;
        this.allergies = allergies;
        this.birthdate = birthdate;
    }

    public List<Any> getMedications() {
        return medications;
    }

    public void setMedications(List<Any> medications) {
        this.medications = medications;
    }

    public List<Any> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Any> allergies) {
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
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yy");
        return  "\n" +"MedicalRecord {" + "\n" +
                "firstname = '" + firstname + '\'' + "\n"+
                "lastname = '" + lastname + '\'' + "\n"+
                "medications = " + medications + "\n"+
                "allergies = " + allergies + "\n"+
                "birthdate = " + formater.format(birthdate) + "\n"+
                '}'+ "\n";
    }
}
