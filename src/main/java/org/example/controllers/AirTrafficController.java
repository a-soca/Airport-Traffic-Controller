package org.example.controllers;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AirTrafficController {
    private final Airport airport;

    /**
     * The logic controller for an {@link Airport}
     * @param airport The airport to control
     */
    public AirTrafficController(Airport airport) {
        this.airport = airport;
    }

    /**
     * Finds incoming traffic and determines which planes are ready to land
     */
    public void controlTraffic() {
        // Plane IDs are used to track flights as directly accessing the plane objects can cause errors when moving
        // the planes between lists. By using the central repository, the plane objects can be found regardless of
        // their status.
        ArrayList<String> planeIDs = new ArrayList<>(); // Array to store plane IDs

        // Get all the plane IDs from the incoming flight list
        for(Plane plane : airport.getIncomingFlights()) {
            planeIDs.add(plane.getID());
        }

        for(String id : planeIDs) { // For each plane,
            Plane plane = PlaneRepository.getPlane(id);
            // If the plane has reached its expected arrival time,
            if(plane.getArrivalTime().isBefore(LocalDateTime.now())) {
                landPlane(plane); // Attempt to land the plane
            }
        }
    }

    /**
     * Attempts to land a plane. If the airport has had a plane land within the minimum duration between landings,
     * make the pilot perform a holding pattern until they are able to land
     * @param plane The plane to attempt to land
     */
    public void landPlane(Plane plane) {
        System.out.println("Plane " + plane.getID() + " attempting to land at " + airport.getID()); // Log message
        plane.setArrivalLocation(airport.getID()); // Update the arrival location of the plane

        // Determine if the previous plane has landed for longer than the safety separation
        int requiredHoldTime = (int) airport.getLandingTimeSeparation()
                .minus(airport.getTimeSinceLastArrival()).toSeconds();

        if(requiredHoldTime > 0) { // If holding time is required,
            // Tell pilot to perform a holding pattern for the appropriate duration
            plane.getPilot().startHoldingPattern(requiredHoldTime);
        } else { // Otherwise,
            airport.addLandedFlight(plane); // Add the plane to the airport's landed flights
            airport.removeIncomingFlight(plane); // Remove the plane from the incoming flight queue
            plane.setLanded(); // Set the plane to landed
            System.out.println("Plane " + plane.getID() + " landed at " + airport.getID()); // Update log
        }
    }
}
