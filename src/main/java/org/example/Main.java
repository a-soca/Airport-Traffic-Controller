package org.example;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program started");
        createAirports();
        createPlanes();

        landPlanes();
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

        for(String planeCode : planeCodes) {
            new Plane(planeCode, LocalDateTime.now().plusSeconds(5), "LGW");
        }
    }

    private static void landPlanes() {
        for(Plane plane : PlaneRepository.getAllPlanes()) {
            plane.getArrivalLocation().landPlane(plane);
        }
    }
}