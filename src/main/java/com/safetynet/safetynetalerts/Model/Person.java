package com.safetynet.safetynetalerts.Model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Person {
        public  String firstName;
        public  String lastName;
        public  String phone;
        public  String zip;
        public MedicalRecord medicalrecord;
        public  String address;
        public  String city;
        public  String email;


        public Person(String firstName, String lastName, String phone, String zip, MedicalRecord medicalrecord, String address, String city, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.zip = zip;
            this.medicalrecord = medicalrecord;
            this.address = address;
            this.city = city;
            this.email = email;
        }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public MedicalRecord getMedicalrecord() {return medicalrecord;}

    /**
     * Check if is this person is majeur or not
     * @return true is majeur, false otherwise
     */
    public Boolean isMajeur () {
        Date dateNow = new Date(System.currentTimeMillis());
        Boolean result = false;
        long between = dateNow.getTime() - medicalrecord.birthdate.getTime();
        if ((between/(24*60*60*1000)) > 6570 ){
            result = true;
        }
        return result;
    }

    /**
     * check if this person is eighteen years old or less
     * @return true if the person is eighteen years old or less, false otherwise
     */
    public Boolean isEighteenOrLess () {
        Date dateNow = new Date(System.currentTimeMillis());
        try {
            long between = dateNow.getTime() - medicalrecord.birthdate.getTime();
            if ((between/(24*60*60*1000)) > 6570 ){
                return false;
            }
            return true;
        }catch (Exception e){

        }
        return null;
    }

    /**
     * Give the age of this person based on the given birthdate
     * @param birthDate The birthdate as a Date object
     * @return the age
     */
    public int getAge(Date birthDate) {
        LocalDate birthdate = convertToLocalDateInMillisecond(birthDate);
        Date dateNow = new Date(System.currentTimeMillis());
        LocalDate localDateNow = convertToLocalDateInMillisecond(dateNow);
        if ((birthDate != null) && (dateNow != null)) {
            return Period.between(birthdate, localDateNow).getYears();
        } else {
            return 0;
        }
    }

    /**
     * Convert the given date in a local date format in millisecond
     * @param dateToConvert the date to convert as a date object
     * @return a localDate in millisecond
     */
    public static LocalDate convertToLocalDateInMillisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", zip='" + zip + '\'' +
                ", medicalrecord=" + medicalrecord +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setMedicalrecord(MedicalRecord medicalrecord) {
        this.medicalrecord = medicalrecord;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}







