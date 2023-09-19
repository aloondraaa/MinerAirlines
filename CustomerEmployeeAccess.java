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
 * CustomerEmployeeAccess.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that handles customer and employee access
 */
public class CustomerEmployeeAccess {
    /**
     * Method that handles all customer access
     * @param person Customer/Employee that will be interacting
     * @param scnrInput Scanner
     * @param flights Hash Map that contains all flight information
     * @param exitProgram To exit the menu
     * @param writer Instance of Writer Class
     */
    public static void customerAccess(Person person, Scanner scnrInput, HashMap<Integer, Flight> flights, boolean exitProgram, Writer writer) {

        // Set exit to false
        boolean exit = false;
                
        // While exit is not true
        while(!exit) {

            // Print main menu for user
            Menus.mainMenuCustomer(person);

            try {
                // Receive user input for choice
                String choice = scnrInput.nextLine();

                // If the user inputs EXIT, exit to ask for log in
                if (choice.equalsIgnoreCase("EXIT")) {
                    exit = true;
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Logged Out Successfully!");
                    break;
                }

                // While input is not 'EXIT'
                while (!choice.equalsIgnoreCase("EXIT")) {

                    try {
                        // Convert choice to integer
                        int selectionMain = Integer.parseInt(choice);
                        
                        // If the user input is greater than 7 or less than 1, ask again
                        if(selectionMain > 7 || selectionMain < 1) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("Invalid Input\nPlease try again");
                            break;
                        }

                        // If user input is 1, print all flights
                        if (selectionMain == 1) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE ALL FLIGHTS MENU");
                            Flight.printAllFlights(flights);
                            break;
                        }

                        // If user input is 2, show account balance
                        if (selectionMain == 2) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE BALANCE MENU");
                            System.out.println();
                            System.out.println(person.getFirstName() +  " " + person.getLastName() + " you have an account balance of $" + person.getMoneyAvailable() + " available.");
                            break;
                        }

                        // If user input is 3, ask for flightID and let user buy tickets
                        if (selectionMain == 3) {
                            
                            // Ask user to input flightID
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("PURCHASE FLIGHT TICKET MENU\n");

                            Menus.askAirportCodeOrID();
                            
                            int userSelection = scnrInput.nextInt();
                            scnrInput.nextLine();

                            // If the user input is greater than 2 or less than 1, ask again
                            if(userSelection > 2 || userSelection < 1) {
                                System.out.println("-----------------------------------------------------------------");
                                System.out.println("Invalid Input\nPlease try again");
                                break;
                            }

                            Menus.askAirportCodeDateCustomer(userSelection, flights, scnrInput);
                            
                            // Ask for Flight ID
                            System.out.println("-----------------------------------------------------------------");
                            Menus.askFlightID(flights);

                            String userPurchase = scnrInput.nextLine();

                            // If the user inputs EXIT, stop the program
                            if (userPurchase.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            TicketTransaction.buyTickets(userPurchase, flights, person, scnrInput, writer);
                            break;
                        }

                        // If user input is 4, show flight info and change options
                        if (selectionMain == 4) {
                            
                            // Ask user to input flightID
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE FLIGHT INFORMATION MENU\n");

                            Menus.askAirportCodeOrID();
                            
                            int userSelection = scnrInput.nextInt();
                            scnrInput.nextLine();

                            // If the user input is greater than 2 or less than 1, ask again
                            if(userSelection > 2 || userSelection < 1) {
                                System.out.println("-----------------------------------------------------------------");
                                System.out.println("Invalid Input\nPlease try again");
                                break;
                            }

                            Menus.askAirportCodeDateCustomer(userSelection, flights, scnrInput);
                            
                            // Ask for Flight ID
                            System.out.println("-----------------------------------------------------------------");
                            Menus.askFlightID(flights);

                            String userView = scnrInput.nextLine();

                            // If the user inputs EXIT, leave this menu
                            if (userView.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            Menus.menuSelectionCustomer(userView, flights, scnrInput);
                            break;
                        }

                        // If user input is 5, print customer's ticket list
                        if (selectionMain == 5) {
                            ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();
                        
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("TICKETS PURCHASED MENU");
                            System.out.println();
                            System.out.println("List of Tickets Purchased: ");
                            System.out.println();
                            
                            // If the list is empty, tell the customer
                            if (customerTicketList.isEmpty()) {
                                System.out.println("You don't have any tickets");
                            
                            // Else, print ticket information
                            } else {
                                // For every ticket in the customer's ticket list, print the ticket information
                                for (Ticket ticket : customerTicketList) {
                                    ticket.printCustomerTicketInfo();
                                    System.out.println();
                                }
                            }
                            break;
                        }

                        // If user input is 6, print flights seats sold
                        if (selectionMain == 6) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE FLIGHT SEATS SOLD MENU");
                            System.out.println();

                            Menus.askAirportCodeOrID();
                            
                            int userSelection = scnrInput.nextInt();
                            scnrInput.nextLine();

                            // If the user input is greater than 2 or less than 1, ask again
                            if(userSelection > 2 || userSelection < 1) {
                                System.out.println("-----------------------------------------------------------------");
                                System.out.println("Invalid Input\nPlease try again");
                                break;
                            }

                            Menus.askAirportCodeDateCustomer(userSelection, flights, scnrInput);
                            
                            // Ask for Flight ID
                            System.out.println("-----------------------------------------------------------------");
                            Menus.askFlightID(flights);

                            String userChoice = scnrInput.nextLine();
                            System.out.println("-----------------------------------------------------------------");

                            // If the user inputs EXIT, stop the program
                            if (userChoice.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            // While input is not 'EXIT', print flight seats sold
                            while (!userChoice.equalsIgnoreCase("EXIT")) {

                                int flightID = Integer.parseInt(userChoice);

                                Flight.printSeatsSold(flights, flightID);
                                break;
                            }
                            break;
                        }

                        // If the user input is 7, print cancel order menu
                        if (selectionMain == 7) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("CANCEL ORDER MENU");
                            System.out.println();

                            ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();

                            System.out.println("List of Tickets Purchased: ");
                            System.out.println();
                            
                            // If the list is empty, tell the customer
                            if (customerTicketList.isEmpty()) {
                                System.out.println("You don't have any tickets");
                                break;
                            
                            // Else, print ticket information
                            } else {
                                // For every ticket in the customer's ticket list, print the ticket information
                                for (Ticket ticket : customerTicketList) {
                                    ticket.printCustomerTicketInfo();
                                    System.out.println();
                                }

                                // Ask the user to input the confimation number of the ticket they want to cancel
                                System.out.println("Enter the confirmation number of the ticket you would like to cancel.");
                                System.out.println();
                                System.out.println("If you would like to exit this menu enter 'EXIT'");
                                System.out.println("-----------------------------------------------------------------");

                                String userCancel = scnrInput.nextLine();

                                // If the user inputs EXIT, exit
                                if (userCancel.equalsIgnoreCase("EXIT")) {
                                    break;
                                }
                                TicketTransaction.cancelTicketCustomer(userCancel, customerTicketList, person, scnrInput, flights, writer);
                                break;
                            }
                        }
                    } catch(Exception InvalidInput) {
                        System.out.println("Invalid input\nPlease try again");
                        InvalidInput.printStackTrace();
                        break;
                    }
                }
            } catch(Exception InvalidInput) {
                System.out.println("Invalid input\nPlease try again");
                break;
            }
        }
    }

    /**
     * Method that handles all employee access
     * @param employee Employee that will be interacting
     * @param scnrInput Scanner
     * @param flights Hash Map that contains all flight information
     * @param exitProgram To exit the menu
     * @param people Hash Map that contains all customer/employee information
     * @param writer Instance of Writer Class
     */
    public static void employeeAccess(Employee employee, Scanner scnrInput, HashMap<Integer, Flight> flights, boolean exitProgram, HashMap<Integer, Person> people, Writer writer) {
        // Set exit to false
        boolean exitEmployee = false;
                
        // While exit is not true
        while(!exitEmployee) {

            // Print main menu for user
            Menus.mainMenuEmployee(employee);

            try {
                // Receive user input for choice
                String choice = scnrInput.nextLine();

                // If the user inputs EXIT, exit to ask for log in
                if (choice.equalsIgnoreCase("EXIT")) {
                    exitEmployee = true;
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Logged Out Successfully!");
                    System.out.println("-----------------------------------------------------------------");
                    break;
                }

                // While input is not 'EXIT'
                while (!choice.equalsIgnoreCase("EXIT")) {
 
                    try {
                        // Convert choice to integer
                        int selectionMain = Integer.parseInt(choice);

                        // If the user input is greater than 6 or less than 1, ask again
                        if(selectionMain > 6 || selectionMain < 1) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("Invalid Input\nPlease try again");
                            break;
                        }

                        // If the user input is 1, print infomation menu
                        if (selectionMain == 1) {
                            // Ask user to input flightID
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE FLIGHT INFORMATION MENU\n");
                            Menus.askFlightID(flights);

                            String userViewFlight = scnrInput.nextLine();

                            // If the user inputs EXIT, leave this menu
                            if (userViewFlight.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            Menus.menuSelectionEmployee(employee, userViewFlight, flights, scnrInput, writer);
                            break;
                        }

                        // If the user input is 2, print Airport info
                        if (selectionMain == 2) {
                            // Ask user to input Airport Code
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("SEE AIRPORT INFORMATION MENU\n");
                            Menus.askAirportCodeEmployee(flights);

                            String userViewAirport = scnrInput.nextLine();

                            // If the user inputs EXIT, leave this menu
                            if (userViewAirport.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            Flight.printAirportInfo(userViewAirport, flights);
                            break;
                        }

                        // If the user input is 3, print cancel flight menu
                        if (selectionMain == 3) {
                            // Ask user to input flightID
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("CANCEL FLIGHT MENU\n");
                            Menus.askFlightID(flights);

                            String userCancel = scnrInput.nextLine();

                            // If the user inputs EXIT, leave this menu
                            if (userCancel.equalsIgnoreCase("EXIT")) {
                                break;
                            }

                            FlightChanges.cancelFlight(employee, flights, userCancel, people, writer);
                            break;
                        }

                        // If the user input is 4, write electronic ticket summary
                        if (selectionMain == 4) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("REQUEST ELECTRONIC TICKET SUMMARY MENU\n");

                            // Ask for Customer name
                            System.out.print("Enter first name of Customer: ");
                            String firstName = scnrInput.nextLine();
                            System.out.println();
                            System.out.print("Enter last name of Customer: ");
                            String lastName = scnrInput.nextLine();
                            System.out.println("-----------------------------------------------------------------");

                            writer.ElectronicTicketSummary("ElectronicTicketSummary.txt", people, firstName, lastName, employee);
                            break;
                        }

                        // If the user input is 5, go to customer menu
                        if (selectionMain == 5) {
                            customerAccess(employee, scnrInput, flights, exitProgram, writer);
                            break;
                        }

                        // If the user input is 6, proceed with autopurchase
                        if (selectionMain == 6) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("AUTOPURCHASE MENU\n");
                            Menus.AutoPurchaserMenu();
                            String userSelection = scnrInput.nextLine();

                            // If the user inputs EXIT, leave this menu
                            if (userSelection.equalsIgnoreCase("EXIT")) {
                                break;
                            // Else, complete autopurchase
                            } else {

                                int autoPurchaserSelection = Integer.parseInt(userSelection);
                                System.out.println("-----------------------------------------------------------------");
                                
                                String fileName = "";

                                if (autoPurchaserSelection == 1) {
                                    fileName = "AutoPurchaser10k.csv";
                                } else if (autoPurchaserSelection == 2) {
                                    fileName = "AutoPurchaser100k.csv";
                                } else if (autoPurchaserSelection == 3) {
                                    fileName = "AutoPurchaser400k.csv";
                                }

                                AutoPurchase.autoPurchaseTickets(fileName, flights, people, writer);
                                break;
                            }
                        }
                    } catch(Exception InvalidInput) {
                        System.out.println("Invalid input\nPlease try again");
                        break;
                    }
                }
            } catch(Exception InvalidInput) {
                System.out.println("Invalid input\nPlease try again");
                break;
            }
        }
    }
}