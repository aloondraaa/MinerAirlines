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
 * AutoPurchase.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Class that handles AutoPurchases
 */
public class AutoPurchase {

    /**
     * Method to complete AutoPurchase of tickets
     * @param fileName File Name of AutoPurchase
     * @param flights Hash Map that contains all flight information
     * @param people Hash Map that contains all customer/employee information
     * @param writer Writer
     * @throws Exception Exception
     */
    public static void autoPurchaseTickets(String fileName, HashMap<Integer, Flight> flights, HashMap<Integer, Person> people, Writer writer) throws Exception {

        // Scanner to read file
        Scanner scnr = new Scanner(new File(fileName));

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
            String firstName = contents[headerTitle.get("First Name")];
            String lastName = contents[headerTitle.get("Last Name")];
            int flightID = Integer.parseInt(contents[headerTitle.get("Flight ID")]);
            String ticketType = contents[headerTitle.get("Ticket Type")];
            int numOfTickets = Integer.parseInt(contents[headerTitle.get("Ticket Quantity")]);

            // Find person
            Person person = Person.findByName(people, firstName, lastName);

            // Access hash map and create an object with the flight info
            Flight flightInfo = flights.get(flightID);

            // If flight is Scheduled, proceed
            if (flightInfo.getFlightStatus().equalsIgnoreCase("Scheduled")) {

                // Calculate First Class Tickets
                if (ticketType.equals("First Class")) {

                    // Get the total seats for first class
                    int firstClassSeats = flightInfo.getFirstClassSeats();

                    // If there are enough seats available in first class
                    if (numOfTickets <= firstClassSeats) {

                        double subtotal = 0.0;

                        // The subtotal equals the number of tickets * the price, apply employee discount if needed
                        if (person.getRole().equalsIgnoreCase("Employee")) {
                            double discount = flightInfo.getFirstClassPrice() * flightInfo.getEmployeeDiscountFirst();
                            subtotal = (numOfTickets * (flightInfo.getFirstClassPrice() - discount));
                        } else {
                            subtotal = (numOfTickets * flightInfo.getFirstClassPrice());
                        }

                        // If flight is international, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            int surcharge = flightInfo.getSurcharge() * numOfTickets;
                            subtotal += surcharge;
                        }

                        // Discount given
                        double minerAirlinesDiscountTotal = 0.0;

                        // If the person has the MinerAir membership, apply the discount
                        if (person.getMinerAirMembership()) {
                            minerAirlinesDiscountTotal = flightInfo.getMinerAirlinesDiscount() * subtotal;
                            subtotal -= minerAirlinesDiscountTotal;
                        }

                        // Get customer's money available
                        double moneyAvailable = person.getMoneyAvailable();

                        // Calculate tax, fees and total
                        double minerAirlinesFee = flightInfo.getMinerAirlinesFee();
                        double securityFee = flightInfo.getSecurityFee() * numOfTickets;
                        double originAirportFee = flightInfo.getOriginAirportFee();
                        double destinationAirportFee = flightInfo.getDestinationAirportFee();
                        double fees = Math.round((minerAirlinesFee + securityFee + originAirportFee + destinationAirportFee) * 100.0) / 100.0;
                        double subtotalWithFees = subtotal + fees;
                        double tax = Math.round((flightInfo.getTax() * subtotalWithFees) * 100.0) / 100.0;
                        double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                        // If the total is less than or equal to the customer's money available
                        if (total <= moneyAvailable) {

                            // Substract the total from the money
                            moneyAvailable -= total;

                            // Get the total seats
                            int totalSeats = flightInfo.getTotalSeats();

                            // Substract first class seats and total seats
                            firstClassSeats -= numOfTickets;
                            totalSeats -= numOfTickets;

                            // Get some variables for ticket
                            String originAirport = flightInfo.getOriginAirport();
                            String originCode = flightInfo.getOriginCode();
                            String destinationAirport = flightInfo.getDestinationAirport();
                            String destinationCode = flightInfo.getDestinationCode();
                            String departureDate = flightInfo.getDepartureDate();
                            String departureTime = flightInfo.getDepartureTime();
                            String arrivalDate = flightInfo.getArrivalDate();
                            String arrivalTime = flightInfo.getArrivalTime();
                            String ticketStatus = "Confirmed";
                            int flightsPurchased = person.getFlightsPurchased();
                            flightsPurchased++;

                            // Create random confirmation number
                            Random random = new Random();
                            int confirmationNumber = random.nextInt(10000);

                            // Create a ticket for customer
                            Ticket customerTicket = new Ticket(ticketType, originAirport, originCode, destinationAirport, destinationCode, departureDate, departureTime, arrivalDate, arrivalTime, numOfTickets, confirmationNumber, flightID, subtotal, total, ticketStatus, minerAirlinesDiscountTotal, tax);

                            // Create a ticket for flight
                            Ticket flightTicket = new Ticket(person.getFirstName(), person.getLastName(), ticketType, numOfTickets, subtotal, total, confirmationNumber, minerAirlinesDiscountTotal, tax);

                            // Set seats sold to num of tickets purchased
                            int firstClassSeatsSold = flightInfo.getFirstClassSeatsSold() + numOfTickets;
                            int totalSeatsSold = flightInfo.getTotalSeatsSold() + numOfTickets;

                            // Set revenue to new total
                            double firstClassRevenue = flightInfo.getFirstClassRevenue() + subtotal;
                            double totalRevenue = flightInfo.getTotalRevenue() + subtotal;

                            // Set fees/taxes charged and dicount given 
                            double minerAirlinesFeeCharged = flightInfo.getMinerAirlinesFeeCharged() + minerAirlinesFee;
                            double securityFeeCharged = flightInfo.getSecurityFeeCharged() + securityFee;
                            double taxCharged = flightInfo.getTaxCharged() + tax;
                            double discounted = flightInfo.getDiscounted() + minerAirlinesDiscountTotal;
                            double savings = person.getSavings() + minerAirlinesDiscountTotal;
                            double originAirportFeeCharged = flightInfo.getOriginAirportFeeCharged() + originAirportFee;
                            double destinationAirportFeeCharged = flightInfo.getDestinationAirportFeeCharged() + destinationAirportFee;


                            writer.writeLog("LogActions.txt", "Order successful. " + person.getFirstName() + " " + person.getLastName() + " purchased " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID + " for $" + total);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s money available updated from $" + person.getMoneyAvailable() + " to $" + moneyAvailable);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s flights purchased updated from " + person.getFlightsPurchased() + " flight(s) to " + flightsPurchased + " flight(s)");
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s savings updated from $" + person.getSavings() + " to $" + savings);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + flightInfo.getFirstClassSeats() + " seat(s) to " + firstClassSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + flightInfo.getFirstClassSeatsSold() + " seat(s) sold to " + firstClassSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) sold updated from " + flightInfo.getTotalSeatsSold() + " seat(s) sold to " + totalSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " First Class revenue updated from $" + flightInfo.getFirstClassRevenue() + " to $" + firstClassRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total revenue updated from $" + flightInfo.getTotalRevenue() + " to $" + totalRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total MinerAirlines fee charged updated from $" + flightInfo.getMinerAirlinesFeeCharged() + " to $" + minerAirlinesFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Security fee charged updated from $" + flightInfo.getSecurityFeeCharged() + " to $" + securityFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Tax charged updated from $" + flightInfo.getTaxCharged() + " to $" + taxCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total discount updated from $" + flightInfo.getDiscounted() + " to $" + discounted);
                            person.setMoneyAvailable(moneyAvailable);
                            person.setFlightsPurchased(flightsPurchased);
                            person.setSavings(savings);
                            flightInfo.setFirstClassSeats(firstClassSeats);
                            flightInfo.setTotalSeats(totalSeats);
                            flightInfo.setFirstClassSeatsSold(firstClassSeatsSold);
                            flightInfo.setTotalSeatsSold(totalSeatsSold);
                            flightInfo.setFirstClassRevenue(firstClassRevenue);
                            flightInfo.setTotalRevenue(totalRevenue);
                            flightInfo.setMinerAirlinesFeeCharged(minerAirlinesFeeCharged);
                            flightInfo.setSecurityFeeCharged(securityFeeCharged);
                            flightInfo.setTaxCharged(taxCharged);
                            flightInfo.setDiscounted(discounted);
                            flightInfo.setOriginAirportFeeCharged(originAirportFeeCharged);
                            flightInfo.setDestinationAirportFeeCharged(destinationAirportFeeCharged);
                            flightInfo.setTicketsPurchased(flightTicket);
                            person.setTicketsPurchased(customerTicket);

                            continue;

                        // If the total is more than the money available, write in log
                        } else {
                            writer.writeLog("LogActions.txt", "Order unsuccessful. " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                            continue;
                        }
                        
                    // If there are not enough seats available, write in log
                    } else {
                        writer.writeLog("LogActions.txt", "Order unsuccessful. Not enough " + ticketType + " seats available in Flight ID " + flightID);
                        continue;
                    }
                }

                // Calculate Business Class Tickets
                if (ticketType.equals("Business Class")) {

                    // Get the total seats for business class
                    int businessClassSeats = flightInfo.getBusinessClassSeats();

                    // If there are enough seats available in business class
                    if (numOfTickets <= businessClassSeats) {

                        double subtotal = 0.0;

                        // The subtotal equals the number of tickets * the price, apply employee discount if needed
                        if (person.getRole().equalsIgnoreCase("Employee")) {
                            double discount = flightInfo.getBusinessClassPrice() * flightInfo.getEmployeeDiscountBusinessMain();
                            subtotal = (numOfTickets * (flightInfo.getBusinessClassPrice() - discount));
                        } else {
                            subtotal = (numOfTickets * flightInfo.getBusinessClassPrice());
                        }

                        // If flight is international, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            int surcharge = flightInfo.getSurcharge() * numOfTickets;
                            subtotal += surcharge;
                        }

                        // Discount given
                        double minerAirlinesDiscountTotal = 0.0;

                        // If the person has the MinerAir membership, apply the discount
                        if (person.getMinerAirMembership()) {
                            minerAirlinesDiscountTotal = flightInfo.getMinerAirlinesDiscount() * subtotal;
                            subtotal -= minerAirlinesDiscountTotal;
                        }

                        // Get customer's money available
                        double moneyAvailable = person.getMoneyAvailable();

                        // Calculate tax, fees and total
                        double minerAirlinesFee = flightInfo.getMinerAirlinesFee();
                        double securityFee = flightInfo.getSecurityFee() * numOfTickets;
                        double originAirportFee = flightInfo.getOriginAirportFee();
                        double destinationAirportFee = flightInfo.getDestinationAirportFee();
                        double fees = Math.round((minerAirlinesFee + securityFee + originAirportFee + destinationAirportFee) * 100.0) / 100.0;
                        double subtotalWithFees = subtotal + fees;
                        double tax = Math.round((flightInfo.getTax() * subtotalWithFees) * 100.0) / 100.0;
                        double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                        // If the total is less than or equal to the customer's money available
                        if (total <= moneyAvailable) {

                            // Substract the total from the money
                            moneyAvailable -= total;

                            // Get the total seats
                            int totalSeats = flightInfo.getTotalSeats();

                            // Substract business class seats and total seats
                            businessClassSeats -= numOfTickets;
                            totalSeats -= numOfTickets;

                            // Get some variables for ticket
                            String originAirport = flightInfo.getOriginAirport();
                            String originCode = flightInfo.getOriginCode();
                            String destinationAirport = flightInfo.getDestinationAirport();
                            String destinationCode = flightInfo.getDestinationCode();
                            String departureDate = flightInfo.getDepartureDate();
                            String departureTime = flightInfo.getDepartureTime();
                            String arrivalDate = flightInfo.getArrivalDate();
                            String arrivalTime = flightInfo.getArrivalTime();
                            String ticketStatus = "Confirmed";
                            int flightsPurchased = person.getFlightsPurchased();
                            flightsPurchased++;

                            // Create random confirmation number
                            Random random = new Random();
                            int confirmationNumber = random.nextInt(10000);

                            // Create a ticket for customer
                            Ticket customerTicket = new Ticket(ticketType, originAirport, originCode, destinationAirport, destinationCode, departureDate, departureTime, arrivalDate, arrivalTime, numOfTickets, confirmationNumber, flightID, subtotal, total, ticketStatus, minerAirlinesDiscountTotal, tax);

                            // Create a ticket for flight
                            Ticket flightTicket = new Ticket(person.getFirstName(), person.getLastName(), ticketType, numOfTickets, subtotal, total, confirmationNumber, minerAirlinesDiscountTotal, tax);

                            // Set seats sold to num of tickets purchased
                            int businessClassSeatsSold = flightInfo.getBusinessClassSeatsSold() + numOfTickets;
                            int totalSeatsSold = flightInfo.getTotalSeatsSold() + numOfTickets;

                            // Set revenue to new total
                            double businessClassRevenue = flightInfo.getBusinessClassRevenue() + subtotal;
                            double totalRevenue = flightInfo.getTotalRevenue() + subtotal;

                            // Set fees/taxes charged and dicount given 
                            double minerAirlinesFeeCharged = flightInfo.getMinerAirlinesFeeCharged() + minerAirlinesFee;
                            double securityFeeCharged = flightInfo.getSecurityFeeCharged() + securityFee;
                            double taxCharged = flightInfo.getTaxCharged() + tax;
                            double discounted = flightInfo.getDiscounted() + minerAirlinesDiscountTotal;
                            double savings = person.getSavings() + minerAirlinesDiscountTotal;
                            double originAirportFeeCharged = flightInfo.getOriginAirportFeeCharged() + originAirportFee;
                            double destinationAirportFeeCharged = flightInfo.getDestinationAirportFeeCharged() + destinationAirportFee;


                            writer.writeLog("LogActions.txt", "Order successful. " + person.getFirstName() + " " + person.getLastName() + " purchased " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID + " for $" + total);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s money available updated from $" + person.getMoneyAvailable() + " to $" + moneyAvailable);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s flights purchased updated from " + person.getFlightsPurchased() + " flight(s) to " + flightsPurchased + " flight(s)");
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s savings updated from $" + person.getSavings() + " to $" + savings);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + flightInfo.getBusinessClassSeats() + " seat(s) to " + businessClassSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + flightInfo.getBusinessClassSeatsSold() + " seat(s) sold to " + businessClassSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) sold updated from " + flightInfo.getTotalSeatsSold() + " seat(s) sold to " + totalSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Business Class revenue updated from $" + flightInfo.getBusinessClassRevenue() + " to $" + businessClassRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total revenue updated from $" + flightInfo.getTotalRevenue() + " to $" + totalRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total MinerAirlines fee charged updated from $" + flightInfo.getMinerAirlinesFeeCharged() + " to $" + minerAirlinesFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Security fee charged updated from $" + flightInfo.getSecurityFeeCharged() + " to $" + securityFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Tax charged updated from $" + flightInfo.getTaxCharged() + " to $" + taxCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total discount updated from $" + flightInfo.getDiscounted() + " to $" + discounted);
                            person.setMoneyAvailable(moneyAvailable);
                            person.setFlightsPurchased(flightsPurchased);
                            person.setSavings(savings);
                            flightInfo.setBusinessClassSeats(businessClassSeats);
                            flightInfo.setTotalSeats(totalSeats);
                            flightInfo.setBusinessClassSeatsSold(businessClassSeatsSold);
                            flightInfo.setTotalSeatsSold(totalSeatsSold);
                            flightInfo.setBusinessClassRevenue(businessClassRevenue);
                            flightInfo.setTotalRevenue(totalRevenue);
                            flightInfo.setMinerAirlinesFeeCharged(minerAirlinesFeeCharged);
                            flightInfo.setSecurityFeeCharged(securityFeeCharged);
                            flightInfo.setTaxCharged(taxCharged);
                            flightInfo.setDiscounted(discounted);
                            flightInfo.setOriginAirportFeeCharged(originAirportFeeCharged);
                            flightInfo.setDestinationAirportFeeCharged(destinationAirportFeeCharged);
                            flightInfo.setTicketsPurchased(flightTicket);
                            person.setTicketsPurchased(customerTicket);

                            continue;

                        // If the total is more than the money available, write in log
                        } else {
                            writer.writeLog("LogActions.txt", "Order unsuccessful. " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                            continue;
                        }
                        
                    // If there are not enough seats available, write in log
                    } else {
                        writer.writeLog("LogActions.txt", "Order unsuccessful. Not enough " + ticketType + " seats available in Flight ID " + flightID);
                        continue;
                    }
                }

                // Calculate Main Cabin Tickets
                if (ticketType.equals("Main Cabin")) {

                    // Get the total seats for main cabin
                    int mainCabinSeats = flightInfo.getMainCabinSeats();

                    // If there are enough seats available in main cabin
                    if (numOfTickets <= mainCabinSeats) {

                        double subtotal = 0.0;

                        // The subtotal equals the number of tickets * the price, apply employee discount if needed
                        if (person.getRole().equalsIgnoreCase("Employee")) {
                            double discount = flightInfo.getMainCabinPrice() * flightInfo.getEmployeeDiscountBusinessMain();
                            subtotal = (numOfTickets * (flightInfo.getMainCabinPrice() - discount));
                        } else {
                            subtotal = (numOfTickets * flightInfo.getMainCabinPrice());
                        }

                        // If flight is international, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            int surcharge = flightInfo.getSurcharge() * numOfTickets;
                            subtotal += surcharge;
                        }

                        // Discount given
                        double minerAirlinesDiscountTotal = 0.0;

                        // If the person has the MinerAir membership, apply the discount
                        if (person.getMinerAirMembership()) {
                            minerAirlinesDiscountTotal = flightInfo.getMinerAirlinesDiscount() * subtotal;
                            subtotal -= minerAirlinesDiscountTotal;
                        }

                        // Get customer's money available
                        double moneyAvailable = person.getMoneyAvailable();

                        // Calculate tax, fees and total
                        double minerAirlinesFee = flightInfo.getMinerAirlinesFee();
                        double securityFee = flightInfo.getSecurityFee() * numOfTickets;
                        double originAirportFee = flightInfo.getOriginAirportFee();
                        double destinationAirportFee = flightInfo.getDestinationAirportFee();
                        double fees = Math.round((minerAirlinesFee + securityFee + originAirportFee + destinationAirportFee) * 100.0) / 100.0;
                        double subtotalWithFees = subtotal + fees;
                        double tax = Math.round((flightInfo.getTax() * subtotalWithFees) * 100.0) / 100.0;
                        double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                        // If the total is less than or equal to the customer's money available
                        if (total <= moneyAvailable) {

                            // Substract the total from the money
                            moneyAvailable -= total;

                            // Get the total seats
                            int totalSeats = flightInfo.getTotalSeats();

                            // Substract main cabin seats and total seats
                            mainCabinSeats -= numOfTickets;
                            totalSeats -= numOfTickets;

                            // Get some variables for ticket
                            String originAirport = flightInfo.getOriginAirport();
                            String originCode = flightInfo.getOriginCode();
                            String destinationAirport = flightInfo.getDestinationAirport();
                            String destinationCode = flightInfo.getDestinationCode();
                            String departureDate = flightInfo.getDepartureDate();
                            String departureTime = flightInfo.getDepartureTime();
                            String arrivalDate = flightInfo.getArrivalDate();
                            String arrivalTime = flightInfo.getArrivalTime();
                            String ticketStatus = "Confirmed";
                            int flightsPurchased = person.getFlightsPurchased();
                            flightsPurchased++;

                            // Create random confirmation number
                            Random random = new Random();
                            int confirmationNumber = random.nextInt(10000);

                            // Create a ticket for customer
                            Ticket customerTicket = new Ticket(ticketType, originAirport, originCode, destinationAirport, destinationCode, departureDate, departureTime, arrivalDate, arrivalTime, numOfTickets, confirmationNumber, flightID, subtotal, total, ticketStatus, minerAirlinesDiscountTotal, tax);

                            // Create a ticket for flight
                            Ticket flightTicket = new Ticket(person.getFirstName(), person.getLastName(), ticketType, numOfTickets, subtotal, total, confirmationNumber, minerAirlinesDiscountTotal, tax);

                            // Set seats sold to num of tickets purchased
                            int mainCabinSeatsSold = flightInfo.getMainCabinSeatsSold() + numOfTickets;
                            int totalSeatsSold = flightInfo.getTotalSeatsSold() + numOfTickets;

                            // Set revenue to new total
                            double mainCabinRevenue = flightInfo.getMainCabinRevenue() + subtotal;
                            double totalRevenue = flightInfo.getTotalRevenue() + subtotal;

                            // Set fees/taxes charged and dicount given 
                            double minerAirlinesFeeCharged = flightInfo.getMinerAirlinesFeeCharged() + minerAirlinesFee;
                            double securityFeeCharged = flightInfo.getSecurityFeeCharged() + securityFee;
                            double taxCharged = flightInfo.getTaxCharged() + tax;
                            double discounted = flightInfo.getDiscounted() + minerAirlinesDiscountTotal;
                            double savings = person.getSavings() + minerAirlinesDiscountTotal;
                            double originAirportFeeCharged = flightInfo.getOriginAirportFeeCharged() + originAirportFee;
                            double destinationAirportFeeCharged = flightInfo.getDestinationAirportFeeCharged() + destinationAirportFee;


                            writer.writeLog("LogActions.txt", "Order successful. " + person.getFirstName() + " " + person.getLastName() + " purchased " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID + " for $" + total);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s money available updated from $" + person.getMoneyAvailable() + " to $" + moneyAvailable);
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s flights purchased updated from " + person.getFlightsPurchased() + " flight(s) to " + flightsPurchased + " flight(s)");
                            writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s savings updated from $" + person.getSavings() + " to $" + savings);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + flightInfo.getMainCabinSeats() + " seat(s) to " + mainCabinSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + flightInfo.getTotalSeats() + " seat(s) to " + totalSeats + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + flightInfo.getMainCabinSeatsSold() + " seat(s) sold to " + mainCabinSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) sold updated from " + flightInfo.getTotalSeatsSold() + " seat(s) sold to " + totalSeatsSold + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Main Cabin revenue updated from $" + flightInfo.getMainCabinRevenue() + " to $" + mainCabinRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total revenue updated from $" + flightInfo.getTotalRevenue() + " to $" + totalRevenue);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total MinerAirlines fee charged updated from $" + flightInfo.getMinerAirlinesFeeCharged() + " to $" + minerAirlinesFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Security fee charged updated from $" + flightInfo.getSecurityFeeCharged() + " to $" + securityFeeCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Tax charged updated from $" + flightInfo.getTaxCharged() + " to $" + taxCharged);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total discount updated from $" + flightInfo.getDiscounted() + " to $" + discounted);
                            person.setMoneyAvailable(moneyAvailable);
                            person.setFlightsPurchased(flightsPurchased);
                            person.setSavings(savings);
                            flightInfo.setMainCabinSeats(mainCabinSeats);
                            flightInfo.setTotalSeats(totalSeats);
                            flightInfo.setMainCabinSeatsSold(mainCabinSeatsSold);
                            flightInfo.setTotalSeatsSold(totalSeatsSold);
                            flightInfo.setMainCabinRevenue(mainCabinRevenue);
                            flightInfo.setTotalRevenue(totalRevenue);
                            flightInfo.setMinerAirlinesFeeCharged(minerAirlinesFeeCharged);
                            flightInfo.setSecurityFeeCharged(securityFeeCharged);
                            flightInfo.setTaxCharged(taxCharged);
                            flightInfo.setDiscounted(discounted);
                            flightInfo.setOriginAirportFeeCharged(originAirportFeeCharged);
                            flightInfo.setDestinationAirportFeeCharged(destinationAirportFeeCharged);
                            flightInfo.setTicketsPurchased(flightTicket);
                            person.setTicketsPurchased(customerTicket);

                            continue;

                        // If the total is more than the money available, write in log
                        } else {
                            writer.writeLog("LogActions.txt", "Order unsuccessful. " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                            continue;
                        }
                        
                    // If there are not enough seats available, write in log
                    } else {
                        writer.writeLog("LogActions.txt", "Order unsuccessful. Not enough " + ticketType + " seats available in Flight ID " + flightID);
                        continue;
                    }
                }
            // Else, tell the user the flight is cancelled
            } else {
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " is cancelled");
                continue;
            }
        }
        System.out.println("AutoPurchaser successful");
    }
}