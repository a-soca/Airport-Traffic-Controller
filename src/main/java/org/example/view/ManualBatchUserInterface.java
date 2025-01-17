package org.example.view;

import org.example.TrafficGenerator;
import org.example.entities.Airport;
import org.example.repository.PlaneRepository;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.World.getAirportRepository;

public class ManualBatchUserInterface extends BasicUserInterface {

    // Arrays used to store the parameters used to generate each aircraft when the simulator starts
    private static final ArrayList<String> flightCodes = new ArrayList<>();
    private static final ArrayList<String> arrivalTimes = new ArrayList<>();
    private static final ArrayList<String> arrivalLocations = new ArrayList<>();

    /**
     * The user interface for manual batches of flights
     */
    public void run() {
        /////////////////////////////
        // Perform user actions here
        /////////////////////////////

        TrafficGenerator trafficGenerator = new TrafficGenerator();

        boolean exit = false;
        printTitle("Select an option:");
        while(!exit) {
            printMenu();

            String option = scanner.nextLine();
            switch(option) {
                case "Add Flight":
                    printTitle("Add a Flight");
                    addFlight();
                    break;
                case "Get Flights":
                    printTitle("Planned Flights:");
                    printFlights();
                    break;
                case "Get Airports":
                    printTitle("Available Airports:");
                    printAirports();
                    break;
                case "Start Simulator":
                    exit = true;
                    trafficGenerator.generateFlights(flightCodes, arrivalTimes, arrivalLocations);
                    break;
                default:
                    printTitle("Invalid Option. Please try again:");
                    break;
            }
        }
    }

    /**
     * The method for creating a blueprint for a flight from user input
     */
    private void addFlight() {
        // Specify the desired plane ID
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        // Check the plane does not already exist and the flight code has not already been entered
        if (PlaneRepository.getPlane(flightCode) != null || flightCodes.contains(flightCode)) {
            printTitle("Error : Flight already exists"); // Print an error message
            return;
        }

        // Specify the arrival time of the plane
        System.out.println("Enter arrival time from simulator start (in seconds): ");
        String arrivalDelay = scanner.nextLine();

        try { // Attempt to convert the user input into an integer
            Integer.parseInt(arrivalDelay);
        } catch (NumberFormatException e) { // If the input is not an integer,
            printTitle("Error : Invalid arrival delay"); // Print an error message
            return;
        }

        printAirports(); // Print the options for airport codes
        // Take the desired airport code
        System.out.println("Enter arrival airport code: ");
        String arrivalAirport = scanner.nextLine();

        // Check if the airport exists
        if (getAirportRepository().getAirport(arrivalAirport) == null) {
            printTitle("Error : Airport does not exist");
            return;
        }

        // Add the information entered to the blueprint arrays
        flightCodes.add(flightCode);
        arrivalTimes.add(arrivalDelay);
        arrivalLocations.add(arrivalAirport);

        printTitle("Flight " + flightCode + " Added successfully"); // Update the log
    }

    /**
     * Method used to print all created flight blueprints
     */
    private void printFlights() {
        if(flightCodes.isEmpty()) { // If no flight blueprints have been entered,
            System.out.println("No flights found"); // Print a message
        } else { // Otherwise,
            for (int i = 0; i < flightCodes.size(); i++) { // For all flight blueprints,
                System.out.println( // Print the flight information
                        "Plane Code : " + flightCodes.get(i)
                        + " | Arrival time : " + arrivalTimes.get(i) + "s"
                        + " | Arrival location : " + arrivalLocations.get(i)
                );
            }
            System.out.println();
        }
    }

    /**
     * Prints the options available to the user
     */
    protected void printMenu() {
        System.out.println(
                "Add Flight      - Add a plane to the Simulator\n" +
                "Get Flights     - Show a list of flights\n" +
                "Get Airports    - Show a list of airports\n" +
                "Start Simulator - Start the simulator\n"
        );
    }
}
