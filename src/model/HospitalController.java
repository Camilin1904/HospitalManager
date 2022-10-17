package src.model;

import java.util.*;

import src.exceptions.*;

@SuppressWarnings("unchecked")
public class HospitalController {

    private IdTable patientDB;
    private IdTable lab;
    private PriorityLine<Patient, String>[] patientLine;
    private NodeBackup undo;
    private String path;

    public HospitalController(String path) throws NoSuchPathException{
        lab = new IdTable(50);
        patientDB = new IdTable(500);
        patientLine = new PriorityLine[3];
        for (int i=0; i<3; i++) patientLine[i] = new PriorityLine<>(i+1);
        this.path = path;
        if(!loadDataBase()) throw new NoSuchPathException("Base de datos no encontrada");
    }
    
//Database module, By: Santiago
    public boolean registerPatient(String name, String surname, String id, String gender, int age){

        return false;
    }

    public boolean loadDataBase(){

        return false;
    }

    public void uptadeDataBase(){

    }

    
//Queues module, By: Mateo
    public void addToQueue(String patientId, int unit) throws NoSuchElementException{

        Node<Patient, String> toAdd = patientDB.search2(patientId);

        
    }

    public void unqueuePatient(String patientId, int unit){

    }
    public String displayQueue(int unit){


        return null;
    }

    
    
//Lab module, By: Camilo    
    /**
     * A patient is added to the list of patients that are inside the laboratory.
     * @param patientId Is the id of the patient that is to be added.
     * @throws NoSuchElementException This exception is trown in case of a non existent patient being inputed.
     */
    public void addPatientToLab(String patientId) throws NoSuchElementException{

        Patient toAdd = patientDB.search(patientId);
        if(toAdd==null) throw new NoSuchElementException("The specified patient does not exist in the database.");//The insertion is cancelled if the patient does not exist
        else{
            undo.push(new Node<>(new BackUp(lab.clone()), patientId));//takes the snapshot of the lab table before the patient is added
            lab.insert(patientId, toAdd);
        } 
    }




    /**
     * Takes the patient out of the table of people inside the lab
     * @param patientId The id of the patient to be dispatched
     */
    public void dispatchPatient(String patientId){
        int i;
        BackUp b = new BackUp(lab.clone());

        for (i=0; i<3; i++){
            if(patientLine[i].takeOut(lab.search2(patientId)))b.setUnit(patientLine[i]);//takes the patient out of the line its in, as it can only be in one, that one line is added to the backup Stack
        }
        undo.push(new Node<>(b, patientId));//adds a new backup
        lab.delete(patientId);//tekes the patient out of the lab 

    }





    /**
     * Makes a visual representation ordered alphabetically of the patients in the facilities of the laboratory
     * @return A visual representation ordered alphabetically of the patients in the facilities of the laboratory
     */
    public String displayPeopleInFacility(){
        String patients = " ---------------------------------------- ";//Separator of patients 

        ArrayList<Patient> all = new ArrayList<>();//ArrayList to hold all patients in the lab
        for (Patient pt : lab){//Takes the patients from the table to this
            all.add(pt);
        }

        all.sort(new Comparator<Patient>(){//Sorts the patients alphabetially
            @Override
            public int compare(Patient o1, Patient o2) {
                return (o1.getName() + " " + o1.getSurName()).compareTo(o2.getName() + " " + o2.getSurName());
            }
        });

        for (Patient pt : all){//Makes the string
            patients += pt.toString() + "\n ---------------------------------------- ";
        }

        return patients;
    }





//Stack module, By: Camilo
    /**
     * Uses the data in the backup modules set up beforehand to revert an action done by the user.
     * @return whether or not there was something to go back to.
     */
    public String unDo(){
        try{
            BackUp bUp = undo.poll().getValue();//gets the backup

            if(bUp.getUnit()!=null) patientLine[bUp.getUnit().getUnit()-1] = bUp.getUnit();//checks if there were any changes to the lines, as there cannot be changes to more than one lien at a time, that is all that he bakup saves.
    
            if(bUp.getLab()!=null) lab = bUp.getLab();//checks if there were any chages to the lab's list

            return "Succesfully undid changes";//as there have to had been some changes, it is ensured that the changes were undone.

        }
        catch(NoSuchFieldException e){//if there is nothing to go back to, an exception is trown
            return e.getMessage();//so it is caught, and the message is returned.
        }
    }


}
