package src.model;

public class HospitalController {

    private IdTable patientDB;
    private PriorityLine<Patient, String> patientLine;
    private NodeBackup undo;
    

    public boolean registerPatient(String name, String surname, String id, String gender, int age){

        return false;
    }

    public boolean loadDataBase(){

        return false;
    }

    public void addToQueue(String patientId){

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
    
    /*public void addPatient(Node patient){

    }*/


}
