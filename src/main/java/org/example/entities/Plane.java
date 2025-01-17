package org.example.entities;


import org.example.controllers.Pilot;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.example.repository.AirportRepository.getAirport;

public class Plane extends Vehicle {
    private LocalDateTime arrivalTime;
    private String arrivalLocation;
    private final Pilot pilot;

    public Plane(String id, LocalDateTime plannedArrivalTime, String arrivalLocation) {
        super(id); // Set the plane ID

        // Set the plane's travel plan
        setArrivalTime(plannedArrivalTime);
        setArrivalLocation(arrivalLocation);

        this.pilot = new Pilot(this);

        PlaneRepository.addPlane(this); // Add the plane to the repository
        AirportRepository.getAirport(arrivalLocation).addIncomingFlight(this);
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    private void setArrivalTime(LocalDateTime time) {
        this.arrivalTime = time;
    }

    public Airport getArrivalLocation() {
        return getAirport(arrivalLocation);
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
