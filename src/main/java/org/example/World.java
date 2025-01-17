package org.example;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;
import org.example.view.SimpleUserInterface;
import org.example.view.UserInterface;

public class World {
    private static boolean running = true;

    public static void main(String[] args) {
        createAirports();

        int mode = 1;

        switch (mode) {
            case 1: // Basic single plane interface
                SimpleUserInterface.run();
                break;
            case 2: // Manual Plane creation
                UserInterface.run();
                break;
            case 3: // Automated Plane creation
                createPlanes();
                break;
        }

        // Start the simulation loop
        startControllingAirTraffic();

//        stopAirTraffic();
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

    private static void createPlanes() {
        TrafficGenerator trafficGenerator = new TrafficGenerator();
        trafficGenerator.readBlueprint();
    }

    private static void startControllingAirTraffic() {
        while(running) {
            for(Airport airport : AirportRepository.getAllAirports()) {
                airport.getAirTrafficController().controlTraffic();
            }
        }
    }

    public static void stopAirTraffic() {
        running = false;
    }
}