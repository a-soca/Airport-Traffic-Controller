package org.example.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Airport extends Location {
    private final ArrayList<Plane> landedFlights;
    private Duration landingTimeSeparation = Duration.ofSeconds(10);

    public Airport(String location) {
        setID(location);
        landedFlights = new ArrayList<>();
    }

    public void landPlane(Plane plane) {
        System.out.println("Plane " + plane.getID() + " attempting to land at " + getID());
        plane.setArrivalLocation(getID()); // Update the arrival location of the plane

        // Determine if the previous plane has landed for longer than the safety separation
        int requiredHoldTime = (int) landingTimeSeparation.minus(getTimeSinceLastArrival()).toSeconds();

        if(requiredHoldTime > 0) { // If holding time is required,
            plane.startHoldingPattern(requiredHoldTime); // Set the plane to a holding pattern for the appropriate duration
        } else { // Otherwise,
            landedFlights.add(plane); // Add the plane to the airport landed flights
            plane.setLanded(); // Set the plane to landed
            System.out.println("Plane " + plane.getID() + " landed at " + getID()); // Update log
        }
    }

    private Duration getTimeSinceLastArrival() { // TODO if empty
        if(landedFlights.isEmpty()) { // If no planes have landed,
            return landingTimeSeparation; // Return a value which will not block other planes
        }

        Plane lastLanded = landedFlights.getLast(); // Get the last arrival
        LocalDateTime landTime = lastLanded.getArrivalTime(); // Get the arrival time

        return Duration.between(landTime, LocalDateTime.now()); // Get the time between now and the last arrival
    }
}
