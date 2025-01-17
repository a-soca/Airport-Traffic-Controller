package org.example.controllers;

import org.example.entities.Plane;

import java.util.concurrent.TimeUnit;

public class Pilot {
    Plane plane;

    /**
     * The logic controller for a {@link Plane}
     * @param plane The plane the pilot controls
     */
    public Pilot(Plane plane) {
        this.plane = plane;
    }

    /**
     * When a {@link Plane} cannot land, it must enter a holding pattern to delay.
     * @param duration The time the holding pattern should last
     */
    public void startHoldingPattern(int duration) {
        // Print log message
        System.out.println(
                "Plane " + plane.getID() +
                " has entered a holding pattern above " + plane.getArrivalLocation().getID());

        // For the specified duration, start a countdown and show a message
        for(int i = duration; i > 0; i--) {
            // Print log message
            System.out.println(
                    "Plane " + plane.getID() +
                    " is Holding for " + i +
                    " seconds at " + plane.getArrivalLocation().getID());

            try { // Wait for one second before repeating
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) { // If the program interrupts, update log
                System.out.println("Plane " + plane.getID() + " was interrupted (and crashed)");
                return;
            }
        }

        // After the countdown is complete, attempt to land again
        plane.getArrivalLocation().getAirTrafficController().landPlane(plane);
    }
}
