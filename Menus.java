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
 * Menus.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that prints different menus and information depending on the user input
 */
public class Menus {
    /**
     * Method to print Main Menu to welcome user
     */
    public static void welcomeUser() {
        System.out.println("\n-----------------------------------------------------------------");
        System.out.println("Welcome to MinerAir!");
        System.out.println();
        System.out.println("Do you have an account? [Y/N]");
        System.out.println();
        System.out.println("If you would like to exit enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    } 

    /**
     * Method to print main menu for customer
     * @param person Customer/Employee who will be interacting
     */
    public static void mainMenuCustomer(Person person) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Welcome to MinerAir " + person.getFirstName() + "! Choose an option below [1-7]");
        System.out.println("\n1. See all flights");
        System.out.println("\n2. See account balance");
        System.out.println("\n3. Purchase a flight ticket");
        System.out.println("\n4. See information of specific flight");
        System.out.println("\n5. See flights purchased");
        System.out.println("\n6. See specific flight seats sold");
        System.out.println("\n7. Cancel an order");
        System.out.println("\nIf you would like to log out enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print main menu for employee
     * @param employee Employee who will be interacting
     */
    public static void mainMenuEmployee(Employee employee) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Welcome to MinerAir " + employee.getFirstName() + "! Choose an option below [1-6]");
        System.out.println("\n1. See information of specific Flight");
        System.out.println("\n2. See information of specific Airport");
        System.out.println("\n3. Cancel a Flight");
        System.out.println("\n4. Request Electronic Ticket Summary for specific Customer");
        System.out.println("\n5. Go to Customer Menu");
        System.out.println("\n6. AutoPurchase");
        System.out.println("\nIf you would like to log out enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print to ask for Flight ID
     * @param flights Hash Map that contains all flight information
     */
    public static void askFlightID(HashMap<Integer, Flight> flights) {
        System.out.println("Enter the Flight ID [1-" + flights.size() + "]");
        System.out.println();
        System.out.println("If you would like to exit this menu enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print to ask for Airport Code for Employee
     * @param flights Hash Map that contains all flight information
     */
    public static void askAirportCodeEmployee(HashMap<Integer, Flight> flights) {
        System.out.println("Enter the Airport Code");
        System.out.println();
        System.out.println("If you would like to exit this menu enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to ask Customer if they want to look up Flight by Airport Codes and Departure Date or Flight ID
     */
    public static void askAirportCodeOrID() {
        System.out.println("How would you like to look up the Flight? [1-2]");
        System.out.println("\n1. Airport Codes & Departure Date");
        System.out.println("\n2. Flight ID");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to ask Employee which AutoPurchase they would like to use
     */
    public static void AutoPurchaserMenu() {
        System.out.println("Please choose which AutoPurchase you would like to use");
        System.out.println("\n1. AutoPurchaser 10K");
        System.out.println("\n2. AutoPurchaser 100K");
        System.out.println("\n3. AutoPurchaser 400K");
        System.out.println("\nIf you would like to log out enter 'EXIT'");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to ask for Airport Codes and Departure Date
     * @param userSelection User Selection
     * @param flights Hash Map that contains all flight information
     * @param scnrInput Scanner
     */
    public static void askAirportCodeDateCustomer(int userSelection, HashMap<Integer, Flight> flights, Scanner scnrInput) {

        // If the user input is 1, ask for Origin Code, Destination Code & Departure Date
        if (userSelection == 1) {
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Enter Origin Code: ");
            String originCode = scnrInput.nextLine();
            System.out.println();
            System.out.print("Enter Destination Code: ");
            String destinationCode = scnrInput.nextLine();
            System.out.println();
            System.out.print("Enter Departure Date [MM/DD/YY]: ");
            String departureDate = scnrInput.nextLine();

            ArrayList<Flight> flightsFound = new ArrayList<>();
            flightsFound = Flight.findFlight(flights, flightsFound, originCode, destinationCode, departureDate);

            // If the list is empty, tell the customer that no flights were found
            if (flightsFound.isEmpty()) {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("No flights found from " + originCode + " to " + destinationCode + " in " + departureDate);
            
            // Else, print Flight info
            } else {
                System.out.println("\nFLIGHTS FOUND\n");
                for (Flight flight : flightsFound) {
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Flight ID: " + flight.getFlightID()
                                    + "\nStatus: " + flight.getFlightStatus()
                                    + "\nFlight Number: " + flight.getFlightNumber()
                                    + "\nOrigin Airport & Code: " + flight.getOriginAirport() + " - " + flight.getOriginCode() 
                                    + "\nDestination Airport & Code: " + flight.getDestinationAirport() + " - " + flight.getDestinationCode()
                                    + "\nDeparture Date & Time: " + flight.getDepartureDate() + " at " + flight.getDepartureTime()
                                    + "\nArrival Date & Time: " + flight.getArrivalDate() + " at " + flight.getArrivalTime());
                }
            }
        }
    }

    /**
     * Method to print tickets available
     * @param flightInfo Flight chosen
     * @param person Customer/Employee who will be interacting
     */
    public static void printTicketsAvailable(Flight flightInfo, Person person) {
        // If the first class seats are not sold out, display the price, else display that it's sold out
        if (flightInfo.getFirstClassSeats() != 0) {
            System.out.println();
            if (person.getRole().equalsIgnoreCase("Employee")) {
                double discount = flightInfo.getFirstClassPrice() * flightInfo.getEmployeeDiscountFirst();
                System.out.println("1. First Class Ticket: $" + (flightInfo.getFirstClassPrice() - discount));
            } else {
                System.out.println("1. First Class Ticket: $" + flightInfo.getFirstClassPrice());
            }
        } else {
            System.out.println();
            System.out.println("1. First Class Ticket: Sold Out");
        }
        // If the business class seats are not sold out, display the price, else display that it's sold out
        if (flightInfo.getBusinessClassSeats() != 0) {
            System.out.println();
            if (person.getRole().equalsIgnoreCase("Employee")) {
                double discount = flightInfo.getBusinessClassPrice() * flightInfo.getEmployeeDiscountBusinessMain();
                System.out.println("2. Business Class Ticket: $" + (flightInfo.getBusinessClassPrice() - discount));
            } else {
                System.out.println("2. Business Class Ticket: $" + flightInfo.getBusinessClassPrice());
            }
        } else {
            System.out.println();
            System.out.println("2. Business Class Ticket: Sold Out");
        }
        // If the main cabin seats are not sold out, display the price, else display that it's sold out
        if (flightInfo.getMainCabinSeats() != 0) {
            System.out.println();
            if (person.getRole().equalsIgnoreCase("Employee")) {
                double discount = flightInfo.getMainCabinPrice() * flightInfo.getEmployeeDiscountBusinessMain();
                System.out.println("3. Main Cabin Ticket: $" + (flightInfo.getMainCabinPrice() - discount));
            } else {
                System.out.println("3. Main Cabin Ticket: $" + flightInfo.getMainCabinPrice());
            }     
        } else {
            System.out.println("3. Main Cabin Ticket: Sold Out");
        }
        // If flight is international, print surcharge
        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
            System.out.println();
            System.out.println("Surcharge: $" + flightInfo.getSurcharge() + " per ticket");
        }
    }

    /**
     * Method to print flight options customer menu
     * @param flightID ID of flight
     */
    public static void printOptionsCustomer(int flightID) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("What would you like to do? Please choose an option below [1-20]");
        System.out.println("\n1. See Flight Number of Flight ID " + flightID);
        System.out.println("\n2. See Flight Type of Flight ID " + flightID);
        System.out.println("\n3. See Origin Airport & Code of Flight ID " + flightID);
        System.out.println("\n4. See Destination Airport & Code of Flight ID " + flightID);
        System.out.println("\n5. See Departure Date & Time of Flight ID " + flightID);
        System.out.println("\n6. See Arrival Date & Time of Flight ID " + flightID);
        System.out.println("\n7. See Duration of Flight ID " + flightID);
        System.out.println("\n8. See Distance of Flight ID " + flightID);
        System.out.println("\n9. See Time Zone Difference of Flight ID " + flightID);
        System.out.println("\n10. See Food Served of Flight ID " + flightID);
        System.out.println("\n11. See First Class Price of Flight ID " + flightID);
        System.out.println("\n12. See Business Class Price of Flight ID " + flightID);
        System.out.println("\n13. See Main Cabin Price of Flight ID " + flightID);
        System.out.println("\n14. See Miner Points of Flight ID " + flightID);
        System.out.println("\n15. See Number of Seats Available in First Class of Flight ID " + flightID);
        System.out.println("\n16. See Number of Seats Available in Business Class of Flight ID " + flightID);
        System.out.println("\n17. See Number of Seats Available in Main Cabin of Flight ID " + flightID);
        System.out.println("\n18. See Number of Total Seats Available of Flight ID " + flightID);
        System.out.println("\n19. See all Information about Flight ID " + flightID);
        System.out.println("\n20. Change Flight ID or Exit");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print flight options employee menu
     * @param flightID ID of flight
     */
    public static void printOptionsEmployee(int flightID) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("What would you like to do? Please choose an option below [1-13]");
        System.out.println("\n1. See all Information about Flight ID " + flightID);
        System.out.println("\n2. See Number of Seats Available in First Class of Flight ID " + flightID);
        System.out.println("\n3. See Number of Seats Available in Business Class of Flight ID " + flightID);
        System.out.println("\n4. See Number of Seats Available in Main Cabin of Flight ID " + flightID);
        System.out.println("\n5. See Number of Total Seats Available of Flight ID " + flightID);
        System.out.println("\n6. See List of Customers of Flight ID " + flightID);
        System.out.println("\n7. See Total Amount Collected from First Class Seats Sold of Flight ID " + flightID);
        System.out.println("\n8. See Total Amount Collected from Business Class Seats Sold of Flight ID " + flightID);
        System.out.println("\n9. See Total Amount Collected from Main Cabin Seats Sold of Flight ID " + flightID);
        System.out.println("\n10. See Total Amount Collected from Total Seats Sold of Flight ID " + flightID);
        System.out.println("\n11. See Total MinerAirlines Fee Charged of Flight ID " + flightID);
        System.out.println("\n12. See Total Security Fee Charged of Flight ID " + flightID);
        System.out.println("\n13. See of Total Tax Charged Flight ID " + flightID);
        System.out.println("\n14. See of Total MinerAirlines Discount Given of Flight ID " + flightID);
        System.out.println("\n15. See Total Current & Expected Profit of Flight ID " + flightID);
        System.out.println("\n16. Change Information of Flight ID " + flightID);
        System.out.println("\n17. Change Flight ID or Exit");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print changes menu
     * @param flightID ID of flight
     */
    public static void printChangeOptions(int flightID) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Choose what information you would like to change about Flight ID " + flightID + " [1-8]");
        System.out.println("\n1. Change Flight Status of Flight ID " + flightID);
        System.out.println("\n2. Change Origin Airport & Code of Flight ID " + flightID);
        System.out.println("\n3. Change Destination Airport & Code of Flight ID " + flightID);
        System.out.println("\n4. Change Departure Date & Time of Flight ID " + flightID);
        System.out.println("\n5. Change First Class Price of Flight ID " + flightID);
        System.out.println("\n6. Change Business Class Price of Flight ID " + flightID);
        System.out.println("\n7. Change Main Cabin Price of Flight ID " + flightID);
        System.out.println("\n8. Choose Different Flight ID or Exit");
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * Method to print information depending on customer input
     * @param userView User input
     * @param flights Hash Map that contains all flight information
     * @param scnrInput Scanner
     */
    public static void menuSelectionCustomer(String userView, HashMap<Integer, Flight> flights, Scanner scnrInput) {

        // While input is not 'EXIT'
        while (!userView.equalsIgnoreCase("EXIT")) {

            int flightID = Integer.parseInt(userView);

            // If the flightId that the user input is bigger than the flight list, print an error message
            if (flights.size() >= flightID && flightID != 0) {

                // Set exitFlight to false
                boolean exitFlight = false;

                // While exitFlight is not true
                while(!exitFlight) {

                    // Access hash map and create an object with the flight info
                    Flight flightInfo = flights.get(flightID);

                    // Print options menu
                    Menus.printOptionsCustomer(flightID);

                    // Ask user to select from menu
                    int menuSelection = scnrInput.nextInt();
                    scnrInput.nextLine();

                    // If user input is 1, print Flight Number
                    if (menuSelection == 1) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Flight Number: " + flightInfo.getFlightNumber());
                    }

                    // If user input is 2, print Flight Type
                    if (menuSelection == 2) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }
                        
                        System.out.println("Flight Type: " + flightInfo.getFlightType());
                    }

                    // If user input is 3, print Origin Airport & Code
                    if(menuSelection == 3) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }
                        
                        System.out.println("Origin Airport & Code: " + flightInfo.getOriginAirport() + " - " + flightInfo.getOriginCode());
                    } 

                    // If user input is 4, print Destination Airport & Code
                    if(menuSelection == 4) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }
                        
                        System.out.println("Destination Airport & Code: " + flightInfo.getDestinationAirport() + " - " + flightInfo.getDestinationCode());
                    }

                    // If user input is 5, print Departure Date & Time
                    if(menuSelection == 5) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Departure Date & Time: " + flightInfo.getDepartureDate() + " at " + flightInfo.getDepartureTime());
                    }

                    // If user input is 6, print Arrival Date & Time
                    if(menuSelection == 6) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Arrival Date & Time: " + flightInfo.getArrivalDate() + " at " + flightInfo.getArrivalTime());
                    }

                    // If user input is 7, print Flight Duration
                    if(menuSelection == 7) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Flight Duration: " + flightInfo.getDuration() + " minutes");
                    }

                    // If user input is 8, print Flight Distance
                    if(menuSelection == 8) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Flight Distance: " + flightInfo.getDistance() + " miles");
                    }

                    // If user input is 9, print Time Zone Difference
                    if(menuSelection == 9) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Time Zone Difference: " + flightInfo.getTimeZoneDifference() + " hour(s)");
                    }

                    // If user input is 10, print Food Served
                    if(menuSelection == 10) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Food Served: " + flightInfo.getFoodServed());
                    }

                    // If user input is 11, print First Class Price
                    if(menuSelection == 11) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("First Class Price: $" + flightInfo.getFirstClassPrice());

                        // If flight is International, print Surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                            System.out.println("Sucharge: $" + flightInfo.getSurcharge() + " per seat");
                        }
                    }

                    // If user input is 12, print Business Class Price
                    if(menuSelection == 12) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Business Class Price: $" + flightInfo.getBusinessClassPrice());

                        // If flight is International, print Surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            System.out.println();
                            System.out.println("Sucharge: $" + flightInfo.getSurcharge() + " per seat");
                        }
                    }

                    // If user input is 13, print Main Cabin Price
                    if(menuSelection == 13) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Main Cabin Price: $" + flightInfo.getMainCabinPrice());

                        // If flight is International, print Surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            System.out.println();
                            System.out.println("Sucharge: $" + flightInfo.getSurcharge() + " per seat");
                        }
                    }

                    // If user input is 14, print Miner Points
                    if(menuSelection == 14) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Miner Points: " + flightInfo.getMinerPoints() + " points");
                    }

                    // If user input is 15, print Number of Seats Available in First Class
                    if(menuSelection == 15) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in First Class: " + flightInfo.getFirstClassSeats() + " seats");
                    }

                    // If user input is 16, print Number of Seats Available in Business Class
                    if(menuSelection == 16) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in Business Class: " + flightInfo.getBusinessClassSeats() + " seats");
                    }

                    // If user input is 17, print Number of Seats Available in Main Cabin
                    if(menuSelection == 17) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in Main Cabin: " + flightInfo.getMainCabinSeats() + " seats");
                    }

                    // If user input is 18, print Number of Total Seats
                    if(menuSelection == 18) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Total Seats Available: " + flightInfo.getTotalSeats());
                    }

                    // If user input is 19, print all of Flight Information
                    if(menuSelection == 19) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        flightInfo.printFlight();
                    }

                    // If user input is 20, set userView to true and exit
                    if(menuSelection == 20) {
                        userView = "EXIT";
                        break;
                    }
                }
            } else {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Invalid Flight ID\nPlease try again");
                break;
            }
        }
    }

    /**
     * Method to print information depending on employee input
     * @param employee Employee who will be interacting
     * @param userView User input
     * @param flights Hash Map that contains all flight information
     * @param scnrInput Scanner
     * @param writer Instance of Writer Class
     * @throws Exception Exception
     */
    public static void menuSelectionEmployee(Employee employee, String userView, HashMap<Integer, Flight> flights, Scanner scnrInput, Writer writer) throws Exception {
        // While input is not 'EXIT'
        while (!userView.equalsIgnoreCase("EXIT")) {

            int flightID = Integer.parseInt(userView);

            // If the flightId that the user input is bigger than the flight list, print an error message
            if (flights.size() >= flightID && flightID != 0) {

                // Set exitFlight to false
                boolean exitFlight = false;

                // While exitFlight is not true
                while(!exitFlight) {

                    // Access hash map and create an object with the flight info
                    Flight flightInfo = flights.get(flightID);

                    // Print options menu
                    Menus.printOptionsEmployee(flightID);

                    // Ask user to select from menu
                    int menuSelection = scnrInput.nextInt();
                    scnrInput.nextLine();

                    // If user input is 1, print all of Flight Information
                    if (menuSelection == 1) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        flightInfo.printFlight();
                    }

                    // If user input is 2, print Number of Seats Available in First Class
                    if(menuSelection == 2) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in First Class: " + flightInfo.getFirstClassSeats() + " seats");
                    }

                    // If user input is 3, print Number of Seats Available in Business Class
                    if(menuSelection == 3) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in Business Class: " + flightInfo.getBusinessClassSeats() + " seats");
                    }

                    // If user input is 4, print Number of Seats Available in Main Cabin
                    if(menuSelection == 4) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Seats Available in Main Cabin: " + flightInfo.getMainCabinSeats() + " seats");
                    }

                    // If user input is 5, print Number of Total Seats Available
                    if(menuSelection == 5) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("Number of Total Seats Available: " + flightInfo.getTotalSeats() + " seats");
                    }

                    // If user input is 6, print List of Customers
                    if (menuSelection == 6) {
                        ArrayList<Ticket> flightTicketList = flightInfo.getTicketsPurchased();
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        System.out.println("List of Customers: ");
                        System.out.println();

                        // If the list is empty, tell the employee
                        if (flightTicketList.isEmpty()) {
                            System.out.println("No Seats Sold For this Flight");
                        
                        // Else, print ticket information
                        } else {
                            // For every ticket in the flight's ticket list, print the ticket information
                            for (Ticket ticket : flightTicketList) {
                                ticket.printFlightTicketInfo();
                                System.out.println();
                            }
                        }
                        break;
                    }

                    // If user input is 7, print Total Amount Collected from First Class Seats Sold
                    if (menuSelection == 7) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Amount Collected from First Class Seats Sold: $" + flightInfo.getFirstClassRevenue());
                    }

                    // If user input is 8, print Total Amount Collected from Business Class Seats Sold
                    if (menuSelection == 8) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Amount Collected from Business Class Seats Sold: $" + flightInfo.getBusinessClassRevenue());
                    }

                    // If user input is 9, print Total Amount Collected from Main Cabin Seats Sold
                    if (menuSelection == 9) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Amount Collected from Main Cabin Seats Sold: $" + flightInfo.getMainCabinRevenue());
                    }

                    // If user input is 10, print Total Amount Collected from Total Seats Sold
                    if (menuSelection == 10) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Amount Collected from Total Seats Sold: $" + flightInfo.getTotalRevenue());
                    }

                    // If user input is 11, print Total MinerAirlines Fee Charged
                    if (menuSelection == 11) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total MinerAirlines Fee Charged: $" + flightInfo.getMinerAirlinesFeeCharged());
                    }

                    // If user input is 12, print Total Security Fee Charged
                    if (menuSelection == 12) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Security Fee Charged: $" + flightInfo.getSecurityFeeCharged());
                    }

                    // If user input is 13, print Total Tax Charged
                    if (menuSelection == 13) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total Tax Charged: $" + flightInfo.getTaxCharged());
                    }

                    // If user input is 14, print Total MinerAirlines Discount Given
                    if (menuSelection == 14) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();
                        System.out.println("Total MinerAirlines Discount Given: $" + flightInfo.getDiscounted());
                    }

                    // If user input is 15, print Current & Expected Profit
                    if (menuSelection == 15) {
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Flight ID " + flightID);
                        System.out.println();

                        // If flight is cancelled, let the user know
                        if (flightInfo.getFlightStatus().equalsIgnoreCase("Cancelled")) {
                            System.out.println("Status: " + flightInfo.getFlightStatus());
                            System.out.println();
                        }

                        // Expected Profit Calculations
                        int firstClassSoldOut = (flightInfo.getFirstClassPrice() * flightInfo.getFirstClassSeatsAvailable());

                        // If flight is International, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            firstClassSoldOut += (flightInfo.getFirstClassSeatsAvailable() * flightInfo.getSurcharge());
                        }

                        int businessClassSoldOut = (flightInfo.getBusinessClassPrice() * flightInfo.getBusinessClassSeatsAvailable());

                        // If flight is International, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            businessClassSoldOut += (flightInfo.getBusinessClassSeatsAvailable() * flightInfo.getSurcharge());
                        }

                        int mainCabinSeatsSoldOut = (flightInfo.getMainCabinPrice() * flightInfo.getMainCabinSeatsAvailable());

                        // If flight is International, add surcharge
                        if (flightInfo.getFlightType().equalsIgnoreCase("International")) {
                            mainCabinSeatsSoldOut += (flightInfo.getMainCabinSeatsAvailable() * flightInfo.getSurcharge());
                        }

                        double expectedProfit = (firstClassSoldOut + businessClassSoldOut + mainCabinSeatsSoldOut);

                        // Profit Made Calculations
                        double profitMade = flightInfo.getTotalRevenue() - flightInfo.getRouteCost();

                        double currentProfit = (profitMade - expectedProfit);

                        System.out.println("Current Profit: $" + currentProfit);
                        System.out.println();
                        System.out.println("Expected Profit: $" + expectedProfit);
                    }

                    // If user input is 16, print Change Information Menu
                    if (menuSelection == 16) {
                        // Print changes options menu
                        printChangeOptions(flightID);

                        // Make changes
                        FlightChanges.makeFlightChanges(employee, flightInfo, flightID, userView, scnrInput, writer);
                    }

                    // If user input is 17, set userView to true and exit
                    if (menuSelection == 17) {
                        userView = "EXIT";
                        break;
                    }
                }
            } else {
                System.out.println("-----------------------------------------------------------------");
                System.out.println("Invalid flight ID. Please try again.");
                break;
            }
        }
    }
}