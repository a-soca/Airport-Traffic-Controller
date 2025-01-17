package org.example.view;

import org.example.TrafficGenerator;

import java.util.Scanner;

import static org.example.World.createPlanes;
import static org.example.World.stopAirTraffic;

public class MainMenu extends UserInterface {
    protected static final Scanner scanner = new Scanner(System.in); // Set the scanner source

    public static void run() {
        /////////////////////////////
        // Perform user actions here
        /////////////////////////////
        boolean exit = false;
        printTitle("Select an option:");
        while (!exit) {
            printMenu();

            String option = scanner.nextLine();
            switch (option) {
                case "Basic": // Basic single plane interface
                    SimpleUserInterface.run();
                    exit = true;
                    break;
                case "Manual Batch": // Manual Plane creation
                    UserInterface.run();
                    exit = true;
                    break;
                case "Automated Batch": // Automated Plane creation
                    createPlanes();
                    exit = true;
                    break;
                case "Exit":
                    exit = true;
                    stopAirTraffic();
                    break;
                default:
                    printTitle("Invalid Option. Please try again:");
                    break;
            }
        }
    }

    /**
     * Prints the options available to the user
     */
    private static void printMenu() {
        System.out.println(
                "Basic           - Land planes individually\n" +
                "Manual Batch    - Generate a batch of scheduled flights\n" +
                "Automated Batch - Run a simulation from a CSV\n" +
                "Exit            - Exit the program\n"
        );
    }
}