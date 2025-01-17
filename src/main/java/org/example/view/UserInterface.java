package org.example.view;

import org.example.TrafficGenerator;
import org.example.entities.Airport;
import org.example.entities.Plane;
import org.example.repository.AirportRepository;
import org.example.repository.PlaneRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import static org.example.World.getAirportRepository;

public abstract class UserInterface {
    protected static final Scanner scanner = new Scanner(System.in); // Set the scanner source

    public abstract void run();

    /**
     * Clears the screen by adding 50 carriage returns (used for cross compatability between all terminals)
     */
    protected void clearScreen() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    /**
     * Prints the header of the application alongside a prompt
     * @param title The prompt to show the user under the banner
     */
    protected void printTitle(String title) {
        clearScreen();
        System.out.println(
                "------------------------------" +
                "\n\tAir Traffic Controller" +
                "\n------------------------------"); // Output the name of the application
        System.out.println("-> " + title + "\n");
    }

    /**
     * Prints the options available to the user
     */
    protected abstract void printMenu();
}
