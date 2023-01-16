package com.safetynet.safetynetalerts.response;

import java.util.List;

public class PersonInfoByAddressByStationNumber {

    String lastname;
    int age;
    String phone;
    List<String> medication;
    List<String> allergies;

    public PersonInfoByAddressByStationNumber(String lastname, int age, String phone, List<String> medication, List<String> allergies) {
        this.lastname = lastname;
        this.age = age;
        this.phone = phone;
        this.medication = medication;
        this.allergies = allergies;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getMedication() {
        return medication;
    }

    public void setMedication(List<String> medication) {
        this.medication = medication;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }
}