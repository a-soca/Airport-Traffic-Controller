package org.example.controllers;

import org.example.entities.Plane;

import java.util.concurrent.TimeUnit;

public class Pilot {
    Plane plane;

    public Pilot(Plane plane) {
        this.plane = plane;
    }

    public void startHoldingPattern(int duration) {
        System.out.println("Plane " + plane.getID() + " has entered a holding pattern above " + plane.getArrivalLocation().getID());

        for(int i = duration; i > 0; i--) {
            System.out.println("Plane " + plane.getID() + " is Holding for " + i + " seconds at " + plane.getArrivalLocation().getID());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Plane " + plane.getID() + " was interrupted (and crashed)");
                return;
            }
        }

        plane.getArrivalLocation().getAirTrafficController().landPlane(plane);
    }
}
