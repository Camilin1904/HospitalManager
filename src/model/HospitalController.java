package model;

import model.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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
    public void registerPatient(String name, String surname, String id, String gender, int age, ArrayList<Ailment> ailment) throws PatientAlreadyRegisteredException{

        patientDB.insert(id, new Patient(name,surname,id,gender,age,ailment));

    }

    public boolean loadDataBase(){

        return false;
    }

    public void uptadeDataBase(){

    }

    
//Queues module, By: Mateo
    public void addToQueue(String patientId, int unit) throws NoSuchElementException {

        Node<Patient, String> toAdd = patientDB.search2(patientId);
        
        if(toAdd==null){
            throw new NoSuchElementException("PACIENTE NO ENCONTARDO");
        }
        
        patientLine[unit-1].insert(toAdd, toAdd.getValue().getAilmentPriority());

        
    }

    public void unqueuePatient(int unit){
        patientLine[unit-1].heapExtractMax();
    }


    public String displayQueue(int unit){
        String out="";
        PriorityLine<Patient,String> arr= patientLine[unit-1];
        int heapSize= arr.getHeapSize();
        while(heapSize!=0){
            out+=arr.heapExtractMax().getName();
            heapSize--;
        }
        return out;
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
