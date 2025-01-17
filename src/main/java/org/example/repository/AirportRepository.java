package org.example.repository;

import org.example.entities.Airport;
import org.example.entities.Location;
import java.util.ArrayList;

public class AirportRepository {
    private static final LocationRepository airports = new LocationRepository();

    public static void addAirport(Airport airport) {
        airports.addLocation(airport);
    }

    public static void removeAirport(String id) {
        Airport airport = (Airport) airports.getLocation(id); // Get the Location object from the hashmap

        airports.removeLocation(airport.getID()); // Remove the Location object from the hashmap
    }

    public static Airport getAirport(String id) {
        return (Airport) airports.getLocation(id);
    }

    public static ArrayList<Airport> getAllAirports() {
        ArrayList<Location> allLocations = airports.getLocations();
        ArrayList<Airport> allAirports = new ArrayList<>();

        for(Location location : allLocations) {
            allAirports.add((Airport) location);
        }

        return allAirports;
    }
}
