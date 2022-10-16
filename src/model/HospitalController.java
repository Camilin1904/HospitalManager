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
    

    public boolean registerPatient(String name, String surname, String id, String gender, int age){

        return false;
    }

    public boolean loadDataBase(){

        return false;
    }

    public void addToQueue(String patientId, int unit) throws NoSuchElementException{

        Node<Patient, String> toAdd = patientDB.search2(patientId);

        
    }

    public void uptadeDataBase(){

    }
    
    public boolean unDo(){

        return false;
    }

    public String displayQueue(){


        return null;
    }

    public String displayPeopleInFacility(){

        return null;
    }

    public void dispathPatient(){

    }

    public void unqueuePatient(String patientId){

    }
    
    public void addPatientToLab(String patientId){

    }


}
