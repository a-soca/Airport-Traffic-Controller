package org.example.view;

import org.example.TrafficGenerator;
import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    protected static final Scanner scanner = new Scanner(System.in); // Set the scanner source

    private static final ArrayList<String> flightCodes = new ArrayList<>();
    private static final ArrayList<String> arrivalTimes = new ArrayList<>();
    private static final ArrayList<String> arrivalLocations = new ArrayList<>();

    public static void run() {
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

    private static void addFlight() {
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        if (PlaneRepository.getPlane(flightCode) != null) {
            printTitle("Error : Flight already exists");
            return;
        }

        System.out.println("Enter arrival time from simulator start (in seconds): ");
        String arrivalDelay = scanner.nextLine();

        try {
            Integer.parseInt(arrivalDelay);
        } catch (NumberFormatException e) {
            printTitle("Error : Invalid arrival delay");
            return;
        }
        printAirports();
        System.out.println("Enter arrival airport code: ");
        String arrivalAirport = scanner.nextLine();

        if (AirportRepository.getAirport(arrivalAirport) == null) {
            printTitle("Error : Airport does not exist");
            return;
        }

        flightCodes.add(flightCode);
        arrivalTimes.add(arrivalDelay);
        arrivalLocations.add(arrivalAirport);

        printTitle("Flight " + flightCode + " Added successfully");
    }

    protected static void printAirports() {
        if(AirportRepository.getAllAirports().isEmpty()) {
            System.out.println("No airports found");
        } else {
            for (Airport airport : AirportRepository.getAllAirports()) {
                System.out.println(airport.toString());
            }
            System.out.println();
        }
    }

    private static void printFlights() {
        if(flightCodes.isEmpty()) {
            System.out.println("No flights found");
        } else {
            for (int i = 0; i < flightCodes.size(); i++) {
                System.out.println(
                        "Plane Code : " + flightCodes.get(i)
                        + " | Arrival time : " + arrivalTimes.get(i) + "s"
                        + " | Arrival location : " + arrivalLocations.get(i)
                );
            }
            System.out.println();
        }
    }

    /**
     * Clears the screen by adding 50 carriage returns (used for cross compatability between all terminals)
     */
    protected static void clearScreen() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    /**
     * Prints the header of the application alongside a prompt
     * @param title The prompt to show the user under the banner
     */
    protected static void printTitle(String title) {
        clearScreen();
        System.out.println(
                "-------------------------------" +
                "\n\tAir Traffic Controller" +
                "\n-------------------------------"); // Output the name of the application
        System.out.println("-> " + title + "\n");
    }

    /**
     * Prints the options available to the user
     */
    private static void printMenu() {
        System.out.println(
                "Add Flight      - Add a plane to the Simulator\n" +
                "Get Flights     - Show a list of flights\n" +
                "Get Airports    - Show a list of airports\n" +
                "Start Simulator - Start the simulator\n"
        );
    }
}
