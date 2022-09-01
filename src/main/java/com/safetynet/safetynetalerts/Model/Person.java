package com.safetynet.safetynetalerts.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Person {
    public String firstName;
    public String lastName;
    public String phone;
    public String zip;
    public String address;
    public String city;
    public String email;
    public Date birthdate;
    public MedicalRecord medicalRecord;


    public Person(String firstName, String lastName, String phone, String zip, String address, String city, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.zip = zip;
        this.address = address;
        this.city = city;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Boolean isMajeur () {
        Date dateNow = new Date(System.currentTimeMillis());
        try {
            Date Formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(String.valueOf(this.birthdate));
            long between = dateNow.getTime() - Formatter.getTime();
            if (between/(1000*60*60*24) > 6570 ){
                return true;
            }
            return false;
        }catch (Exception e){

        }
        return null;
    }
}


