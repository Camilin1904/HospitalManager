package model;

public class BackUp {
    private PriorityLine<Patient> unit;
    private IdTable<Patient,String> lab;

    public BackUp(PriorityLine<Patient> unit){
        this.unit = unit;
    }
    public BackUp(IdTable<Patient,String>  lab){
        this.lab = lab;
    }    

    public IdTable<Patient,String>  getLab() {
        return lab;
    }
    public PriorityLine<Patient> getUnit() {
        return unit;
    }
    public void setUnit(PriorityLine<Patient> unit) {
        this.unit = unit;
    }

}
