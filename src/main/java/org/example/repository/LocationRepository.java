package org.example.repository;

import org.example.entities.Location;
import org.example.entities.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;

public class LocationRepository {
    private final HashMap<String, Location> locations;

    /**
     * A repository used to store {@link Location} objects efficiently via a {@link HashMap}
     */
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

    /**
     * Gets all locations in the repository
     * @return An {@link ArrayList} of {@link Location} objects stored in the repository
     */
    public ArrayList<Location> getLocations() {
        return new ArrayList<>(locations.values());
    }
}
