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
 * PersonLogin.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.HashMap;
import java.util.Scanner;

/**
 * Class that checks if the person exists and handles log in
 */
public class PersonLogin {
    /**
     * Method to find customer by first and last name entered by user
     * @param people Hash Map that contains all customer/employee information
     * @param scnrInput Scanner
     * @return Person found
     */
    public static Person findPersonByName(HashMap<Integer, Person> people, Scanner scnrInput) {
        try {
            // Create new factory
            PersonCreator factory = new PersonCreator();
            // Ask for first and last name
            System.out.println("-----------------------------------------------------------------");
            System.out.print("Enter your first name: ");
            String firstName = scnrInput.nextLine();
            System.out.println();
            System.out.print("Enter your last name: ");
            String lastName = scnrInput.nextLine();

            // Look through hashmap to find customers
            for (int i = 1; i < people.size() + 1; i++) {
                // Create a new person type depending on their role
                Person foundPerson = factory.createPerson(people.get(i).getRole());
                foundPerson = people.get(i);

                // If the first and last name match, return the person
                if(foundPerson.getFirstName().equalsIgnoreCase(firstName) && foundPerson.getLastName().equalsIgnoreCase(lastName)){
                    return foundPerson;
                }
            }
        // Return null if an exception occurs
        } catch(Exception PersonNotFound) {
            return null;
        }
        // Return null if no customer is found
        return null;
    }

    /**
     * Method that will ask for username and password and will check if it matches
     * @param findPerson Person found
     * @param scnrInput Scanner
     * @return boolean if person was found or not
     */
    public static boolean logIn(Person findPerson, Scanner scnrInput) {

        // Ask user for username and password, if both are match the person, return true
        try {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Enter your username and password.");
            System.out.println();
            System.out.print("Username: ");
            String username = scnrInput.nextLine();
            System.out.println();
            System.out.print("Password: ");
            String password = scnrInput.nextLine();
            System.out.println("-----------------------------------------------------------------");

            // Check if the username and password entered by user match the one in the file
            if(findPerson.getUsername().equals(username) && findPerson.getPassword().equals(password)){
                return true;
            }
        // Return false if an exception occurs
        } catch(Exception IncorrectUserOrPass){
            return false;
        }
        // Return false if username and/or password don't match
        return false;
    }
} 