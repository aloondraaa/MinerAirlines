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
 * FlightChanges.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Class that handles changes in flights
*/
public class FlightChanges {
    /**
     * Method to let user make flight changes
    * @param employee Employee who will make changes
    * @param flightInfo Hash Map that contains all flight information
    * @param flightID ID of Flight that changes are being made to
    * @param userView User Input
    * @param scnrInput Scanner
    * @param writer Instance of Writer Class
    * @throws Exception Exception
    */
    public static void makeFlightChanges(Employee employee, Flight flightInfo, int flightID, String userView, Scanner scnrInput, Writer writer) throws Exception  {

        // Ask user to select from menu
        int changeSelection = scnrInput.nextInt();
        scnrInput.nextLine();
        System.out.println("-----------------------------------------------------------------");

        // If user input is 1, ask for new Status
        // Update log and set variables to user input
        if (changeSelection == 1) {
            System.out.print("Enter new Status (Scheduled/Cancelled): ");
            String newStatus = scnrInput.nextLine();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Status from " + flightInfo.getFlightStatus() + " to " + newStatus);
            System.out.println("\nFlight ID " + flightID + " Status successfully updated from " + flightInfo.getFlightStatus() + " to " + newStatus);
            flightInfo.setFlightStatus(newStatus);
        }

        // If user input is 2, ask for new Origin Airport & Code
        // Update log and set variables to user input
        if(changeSelection == 2) {
            System.out.print("Enter new Origin Airport: ");
            String newOriginAirport = scnrInput.nextLine();
            System.out.println();
            System.out.print("\nEnter new Origin Code: ");
            String newOriginCode = scnrInput.nextLine();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Origin Airport & Code from " + flightInfo.getOriginAirport() + " - " + flightInfo.getOriginCode() + " to " + newOriginAirport + " - " + newOriginCode);
            System.out.println("Flight ID " + flightID + " Origin Airport & Code successfully updated from " + flightInfo.getOriginAirport() + " - " + flightInfo.getOriginCode() + " to " + newOriginAirport + " - " + newOriginCode);
            flightInfo.setOriginAirport(newOriginAirport);
            flightInfo.setOriginCode(newOriginCode);
        }

        // If user input is 3, ask for new Destination Airport & Code
        // Update log and set variables to user input
        if(changeSelection == 3) {
            System.out.print("Enter new Destination Airport: ");
            String newDestinationAirport = scnrInput.nextLine();
            System.out.println();
            System.out.print("Enter new Destination Code: ");
            String newDestinationCode = scnrInput.nextLine();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Destination Airport & Code from " + flightInfo.getDestinationAirport() + " - " + flightInfo.getDestinationCode() + " to " + newDestinationAirport + " - " + newDestinationCode);
            System.out.println("\nFlight ID " + flightID + " Destination Airport & Code successfully updated from " + flightInfo.getDestinationAirport() + " - " + flightInfo.getDestinationCode() + " to " + newDestinationAirport + " - " + newDestinationCode);
            flightInfo.setDestinationAirport(newDestinationAirport);
            flightInfo.setDestinationCode(newDestinationCode);
        }

        // If user input is 4, ask for new Departure Date & Time
        // Update log and set variables to user input
        // Update the Arrival Date & Time and the log too
        if(changeSelection == 4) {
            System.out.print("Enter new Departure Date (MM/DD/YY): ");
            String newDepartureDate = scnrInput.nextLine();
            System.out.println();
            System.out.print("Enter new Departure Time (HH:MM XM): ");
            String newDepartureTime = scnrInput.nextLine();
            
            // Get duration
            int duration = flightInfo.getDuration();

            // Get time zone difference
            int timeZoneDifference = flightInfo.getTimeZoneDifference();

            // Get original arrival date & time
            String oldArrivalDate = flightInfo.getArrivalDate();
            String oldArrivalTime = flightInfo.getArrivalTime();

            // Call method that updates arrival date & time
            updateArrivalDateTime(newDepartureDate, newDepartureTime, duration, timeZoneDifference, flightInfo, writer);
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Departure Date & Time from " + flightInfo.getDepartureDate() + " at " + flightInfo.getDepartureTime() + " to " + newDepartureDate + " at " + newDepartureTime);
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Arrival Date & Time from " + oldArrivalDate + " at " + oldArrivalTime + " to " + flightInfo.getArrivalDate() + " at " + flightInfo.getArrivalTime());
            System.out.println("\nFlight ID " + flightID + " Departure Date & Time successfully updated from " + flightInfo.getDepartureDate() + " at " + flightInfo.getDepartureTime() + " to " + newDepartureDate + " at " + newDepartureTime);
            System.out.println("\nFlight ID " + flightID + " Arrival Date & Time successfully updated from " + oldArrivalDate + " at " + oldArrivalTime + " to " + flightInfo.getArrivalDate() + " at " + flightInfo.getArrivalTime());
            flightInfo.setDepartureDate(newDepartureDate);
            flightInfo.setDepartureTime(newDepartureTime);
        }

        // If user input is 5, ask for new First Class Price
        // Update log and set variable to user input
        if (changeSelection == 5) {
            System.out.print("Enter new First Class Price: $");
            int newFirstClassPrice = scnrInput.nextInt();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " First Class Price from $" + flightInfo.getFirstClassPrice() + " to $" + newFirstClassPrice);
            System.out.println("\nFlight ID " + flightID + " First Class Price successfully updated from $" + flightInfo.getFirstClassPrice() + " to $" + newFirstClassPrice);
            flightInfo.setFirstClassPrice(newFirstClassPrice);
        }

        // If user input is 6, ask for new Business Class Price
        // Update log and set variable to user input
        if (changeSelection == 6) {
            System.out.print("Enter new Business Class Price: $");
            int newBusinessClassPrice = scnrInput.nextInt();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Business Class Price from $" + flightInfo.getBusinessClassPrice() + " to $" + newBusinessClassPrice);
            System.out.println("\nFlight ID " + flightID + " Business Class Price successfully updated from $" + flightInfo.getBusinessClassPrice() + " to $" + newBusinessClassPrice);
            flightInfo.setBusinessClassPrice(newBusinessClassPrice);
        }

        // If user input is 7, ask for new Main Cabin Price
        // Update log and set variable to user input
        if (changeSelection == 7) {
            System.out.print("Enter new Main Cabin Price: $");
            int newMainCabinPrice = scnrInput.nextInt();
            writer.writeLog("LogActions.txt", employee.getFirstName() + " updated " + "Flight ID " + flightID + " Main Cabin Price from $" + flightInfo.getMainCabinPrice() + " to $" + newMainCabinPrice);
            System.out.println("\nFlight ID " + flightID + " Main Cabin Price successfully updated from $" + flightInfo.getMainCabinPrice() + " to $" + newMainCabinPrice);
            flightInfo.setMainCabinPrice(newMainCabinPrice);
        }

        // If input is 8, exit while loop
        if (changeSelection == 8) {
            userView = "EXIT";
        }
    }

    /**
     * Method to update arrival date and time
    * @param newDepartureDate Departure Date entered by user
    * @param newDepartureTime Departure Time entered by user
    * @param duration Duration of flight
    * @param timeZoneDifference Time zone difference of cities
    * @param flightInfo Flight selected
    * @param writer Instance of Writer Class
    */
    public static void updateArrivalDateTime(String newDepartureDate, String newDepartureTime, int duration, int timeZoneDifference, Flight flightInfo, Writer writer) {

        // Format used for date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy hh:mm a");
    
        // Convert the new departure date and time to Date object
        Date departureDateTime = null;
        try {
            departureDateTime = dateFormat.parse(newDepartureDate + " " + newDepartureTime);
        } catch (Exception e) {
            System.out.println("Error");
        }
    
        // Add the duration, in minutes, to the departure date and time and store it in a new variable
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDateTime);
        calendar.add(Calendar.MINUTE, duration);
        Date arrivalDateTime = calendar.getTime();
    
        // Calculate the time zone difference
        calendar.setTime(arrivalDateTime);
        calendar.add(Calendar.HOUR, timeZoneDifference);
        arrivalDateTime = calendar.getTime();
    
        // Format the new arrival date and time, from 0 to 8 is the date and 9 to end is the time
        String newArrivalDate = dateFormat.format(arrivalDateTime).substring(0,8);
        String newArrivalTime = dateFormat.format(arrivalDateTime).substring(9);

        
        // Set the arrival time and date to the new calculated ones
        flightInfo.setArrivalDate(newArrivalDate);
        flightInfo.setArrivalTime(newArrivalTime);
    }

    /**
     * Method for employees to cancel flights
    * @param employee Employee who will cancel flights 
    * @param flights Hash Map that contains all flight information
    * @param userCancel User input
    * @param people Hash Map that contains all customer/employee information
    * @param writer Instance of Writer Class
    * @throws Exception Exception
    */
    public static void cancelFlight(Employee employee, HashMap<Integer, Flight> flights, String userCancel, HashMap<Integer, Person> people, Writer writer) throws Exception {

        // While input is not 'EXIT'
        while (!userCancel.equalsIgnoreCase("EXIT")) {

            int flightID = Integer.parseInt(userCancel);

            // If the flightId that the user input is bigger than the flight list, print an error message
            if (flights.size() >= flightID && flightID != 0) {

                // Access hash map and create an object with the flight info
                Flight flightInfo = flights.get(flightID);

                // If flight is Scheduled, proceed
                if (flightInfo.getFlightStatus().equalsIgnoreCase("Scheduled")) {

                    ArrayList<Ticket> flightTicketList = flightInfo.getTicketsPurchased();

                    Ticket ticketFound = null;

                    // For every ticket in the list
                    for (int i = 0; i < flightTicketList.size(); i++) {

                        ticketFound = flightTicketList.get(i);

                        // Get type, total, amount of tickets, first and last name
                        String ticketType = ticketFound.getTicketType();
                        double subtotal = ticketFound.getSubtotal();
                        double totalPrice = ticketFound.getTotalPrice();
                        double discounted = flightInfo.getDiscounted();
                        double taxCharged = flightInfo.getTaxCharged();
                        double minerAirlinesFeeCharged = flightInfo.getMinerAirlinesFee();
                        double securityFeeCharged = flightInfo.getSecurityFeeCharged();
                        double previousOriginAirportFeeCharged = flightInfo.getOriginAirportFeeCharged();
                        double previousDestinationAirportFeeCharged = flightInfo.getDestinationAirportFeeCharged();
                        int numOfTickets = ticketFound.getAmountOfTickets();
                        String firstName = ticketFound.getFirstName();
                        String lastName = ticketFound.getLastName();

                        // Find person that matches with the one on the ticket
                        Person person = Person.findByName(people, firstName, lastName);

                        // Create new Customer object and set person to customer
                        Customer customer = new Customer();
                        customer = (Customer) person;

                        double oldMoneyAvailable = customer.getMoneyAvailable();
                        int previousFlightsPurchased = customer.getFlightsPurchased();

                        // Update customer's money and flights purchased
                        customer.setMoneyAvailable(oldMoneyAvailable + totalPrice);
                        customer.setFlightsPurchased(previousFlightsPurchased - 1);

                        writer.writeLog("LogActions.txt", customer.getFirstName() + " " + customer.getLastName() + "'s money available updated from " + oldMoneyAvailable + " to $" + customer.getMoneyAvailable());
                        writer.writeLog("LogActions.txt", customer.getFirstName() + " " + customer.getLastName() + "'s flights purchased updated from " + previousFlightsPurchased + " flight(s) to " + customer.getFlightsPurchased());

                        
                        ArrayList<Ticket> customerTickets = customer.getTicketsPurchased();

                        // For each ticket in the customer's tickets
                        for (Ticket customerTicketFound : customerTickets) {
                            // If the flight ID on the ticket matches the flight ID of the flight being cancelled, set status to Cancelled and total to 0
                            if (customerTicketFound.getFlightID() == flightID) {
                                customerTicketFound.setTicketStatus("Cancelled");
                                customerTicketFound.setTotalPrice(totalPrice - totalPrice);
                                break;
                            }
                        }

                        int previousTotalSeats = flightInfo.getTotalSeats();
                        int previousTotalSeatsSold = flightInfo.getTotalSeatsSold();
                        double previousTotalRevenue = flightInfo.getTotalRevenue();
        
                        // Update flight's total seats available, total seats sold, status and revenue and update ticket's total price
                        flightInfo.setTotalSeats(previousTotalSeats + numOfTickets);
                        flightInfo.setTotalSeatsSold(previousTotalSeatsSold - numOfTickets);        
                        flightInfo.setTotalRevenue(previousTotalRevenue - subtotal);
                        flightInfo.setMinerAirlinesFeeCharged(minerAirlinesFeeCharged - minerAirlinesFeeCharged);
                        flightInfo.setDiscounted(discounted - discounted);
                        flightInfo.setTaxCharged(taxCharged - taxCharged);
                        flightInfo.setSecurityFeeCharged(securityFeeCharged - securityFeeCharged);
                        flightInfo.setOriginAirportFeeCharged(previousOriginAirportFeeCharged - previousOriginAirportFeeCharged);
                        flightInfo.setDestinationAirportFeeCharged(previousDestinationAirportFeeCharged - previousDestinationAirportFeeCharged);        
                        ticketFound.setTotalPrice(totalPrice - totalPrice);

                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + previousTotalSeats + " seat(s) to " + flightInfo.getTotalSeats() + " seat(s)");
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + previousTotalSeatsSold + " seat(s) sold to " + flightInfo.getTotalSeatsSold() + " seat(s) sold");
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total revenue updated from $" + previousTotalRevenue + " to $" + flightInfo.getTotalRevenue());
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total minerAir fee charged updated from $" + minerAirlinesFeeCharged + " to $" + flightInfo.getMinerAirlinesFeeCharged());
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total discounted updated from $" + discounted + " to $" + flightInfo.getDiscounted());
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total tax charged updated from $" + taxCharged + " to $" + flightInfo.getTaxCharged());
                        writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total security fee charged updated from $" + securityFeeCharged + " to $" + flightInfo.getSecurityFeeCharged());

                        // Depending on the ticket type, update total seats available and total seats sold for each category
                        if (ticketType.equalsIgnoreCase("First Class")) {
                            int previousFirstClassSeats = flightInfo.getFirstClassSeats();
                            int previousFirstClassSeatsSold = flightInfo.getFirstClassSeatsSold();
                            double previousFirstClassRevenue = flightInfo.getFirstClassRevenue();
                            flightInfo.setFirstClassSeats(previousFirstClassSeats + numOfTickets);
                            flightInfo.setFirstClassSeatsSold(previousFirstClassSeatsSold - numOfTickets);
                            flightInfo.setFirstClassRevenue(previousFirstClassRevenue - subtotal);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + previousFirstClassSeats + " seat(s) to " + flightInfo.getFirstClassSeats() + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + previousFirstClassSeatsSold + " seat(s) sold to " + flightInfo.getFirstClassSeatsSold() + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " First Class revenue updated from $" + previousFirstClassRevenue + " to $" + flightInfo.getFirstClassRevenue());
                        } else if (ticketType.equalsIgnoreCase("Business Class")) {
                            int previousBusinessClassSeats = flightInfo.getBusinessClassSeats();
                            int previousBusinessClassSeatsSold = flightInfo.getBusinessClassSeatsSold();
                            double previousBusinessClassRevenue = flightInfo.getBusinessClassRevenue();
                            flightInfo.setBusinessClassSeats(previousBusinessClassSeats + numOfTickets);
                            flightInfo.setBusinessClassSeatsSold(previousBusinessClassSeatsSold - numOfTickets);
                            flightInfo.setBusinessClassRevenue(previousBusinessClassRevenue - subtotal);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + previousBusinessClassSeats + " seat(s) to " + flightInfo.getBusinessClassSeats() + " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + previousBusinessClassSeatsSold + " seat(s) sold to " + flightInfo.getBusinessClassSeatsSold() + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Business Class revenue updated from $" + previousBusinessClassRevenue + " to $" + flightInfo.getBusinessClassRevenue());
                        } else if (ticketType.equalsIgnoreCase("Main Cabin")) {
                            int previousMainCabinSeats = flightInfo.getMainCabinSeats();
                            int previousMainCabinSeatsSold = flightInfo.getMainCabinSeatsSold();
                            double previousMainCabinRevenue = flightInfo.getMainCabinRevenue();
                            flightInfo.setMainCabinSeats(previousMainCabinSeats + numOfTickets);
                            flightInfo.setMainCabinSeatsSold(previousMainCabinSeatsSold - numOfTickets);
                            flightInfo.setMainCabinRevenue(previousMainCabinRevenue - subtotal);
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) updated from " + previousMainCabinSeats + " seat(s) to " + flightInfo.getMainCabinSeats()+ " seat(s)");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " seat(s) sold updated from " + previousMainCabinSeatsSold + " seat(s) sold to " + flightInfo.getMainCabinSeatsSold() + " seat(s) sold");
                            writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Main Cabin revenue updated from $" + previousMainCabinRevenue + " to $" + flightInfo.getMainCabinRevenue());
                        }
                    }
                    flightInfo.setFlightStatus("Cancelled");
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Flight ID " + flightID + " was successfully cancelled");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " was successfully cancelled by " + employee.getFirstName() + " " + employee.getLastName());
                    break;
                
                // Else, tell the user the flight is cancelled
                } else {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Flight ID " + flightID + " was already cancelled");
                    break;
                }
            // If the flight ID is greater than the size of the hashmap, tell the customer it's an invalid flightID
            } else {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Invalid flight ID\nPlease try again");
                break;
            }
        }
    }
}