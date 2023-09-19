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
 * PurchaseTickets.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.Random;
 import java.util.Scanner;

/**
 * Ticket Transaction Class that contains methods to let customer buy different types of tickets, cancel their order
 * Also removes flight tickets
 */
public class TicketTransaction {
    /**
     * Method that lets customers and employees purchase tickets
     * @param userPurchase User input
     * @param flights Hash Map that contains all flight information
     * @param person Customer/Employee who will be purchasing tickets
     * @param scnrInput Scanner
     * @param writer Instace of Writer Class
     * @throws Exception Exception
     */
    public static void buyTickets(String userPurchase, HashMap<Integer, Flight> flights, Person person, Scanner scnrInput, Writer writer) throws Exception {

        // While input is not 'EXIT'
        while (!userPurchase.equalsIgnoreCase("EXIT")) {

            int flightID = Integer.parseInt(userPurchase);

            // If the flightId that the user input is bigger than the flight list, print an error message
            if (flights.size() >= flightID && flightID != 0) {

                // Access hash map and create an object with the flight info
                Flight flightInfo = flights.get(flightID);

                // If flight is Scheduled, proceed
                if (flightInfo.getFlightStatus().equalsIgnoreCase("Scheduled")) {

                    // Print tickets types available
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Tickets available for Flight " + flightID);
                    Menus.printTicketsAvailable(flightInfo, person);
                    System.out.println("-----------------------------------------------------------------");
                    
                    // Ask user which type of tickets they want
                    System.out.println("Which ticket type would you like to purchase? [1-3]");
                    System.out.println("-----------------------------------------------------------------");
                    int purchaseSelection = scnrInput.nextInt();
                    scnrInput.nextLine();
                    System.out.println("-----------------------------------------------------------------");

                    String ticketType = "";

                    // If the ticket type is valid
                    if (!(purchaseSelection > 3 || purchaseSelection < 1)) {

                        // Set ticket type depending on selection
                        if (purchaseSelection == 1) {
                            ticketType = "First Class";
                        } else if (purchaseSelection == 2) {
                            ticketType = "Business Class";
                        } else if (purchaseSelection == 3) {
                            ticketType = "Main Cabin";
                        }

                        // Ask user the amount of tickets
                        System.out.println("How many " + ticketType + " Tickets would you like to purchase? [1-8]");
                        System.out.println("-----------------------------------------------------------------");
                        int numOfTickets = scnrInput.nextInt();
                        scnrInput.nextLine();
                        System.out.println("-----------------------------------------------------------------");

                        // Calculate First Class Tickets
                        if (ticketType.equals("First Class")) {

                            // Get the total seats for first class
                            int firstClassSeats = flightInfo.getFirstClassSeats();

                            // If the number of tickets is greater or equal to 1 and greater than or equal to 8
                            if (numOfTickets >= 1 && numOfTickets <= 8) {

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
                                    if(person.getMinerAirMembership()) {
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
                                    double tax = flightInfo.getTax() * subtotalWithFees;
                                    double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                                    // If the total is less than or equal to the customer's money available
                                    if (total <= moneyAvailable) {

                                        // Tell the user the subtotal, tax and total, ask them to confirm
                                        System.out.println("Subtotal: $" + subtotalWithFees);
                                        System.out.println("Tax: $" + Math.round(tax * 100.0) / 100.0);
                                        System.out.println("Total: $" + total);
                                        System.out.println("\nConfirm your purchase [Y/N]");
                                        System.out.println("-----------------------------------------------------------------");
                                        char confirmYN = scnrInput.next().charAt(0);
                                        scnrInput.nextLine();

                                        // If the user enters Y, confirm purchase
                                        if (confirmYN == 'Y' || confirmYN == 'y') {

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

                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order successful");
                                            System.out.println("Your confirmation number is " + confirmationNumber);
                                            System.out.println("-----------------------------------------------------------------");

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

                                            // Ask user if they would like to print their ticket
                                            System.out.println("Would you like to print your ticket? [Y/N]");
                                            System.out.println("-----------------------------------------------------------------");
                                            char printTicket = scnrInput.next().charAt(0);
                                            scnrInput.nextLine();

                                            // If user enters Y, print ticket info
                                            if (printTicket == 'Y' || printTicket == 'y') {
                                                System.out.println("-----------------------------------------------------------------");
                                                customerTicket.printCustomerTicketInfo();
                                            }

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

                                            break;

                                        // If the user enters something different to Y, do not process order
                                        } else {
                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order not processed");
                                            writer.writeLog("LogActions.txt", "Order not processed. " + person.getFirstName() + " " + person.getLastName() + " did not confirm the purchase.");
                                            break;
                                        }

                                    // If the total is more than the money available, tell the customer the order was unsuccessful
                                    } else {
                                        System.out.println("Order unsuccessful\nNot enough money available.");
                                        writer.writeLog("LogActions.txt", "Order unsuccessful. " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                                        break;
                                    }
                                    
                                // If there are not enough seats available, tell the customer
                                } else {
                                    System.out.println("There are not enough seats available in " + ticketType);
                                    System.out.println("Number of seats available: " + flightInfo.getFirstClassSeats() + " seats");
                                    continue;
                                }

                            // If the number of tickets is greater than 8, tell the customer it's an invalid amount of tickets
                            } else {
                                System.out.println("Enter a valid amount of tickets [1-8]");
                                continue;
                            }
                        }

                        // Calculate Business Class Tickets
                        if (ticketType.equals("Business Class")) {

                            // Get the total seats for business class
                            int businessClassSeats = flightInfo.getBusinessClassSeats();

                            // If the number of tickets is greater or equal to 1 and greater than or equal to 8
                            if (numOfTickets >= 1 && numOfTickets <= 8) {

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
                                    if(person.getMinerAirMembership()) {
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
                                    double tax = flightInfo.getTax() * subtotalWithFees;
                                    double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                                    // If the total is less than or equal to the customer's money available
                                    if (total <= moneyAvailable) {

                                        // Tell the user the subtotal, tax and total, ask them to confirm
                                        System.out.println("Subtotal: $" + subtotalWithFees);
                                        System.out.println("Tax: $" + Math.round(tax * 100.0) / 100.0);
                                        System.out.println("Total: $" + total);
                                        System.out.println("\nConfirm your purchase [Y/N]");
                                        System.out.println("-----------------------------------------------------------------");
                                        char confirmYN = scnrInput.next().charAt(0);
                                        scnrInput.nextLine();

                                        // If the user enters Y, confirm purchase
                                        if (confirmYN == 'Y' || confirmYN == 'y') {

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

                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order successful");
                                            System.out.println("Your confirmation number is " + confirmationNumber);
                                            System.out.println("-----------------------------------------------------------------");

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


                                            // Ask user if they would like to print their ticket
                                            System.out.println("Would you like to print your ticket? [Y/N]");
                                            System.out.println("-----------------------------------------------------------------");
                                            char printTicket = scnrInput.next().charAt(0);
                                            scnrInput.nextLine();

                                            // If user enters Y, print ticket info
                                            if (printTicket == 'Y' || printTicket == 'y') {
                                                System.out.println("-----------------------------------------------------------------");
                                                customerTicket.printCustomerTicketInfo();
                                            }

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

                                            break;

                                        // If the user enters something different to Y, do not process order
                                        } else {
                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order not processed");
                                            writer.writeLog("LogActions.txt", "Order not processed. " + person.getFirstName() + " " + person.getLastName() + " did not confirm the purchase.");
                                            break;
                                        }

                                    // If the total is more than the money available, tell the customer the order was unsuccessful
                                    } else {
                                        System.out.println("Order unsuccessful\nNot enough money available.");
                                        writer.writeLog("LogActions.txt", "Order unsuccessful. " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                                        break;
                                    }
                                    
                                // If there are not enough seats available, tell the customer
                                } else {
                                    System.out.println("There are not enough seats available in " + ticketType);
                                    System.out.println("Number of seats available: " + flightInfo.getBusinessClassSeats() + " seats");
                                    continue;
                                }

                            // If the number of tickets is greater than 8, tell the customer it's an invalid amount of tickets
                            } else {
                                System.out.println("Enter a valid amount of tickets [1-8]");
                                continue;
                            }
                        }

                        // Calculate Main Cabin Tickets
                        if (ticketType.equals("Main Cabin")) {

                            // Get the total seats for main cabin
                            int mainCabinSeats = flightInfo.getMainCabinSeats();

                            // If the number of tickets is greater or equal to 1 and greater than or equal to 8
                            if (numOfTickets >= 1 && numOfTickets <= 8) {

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
                                    if(person.getMinerAirMembership()) {
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
                                    double tax = flightInfo.getTax() * subtotalWithFees;
                                    double total = Math.round((tax + subtotalWithFees) * 100.0) / 100.0;

                                    // If the total is less than or equal to the customer's money available
                                    if (total <= moneyAvailable) {

                                        // Tell the user the subtotal, tax and total, ask them to confirm
                                        System.out.println("Subtotal: $" + subtotalWithFees);
                                        System.out.println("Tax: $" + Math.round(tax * 100.0) / 100.0);
                                        System.out.println("Total: $" + total);
                                        System.out.println("\nConfirm your purchase [Y/N]");
                                        System.out.println("-----------------------------------------------------------------");
                                        char confirmYN = scnrInput.next().charAt(0);
                                        scnrInput.nextLine();

                                        // If the user enters Y, confirm purchase
                                        if (confirmYN == 'Y' || confirmYN == 'y') {

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

                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order successful");
                                            System.out.println("Your confirmation number is " + confirmationNumber);
                                            System.out.println("-----------------------------------------------------------------");

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


                                            // Ask user if they would like to print their ticket
                                            System.out.println("Would you like to print your ticket? [Y/N]");
                                            System.out.println("-----------------------------------------------------------------");
                                            char printTicket = scnrInput.next().charAt(0);
                                            scnrInput.nextLine();

                                            // If user enters Y, print ticket info
                                            if (printTicket == 'Y' || printTicket == 'y') {
                                                System.out.println("-----------------------------------------------------------------");
                                                customerTicket.printCustomerTicketInfo();
                                            }

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

                                            break;

                                        // If the user enters something different to Y, do not process order
                                        } else {
                                            System.out.println("-----------------------------------------------------------------");
                                            System.out.println("Order not processed");
                                            writer.writeLog("LogActions.txt", "Order not processed " + person.getFirstName() + " " + person.getLastName() + " did not confirm the purchase");
                                            break;
                                        }

                                    // If the total is more than the money available, tell the customer the order was unsuccessful
                                    } else {
                                        System.out.println("Order unsuccessful\nNot enough money available.");
                                        writer.writeLog("LogActions.txt", "Order unsuccessful " + person.getFirstName() + " " + person.getLastName() + " did not have enough money available to purchase " + numOfTickets + " " + ticketType + " ticket(s) for Flight ID " + flightID);
                                        break;
                                    }
                                    
                                // If there are not enough seats available, tell the customer
                                } else {
                                    System.out.println("There are not enough seats available in " + ticketType);
                                    System.out.println("Number of seats available: " + flightInfo.getMainCabinSeats() + " seats");
                                    continue;
                                }
                            // If the number of tickets is greater than 8, tell the customer it's an invalid amount of tickets
                            } else {
                                System.out.println("Enter a valid amount of tickets [1-8]");
                                continue;
                            }
                        }
                    // If the ticket type is invalid, ask again
                    } else {
                        System.out.println("Enter a valid ticket type [1-3]");
                        continue;
                    }
                // Else, tell the user the flight is cancelled
                } else {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Flight ID " + flightID + " is cancelled");
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

    /**
     * Method that lets user cancel tickets
     * @param userCancel User input
     * @param customerTicketList Customer's/Employee's ticket list
     * @param person Customer/Employee who will be cancelling tickets
     * @param scanner Scanner
     * @param flights Hash Map that contains all flight information
     * @param writer Instance of Writer Class
     * @throws Exception Exception
     */
    public static void cancelTicketCustomer(String userCancel, ArrayList<Ticket> customerTicketList, Person person, Scanner scanner, HashMap<Integer, Flight> flights, Writer writer) throws Exception {

        // While input is not 'EXIT', print flight seats sold
        while (!userCancel.equalsIgnoreCase("EXIT")) {
            System.out.println("-----------------------------------------------------------------");

            int confirmationNumber = Integer.parseInt(userCancel);

            // Find the ticket with the confirmation number in the customerTicketList
            Ticket customerTicketToRemove = null;
            for (Ticket customerTicket : customerTicketList) {
                // If the ticket's confirmation number matches the confirmation number entered, the ticket was found
                if (customerTicket.getConfirmationNumber() == confirmationNumber) {
                    customerTicketToRemove = customerTicket;
                    break;
                }
            }

            // If the customer ticket was found, remove it from the customerTicketList and update values
            if (customerTicketToRemove != null) {
                customerTicketList.remove(customerTicketToRemove);

                int flightID = customerTicketToRemove.getFlightID();
                Flight flightInfo = flights.get(flightID);

                double oldMoneyAvailable = person.getMoneyAvailable();
                int numOfTickets = customerTicketToRemove.getAmountOfTickets();
                double totalPrice = customerTicketToRemove.getTotalPrice();
                double subtotal = customerTicketToRemove.getSubtotal();
                int previousFlightsPurchased = person.getFlightsPurchased();
                double refund = totalPrice - flightInfo.getMinerAirlinesFee();
                double previousDiscounted = flightInfo.getDiscounted();
                double discountGiven = customerTicketToRemove.getDiscount();
                double tax = customerTicketToRemove.getTax();
                double previousSavings = person.getSavings();

                // Update customer's money, flights purchased and savings
                person.setMoneyAvailable(oldMoneyAvailable + refund);
                person.setFlightsPurchased(previousFlightsPurchased - 1);
                person.setSavings(previousSavings - discountGiven);

                String ticketType = customerTicketToRemove.getTicketType();
                int previousTotalSeats = flightInfo.getTotalSeats();
                int previousTotalSeatsSold = flightInfo.getTotalSeatsSold();
                double previousTotalRevenue = flightInfo.getTotalRevenue();
                double previousTaxCharged = flightInfo.getTaxCharged();
                double previousSecurityFeeCharged = flightInfo.getSecurityFeeCharged();
                double previousOriginAirportFeeCharged = flightInfo.getOriginAirportFeeCharged();
                double previousDestinationAirportFeeCharged = flightInfo.getDestinationAirportFeeCharged();

                // Update flight's total seats available, total seats sold, total revenue, security fee charged, discount given
                flightInfo.setTotalSeats(previousTotalSeats + numOfTickets);
                flightInfo.setTotalSeatsSold(previousTotalSeatsSold - numOfTickets);
                flightInfo.setTotalRevenue(previousTotalRevenue - subtotal);
                flightInfo.setTaxCharged(previousTaxCharged - tax);
                flightInfo.setSecurityFeeCharged(previousSecurityFeeCharged - (flightInfo.getSecurityFee() * numOfTickets));
                flightInfo.setOriginAirportFeeCharged(previousOriginAirportFeeCharged - flightInfo.getOriginAirportFee());
                flightInfo.setDestinationAirportFeeCharged(previousDestinationAirportFeeCharged - flightInfo.getDestinationAirportFee());
                flightInfo.setDiscounted(previousDiscounted - discountGiven);
                
                System.out.println("Order " + confirmationNumber + " has been successfully canceled");
                writer.writeLog("LogActions.txt", "Order " + confirmationNumber + " was successfully cancelled by " + person.getFirstName() + " " + person.getLastName() + ".");
                writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s money available updated from " + oldMoneyAvailable + " to $" + person.getMoneyAvailable());
                writer.writeLog("LogActions.txt", person.getFirstName() + " " + person.getLastName() + "'s flights purchased updated from " + previousFlightsPurchased + " flight(s) to " + person.getFlightsPurchased() + " flight(s)");
                writer.writeLog("LogActions.txt", person.getFirstName() + "'s savings updated from $" + previousSavings + " to $" + person.getSavings());
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + previousTotalSeats + " seat(s) to " + flightInfo.getTotalSeats() + " seat(s)");
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total seat(s) updated from " + previousTotalSeatsSold + " seat(s) sold to " + flightInfo.getTotalSeatsSold() + " seat(s) sold");
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total revenue updated from $" + previousTotalRevenue + " to $" + flightInfo.getTotalRevenue());
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Security fee charged updated from $" + previousSecurityFeeCharged + " to $" + flightInfo.getSecurityFeeCharged());
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total Tax charged updated from $" + previousTaxCharged + " to $" + flightInfo.getTaxCharged());
                writer.writeLog("LogActions.txt", "Flight ID " + flightID + " total discount updated from $" + previousDiscounted + " to $" + flightInfo.getDiscounted());

                // Depending on the ticket type, update total seats available, total seats sold and revenue for each category
                if (ticketType.equalsIgnoreCase("First Class")) {
                    int previousFirstClassSeats = flightInfo.getFirstClassSeats();
                    int previousFirstClassSeatsSold = flightInfo.getFirstClassSeatsSold();
                    double previousFirstClassRevenue = flightInfo.getFirstClassRevenue();
                    flightInfo.setFirstClassSeats(previousFirstClassSeats + numOfTickets);
                    flightInfo.setFirstClassSeatsSold(previousFirstClassSeatsSold - numOfTickets);
                    flightInfo.setFirstClassRevenue(previousFirstClassRevenue - subtotal);
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " First Class seat(s) updated from " + previousFirstClassSeats + " seat(s) to " + flightInfo.getFirstClassSeats() + " seat(s)");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " First Class seat(s) sold updated from " + previousFirstClassSeatsSold + " seat(s) sold to " + flightInfo.getFirstClassSeatsSold() + " seat(s) sold");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " First Class revenue updated from $" + previousFirstClassRevenue + " to $" + flightInfo.getFirstClassRevenue());
                } else if (ticketType.equalsIgnoreCase("Business Class")) {
                    int previousBusinessClassSeats = flightInfo.getBusinessClassSeats();
                    int previousBusinessClassSeatsSold = flightInfo.getBusinessClassSeatsSold();
                    double previousBusinessClassRevenue = flightInfo.getBusinessClassRevenue();
                    flightInfo.setBusinessClassSeats(previousBusinessClassSeats + numOfTickets);
                    flightInfo.setBusinessClassSeatsSold(previousBusinessClassSeatsSold - numOfTickets);
                    flightInfo.setBusinessClassRevenue(previousBusinessClassRevenue - subtotal);
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " Business Class seat(s) updated from " + previousBusinessClassSeats + " seat(s) to " + flightInfo.getBusinessClassSeats() + " seat(s)");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " Business Class seat(s) sold updated from " + previousBusinessClassSeatsSold + " seat(s) sold to " + flightInfo.getBusinessClassSeatsSold() + " seat(s) sold");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Business Class revenue updated from $" + previousBusinessClassRevenue + " to $" + flightInfo.getBusinessClassRevenue());
                } else if (ticketType.equalsIgnoreCase("Main Cabin")) {
                    int previousMainCabinSeats = flightInfo.getMainCabinSeats();
                    int previousMainCabinSeatsSold = flightInfo.getMainCabinSeatsSold();
                    double previousMainCabinRevenue = flightInfo.getMainCabinRevenue();
                    flightInfo.setMainCabinSeats(previousMainCabinSeats + numOfTickets);
                    flightInfo.setMainCabinSeatsSold(previousMainCabinSeatsSold - numOfTickets);
                    flightInfo.setMainCabinRevenue(previousMainCabinRevenue - subtotal);
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " Main Cabin seat(s) updated from " + previousMainCabinSeats + " seat(s) to " + flightInfo.getMainCabinSeats()+ " seat(s)");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " " + ticketType + " Main Cabin seat(s) sold updated from " + previousMainCabinSeatsSold + " seat(s) sold to " + flightInfo.getMainCabinSeatsSold() + " seat(s) sold");
                    writer.writeLog("LogActions.txt", "Flight ID " + flightID + " Main Cabin revenue updated from $" + previousMainCabinRevenue + " to $" + flightInfo.getMainCabinRevenue());
                }
                removeFlightTicket(flightInfo, confirmationNumber);
                break;
            // If the ticket is null, tell the customer
            } else {
                System.out.println("Order " + confirmationNumber + " was not found");
                break;
            }
        }
    }

    /**
     * Method to remove flight ticket from flight's ticket list
     * @param flightInfo Flight
     * @param confirmationNumber Ticket's confirmation number
     */
    public static void removeFlightTicket(Flight flightInfo, int confirmationNumber) {
        ArrayList<Ticket> flightTicketList = flightInfo.getTicketsPurchased();
    
        // Find the ticket with the confirmation number in the flightTicketList
        Ticket flightTicketToRemove = null;
        for (Ticket flightTicket : flightTicketList) {
            // If the ticket's confirmation number matches the confirmation number entered, the ticket was found
            if (flightTicket.getConfirmationNumber() == confirmationNumber) {
                flightTicketToRemove = flightTicket;
                break;
            }
        }
        
        // If the flight ticket was found, remove it from the flightTicketList
        if (flightTicketToRemove != null) {
            flightTicketList.remove(flightTicketToRemove);
        }
    }
}