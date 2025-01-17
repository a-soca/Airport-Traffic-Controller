package org.example.repository;

import org.example.entities.Airport;
import org.example.entities.Location;
import java.util.ArrayList;

public class AirportRepository {
    private final LocationRepository airports; // Used to store the airports added to the repository

    /**
     * A repository used to store {@link Airport} objects
     */
    public AirportRepository() {
        this.airports = new LocationRepository();
    }

    public void addAirport(Airport airport) {
        airports.addLocation(airport);
    }

    public void removeAirport(String id) {
        Airport airport = (Airport) airports.getLocation(id); // Get the Location object from the hashmap

        airports.removeLocation(airport.getID()); // Remove the Location object from the hashmap
    }

    public Airport getAirport(String id) {
        return (Airport) airports.getLocation(id);
    }

    /**
     * Finds all airports added to the repository and returns the objects in an array list
     * @return An {@link ArrayList} of {@link Airport} objects
     */
    public ArrayList<Airport> getAllAirports() {
        ArrayList<Location> allLocations = airports.getLocations(); // Get all location codes stored in the repository
        ArrayList<Airport> allAirports = new ArrayList<>(); // Create a new array list to store the airport objects

        // For all locations in the repository,
        for(Location location : allLocations) {
            allAirports.add((Airport) location); // Cast the location to an Airport and store it in the airport array
        }

        return allAirports; // Return the airports
    }
}
