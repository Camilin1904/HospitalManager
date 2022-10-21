package ui;

import model.HospitalController;
import java.util.Arrays;
import java.util.Scanner;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.*;

public class HospitalManager {
    private Scanner sc = new Scanner(System.in);
    private HospitalController ctrl;
    private AutoUnqueuer unQ;

    public static void main(String[] args) {
        System.out.println("Initializing....\n\n");
        HospitalManager mng = new HospitalManager();
        while (mng.menu() != 0) {
        }
        mng.ctrl.updateDataBase();
        System.exit(0);
    }

    public HospitalManager(){
        ctrl = new HospitalController();
        unQ = new AutoUnqueuer();
        unQ.unQueueAuto(this);
    }

    public int menu() {
        System.out.println("************************************\n" + "Welcome to the Hospital Manager\n" + "***********************************");
        System.out.println("Please select an option: \n" +
                "(1) Register or update a patient.\n" +
                "(2) Register a patient entrance.\n" +
                "(3) Add a patient to the line.\n" +
                "(4) Pass the next in line.\n" +
                "(5) Register a patient exit.\n" +
                "(6) Display facility.\n" +
                "(7) Display line.\n" +
                "(8) UnD    o.\n" +
                "(0) Exit app.");

        int option = Integer.parseInt(sc.nextLine());
        executeMenu(option);
        return option;
    }

    public void executeMenu(int option) {
        unQ.stop();
        switch (option) {
            case 1:
                System.out.println("\nRegistering/Updating patient...\n");
                registerPatient();
                break;
            case 2:
                System.out.println("Registering entrance...\n");
                registerEntry();
                break;
            case 3:
                System.out.println("Adding to line...\n");
                addToQueue();
                break;
            case 4:
                System.out.println("Unqueueing patient...\n");
                unqueue();
                break;
            case 5:
                System.out.println("Registering exit...\n");
                dispatchPatient();
                break;
            case 6:
                System.out.println("Displaying people in the facility...\n");
                displayFacility();
                break;
            case 7:
                System.out.println("Displaying people queueing...\n");
                displayUnit();
                break;
            case 8:
                unDo();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid input.");
        }
        unQ.unQueueAuto(this);
    }

    public void registerPatient() {

        String name, surname, id, gender;
        int age, yesNo;

        System.out.println("Type the name of the patient:\n");
        name = sc.nextLine();

        System.out.println("Type the surname of the patient:\n");
        surname = sc.nextLine();

        System.out.println("Type the id of the patient:\n");
        id = sc.nextLine();

        System.out.println("Type the gender of the patient:\n");
        gender = sc.nextLine();

        System.out.println("Type the age of the patient:\n");
        age = Integer.parseInt(sc.nextLine());

        do {
            System.out.println("The patient has any ailment?\n" +
                    "(1) yes.\n" +
                    "(2) no.");
            yesNo = Integer.parseInt(sc.nextLine());

            if (yesNo != 2 && yesNo != 1) {
                System.out.println("\nType a valid option.\n");
            }
        } while (yesNo != 2 && yesNo != 1);

        if (yesNo == 1) {
            System.out.println("Select the one or more of the following options in this format \"x x x x\"\n");
            System.out.println("(1) Cancer.\n" +
                    "(2) Immune vulnerability.\n" +
                    "(3) Heart risk.\n" +
                    "(4) Pregnant.\n" +
                    "(5) Post surgery.\n" +
                    "(6) Physical disability.\n" +
                    "(7) Fever.\n" +
                    "(8) Diarrhea.\n" +
                    "(9) Pain.");
            int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            ctrl.registerPatient(name, surname, id, gender, age, a);

        } else {

            ctrl.registerPatient(name, surname, id, gender, age);
        }
        System.out.println("Patient registered/updated successfully.");
    }

    private void registerEntry(){
        System.out.println("Input the id of the patient: ");
        String id = sc.next();
        System.out.println(id);
        sc.nextLine();
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
        System.out.println("Input the unit that the patient would enter to (1-3)");
        int unit = sc.nextInt();
        sc.nextLine();
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
        //while(true){
            try{
                System.out.println("Which unit will be displayed? (1-3)");
                unit = sc.nextInt();
                sc.nextLine();
                if(unit<1||unit>3) throw new InputMismatchException();
                System.out.println("The patients in unit " + unit + " are: " + ctrl.displayQueue(unit)); 
                //break;
            }
            catch(InputMismatchException e){
                System.out.println("Input a valid unit");
            }
        //}
        
    }


    private void unqueue(){
        int unit=0;
        while (true){
            try{
                System.out.println("Which unit?(1-3)");
                unit = sc.nextInt();
                sc.nextLine();
                if(unit<1||unit>3) throw new InputMismatchException();
                break;
            }
            catch(InputMismatchException e){
                System.out.println("Input a valid unit");
            }
        }
        ctrl.unqueuePatient(unit);
    }

    public void autoUnqueue(int u){
        ctrl.autoUnqueuePatient(u);
    }

    public void dispatchPatient(){
        System.out.println("Input the id of the patient: ");
        String id = sc.next();
        sc.nextLine();
        try{
            ctrl.dispatchPatient(id);
            System.out.println("\nSuccesfully registered exit.\n");
        }
        catch(NullPointerException e){
            System.out.println("No patient with that id on the lab");
        }
    }

    public void unDo(){
        System.out.println(ctrl.unDo());
    }
}






class AutoUnqueuer {
    private ScheduledFuture<?> unqueueHandle;
    private final ScheduledExecutorService scheduler =
      Executors.newScheduledThreadPool(1);

    public void unQueueAuto(HospitalManager hosp) {
      final Runnable unqueuer = new Runnable() {
        public void run() { 
            int u = (int)(Math.random()*3)+1;
            hosp.autoUnqueue(u);
            System.out.println("Paciente pasado automaticamente");
        }
      };
      unqueueHandle =
        scheduler.scheduleAtFixedRate(unqueuer, 60, 60, SECONDS);
    }
    public void stop(){
        unqueueHandle.cancel(true);
    }
  }