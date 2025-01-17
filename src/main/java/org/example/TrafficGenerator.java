package org.example;

import org.example.entities.Plane;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrafficGenerator {
    public void readBlueprint() {
        File file = new File("src/main/resources/Flights.csv"); // The location of the blueprint CSV

        FileReader fileReader; // File reader used to access the CSV
        try { // Try to create a file reader for the file
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) { // If the file is missing,
            throw new RuntimeException(e); // Throw an exception
        }

        BufferedReader reader = new BufferedReader(fileReader); // Used to read each line of the file

        // Store each columns data
        ArrayList<String> flightCodes = new ArrayList<>();
        ArrayList<String> arrivalTimes = new ArrayList<>();
        ArrayList<String> arrivalLocations = new ArrayList<>();

        String line; // The line read by the buffered reader
        String[] tokens; // Each cell of the CSV (using the comma as a delimiter)

        while(true) { // Loop until break
            try { // Try to read the next line
                if ((line = reader.readLine()) == null) break; // If the next line is empty, escape the loop
            } catch (IOException e) { // If an error occurs,
                throw new RuntimeException(e); // Throw an exception
            }

            tokens = line.split(","); // Split the line into tokens separated by a comma (CSV)
            if (tokens.length != 3) { // Skip any lines without 3 columns
                continue;
            }

            // Store the tokens in their respective arrays
            flightCodes.add(tokens[0].trim());
            arrivalTimes.add(tokens[1].trim());
            arrivalLocations.add(tokens[2].trim());
        }

        // Generate the flights using the data collected from the blueprint
        generateFlights(flightCodes, arrivalTimes, arrivalLocations);
    }

    /**
     * Method used to create a batch of flights
     * @param flightCodes Array of unique IDs to give the flights
     * @param arrivalTimes Array of delays in seconds from the point of generation to landing
     * @param arrivalLocations Array of IDs of airports the planes will land at
     */
    public void generateFlights(ArrayList<String> flightCodes, ArrayList<String> arrivalTimes, ArrayList<String> arrivalLocations) {
        for (int i = 0; i < flightCodes.size(); i++) { // For all blueprints,
            int delay = Integer.parseInt(arrivalTimes.get(i)); // Parse the integer
            LocalDateTime arrivalTime = LocalDateTime.now().plusSeconds(delay); // Generate the actual arrival time

            System.out.println("Generated flight " + flightCodes.get(i) + " to " + arrivalLocations.get(i));

            new Plane(flightCodes.get(i), arrivalTime, arrivalLocations.get(i)); // Create the plane
        }
    }
}
