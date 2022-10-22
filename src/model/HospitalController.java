package model;

import exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.google.gson.Gson;

@SuppressWarnings({"unchecked","deprecated"})
public class HospitalController {

    private IdTable<Patient, String> patientDB;
    private IdTable<Patient, String> lab;
    private PriorityLine<Patient, String>[] patientLine;
    private NodeBackup<Node<Patient,String>, String> undo;
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
        if (!loadDataBase()) throw new NoSuchPathException("Base de datos no encontrada");
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

    public boolean loadDataBase() {

        //ASIGNAMOS EL ARCHIVO A UNA VARIABLE
        
        File file = new File(".\\database\\Database.txt");
        System.out.println(file.getAbsolutePath());
        System.out.println(file.exists());
        //COMPROBAMOS QUE EL ARCHIVO EXISTA
        if (file.exists()) {

            try {

                FileInputStream fis = new FileInputStream(file);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(fis)
                );

                String line = reader.readLine();

                String[] data = line!=null? line.split("}"):null;
                System.out.println(Arrays.toString(data));

                for (int e=0;e<data.length&&data[e].length()>2;e++){
                    //AQUI SE "DESARMA" EL JSON
                    String w = data[e];
                    w = w.replace(" [", "");
                    w = w.replace("]", "");
                    w = w.replace("\\u0027", "");
                    w = e>0?w.substring(1,w.length()-1):w;
                    String[] info = w.split(",");
                    String name = info[0].substring(10,info[0].length()-1);
                    String surname = info[1].substring(11,info[1].length()-1);
                    String id = info[2].substring(6,info[2].length()-1);
                    System.out.println(id);
                    String gender = info[3].substring(10,info[3].length()-1);
                    int age = Integer.parseInt(info[4].substring(6,info[4].length()));
                    ArrayList<Ailment> ailments = new ArrayList<>();
                    //EN CASO DE QUE TENGA 1 O MAS CONDICIONES, SE RECORRERA LO QUE RESTA DEL ARREGLO info Y
                    //SE AGREGARAN EN UN ARRAYLIST DE AILMENTS
                    System.out.println(info.length );
                    if (info.length > 5) {
                        for (int i = 5; i < info.length; i++) {
                            System.out.println(info[i]);
                            System.out.println(info[i].length()>12);
                            String o = info[i].length()>12? info[i].substring(13,info[i].length()-1):"";
                            switch (o) {
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
        toAdd.setProcedence(unit+"");
        undo.push(toAdd);
        patientLine[unit - 1].insert(toAdd, toAdd.getValue().getAilmentPriority());


    }

    public void unqueuePatient(int unit) {
        Node<Patient,String> p = patientLine[unit - 1].heapExtractMax();
        p.setProcedence(unit+"");
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
        ArrayList<Node<Patient,String>> arr = patientLine[unit - 1].getInternalArrayList();

        for (Node<Patient,String> patient : arr) {
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
    public void addPatientToLab(String patientId) throws NoSuchElementException, PatientAlreadyRegisteredException{

        Node<Patient,String> toAdd = patientDB.search2(patientId);
        if(toAdd==null) throw new NoSuchElementException("The specified patient does not exist in the database.");//The insertion is cancelled if the patient does not exist
        else if(lab.search(patientId)!=null) throw new PatientAlreadyRegisteredException("Patient already registered");
        else{
            toAdd.setProcedence("lab");
            undo.push(toAdd);//takes the snapshot of the lab table before the patient is added
            lab.insert(toAdd);
            lab2.add(toAdd.getValue());
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

        ArrayList<Patient> all = new ArrayList<>();//ArrayList to hold all patients in the lab
        for (Patient pt : lab2) {//Takes the patients from the table to this
            all.add(pt);
        }

        all.sort(new Comparator<Patient>() {//Sorts the patients alphabetially
            @Override
            public int compare(Patient o1, Patient o2) {
                return (o1.getName() + " " + o1.getSurName()).compareTo(o2.getName() + " " + o2.getSurName());
            }
        });

        for (Patient pt : all) {//Makes the string
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
            Node<Patient,String> bUp = undo.poll();//gets the backup
            String s = bUp.getProcedence();
            if(bUp.getDel()){
                bUp.setDel(true);
                try{
                    int i = Integer.parseInt(s.charAt(0)+"") - 1;
                    patientLine[i].increaseAllKeys(-1-extraPriority);//checks if there were any changes to the lines, as there cannot be changes to more than one lien at a time, that is all that he bakup saves.
                    patientLine[i].insert(bUp, bUp.getValue().getAilmentPriority());
                }
                catch(NumberFormatException n){}
    
                if (s.length()>1) {
                    lab.insert(bUp);//checks if there were any chages to the lab's list
                    lab2.add(bUp.getValue());
                }
            }
            else{
                try{
                    int i = Integer.parseInt(s.charAt(0)+"") - 1;
                    patientLine[i].takeOut(bUp.getValue());
                }
                catch(NumberFormatException n){}
    
                if (s.length()>1) {
                    lab.delete(bUp.getKey());//checks if there were any chages to the lab's list
                    lab2.remove(bUp.getValue());
                }
            }
            return "Succesfully undid changes";//as there have to had been some changes, it is ensured that the changes were undone.


        } catch (NoSuchFieldException e) {//if there is nothing to go back to, an exception is trown
            return e.getMessage();//so it is caught, and the message is returned.
        }
    }


}