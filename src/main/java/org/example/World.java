package org.example;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;
import org.example.view.MainMenu;
import org.example.view.SimpleUserInterface;
import org.example.view.UserInterface;

import java.util.Scanner;

public class World {
    private static boolean running = true;

    public static void main(String[] args) {
        createAirports();

        MainMenu.run();

        // Start the simulation loop
        startControllingAirTraffic();
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

    public static void createPlanes() {
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