package org.example;

import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AirTrafficController {
    private final Airport airport;

    public AirTrafficController(Airport airport) {
        this.airport = airport;
    }

    public void controlTraffic() {
        ArrayList<String> planeIDs = new ArrayList<>();;

        for(Plane plane : airport.getIncomingFlights()) {
            planeIDs.add(plane.getID());
        }

        for(String id : planeIDs) {
            Plane plane = PlaneRepository.getPlane(id);
            if(plane.getArrivalTime().isBefore(LocalDateTime.now())) {
                landPlane(plane);
            }
        }
    }

    public void landPlane(Plane plane) {
        System.out.println("Plane " + plane.getID() + " attempting to land at " + airport.getID());
        plane.setArrivalLocation(airport.getID()); // Update the arrival location of the plane

        // Determine if the previous plane has landed for longer than the safety separation
        int requiredHoldTime = (int) airport.getLandingTimeSeparation().minus(airport.getTimeSinceLastArrival()).toSeconds();

        if(requiredHoldTime > 0) { // If holding time is required,
            plane.getPilot().startHoldingPattern(requiredHoldTime); // Set the plane to a holding pattern for the appropriate duration
        } else { // Otherwise,
            airport.addLandedFlight(plane); // Add the plane to the airport landed flights
            airport.removeIncomingFlight(plane);
            plane.setLanded(); // Set the plane to landed
            System.out.println("Plane " + plane.getID() + " landed at " + airport.getID()); // Update log
        }
    }
}
