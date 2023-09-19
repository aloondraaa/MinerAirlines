/*
 * Alondra N Gonzalez-Ayala
 * 
 * April 4, 2023
 * 
 * CS 3331 - Advanced Object-Oriented Programming
 * 
 * Dr. Daniel Mejia
 * 
 * Programming Assignment 4
 * 
 * ReadFiles.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.io.*;

/**
 * Class that reads CSV files and return hash maps with objects of each type
 */
public class ReadFiles {
    /**
     * Method to create hash map that contains flight objects
     * @return Hash Map that contains all flight information
     * @throws Exception Exception
     */
    public static HashMap<Integer, Flight> flightObjects() throws Exception {
        // Scanner to read file
        Scanner scnr = new Scanner(new File("FlightSchedule.csv"));
        
        // Hash Map to store flight objects
        HashMap <Integer, Flight> flights = new HashMap<Integer, Flight>();
		
        // Read line
        String headerLine = scnr.nextLine();

        // Split header by ","
        String[] headers = headerLine.split(",");

        // Hash Map to store header titles
        HashMap<String, Integer> headerTitle = new HashMap<String, Integer>();

        // Populate hash map with header titles
        for (int i = 0; i < headers.length; i++) {
            headerTitle.put(headers[i], i);
        }

		// For every line in the file
		while(scnr.hasNextLine()) {

			// Read line
			String line = scnr.nextLine();

			// Split flight info by ","
			String[] contents = line.split(",");

            // Set each attribute depending on header title
            int flightID = Integer.parseInt(contents[headerTitle.get("ID")]);
            int flightNumber = Integer.parseInt(contents[headerTitle.get("Flight Number")]);
            String originAirport = contents[headerTitle.get("Origin Airport")];
            String originCode = contents[headerTitle.get("Origin Code")];
            String originAirportCity = contents[headerTitle.get("Origin Airport City")];
            String originAirportState = contents[headerTitle.get("Origin Airport State")];
            String originAirportCountry = contents[headerTitle.get("Origin Airport Country")];
            String destinationAirport = contents[headerTitle.get("Destination Airport")];
            String destinationCode = contents[headerTitle.get("Destination Code")];
            String destinationAirportCity = contents[headerTitle.get("Destination Airport City")];
            String destinationAirportState = contents[headerTitle.get("Destination Airport State")];
            String destinationAirportCountry = contents[headerTitle.get("Destination Airport Country")];
            double originAirportFee = Double.parseDouble(contents[headerTitle.get("Origin Airport Fee")]);
            double destinationAirportFee = Double.parseDouble(contents[headerTitle.get("Destination Airport Fee")]);
            boolean originAirportLounge = Boolean.parseBoolean(contents[headerTitle.get("Origin Airport Lounge")]);
            boolean destinationAirportLounge = Boolean.parseBoolean(contents[headerTitle.get("Destination Airport Lounge")]);
            String departureDate = contents[headerTitle.get("Departing Date")];
            String departureTime = contents[headerTitle.get("Departing Time")];
            int duration = Integer.parseInt(contents[headerTitle.get("Duration")]);
            int distance = Integer.parseInt(contents[headerTitle.get("Distance")]);
            int timeZoneDifference = Integer.parseInt(contents[headerTitle.get("Time Zone Difference")]);
            String arrivalDate = contents[headerTitle.get("Arrival Date")];
            String arrivalTime = contents[headerTitle.get("Arrival Time")];
            String flightType = contents[headerTitle.get("Type")];
            int surcharge = Integer.parseInt(contents[headerTitle.get("Surcharge")]);
            boolean foodServed = Boolean.parseBoolean(contents[headerTitle.get("Food Served")]);
            int routeCost = Integer.parseInt(contents[headerTitle.get("Route Cost")]);
            int minerPoints = Integer.parseInt(contents[headerTitle.get("Miner Points")]);
            int totalSeats = Integer.parseInt(contents[headerTitle.get("Total Seats")]);
            int firstClassSeats = Integer.parseInt(contents[headerTitle.get("First Class Seats")]);
            int businessClassSeats = Integer.parseInt(contents[headerTitle.get("Business Class Seats")]);
            int mainCabinSeats = Integer.parseInt(contents[headerTitle.get("Main Cabin Seats")]);
            int firstClassPrice = Integer.parseInt(contents[headerTitle.get("First Class Price")]);
            int businessClassPrice = Integer.parseInt(contents[headerTitle.get("Business Class Price")]);
            int mainCabinPrice = Integer.parseInt(contents[headerTitle.get("Main Cabin Price")]);
            

            // Create an instance of the flight factory
            FlightCreator flightFactory = new FlightCreator();

			// Create a flight object
			Flight flightsList = flightFactory.createFlight(flightID, flightNumber, originAirport, originCode, originAirportCity, originAirportState, 
                                                            originAirportCountry, destinationAirport, destinationCode, destinationAirportCity, 
                                                            destinationAirportState, destinationAirportCountry, originAirportFee, destinationAirportFee, 
                                                            originAirportLounge, destinationAirportLounge, departureDate, departureTime, duration, distance, 
                                                            timeZoneDifference, arrivalDate, arrivalTime, flightType, surcharge, foodServed, routeCost, minerPoints, 
                                                            totalSeats, firstClassSeats, businessClassSeats, mainCabinSeats, firstClassPrice, businessClassPrice, mainCabinPrice);
			
            // Add to hash map
            flights.put(flightID, flightsList);
            
		}
        return flights;
    }

    /**
     * Method to create hash map that contains people objects
     * @return Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */ 
    public static HashMap<Integer, Person> personObjects() throws Exception {
        // Scanner to read file
        Scanner scnr = new Scanner(new File("CustomerListPA4.csv"));
        
        // Hash Map to store flight objects
        HashMap <Integer, Person> people = new HashMap<Integer, Person>();
		
        // Read line
        String headerLine = scnr.nextLine();

        // Split header by ","
        String[] headers = headerLine.split(",");

        // Hash Map to store header titles
        HashMap<String, Integer> headerTitle = new HashMap<String, Integer>();

        // Populate hash map with header titles
        for (int i = 0; i < headers.length; i++) {
            headerTitle.put(headers[i], i);
        }

		// For every line in the file
		while(scnr.hasNextLine()) {

			// Read line
			String line = scnr.nextLine();

			// Split flight info by ","
			String[] contents = line.split(",");

            // Set each attribute depending on header title
            int ID = Integer.parseInt(contents[headerTitle.get("ID")]);
            String firstName = contents[headerTitle.get("First Name")];
            String lastName = contents[headerTitle.get("Last Name")];
            String dob = contents[headerTitle.get("DOB")];
            String role = contents[headerTitle.get("Role")];
            double moneyAvailable = Double.parseDouble(contents[headerTitle.get("Money Available")]);
            int flightsPurchased = Integer.parseInt(contents[headerTitle.get("Flights Purchased")]);
            boolean minerAirMembership = Boolean.parseBoolean(contents[headerTitle.get("MinerAirlines Membership")]);
            String username = contents[headerTitle.get("Username")];
            String password = contents[headerTitle.get("Password")];

            // Create an instance of the person factory
            PersonCreator personFactory = new PersonCreator();

			// Create a flight object
			Person personList = personFactory.createPerson(ID, firstName, lastName, dob, role, moneyAvailable, flightsPurchased, 
                                                           minerAirMembership, username, password);

            // Add to hash map
            people.put(ID, personList);
		}
        return people;
    }
}