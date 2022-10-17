package ui;

import model.HospitalController;
import java.util.Arrays;
import java.util.Scanner;

public class HospitalManager {

    private HospitalController cntrl;
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Initializing....\n\n");
        HospitalManager mng = new HospitalManager();
        while (mng.menu() != 0) {

        }
    }

    public HospitalManager() {
        cntrl = new HospitalController("");
    }

    public int menu() {
        System.out.println("************************************\n" + "Welcome to the Hospital Manager\n" + "***********************************");
        System.out.println("Please select an option: \n" +
                "(1) Register or update a patient.\n" +
                "(2) Register a patient entrance.\n" +
                "(3) Add a patient to the line.\n" +
                "(4) Pass the next in line.\n" +
                "(5) Register a patient exit.\n" +
                "(0) Exit app.");

        int option = Integer.parseInt(sc.nextLine());

        return option;
    }

    public void executeMenu(int option) {
        int option1;
        String ans;
        switch (option) {
            case 1 -> {
                System.out.println("\nRegistering/Updating patient...\n");
                registerPatient();
            }

        }
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
            System.out.println("""
                    The patient has any ailment?
                    (1) yes.
                    (2) no.
                    """);
            yesNo = Integer.parseInt(sc.nextLine());

            if (yesNo != 2 || yesNo != 1) {
                System.out.println("\nType a valid option.\n");
            }
        } while (yesNo != 2 || yesNo != 1);

        if (yesNo == 1) {
            System.out.println("Select the one or more of the following options in this format \"x x x x\"\n");
            System.out.println("""
                    (1) Cancer.
                    (2) Immune vulnerability.
                    (3) Heart risk.
                    (4) Pregnant.
                    (5) Post surgery.
                    (6) Physical disability.
                    (7) Fever.
                    (8) Diarrhea.
                    (9) Pain.
                    """);
            int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            cntrl.registerPatient(name, surname, id, gender, age, a);

        } else {

            cntrl.registerPatient(name, surname, id, gender, age);
        }
        System.out.println("Patient registered/updated successfully.");
    }
}
