package com.safetynet.safetynetalerts.Model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            public PersonBuilder firstName(MedicalRecord medicalRecord) {
                this.firstName = firstName;
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
    public Boolean isMajeur () {
        Date dateNow = new Date(System.currentTimeMillis());
        try {
            Date Formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(String.valueOf(this.medicalrecord.birthdate));
            long between = dateNow.getTime() - Formatter.getTime();
            if (between/(1000*60*60*24) > 6570 ){
                return true;
            }
            return false;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String toString() {
        return "\n" + "Person {"
                + "\n" +"firstName = '" + firstName + '\'' + "\n"+
                "lastName = '" + lastName + '\'' + "\n"+
                "phone = '" + phone + '\'' + "\n"+
                "zip = '" + zip + '\'' + "\n"+
                "medicalrecord ==> " + medicalrecord.toString() + "\n"+
                "address = '" + address + '\'' + "\n"+
                "city = '" + city + '\'' + "\n"+
                "email = '" + email + '\'' + "\n"+
                '}' + "\n" ;
    }
}







