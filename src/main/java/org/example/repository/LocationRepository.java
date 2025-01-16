package org.example.repository;

import org.example.entities.Location;

import java.util.HashMap;

public class LocationRepository {
    private final HashMap<String, Location> locations;

    public LocationRepository() {
        locations = new HashMap<>();
    }

    public void addLocation(Location location) {
        String id = location.getID(); // Get the location ID
        this.locations.put(id, location); // Put the Location object in the hashmap using the ID as a key
    }

    public void removeLocation(String id) {
        Location location = getLocation(id); // Get the Location object from the hashmap

        if (location != null) { // If the location is present,
            this.locations.remove(location.getID()); // Remove the Location object from the hashmap
        }
    }

    public Location getLocation(String id) {
        return locations.get(id);
    }
}
