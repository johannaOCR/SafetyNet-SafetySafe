package com.safetynet.safetynetalerts.response;

import java.util.List;

public class PersonInfoByFirstnameLastnameResponse {
    String lastname;
    String address;
    int age;
    String email;
    List<String> medication;
    List<String> allergies;

    public PersonInfoByFirstnameLastnameResponse(String lastname, String address, int age, String email, List<String> medication, List<String> allergies) {
        this.lastname = lastname;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medication = medication;
        this.allergies = allergies;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
