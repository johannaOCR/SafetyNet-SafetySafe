package com.safetynet.safetynetalerts.Response;

public class PersonByFirestationPersonInfoResponse {
    String lastname;
    String firstname;
    String address;
    String phone;

    public PersonByFirestationPersonInfoResponse(String lastname, String firstname, String address, String phone) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.address = address;
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
