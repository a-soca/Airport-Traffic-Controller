package org.example.repository;

import org.example.entities.Plane;
import org.example.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;


public class PlaneRepository extends VehicleRepository {
    private static final VehicleRepository planes = new VehicleRepository();

    public static void addPlane(Plane plane) {
        planes.addVehicle(plane);
    }

    public static void removePlane(String id) {
        Plane plane = (Plane) planes.getVehicle(id); // Get the Vehicle object from the hashmap

        planes.removeVehicle(plane.getID()); // Remove the Vehicle object from the hashmap
    }

    public static Plane getPlane(String id) {
        return (Plane) planes.getVehicle(id);
    }

    public static ArrayList<Plane> getAllPlanes() {
        ArrayList<Vehicle> allVehicles = planes.getVehicles();
        ArrayList<Plane> allPlanes = new ArrayList<>();

        for(Vehicle vehicle : allVehicles) {
            allPlanes.add((Plane) vehicle);
        }

        return allPlanes;
    }
}
