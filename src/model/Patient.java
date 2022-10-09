package src.model;


public class Patient{
    private String name;
    private String surname;
    private String id;
    private String gender;
    private int age;
    private Ailment ailment;

    public Patient(String name, String surname, String id, String gender, int age, Ailment ailment){
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.id = id;
        this.surname = surname;
        this.ailment = ailment;
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
    public Ailment getAilment() {
        return ailment;
    }
    public void setAilment(Ailment ailment) {
        this.ailment = ailment;
    }
}