package org.example.repository;

import org.example.entities.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;

public class VehicleRepository {
    private final HashMap<String, Vehicle> vehicles;

    public VehicleRepository() {
        vehicles = new HashMap<>();
    }

    public void addVehicle(Vehicle vehicle) {
        String id = vehicle.getID(); // Get the vehicle ID
        this.vehicles.put(id, vehicle); // Put the Vehicle object in the hashmap using the ID as a key
    }

    public void removeVehicle(String id) {
        Vehicle vehicle = getVehicle(id); // Get the Vehicle object from the hashmap

        if (vehicle != null) { // If the location is present,
            this.vehicles.remove(vehicle.getID()); // Remove the Vehicle object from the hashmap
        }
    }

    public Vehicle getVehicle(String id) {
        return vehicles.get(id);
    }

    public ArrayList<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles.values());
    }
}
