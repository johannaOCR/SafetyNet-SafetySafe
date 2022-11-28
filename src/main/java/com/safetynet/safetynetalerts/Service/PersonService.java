package com.safetynet.safetynetalerts.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.safetynet.safetynetalerts.Model.Person;
import com.safetynet.safetynetalerts.Response.ChildFamilyByAddressResponse;
import com.safetynet.safetynetalerts.Response.PersonInfoByFirstnameLastnameResponse;
import com.safetynet.safetynetalerts.Util.Filter;
import com.safetynet.safetynetalerts.Util.ImportData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.*;

public class PersonService {
    private final static Logger logger = LogManager.getLogger("PersonService");
    private ImportData importData = new ImportData();
    private Filter filter = new Filter();
    private GsonBuilder builder = new GsonBuilder();


    public PersonService() throws MalformedURLException {
    }

    /*****************************
     *          TOOLS
     * ***************************/

    public List<Person> findAll(){
        List<Person> listPersons = importData.loadPerson();
        return listPersons;
    }
    public  List<Person> findAllByAddresses(Set<String> adresses){
        List<Person> persons = this.findAll();
        List<Person> personsResult = new ArrayList<>();
        for(Person person:persons){
            if (adresses.contains(person.getAddress())){
                personsResult.add(person);
            }
        }
        return  personsResult;
    }
    public  List<Person> findAllByAddress(String adress){
        List<Person> persons = this.findAll();
        List<Person> personsResult = new ArrayList<>();
        for(Person person:persons){
            if (Objects.equals(adress, person.getAddress())){
                personsResult.add(person);
            }
        }
        return  personsResult;
    }

    public int findNumberOfAdult(List<Person> persons){
        int adulte = 0;
        for (Person person: persons){
            if (person.isMajeur() == null) {
                logger.info(person.getFirstName()+ " "+ person.getLastName() + "ne possède pas de date de naissance");
            } else if (person.isMajeur()) {
                adulte++;
            }
        }
        return adulte;
    }
    public int findNumberOfChild(List<Person> persons){
        int child = 0;
        for (Person person: persons){
            if (person.isMajeur() == null) {
                logger.info(person.getFirstName()+ " "+ person.getLastName() + "ne possède pas de date de naissance");
            } else if (!person.isMajeur()) {
                child++;
            }
        }
        return child;
    }


    public List<Person> findAllByCity(String city) {
        List<Person> persons = this.findAll();
        List<Person> personsResult = new ArrayList<>();
        for (Person person : persons) {
            if(city.equals(person.getCity())){
                personsResult.add(person);
            }
        }
        return personsResult;
    }

    public Map<String,String> personInHashmap(Person person) {
        Map<String,String> personMap = new HashMap<>();
        personMap.put("firstname",person.getFirstName());
        personMap.put("lastname",person.getLastName());
        personMap.put("address",person.getAddress());
        personMap.put("email",person.getEmail());
        personMap.put("phone",person.getPhone());
        personMap.put("zip",person.getZip());
        return personMap;
    }





    /*****************************
     *          SERVICES
     * ***************************/

    public String findMailByCity(String city) throws JSONException {
    List<String> listMail = new ArrayList<>();
    Map<String,List<String>> resultFormat= new HashMap<>();
        for (Person person : this.findAllByCity(city)){
            listMail.add(person.getEmail());
        }
        resultFormat.put(city,listMail);
        if (!listMail.isEmpty()) {
            return new JSONObject(resultFormat).toString();
        } else {
            return null;
        }
    }

    public String childFamilyByAddress(String address) {
        Gson gson = builder.create();
        List<Person> persons = this.findAll();
        ArrayList<ChildFamilyByAddressResponse> resultat= new ArrayList<>();
        List<Person> family = new ArrayList<>();
        // Pour chaque person de la liste
        for (Person person : persons) {
            // Si la personne a la même adresse et est mineure
            if(address.equals(person.getAddress())) {
                family.add(person);
            }
        }
        for (Person member : family){
            if (member.isEighteenOrLess()){

                List<Person> fam = new ArrayList<>();
                for (Person famillyMember : family) {
                    if (famillyMember != member) {
                        fam.add(famillyMember);
                    }
                }
                ChildFamilyByAddressResponse child = new ChildFamilyByAddressResponse(
                        member.getLastName(),
                        member.getFirstName(),
                        member.getAge(member.medicalrecord.getBirthdate()),
                        fam
                );
                resultat.add(child);
            }
        }
        return gson.toJson(resultat);
    }

    public String findPersByFirstnameLastname (String firstname, String lastname) throws JSONException {
        List<Person> persons = this.findAll();
        List<PersonInfoByFirstnameLastnameResponse> person = new ArrayList<>();
        Gson gson = builder.create();

        for (Person member : persons) {
            if (member.getLastName().equals(lastname) && member.getFirstName().equals(firstname)
            ) {
                PersonInfoByFirstnameLastnameResponse personInfo = new PersonInfoByFirstnameLastnameResponse(member.getLastName(),
                        member.getAddress(),
                        member.getAge(member.medicalrecord.getBirthdate()),
                        member.getEmail(),
                        member.medicalrecord.getMedications(),
                        member.medicalrecord.getAllergies()
                );
                person.add(personInfo);
            }
        }
        return gson.toJson(person);

    }

}


