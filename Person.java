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
 * Person.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract Class that contains attributes with setters and getters and a constructor for the attributes
 */
public abstract class Person {
    // Attributes
    private String firstName, lastName, dob, role, username, password;
    private boolean minerAirMembership;
    private int ID, flightsPurchased;
    private double moneyAvailable, savings;
    private ArrayList<Ticket> ticketsPurchased = new ArrayList<>();

    /**
     * Default Constructor
     */
    public Person() {

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
    public Person(int IDIn, String firstNameIn, String lastNameIn, String dobIn, String roleIn, double moneyAvailableIn,  
                  int flightsPurchasedIn, boolean minerAirMembershipIn, String usernameIn, String passwordIn) {
        this.ID = IDIn;
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.dob = dobIn;
        this.role = roleIn;
        this.username = usernameIn;
        this.password = passwordIn;
        this.minerAirMembership = minerAirMembershipIn;
        this.flightsPurchased = flightsPurchasedIn;
        this.moneyAvailable = moneyAvailableIn;
    }

    /**
     * Getter for First Name
     * @return String that contains First Name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Setter for First Name
     * @param firstNameIn First Name
     */
    public void setFirstName(String firstNameIn) {
        this.firstName = firstNameIn;
    }

    /**
     * Getter for Last Name
     * @return String that contains Last Name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for Last Name
     * @param lastNameIn Last Name
     */
    public void setLastName(String lastNameIn) {
        this.lastName = lastNameIn;
    }

    /**
     * Getter for Date of Birth
     * @return String for Date of Birth
     */
    public String getDob() {
        return this.dob;
    }

    /**
     * Setter for Date of Birth
     * @param dobIn Date of Birth
     */
    public void setDob(String dobIn) {
        this.dob = dobIn;
    }

    /**
     * Getter for Role
     * @return String that contains Role
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Setter for Role
     * @param roleIn Role
     */
    public void setRole(String roleIn) {
        this.role = roleIn;
    }

    /**
     * Getter for Username
     * @return String that contains Username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Setter for Username
     * @param usernameIn Username
     */
    public void setUsername(String usernameIn) {
        this.username = usernameIn;
    }

    /**
     * Getter for Password
     * @return String that contains Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter for Password
     * @param passwordIn Password
     */
    public void setPassword(String passwordIn) {
        this.password = passwordIn;
    }

    /**
     * Getter for MinerAirlines Membership
     * @return Boolean that contains MinerAirlines Membership
     */
    public boolean getMinerAirMembership() {
        return this.minerAirMembership;
    }

    /**
     * Setter for MinerAirlines Membership
     * @param minerAirMembershipIn MinerAirlines Membership
     */
    public void setMinerAirMembership(boolean minerAirMembershipIn) {
        this.minerAirMembership = minerAirMembershipIn;
    }

    /**
     * Getter for ID
     * @return Integer that contains ID
     */
    public int getID() {
        return this.ID;
    }

    /**
     * Setter for ID
     * @param iDIn ID
     */
    public void setID(int iDIn) {
        ID = iDIn;
    }

    /**
     * Getter for Flights Purchased
     * @return Integer that contains Flights Purchased
     */
    public int getFlightsPurchased() {
        return this.flightsPurchased;
    }

    /**
     * Setter for Flights Purchased
     * @param flightsPurchasedIn Flights Purchased
     */
    public void setFlightsPurchased(int flightsPurchasedIn) {
        this.flightsPurchased = flightsPurchasedIn;
    }

    /**
     * Getter for Money Available
     * @return Double that contains Money Available
     */
    public double getMoneyAvailable() {
        return this.moneyAvailable;
    }

    /**
     * Setter for Money Available
     * @param moneyAvailableIn Money Available
     */
    public void setMoneyAvailable(double moneyAvailableIn) {
        this.moneyAvailable =  Math.round(moneyAvailableIn * 100.0) / 100.0;
    }

    /**
     * Getter for Savings
     * @return Double that contains Savings
     */
    public double getSavings() {
        return this.savings;
    }

    /**
     * Setter for Savings
     * @param savingsIn Savings
     */
    public void setSavings(double savingsIn) {
        this.savings = Math.round(savingsIn * 100.0) / 100.0;
    }
    
    /**
     * Getter for Tickets Purchased
     * @return ArrayList of type Ticket that contains Tickets Purchased
     */
    public ArrayList<Ticket> getTicketsPurchased() {
        return this.ticketsPurchased;
    }

    /**
     * Setter for Tickets Purchased
     * @param ticketIn Ticket
     */
    public void setTicketsPurchased(Ticket ticketIn){
        ticketsPurchased.add(ticketIn);
    }

    /**
     * Method to find a person by name
     * @param people Hash Map that contains all customer/employee information
     * @param firstName First name of person to be found
     * @param lastName Last name of person to be found
     * @return Person found
     */ 
    public static Person findByName(HashMap<Integer, Person> people, String firstName, String lastName) {
        for (int i = 1; i < people.size() + 1; i++) {

            // Create new factory
            PersonCreator factory = new PersonCreator();
            
            // Create a new person depending on role
            Person foundPerson = factory.createPerson(people.get(i).getRole());
                foundPerson = people.get(i);

            // If the first and last name match, return the customer
            if(foundPerson.getFirstName().equalsIgnoreCase(firstName) && foundPerson.getLastName().equalsIgnoreCase(lastName)){
                return foundPerson;
            }
        }
        // Person not found
        return null;
    }
}