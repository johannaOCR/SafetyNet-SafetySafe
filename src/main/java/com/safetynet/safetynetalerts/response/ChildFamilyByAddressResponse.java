package com.safetynet.safetynetalerts.response;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public class ChildFamilyByAddressResponse {
    String lastname;
    String firstname;
    int age;
    List<Person> family;

    public ChildFamilyByAddressResponse(String lastname, String firstname, int age, List<Person> family) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.age = age;
        this.family = family;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Person> getFamily() {
        return family;
    }

    public void setFamily(List<Person> family) {
        this.family = family;
    }
}
