package model;

import exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import com.google.gson.Gson;

@SuppressWarnings("unchecked")
public class HospitalController {

    private IdTable patientDB;
    private IdTable lab;
    private PriorityLine<Patient, String>[] patientLine;
    private NodeBackup undo;
    private String path;
    private Gson gson;
    private ArrayList<Patient> patients = new ArrayList<>();

    public HospitalController(String path) throws NoSuchPathException {
        lab = new IdTable(50);
        patientDB = new IdTable(500);
        patientLine = new PriorityLine[3];
        for (int i = 0; i < 3; i++) patientLine[i] = new PriorityLine<>(i + 1);
        this.path = path;
        if (!loadDataBase()) throw new NoSuchPathException("Base de datos no encontrada");
    }

    //Database module, By: Santiago
    public void registerPatient(String name, String surname, String id, String gender, int age, ArrayList<Ailment> ailment) throws PatientAlreadyRegisteredException {

        Patient toAdd = new Patient(name, surname, id, gender, age, ailment);
        patientDB.insert(id, toAdd);
        patients.add(toAdd);
    }

    public void updateDataBase() {

        //TRAEMOS LA INFORMACION DE TODOS LOS PACIENTES QUE TENEMOS EN EL ARRAYLIST DE PACIENTES Y LO VOLVEMOS UN JSON
        String json = gson.toJson(patients);
        try {
            FileOutputStream fos = new FileOutputStream(new File("DataBase.txt"));
            //AQUI ESCRIBIRIAMOS TODOS LOS DATOS DEL JSON EN UN ARCHIVO LLAMADO DATABASE DE TIPO TXT
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadDataBase() {

        //ASIGNAMOS EL ARCHIVO A UNA VARIABLE
        File file = new File("DataBase.txt");

        //COMPROBAMOS QUE EL ARCHIVO EXISTA
        if (file.exists()) {

            try {

                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(fis)
                );

                String line;
                while ((line = reader.readLine()) != null) {

                    //AQUI SE "DESARMA" EL JSON
                    String w = line;
                    w = w.replace(" [", "");
                    w = w.replace("]", "");
                    w = w.replace("\\u0027", "");
                    String[] info = w.split(",");
                    String name = info[0];
                    String surname = info[1];
                    String id = info[2];
                    String gender = info[3];
                    int age = Integer.parseInt(info[4]);
                    ArrayList<Ailment> ailments = new ArrayList<>();
                    //EN CASO DE QUE TENGA 1 O MAS CONDICIONES, SE RECORRERA LO QUE RESTA DEL ARREGLO info Y
                    //SE AGREGARAN EN UN ARRAYLIST DE AILMENTS
                    if (info.length > 5) {
                        for (int i = 5; i < info.length - 1; i++) {

                            switch (info[i]) {
                                case "CANCER" -> {
                                    ailments.add(Ailment.CANCER);
                                }

                                case "IMMUNE_VULNERABILITY" -> {
                                    ailments.add(Ailment.IMMUNE_VULNERABILITY);
                                }

                                case "HEART_RISK" -> {
                                    ailments.add(Ailment.HEART_RISK);
                                }

                                case "PREGNANT" -> {
                                    ailments.add(Ailment.PREGNANT);
                                }

                                case "POST_SURGERY" -> {
                                    ailments.add(Ailment.POST_SURGERY);
                                }

                                case "PHYSICAL_DISABILITY" -> {
                                    ailments.add(Ailment.PHYSICAL_DISABILITY);
                                }

                                case "FEVER" -> {
                                    ailments.add(Ailment.FEVER);
                                }

                                case "DIARRHEA" -> {
                                    ailments.add(Ailment.DIARRHEA);
                                }

                                case "PAIN" -> {
                                    ailments.add(Ailment.PAIN);
                                }
                            }

                        }
                    }
                    //SE CREA UN OBJETO TEMPORAL DE PACIENTE
                    Patient temp = new Patient(name, surname, id, gender, age, ailments);
                    //AQUI SE ESTARIA AGREGANDO A LA HASHTABLE EL PACIENTE EN CUESTION
                    patientDB.insert(id, temp);
                    //SE AGREGA EL PACIENTE AL ARRAYLIST DE PACIENTES
                    patients.add(temp);
                }
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //SI EXISTE, RETORNAMOS TRUE
            return true;
        } else {
            //SI NO EXISTE, RETORNAMOS FALSE
            return false;
        }


    }


    //Queues module, By: Mateo
    public void addToQueue(String patientId, int unit) throws NoSuchElementException {


    }

    public void unqueuePatient(String patientId, int unit) {

    }

    public String displayQueue(int unit) {


        return null;
    }


    //Lab module, By: Camilo
    public void addPatientToLab(String patientId) {

    }

    public void dispathPatient() {

    }

    public String displayPeopleInFacility() {

        return null;
    }


    //Stack module, By: Camilo
    public boolean unDo() {

        return false;
    }


}
