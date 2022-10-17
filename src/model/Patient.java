package model;

import java.util.ArrayList;


public class Patient{
    private String name;
    private String surname;
    private String id;
    private String gender;
    private int age;
    private ArrayList<Ailment> ailments;
    private int ailmentPriority;

    public Patient(String name, String surname, String id, String gender, int age, ArrayList<Ailment> ailments){
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.surname = surname;
        this.ailments = ailments;

        int prio = 0;
        //AQUI CALCULAMOS LA PRIORIDAD QUE VA A TENER EL PACIENTE SIEMPRE A PARTIR DE SUS COMPLICACIONES
        for(Ailment a : ailments){
            switch (a){
                case CANCER:
                    prio += 10;
                    break;

                case IMMUNE_VULNERABILITY:
                    prio += 9;
                    break;

                case HEART_RISK:
                    prio += 8;
                    break;

                case PREGNANT:
                    prio += 7;
                    break;

                case POST_SURGERY:
                    prio += 6;
                    break;

                case PHYSICAL_DISABILITY:
                    prio += 5;
                    break;

                case FEVER:
                    prio += 4;
                    break;

                case DIARRHEA:
                    prio += 3;
                    break;

                case PAIN:
                    prio += 2;
                    break;
            }
        }
        ailmentPriority = prio;
    }

    public Patient(String name, String surname, String id, String gender, int age){
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.surname = surname;
    }
    public int getAge() {
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurName() {
        return surname;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurName(String surName) {
        this.surname = surName;
    }
    public ArrayList<Ailment> getAilments() {
        return ailments;
    }
    public void setAilments(ArrayList<Ailment>  ailments) {
        this.ailments = ailments;
    }
    public void addAilments(Ailment ailment){
        ailments.add(ailment);
    }
    public int getAilmentPriority() {
        return ailmentPriority;
    }
    public void setAilmentPriority(int ailmentPriority) {
        this.ailmentPriority = ailmentPriority;
    }
    private String ailmentsString(){
        String a = "";
        for (Ailment al : ailments){
            a += al + ", ";
        }
        a = a.substring(0, a.length()-3);
        return a;
    }
    
    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + " " + surname + "\nAge: " + age + "\nGender: " + gender + "\nAilments: " + ailmentsString();
    }
    
}