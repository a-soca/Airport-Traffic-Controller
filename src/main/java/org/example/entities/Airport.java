package org.example.entities;

import org.example.controllers.AirTrafficController;
import org.example.repository.AirportRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Airport extends Location {
    private final ArrayList<Plane> incomingFlights;
    private final ArrayList<Plane> landedFlights;
    private final Duration landingTimeSeparation = Duration.ofSeconds(60);

    private final AirTrafficController airTrafficController;

    public Airport(String location) {
        setID(location);
        landedFlights = new ArrayList<>();
        incomingFlights = new ArrayList<>();

        airTrafficController = new AirTrafficController(this);

        AirportRepository.addAirport(this);
    }

    public Duration getTimeSinceLastArrival() {
        if(landedFlights.isEmpty()) { // If no planes have landed,
            return landingTimeSeparation; // Return a value which will not block other planes
        }

        Plane lastLanded = landedFlights.getLast(); // Get the last arrival
        LocalDateTime landTime = lastLanded.getArrivalTime(); // Get the arrival time

        return Duration.between(landTime, LocalDateTime.now()); // Get the time between now and the last arrival
    }

    public Duration getLandingTimeSeparation() {
        return landingTimeSeparation;
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
