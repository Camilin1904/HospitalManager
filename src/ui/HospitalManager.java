package ui;

import model.HospitalController;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HospitalManager {
    private Scanner sc = new Scanner(System.in);
    private HospitalController ctrl;
    
    public static void main(String[] args) {
        System.out.println("Initializing....\n\n");
        HospitalManager mng = new HospitalManager();
        mng.menu();
    }   

    public HospitalManager(){
        ctrl = new HospitalController("");
    }

    private void menu(){
        System.out.println("Options:\n\n1)");
    }

    private void registerEntry(){
        System.out.println("Input the id of the patient: ");
        String id = sc.next();
        try{
            ctrl.addPatientToLab(id);
            System.out.println("\nSuccesfully registered entry.\n");
        }
        catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    private void addToQueue(){
        System.out.println("Input the id of the patient: ");
        String id = sc.next();
        System.out.println("Inout the unit that the patient wuold enter to (1-3)");
        int unit = sc.nextInt();
        try{
            ctrl.addToQueue(id, unit);
        } 
        catch (NoSuchElementException e){
            System.out.println("\n" + e.getMessage() + "\n");
        }
    }

    private void displayFacility(){
        System.out.println("Patients currently in the lab: \n" + ctrl.displayPeopleInFacility());
    }

    private void displayUnit(){
        int unit = 0;
        while(true){
            try{
                System.out.println("Which unit would the patient go to? (1-3)");
                unit = sc.nextInt();
                if(unit<1||unit>3) throw new InputMismatchException();
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Input a valid unit");
            }
        }
        System.out.println("The patients in unit " + unit + " are: " + ctrl.displayQueue(unit)); 
    }
}
