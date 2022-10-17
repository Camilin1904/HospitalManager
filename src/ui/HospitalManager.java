package ui;

import model.HospitalController;

public class HospitalManager {

    private HospitalController cntrl;
    
    public static void main(String[] args) {
        System.out.println("Initializing....\n\n");
        HospitalManager mng = new HospitalManager();
        mng.menu();
    }   

    public HospitalManager(){
        cntrl = new HospitalController("");
    }

    private void menu(){
        System.out.println("Options:\n\n1)");
    }
}
