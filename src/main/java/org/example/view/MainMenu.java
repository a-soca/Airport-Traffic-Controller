package org.example.view;

import org.example.entities.Airport;

import java.util.Scanner;

import static org.example.World.*;

public class MainMenu extends UserInterface {
    protected static final Scanner scanner = new Scanner(System.in); // Set the scanner source

    /**
     * The basic user interface used to choose the specific mode of the program
     */
    public void run() {
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
                    BasicUserInterface basicUI = new BasicUserInterface();
                    basicUI.run();
                    exit = true;
                    break;
                case "Manual Batch": // Manual Plane creation
                    ManualBatchUserInterface manualUI = new ManualBatchUserInterface();
                    manualUI.run();
                    exit = true;
                    break;
                case "Automated Batch": // Automated Plane creation
                    createPlanes();
                    exit = true;
                    break;
                case "Settings":
                    settings();
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
     * Allows default parameters to be customised
     */
    private void settings() {
        printTitle("Settings");
        System.out.println("Enter custom wait time between landings:");

        String waitTime = scanner.nextLine();
        int separation;
        try { // Attempt to convert the input to an integer
           separation = Integer.parseInt(waitTime);
        } catch (NumberFormatException e) { // If the conversion fails,
            printTitle("Error : Invalid wait time"); // Reset the menu and display the error
            return;
        }

        // Set the default waiting time between landings for all airports
        for(Airport airport : getAirportRepository().getAllAirports()) {
            airport.setLandingTimeSeparation(separation);
        }
    }

    /**
     * Prints the options available to the user
     */
    protected void printMenu() {
        System.out.println(
                "Basic           - Land planes individually\n" +
                "Manual Batch    - Generate a batch of scheduled flights\n" +
                "Automated Batch - Run a simulation from a CSV\n" +
                "Settings        - Customise default parameters\n" +
                "Exit            - Exit the program\n"
        );
    }
}