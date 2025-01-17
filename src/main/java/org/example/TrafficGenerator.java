package org.example;

import org.example.entities.Plane;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TrafficGenerator {
    public void read() {
        File file = new File("src/main/resources/Flights.csv");

        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        BufferedReader reader = new BufferedReader(fileReader);

        // Store each column data
        ArrayList<String> flightCodes = new ArrayList<>();
        ArrayList<String> arrivalTimes = new ArrayList<>();
        ArrayList<String> arrivalLocations = new ArrayList<>();

        String line; // The line read by the buffered reader
        String[] tokens; // Each cell of the CSV (using the comma as a delimiter)

        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            tokens = line.split(",");
            if (tokens.length != 3) { // Skip any lines without 3 columns
                continue;
            }
            flightCodes.add(tokens[0].trim());
            arrivalTimes.add(tokens[1].trim());
            arrivalLocations.add(tokens[2].trim());
        }

        generateFlights(flightCodes, arrivalTimes, arrivalLocations);
    }

    public void generateFlights(ArrayList<String> flightCodes, ArrayList<String> arrivalTimes, ArrayList<String> arrivalLocations) {
        for (int i = 0; i < flightCodes.size(); i++) {
            int delay = Integer.parseInt(arrivalTimes.get(i)); // Parse the integer
            LocalDateTime arrivalTime = LocalDateTime.now().plusSeconds(delay); // Generate the actual arrival time

            System.out.println("Generated flight " + flightCodes.get(i) + " to " + arrivalLocations.get(i));

            new Plane(flightCodes.get(i), arrivalTime, arrivalLocations.get(i)); // Create the plane
        }
    }
}
