package model;

public class BackUp {
    private PriorityLine<Package,String> unit;
    private IdTable lab;

    public BackUp(PriorityLine<Package,String> unit){
        this.unit = unit;
    }
    public BackUp(IdTable lab){
        this.lab = lab;
    }

    public IdTable getLab() {
        return lab;
    }
    public PriorityLine<Package, String> getUnit() {
        return unit;
    }

}
