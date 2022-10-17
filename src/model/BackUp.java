package src.model;

public class BackUp {
    private PriorityLine<Patient,String> unit;
    private IdTable lab;

    public BackUp(PriorityLine<Patient,String> unit){
        this.unit = unit;
    }
    public BackUp(IdTable lab){
        this.lab = lab;
    }    

    public IdTable getLab() {
        return lab;
    }
    public PriorityLine<Patient, String> getUnit() {
        return unit;
    }
    public void setUnit(PriorityLine<Patient, String> unit) {
        this.unit = unit;
    }

}
