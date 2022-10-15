package src.model;

import java.util.ArrayList;


public class Patient{
    private String name;
    private String surname;
    private String id;
    private String gender;
    private int age;
    private ArrayList<Ailment> ailments;

    public Patient(String name, String surname, String id, String gender, int age, ArrayList<Ailment> ailments){
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.surname = surname;
        this.ailments = ailments;
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
}