package org.example.entities;


import org.example.controllers.Pilot;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.example.World.getAirportRepository;


public class Plane extends Vehicle {
    private LocalDateTime arrivalTime; // The planned or actual time the plane arrives at an airport
    private String arrivalLocation; // The location code of the airport the plane will land at
    private final Pilot pilot; // The pilot controlling the plane

    /**
     * Planes are a {@link Vehicle} which can land at an {@link Airport}. They have a unique identifier.
     * @param id The code used to identify the plane
     * @param plannedArrivalTime The arrival time of the plane assuming no delays
     * @param arrivalLocation The code of the airport the plane is travelling to
     */
    public Plane(String id, LocalDateTime plannedArrivalTime, String arrivalLocation) {
        super(id); // Set the plane ID

        // Set the plane's travel plan
        setArrivalTime(plannedArrivalTime);
        setArrivalLocation(arrivalLocation);

        this.pilot = new Pilot(this); // Add a pilot to control the plane

        PlaneRepository.addPlane(this); // Add the plane to the repository

        // Update the airport on a new arrival
        getAirportRepository().getAirport(arrivalLocation).addIncomingFlight(this);
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    private void setArrivalTime(LocalDateTime time) {
        this.arrivalTime = time;
    }

    public Airport getArrivalLocation() {
        return getAirportRepository().getAirport(arrivalLocation);
    }

    public void setArrivalLocation(String location) {
        this.arrivalLocation = location;
    }

    public void setLanded() {
        setArrivalTime(LocalDateTime.now());
    }

    public Pilot getPilot() {
        return pilot;
    }

    @Override
    public String toString() {
        return "Plane Code : " + getID()
                + " | Arrival time : " + getArrivalTime().truncatedTo(ChronoUnit.SECONDS)
                + " | Arrival location : " + getArrivalLocation().getID();
    }

}
