package model;

import exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.google.gson.Gson;

@SuppressWarnings({"unchecked", "deprecated"})
public class HospitalController {

    private IdTable<Patient, String> patientDB;
    private IdTable<Patient, String> lab;
    private PriorityLine<Patient, String>[] patientLine;
    private NodeBackup<Node<Patient, String>, String> undo;
    private Gson gson;
    private ArrayList<Patient> patients = new ArrayList<>();
    private int extraPriority;
    private ArrayList<Patient> lab2;

    public HospitalController() throws NoSuchPathException {
        lab = new IdTable<>(51);
        lab2 = new ArrayList<>();
        patientDB = new IdTable<>(501);
        patientLine = new PriorityLine[3];
        undo = new NodeBackup<>();
        for (int i = 0; i < 3; i++) patientLine[i] = new PriorityLine<>(i + 1);
        if (!loadDataBase()) throw new NoSuchPathException("Database was not found.");
        extraPriority = 0;
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
        gson = new Gson();
        String json = gson.toJson(patients);
        try {
            File file = new File(".\\database\\Database.txt");
            System.out.println(file.getAbsolutePath());
            FileOutputStream fos = new FileOutputStream(file);


            //AQUI ESCRIBIRIAMOS TODOS LOS DATOS DEL JSON EN UN ARCHIVO LLAMADO DATABASE DE TIPO TXT
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean loadDataBase(){
        try {
            File file = new File(".\\database\\Database.txt");
            FileInputStream fis = new FileInputStream(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String json = "";
            String line;
            if((line=reader.readLine())!=null){
                json= line;
            }
            fis.close();

            Gson gson = new Gson();
            Patient[] peopleFromJson = gson.fromJson(json, Patient[].class);

            for(Patient p : peopleFromJson){
                patientDB.insert(p.getId(), p);
                patients.add(p);
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    //Queues module, By: Mateo
    public void addToQueue(String patientId, int unit) throws NoSuchElementException {

        Node<Patient, String> toAdd = lab.search2(patientId);

        if (toAdd == null) {
            throw new NoSuchElementException("Patient is not inside the laboratory.");
        }
        toAdd.setProcedence(unit + "");
        undo.push(toAdd);
        patientLine[unit - 1].insert(toAdd, toAdd.getValue().getAilmentPriority());


    }

    public void unqueuePatient(int unit) {
        Node<Patient, String> p = patientLine[unit - 1].heapExtractMax();
        p.setProcedence(unit + "");
        p.setDel(true);
        undo.push(p);

        extraPriority = 0;
    }

    public boolean autoUnqueuePatient(int unit){
        if(patientLine[unit-1].heapExtractMax()!=null) {
            extraPriority++;
            return true;
        }
        else return false;
    }

    public String displayQueue(int unit) {
        String out = "";
        ArrayList<Node<Patient, String>> arr = patientLine[unit - 1].getInternalArrayList();

        for (Node<Patient, String> patient : arr) {
            out += "\n ---------------------------------------- \n" + patient.getValue().toString() + "\n ---------------------------------------- ";
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
    public void addPatientToLab(String patientId) throws NoSuchElementException, PatientAlreadyRegisteredException {

        Node<Patient, String> toAdd = patientDB.search2(patientId);
        if (toAdd == null)
            throw new NoSuchElementException("The specified patient does not exist in the database.");//The insertion is cancelled if the patient does not exist
        else if (lab.search(patientId) != null)
            throw new PatientAlreadyRegisteredException("Patient already registered");
        else {
            toAdd.setProcedence("lab");
            undo.push(toAdd);//takes the snapshot of the lab table before the patient is added
            lab.insert(toAdd);
            lab2.add(toAdd.getValue());
        }
    }

    /**
     * Takes the patient out of the table of people inside the lab
     *
     * @param patientId The id of the patient to be dispatched
     */
    public void dispatchPatient(String patientId) throws NullPointerException{
            int i;
            String s = "";
            Node<Patient,String> p = lab.search2(patientId);
            for (i = 0; i < 3; i++) {
                if (patientLine[i].takeOut(p.getValue()))
                    s = ""+(i+1);//takes the patient out of the line its in, as it can only be in one, that one line is added to the backup Stack
            }
            p.setProcedence(s+"lab");
            p.setDel(true);
            lab2.remove(p.getValue());
            undo.push(p);//adds a new backup
            lab.delete(patientId);//tekes the patient out of the lab 
    }


    /**
     * Makes a visual representation ordered alphabetically of the patients in the facilities of the laboratory
     *
     * @return A visual representation ordered alphabetically of the patients in the facilities of the laboratory
     */
    public String displayPeopleInFacility() {
        String patients = " ---------------------------------------- \n";//Separator of patients 

        lab2.sort(new Comparator<Patient>() {//Sorts the patients alphabetially
            @Override
            public int compare(Patient o1, Patient o2) {
                return (o1.getName() + " " + o1.getSurName()).compareTo(o2.getName() + " " + o2.getSurName());
            }
        });

        for (Patient pt : lab2) {//Makes the string
            patients += pt.toString() + "\n ---------------------------------------- \n";
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
            Node<Patient, String> bUp = undo.poll();//gets the backup
            String s = bUp.getProcedence();
            if (bUp.getDel()) {
                bUp.setDel(true);
                try {
                    int i = Integer.parseInt(s.charAt(0) + "") - 1;
                    patientLine[i].increaseAllKeys(-1 - extraPriority);//checks if there were any changes to the lines, as there cannot be changes to more than one lien at a time, that is all that he bakup saves.
                    patientLine[i].insert(bUp, bUp.getValue().getAilmentPriority());
                } catch (NumberFormatException n) {
                }

                if (s.length() > 1) {
                    lab.insert(bUp);//checks if there were any changes to the lab's list
                    lab2.add(bUp.getValue());
                }
            } else {
                try {
                    int i = Integer.parseInt(s.charAt(0) + "") - 1;
                    patientLine[i].takeOut(bUp.getValue());
                } catch (NumberFormatException n) {
                }

                if (s.length() > 1) {
                    lab.delete(bUp.getKey());//checks if there were any changes to the lab's list
                    lab2.remove(bUp.getValue());
                }
            }
            return "Successfully undid changes";//as there have to had been some changes, it is ensured that the changes were undone.


        } catch (NoSuchFieldException e) {//if there is nothing to go back to, an exception is thrown
            return e.getMessage();//so it is caught, and the message is returned.
        }
    }
}