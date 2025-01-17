package org.example.repository;

import org.example.entities.Plane;

public class PlaneRepository extends VehicleRepository {
    private static final VehicleRepository planes = new VehicleRepository();

    /**
     * A repository used to store {@link Plane} objects
     */
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
}
