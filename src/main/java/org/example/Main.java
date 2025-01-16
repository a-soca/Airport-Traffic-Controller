package org.example;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started");
        createAirports();
        createPlanes();
    }

    private static void createAirports() {
        String[] airportCodes = new String[] {
                "LGW",
                "EMA",
                "MAN"
        };

        for(String airportCode : airportCodes) {
            AirportRepository.addAirport(new Airport(airportCode));
        }
    }

    private static void createPlanes() {
        String[] planeCodes = new String[] {
                "1XXX",
                "2XXX",
                "3XXX"
        };

        ArrayList<Plane> planes = new ArrayList<>();

        for(String planeCode : planeCodes) {
            planes.add(new Plane(planeCode));
        }

        AirportRepository.getAirport("LGW").landPlane(planes.getFirst());
        AirportRepository.getAirport("LGW").landPlane(planes.getLast());
    }
}