package org.example.repository;

import org.example.entities.Airport;

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
}
