package org.example.entities;

import org.example.controllers.AirTrafficController;
import org.example.repository.AirportRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.example.World.getAirportRepository;

public class Airport extends Location {
    private final ArrayList<Plane> incomingFlights; // Flights bound for this airport
    private final ArrayList<Plane> landedFlights; // Flights which have landed at this airport
    private Duration landingTimeSeparation = Duration.ofSeconds(60); // Default time 60s (following requirements)

    private final AirTrafficController airTrafficController; // The logic controller for the airport

    // A possible extension would be adding a location repository to store multiple runways for each airport
    // to improve landing efficiency

    /**
     * Airports are a {@link Location} that planes are able to land at.
     * @param location The code used to identify the airport
     */
    public Airport(String location) {
        super(location); // Set the airport identifier

        landedFlights = new ArrayList<>(); // Initialise arraylist to store landed flights
        incomingFlights = new ArrayList<>(); // Initialise arraylist to store incoming flights

        airTrafficController = new AirTrafficController(this); // Create an air traffic controller for the airport

        getAirportRepository().addAirport(this); // Add the airport to the repository
    }

    /**
     * Finds the time between the plane which last landed at this airport and the current time. Returns the
     * {@link #landingTimeSeparation} if no planes have ever landed to prevent blocking.
     * @return Time between previously landed plane and current time
     */
    public Duration getTimeSinceLastArrival() {
        if(landedFlights.isEmpty()) { // If no planes have landed,
            return landingTimeSeparation; // Return a value which will not block other planes
        }

        Plane lastLanded = landedFlights.getLast(); // Get the last arrival
        LocalDateTime landTime = lastLanded.getArrivalTime(); // Get the arrival time

        return Duration.between(landTime, LocalDateTime.now()); // Get the time between now and the last arrival
    }

    public boolean hasNoIncomingTraffic() {
        return incomingFlights.isEmpty();
    }

    public Duration getLandingTimeSeparation() {
        return landingTimeSeparation;
    }

    public void setLandingTimeSeparation(int seconds) {
        this.landingTimeSeparation = Duration.ofSeconds(seconds);
    }

    public void addLandedFlight(Plane plane) {
        landedFlights.add(plane);
    }

    public void addIncomingFlight(Plane plane) {
        incomingFlights.add(plane);
    }

    public void removeIncomingFlight(Plane plane) {
        incomingFlights.remove(plane);
    }

    public ArrayList<Plane> getIncomingFlights() {
        return incomingFlights;
    }

    public AirTrafficController getAirTrafficController() {
        return airTrafficController;
    }

    @Override
    public String toString() {
        return "Airport ID : " + getID() +
                " | Landing Time Separation : " + getLandingTimeSeparation().getSeconds() + "s";
    }
}
