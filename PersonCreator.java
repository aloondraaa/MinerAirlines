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
 * PersonCreator.java
 *  
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

/**
 * Class that contains factory to create Person objects depending on the person's role
 */
public class PersonCreator {
    /**
     * Default Constructor
     */
    public PersonCreator() {

    }

    /**
     * Method to create empty person objects depending on type
     * @param role Role assigned to a person
     * @return Person depending on the role
     */
    public Person createPerson(String role) {
        if (role.equalsIgnoreCase("Customer")) {
            return new Customer();
        } else {
            return new Employee();
        }
    }

    /**
     * Method to create person objects depending on type
     * @param ID ID
     * @param firstName First Name
     * @param lastName Last Name
     * @param dob Date of Birth
     * @param role Role
     * @param moneyAvailable Money Available
     * @param flightsPurchased Flights Purchased
     * @param minerAirMembership MinerAir Membership
     * @param username Username
     * @param password Password
     * @return Person with all attributes, depending on the role
     */
    public Person createPerson(int ID, String firstName, String lastName, String dob, String role, double moneyAvailable,  int flightsPurchased, 
                               boolean minerAirMembership, String username, String password) {
        if (role.equalsIgnoreCase("Customer")) {
            return new Customer(ID, firstName, lastName, dob, role, moneyAvailable, flightsPurchased, minerAirMembership, username, password);
        } else {
            return new Employee(ID, firstName, lastName, dob, role, moneyAvailable, flightsPurchased, minerAirMembership, username, password);
        } 
    }
}