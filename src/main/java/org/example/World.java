package org.example;

import org.example.entities.Airport;
import org.example.repository.AirportRepository;
import org.example.view.MainMenu;

public class World {
    private static boolean running = true; // Used to escape the air traffic loop

    // A static airport repository is used to store all the airports created for the world
    private static final AirportRepository airports = new AirportRepository();

    public static void main(String[] args) {
        createAirports(); // Create the default airports specified in the requirements

        MainMenu ui = new MainMenu();
        ui.run(); // Run the main menu to allow the user to choose the appropriate mode

        // Start the simulation loop
        simulateTraffic();
    }

    private static void createAirports() {
        String[] airportCodes = new String[] {
                "LGW",
                "EMA",
                "MAN"
        };

        for(String airportCode : airportCodes) {
            new Airport(airportCode);
        }
    }

    /**
     * Automatically generates planes from a CSV
     */
    public static void createPlanes() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();
        trafficGenerator.readBlueprint();
    }

    /**
     * The main loop of the simulator
     */
    private static void simulateTraffic() {
        while(running) { // While the simulator is running,
            if(airports.getAllAirports().isEmpty()) { // If there are no airports,
                stopAirTraffic(); // Stop the simulator
            } else { // Otherwise,
                controlTraffic(); // Control the air traffic
            }
        }
    }

    /**
     * Finds each airport in the world and requests that it controls its traffic. If the airport has no more incoming
     * traffic, it is removed from the world.
     */
    private static void controlTraffic() {
        for(Airport airport : airports.getAllAirports()) {
            if(airport.hasNoIncomingTraffic()) {
                getAirportRepository().removeAirport(airport.getID());
            } else {
                airport.getAirTrafficController().controlTraffic();
            }
        }
    }

    public static void stopAirTraffic() {
        running = false;
    }

    public static AirportRepository getAirportRepository() {
        return airports;
    }
}