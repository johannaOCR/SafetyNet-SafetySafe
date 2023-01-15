package com.safetynet.safetynetalerts.Util;


import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.safetynet.safetynetalerts.Model.FireStation;
import com.safetynet.safetynetalerts.Model.MedicalRecord;
import com.safetynet.safetynetalerts.Model.Person;
import org.apache.logging.log4j.LogManager;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



public class ImportData {

    private final URL url  = new URL("https://s3-eu-west-1.amazonaws.com/course.oc-static.com/projects/DA+Java+EN/P5+/data.json");
    private final static org.apache.logging.log4j.Logger logger = LogManager.getLogger("ImportData") ;


    public ImportData() throws MalformedURLException {}

    /**
     * Récupère les données depuis le fichier distant web
     * @return le fichier des données de type Any
     */
    public Any loadFileByUrl()   {
        String Text = null;
        InputStream urlLoad = null;
        try {
            urlLoad = url.openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlLoad, StandardCharsets.UTF_8));
            Text = read(rd);
        } catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            if(urlLoad != null) {
                try {
                    urlLoad.close();
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        assert Text != null;
        Any file = JsonIterator.deserialize(Text);
        return file;
    }

    /**
     * Reader du fichier distant web. Permet de récupérer toutes les données du fichier.
     * @param re
     * @return Le fichier distant web au format String
     * @throws IOException
     */
    public String read(Reader re) throws IOException {
        StringBuilder str = new StringBuilder();
        int temp;
        do {
            temp = re.read();
            str.append((char)temp);

        } while (temp != -1);

        // On soustrait les caractères en dehors du tableau
        int index = str.toString().lastIndexOf("}");
        String result = str.toString().substring(0,index+1);
        return result;
    }

    /**
     * Fonction instanciant les objets de type Person et de type Médical Record contenus dans le fichier distant
     * @return Une arraylist de Person
     */
    public  List<Person> loadPerson(){
        Any file = this.loadFileByUrl();
        return loadPerson(file);
    }
    /**
     * Fonction instanciant les objets de type Person  et de type Médical Record contenus dans le fichier distant
     * @return Une arraylist de Person
     */
    private List<Person> loadPerson(Any file){
        Any personAny = file.get("persons");
        List<Person> persons = new ArrayList<>();
        personAny.forEach(a -> persons.add(
                new Person(
                        a.get("firstName").toString(),
                        a.get("lastName").toString(),
                        a.get("phone").toString(),
                        a.get("zip").toString(),
                        null,
                        a.get("address").toString(),
                        a.get("city").toString(),
                        a.get("email").toString()
                        )
        ));
        List<MedicalRecord> medicalRecordsList = this.loadMedicalRecord(file);
        for (MedicalRecord medicalRecord : medicalRecordsList) {
            for (Person person : persons) {
                if((Objects.equals(medicalRecord.getFirstname(), person.getFirstName())) & (Objects.equals(medicalRecord.getLastname(), person.getLastName()))){
                    person.medicalrecord = medicalRecord;
                }
            }
        }
        return persons;
    }

    /**
     *  Fonction instanciant les objets de type FireStation contenus dans le fichier distant
     * @return Une Arraylist de FireStation
     * @throws IOException
     */
    public List<FireStation> loadFirestations() throws IOException {
        Any file = loadFileByUrl();
        return this.loadFirestations(file);
    }

    /**
     *  Fonction instanciant les objets de type FireStation contenus dans le fichier distant
     * @return Une Arraylist de FireStation
     */
    private List<FireStation> loadFirestations(Any file){
        Map<String, FireStation> fireStationMap = new HashMap<>();
        Any fireStationAny = file.get("firestations");
        fireStationAny.forEach(anyStation -> {
            fireStationMap.compute(anyStation.get("station").toString(),
                    (k, v) -> v == null ?
                            new FireStation(anyStation.get("station").toInt())
                                    .addAddress(anyStation.get("address").toString()) :
                                    v.addAddress(anyStation.get("address").toString()));
        });
        List<FireStation> fireStations = new ArrayList<>(fireStationMap.values());
        return fireStations;
    }

    /**
     * Fonction instanciant des objets du type MedicalRecord à partir du fichier distant
     * @param file
     * @return
     */
    public List<MedicalRecord> loadMedicalRecord(Any file){
        Any medicalRecordAny = file.get("medicalrecords");
        List<MedicalRecord> medicalRecords = new ArrayList<>();
        medicalRecordAny.forEach(a -> {
            try {
                List<String> medication = new ArrayList<>();
                List<String> allergies = new ArrayList<>();
                for (Any medi : a.get("medications").asList()) {
                    medication.add(medi.toString());
                }
                for (Any allergie : a.get("allergies").asList()) {
                    allergies.add(allergie.toString());
                }
                medicalRecords.add(new MedicalRecord(
                        a.get("firstName").toString(),
                        a.get("lastName").toString(),
                        medication,
                        allergies,
                        new SimpleDateFormat("dd/MM/yyyy").parse(a.get("birthdate").toString())
                ));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return medicalRecords;
    }


}
