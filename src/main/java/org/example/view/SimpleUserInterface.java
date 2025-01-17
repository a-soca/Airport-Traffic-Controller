package org.example.view;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;

import java.time.LocalDateTime;

public class SimpleUserInterface extends UserInterface {

    public static void run() {
        /////////////////////////////
        // Perform user actions here
        /////////////////////////////

        boolean exit = false;
        printTitle("Select an option:");
        while(!exit) {
            printMenu();

            String option = scanner.nextLine();
            switch(option) {
                case "LandPlane":
                    printTitle("Land a Plane");
                    landFlight();
                    break;
                case "GetAirports":
                    printTitle("Available Airports:");
                    printAirports();
                    break;
                case "Exit":
                    exit = true;
                    break;
                default:
                    printTitle("Invalid Option. Please try again:");
                    break;
            }
        }
    }

    private static void landFlight() {
        printAirports();

        System.out.println("Enter arrival airport code: ");
        String arrivalAirport = scanner.nextLine();

        Airport airport = AirportRepository.getAirport(arrivalAirport);
        if (airport == null) {
            printTitle("Error : Airport does not exist");
        } else {
            airport.getAirTrafficController().landPlane(new Plane("DEMO", LocalDateTime.now(), arrivalAirport));
            System.out.println();
        }
    }

    /**
     * Prints the options available to the user
     */
    private static void printMenu() {
        System.out.println( "LandPlane      - Attempt to land a plane\n" +
                "GetAirports    - Show a list of airports\n" +
                "Exit           - Exit the program\n"
        );
    }
}
