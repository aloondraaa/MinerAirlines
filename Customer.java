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
 * Customer.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

/**
 * Class that receives attributes from the Person Class and contains a constructor for the attributes
 */
public class Customer extends Person { 
    /**
     * Default Constructor
     */
    public Customer() {
 
    }

    /**
     * Constructor that receives attributes
     * @param IDIn ID
     * @param firstNameIn First Name
     * @param lastNameIn Last Name
     * @param dobIn Date of Birth
     * @param roleIn Role
     * @param moneyAvailableIn Money Available
     * @param flightsPurchasedIn Flights Purchased
     * @param minerAirMembershipIn MinerAir Membership
     * @param usernameIn Username
     * @param passwordIn Password
     */
    public Customer(int IDIn, String firstNameIn, String lastNameIn, String dobIn, String roleIn, double moneyAvailableIn,  int flightsPurchasedIn, 
                    boolean minerAirMembershipIn, String usernameIn, String passwordIn) {
        super(IDIn, firstNameIn, lastNameIn, dobIn, roleIn, moneyAvailableIn, flightsPurchasedIn, minerAirMembershipIn, usernameIn, passwordIn);
    } 
}