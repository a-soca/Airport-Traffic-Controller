package org.example.view;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;

import java.time.LocalDateTime;

import static org.example.World.getAirportRepository;
import static org.example.World.stopAirTraffic;

public class SimpleUserInterface extends UserInterface {

    /**
     * The simple user interface for manually landing individual planes
     */
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
                case "Land Plane":
                    printTitle("Land a Plane");
                    landFlight();
                    break;
                case "Get Airports":
                    printTitle("Available Airports:");
                    printAirports();
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
     * Manually attempt to land a plane at a chosen airport
     */
    private static void landFlight() {
        printAirports(); // Display the airport options

        // Take the airport input by the user
        System.out.println("Enter arrival airport code: ");
        String arrivalAirport = scanner.nextLine();

        Airport airport = getAirportRepository().getAirport(arrivalAirport); // Find the airport object
        if (airport == null) { // If it does not exist,
            printTitle("Error : Airport does not exist"); // Print an error
        } else { // Otherwise,
            // Land a demo plane
            airport.getAirTrafficController().landPlane(new Plane("DEMO", LocalDateTime.now(), arrivalAirport));
            System.out.println();
        }
    }

    /**
     * Prints the options available to the user
     */
    private static void printMenu() {
        System.out.println(
                "Land Plane     - Attempt to land a plane\n" +
                "Get Airports   - Show a list of airports\n" +
                "Exit           - Exit the program\n"
        );
    }
}
