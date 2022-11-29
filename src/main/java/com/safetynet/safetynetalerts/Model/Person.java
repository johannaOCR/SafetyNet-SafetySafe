package com.safetynet.safetynetalerts.Model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class Person {
        public final String firstName;
        public final String lastName;
        public final String phone;
        public final String zip;
        public MedicalRecord medicalrecord;
        public final String address;
        public final String city;
        public final String email;


        public static class PersonBuilder {
            private String firstName;
            private String lastName;
            private String phone;
            private String zip;
            private String address;
            private String city;
            private String email;
            private MedicalRecord medicalrecord;

            public PersonBuilder() {
            }


            public PersonBuilder firstName(String firstName) {
                this.firstName = firstName;
                return this;
            }

            public PersonBuilder lastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public PersonBuilder phone(String phone) {
                this.phone = phone;
                return this;
            }

            public PersonBuilder zip(String zip) {
                this.zip = zip;
                return this;
            }

            public PersonBuilder address(String address) {
                this.address = address;
                return this;
            }

            public PersonBuilder city(String city) {
                this.city = city;
                return this;
            }

            public PersonBuilder email(String email) {
                this.email = email;
                return this;
            }
            public PersonBuilder medicalRecord(MedicalRecord medicalRecord) {
                this.medicalrecord = medicalRecord;
                return this;
            }

            public Person build() {
                return new Person(firstName, lastName, phone, zip, medicalrecord, address, city, email);
            }
        }

        private Person(String firstName, String lastName, String phone, String zip, MedicalRecord medicalrecord, String address, String city, String email) {
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
}







