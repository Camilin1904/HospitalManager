package model;

import exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;
import java.util.*;

import com.google.gson.Gson;

@SuppressWarnings("unchecked")
public class HospitalController {

    private IdTable<Patient, String> patientDB;
    private IdTable<Patient, String> lab;
    private PriorityLine<Patient>[] patientLine;
    private NodeBackup<BackUp, String> undo;
    private String path;
    private Gson gson;
    private ArrayList<Patient> patients = new ArrayList<>();

    public HospitalController(String path) throws NoSuchPathException {
        lab = new IdTable<>(51);
        patientDB = new IdTable<>(501);
        patientLine = new PriorityLine[3];
        for (int i = 0; i < 3; i++) patientLine[i] = new PriorityLine<>(i + 1);
        this.path = path;
        if (!loadDataBase()) throw new NoSuchPathException("Base de datos no encontrada");
        else {
            AutoUnqueuer unQ = new AutoUnqueuer();
            unQ.unQueueAuto(this);
        }
    }

    //Database module, By: Santiago
    public void registerPatient(String name, String surname, String id, String gender, int age, int[] ailment) {

        Patient toAdd = new Patient(name, surname, id, gender, age, checkAilments(ailment));
        register(id, toAdd);
    }

    public void registerPatient(String name, String surname, String id, String gender, int age) {

        Patient toAdd = new Patient(name, surname, id, gender, age);
        register(id, toAdd);
    }

    public void register(String id, Patient toAdd) {
        boolean exists = false;
        for (Patient x : patients) {
            if (x.getId().equals(toAdd.getId())) {
                x = toAdd;
                exists = true;
            }
        }
        if (!exists) {
            patients.add(toAdd);
        }
        patientDB.insert(id, toAdd);
    }


    public ArrayList<Ailment> checkAilments(int[] a) {

        ArrayList<Ailment> ailments = new ArrayList<>();
        for (int x : a) {
            switch (x) {

                case 1:
                    ailments.add(Ailment.CANCER);
                    break;

                case 2:
                    ailments.add(Ailment.IMMUNE_VULNERABILITY);
                    break;

                case 3:
                    ailments.add(Ailment.HEART_RISK);
                    break;

                case 4:
                    ailments.add(Ailment.PREGNANT);
                    break;

                case 5:
                    ailments.add(Ailment.POST_SURGERY);
                    break;

                case 6:
                    ailments.add(Ailment.PHYSICAL_DISABILITY);
                    break;

                case 7:
                    ailments.add(Ailment.FEVER);
                    break;

                case 8:
                    ailments.add(Ailment.DIARRHEA);
                    break;

                case 9:
                    ailments.add(Ailment.PAIN);
                    break;
            }
        }
        return ailments;
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
                                case "CANCER":
                                    ailments.add(Ailment.CANCER);
                                    break;

                                case "IMMUNE_VULNERABILITY":
                                    ailments.add(Ailment.IMMUNE_VULNERABILITY);
                                    break;

                                case "HEART_RISK":
                                    ailments.add(Ailment.HEART_RISK);
                                    break;

                                case "PREGNANT":
                                    ailments.add(Ailment.PREGNANT);
                                    break;

                                case "POST_SURGERY":
                                    ailments.add(Ailment.POST_SURGERY);
                                    break;

                                case "PHYSICAL_DISABILITY":
                                    ailments.add(Ailment.PHYSICAL_DISABILITY);
                                    break;

                                case "FEVER":
                                    ailments.add(Ailment.FEVER);
                                    break;

                                case "DIARRHEA":
                                    ailments.add(Ailment.DIARRHEA);
                                    break;
                                case "PAIN":
                                    ailments.add(Ailment.PAIN);
                                    break;
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

        Node<Patient, String> toAdd = lab.search2(patientId);
        
        if(toAdd==null){
            throw new NoSuchElementException("Patient is not inside the laboratory.");
        }
        undo.push(new BackUp(patientLine[unit - 1].clone()));
        patientLine[unit - 1].insert(toAdd.getValue(), toAdd.getValue().getAilmentPriority());


    }

    public void unqueuePatient(int unit) {
        undo.push(new BackUp(patientLine[unit - 1].clone()));
        patientLine[unit - 1].heapExtractMax();
    }

    public void autoUnqueuePatient(int unit){
        patientLine[unit-1].heapExtractMax();
    }

    public String displayQueue(int unit) {
        String out = "";
        PriorityLine<Patient> arr = patientLine[unit - 1].clone();
        int heapSize = arr.getHeapSize();
        while (heapSize != 0) {
            out += arr.heapExtractMax().getName();
            heapSize--;
        }
        return out;
    }

//Lab module, By: Camilo    

    /**
     * A patient is added to the list of patients that are inside the laboratory.
     *
     * @param patientId Is the id of the patient that is to be added.
     * @throws NoSuchElementException This exception is trown in case of a non-existent patient being inputed.
     */
    public void addPatientToLab(String patientId) throws NoSuchElementException, PatientAlreadyRegisteredException{

        Patient toAdd = patientDB.search(patientId);
        if(toAdd==null) throw new NoSuchElementException("The specified patient does not exist in the database.");//The insertion is cancelled if the patient does not exist
        else if(lab.search(patientId)!=null) throw new PatientAlreadyRegisteredException("Patient already registered");
        else{
            undo.push(new BackUp(lab.clone()));//takes the snapshot of the lab table before the patient is added
            lab.insert(patientId, toAdd);
        }
    }


    /**
     * Takes the patient out of the table of people inside the lab
     * @param patientId The id of the patient to be dispatched
     */


    /**
     * Takes the patient out of the table of people inside the lab
     *
     * @param patientId The id of the patient to be dispatched
     */
    public void dispatchPatient(String patientId) {
        int i;
        BackUp b = new BackUp(lab.clone());

        for (i = 0; i < 3; i++) {
            if (patientLine[i].takeOut(lab.search2(patientId).getValue()))
                b.setUnit(patientLine[i]);//takes the patient out of the line its in, as it can only be in one, that one line is added to the backup Stack
        }
        undo.push(b);//adds a new backup
        lab.delete(patientId);//tekes the patient out of the lab 

    }


    /**
     * Makes a visual representation ordered alphabetically of the patients in the facilities of the laboratory
     *
     * @return A visual representation ordered alphabetically of the patients in the facilities of the laboratory
     */
    public String displayPeopleInFacility() {
        String patients = " ---------------------------------------- ";//Separator of patients 

        ArrayList<Patient> all = new ArrayList<>();//ArrayList to hold all patients in the lab
        for (Patient pt : lab) {//Takes the patients from the table to this
            all.add(pt);
        }

        all.sort(new Comparator<Patient>() {//Sorts the patients alphabetially
            @Override
            public int compare(Patient o1, Patient o2) {
                return (o1.getName() + " " + o1.getSurName()).compareTo(o2.getName() + " " + o2.getSurName());
            }
        });

        for (Patient pt : all) {//Makes the string
            patients += pt.toString() + "\n ---------------------------------------- ";
        }

        return patients;
    }


//Stack module, By: Camilo

    /**
     * Uses the data in the backup modules set up beforehand to revert an action done by the user.
     *
     * @return whether or not there was something to go back to.
     */
    public String unDo() {
        try {
            BackUp bUp = undo.poll();//gets the backup

            if (bUp.getUnit() != null)
                patientLine[bUp.getUnit().getUnit() - 1] = bUp.getUnit();//checks if there were any changes to the lines, as there cannot be changes to more than one lien at a time, that is all that he bakup saves.

            if (bUp.getLab() != null) lab = bUp.getLab();//checks if there were any chages to the lab's list

            return "Succesfully undid changes";//as there have to had been some changes, it is ensured that the changes were undone.

        } catch (NoSuchFieldException e) {//if there is nothing to go back to, an exception is trown
            return e.getMessage();//so it is caught, and the message is returned.
        }
    }


}



class AutoUnqueuer {
    private final ScheduledExecutorService scheduler =
      Executors.newScheduledThreadPool(1);

    public void unQueueAuto(HospitalController hosp) {
      final Runnable unqueuer = new Runnable() {
        public void run() { hosp.autoUnqueuePatient(1);
            hosp.autoUnqueuePatient(2);
            hosp.autoUnqueuePatient(3);
        }
      };
      final ScheduledFuture<?> beeperHandle =
        scheduler.scheduleAtFixedRate(unqueuer, 120, 120, SECONDS);
      scheduler.schedule(new Runnable() {
        public void run() { beeperHandle.cancel(true); }
      }, 60 * 60, TimeUnit.MINUTES);
    }
  }
