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
 * FlightRunner.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that contains Main Method
 */
public class FlightRunner {
    /**
     * Main method where all of the methods needed to run the program are called
     * @param args Arguments received
     */
    public static void main(String[] args) {
        // Scanner for user input
        Scanner scnrInput = new Scanner(System.in);
        try {
            // Get singleton instance of the Writer
            Writer writer = Writer.getInstance();

            // Overwrite Files
            writer.overwriteFiles();

            // Create hash maps using the methods
            HashMap<Integer,Flight> flights = ReadFiles.flightObjects();
            HashMap<Integer,Person> people = ReadFiles.personObjects();

            // Call methods to set seats available, status and fees/tax
            Flight.setSeatsAvailable(flights);
            Flight.setStatusFlights(flights);
            Flight.setFlightFeesTaxDiscount(flights);

            boolean exitProgram = false;

            // While exitProgram is false
            while(!exitProgram) {

                Menus.welcomeUser();

                String userChoice = scnrInput.nextLine();
                System.out.println("-----------------------------------------------------------------");

                // If the user inputs EXIT, exit to ask for log in
                if (userChoice.equalsIgnoreCase("EXIT")) {
                    System.out.println("Thank you for choosing MinerAir.");
                    System.out.println("-----------------------------------------------------------------");
                    writer.writeFiles(flights, people);
                    System.exit(0);
                }

                // While input is not 'EXIT'
                while (!userChoice.equalsIgnoreCase("EXIT")) {

                    char accountYN = userChoice.charAt(0);

                    // If user is has an account, ask them to log in
                    if (accountYN == 'Y' || accountYN == 'y') {
                        System.out.println("Please log in.");

                        // Create new customer and ask user to enter their info
                        Person person = PersonLogin.findPersonByName(people, scnrInput);

                        // While customer is not found, keep asking for first and last name
                        while(person == null) {
                            System.out.println("-----------------------------------------------------------------");
                            System.out.println("Person not found. Please try again.");
                            person = PersonLogin.findPersonByName(people, scnrInput);
                        }
                        
                        System.out.println("-----------------------------------------------------------------");
                        System.out.println("Enter your login information. You will only have 3 tries to log in or you will have to try again later.");

                        // Give the user 3 attempts to log in, while login is not successful keep asking until they run out of attempts
                        int loginTries = 3;
                        while (!PersonLogin.logIn(person, scnrInput)) {
                            loginTries -= 1;

                            // If there are no more tries left, exit the program
                            if (loginTries == 0) {
                                System.out.println("Maximum number of tries reached. Please try again later.");
                                System.out.println("-----------------------------------------------------------------");
                                System.exit(0);
                            }
                            
                            System.out.println("Incorrect username or password. Please try again.");
                            System.out.println("You have " + loginTries + " tries remaining.");
                        }
                        
                        // If it gets out the while loop, login was successful
                        System.out.println("Login Successful!");

                        // If the person's role is Customer, give them access to the customer menu
                        if (person.getRole().equalsIgnoreCase("Customer")) {
                            // Create new Customer object and set person to customer
                            Customer customer = new Customer();
                            customer = (Customer) person;
                            CustomerEmployeeAccess.customerAccess(customer, scnrInput, flights, exitProgram, writer);
                            break;
                             
                        // If the person's role is Employee, give them access to the employee menu
                        } else if (person.getRole().equalsIgnoreCase("Employee")) {
                            // Create new Employee object and set person to employee
                            Employee employee = new Employee();
                            employee = (Employee) person;
                            CustomerEmployeeAccess.employeeAccess(employee, scnrInput, flights, exitProgram, people, writer);
                            break;
                        }
                    
                    //  If user doesn't have an account, tell them they need to have an account and end the program
                    } else if (accountYN == 'N' || accountYN == 'n') {
                        System.out.println("You need to have a MinerAir account");
                        System.out.println("-----------------------------------------------------------------");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid input\nPlease try again");
                        break;
                    }
                }
            }
        } catch(Exception e) {
            System.out.println("Unexpected Error");
        }
        scnrInput.close();
    }
}