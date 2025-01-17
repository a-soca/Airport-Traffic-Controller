package org.example.entities;


import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.example.repository.AirportRepository.getAirport;

public class Plane extends Vehicle {
    private LocalDateTime arrivalTime;
    private String arrivalLocation;

    public Plane(String id, LocalDateTime plannedArrivalTime, String arrivalLocation) {
        super(id); // Set the plane ID

        // Set the plane's travel plan
        setArrivalTime(plannedArrivalTime);
        setArrivalLocation(arrivalLocation);

        PlaneRepository.addPlane(this); // Add the plane to the repository
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

    public void startHoldingPattern(int duration) {
        System.out.println("Plane " + getID() + " has entered a holding pattern above " + getArrivalLocation().getID());

        for(int i = duration; i > 0; i--) {
            System.out.println("Plane " + getID() + " is Holding for " + i + " seconds at " + getArrivalLocation().getID());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Plane " + getID() + " was interrupted (and crashed)");
                return;
            }
        }

        getArrivalLocation().landPlane(this);
    }

}
