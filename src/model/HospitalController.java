package src.model;

import java.util.NoSuchElementException;

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
    public void addPatientToLab(String patientId){

    }

    public void dispathPatient(){

    }

    public String displayPeopleInFacility(){

        return null;
    }


//Stack module, By: Camilo
    public boolean unDo(){

        return false;
    }


}
