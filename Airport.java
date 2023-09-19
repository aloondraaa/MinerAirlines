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
 * Airport.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

/**
 * Abstract Class that contains attributes with setters and getters and a constructor for the attributes 
 */
public abstract class Airport {
    // Attributes
    private String originAirport, originCode, originAirportCity, originAirportState, originAirportCountry, 
                   destinationAirport, destinationCode, destinationAirportCity, destinationAirportState, destinationAirportCountry;
    private double originAirportFee, destinationAirportFee, originAirportFeeCharged, destinationAirportFeeCharged;
    private boolean originAirportLounge, destinationAirportLounge;

    /**
     * Default Constructor
     */
    public Airport() {

    }

    /**
     * Constructor that receives attributes
     * @param originAirportIn Origin Airport
     * @param originCodeIn Origin Code
     * @param originAirportCityIn Origin Airport City
     * @param originAirportStateIn Origin Airport State
     * @param originAirportCountryIn Origin Airport Country
     * @param destinationAirportIn Destination Airport
     * @param destinationCodeIn Destination Code
     * @param destinationAirportCityIn Destination Airport City
     * @param destinationAirportStateIn Destination Airport State
     * @param destinationAirportCountryIn Destination Airport Country
     * @param originAirportFeeIn Origin Airport Fee
     * @param destinationAirportFeeIn Destination Airport Fee
     * @param originAirportLoungeIn Origin Airport Lounge
     * @param destinationAirportLoungeIn Destination Airport Lounge
     */
    public Airport(String originAirportIn, String originCodeIn, String originAirportCityIn, String originAirportStateIn,
                   String originAirportCountryIn, String destinationAirportIn, String destinationCodeIn,
                   String destinationAirportCityIn, String destinationAirportStateIn, String destinationAirportCountryIn,
                   double originAirportFeeIn, double destinationAirportFeeIn, boolean originAirportLoungeIn, boolean destinationAirportLoungeIn) {
        this.originAirport = originAirportIn;
        this.originCode = originCodeIn;
        this.originAirportCity = originAirportCityIn;
        this.originAirportState = originAirportStateIn;
        this.originAirportCountry = originAirportCountryIn;
        this.originAirportFee = originAirportFeeIn;
        this.destinationAirport = destinationAirportIn;
        this.destinationCode = destinationCodeIn;
        this.destinationAirportCity = destinationAirportCityIn;
        this.destinationAirportState = destinationAirportStateIn;
        this.destinationAirportCountry = destinationAirportCountryIn;
        this.destinationAirportFee = destinationAirportFeeIn;
        this.originAirportLounge = originAirportLoungeIn;
        this.destinationAirportLounge = destinationAirportLoungeIn;
    }

    /**
     * Getter for Origin Airport
     * @return String that contains Origin Airport
     */
    public String getOriginAirport() {
        return this.originAirport;
    }

    /**
     * Setter for Origin Airport
     * @param originAirportIn Origin Airport
     */
    public void setOriginAirport(String originAirportIn) {
        this.originAirport = originAirportIn;
    }

    /**
     * Getter for Origin Code
     * @return String that contains Origin Code
     */
    public String getOriginCode() {
        return this.originCode;
    }

    /**
     * Setter for Origin Code
     * @param originCodeIn Origin Code
     */
    public void setOriginCode(String originCodeIn) {
        this.originCode = originCodeIn;
    }

    /**
     * Getter for Origin Airport City
     * @return String that contains Origin Airport City
     */
    public String getOriginAirportCity() {
        return this.originAirportCity;
    }

    /**
     * Setter for Origin Airport City
     * @param originAirportCityIn Origin Airport City
     */
    public void setOriginAirportCity(String originAirportCityIn) {
        this.originAirportCity = originAirportCityIn;
    }

    /**
     * Getter for Origin Airport State
     * @return String that contains Origin Airport State
     */
    public String getOriginAirportState() {
        return this.originAirportState;
    }

    /**
     * Setter for Origin Airport State
     * @param originAirportStateIn Origin Airport State
     */
    public void setOriginAirportState(String originAirportStateIn) {
        this.originAirportState = originAirportStateIn;
    }

    /**
     * Getter for Origin Airport Country
     * @return String that contains Origin Airport Country
     */
    public String getOriginAirportCountry() {
        return this.originAirportCountry;
    }

    /**
     * Setter for Origin Airport Country
     * @param originAirportCountryIn Origin Airport Country
     */
    public void setOriginAirportCountry(String originAirportCountryIn) {
        this.originAirportCountry = originAirportCountryIn;
    }

    /**
     * Getter for Destination Airport
     * @return String that contains Destination Airport
     */
    public String getDestinationAirport() {
        return this.destinationAirport;
    }

    /**
     * Setter for Destination Airport
     * @param destinationAirportIn Destination Airport
     */
    public void setDestinationAirport(String destinationAirportIn) {
        this.destinationAirport = destinationAirportIn;
    }

    /**
     * Getter for Destination Code
     * @return String that contains Destination Code
     */
    public String getDestinationCode() {
        return this.destinationCode;
    }

    /**
     * Setter for Destination Code
     * @param destinationCodeIn Destination Code
     */
    public void setDestinationCode(String destinationCodeIn) {
        this.destinationCode = destinationCodeIn;
    }

    /**
     * Getter for Destination Airport City
     * @return String that contains Destination Airport City
     */
    public String getDestinationAirportCity() {
        return this.destinationAirportCity;
    }

    /**
     * Setter for Destination Airport City
     * @param destinationAirportCityIn Destination Airport City
     */
    public void setDestinationAirportCity(String destinationAirportCityIn) {
        this.destinationAirportCity = destinationAirportCityIn;
    }

    /**
     * Getter for Destination Airport State
     * @return String that contains Destination Airport State
     */
    public String getDestinationAirportState() {
        return this.destinationAirportState;
    }

    /**
     * Setter for Destination Airport State
     * @param destinationAirportStateIn Destination Airport State
     */
    public void setDestinationAirportState(String destinationAirportStateIn) {
        this.destinationAirportState = destinationAirportStateIn;
    }

    /**
     * Getter for Destination Airport Country
     * @return String that conatins Destination Airport Country
     */
    public String getDestinationAirportCountry() {
        return this.destinationAirportCountry;
    }

    /**
     * Setter for Destination Airport Country
     * @param destinationAirportCountryIn Destination Airport Country
     */
    public void setDestinationAirportCountry(String destinationAirportCountryIn) {
        this.destinationAirportCountry = destinationAirportCountryIn;
    }

    /**
     * Getter for Origin Airport Fee
     * @return Double that contains Origin Airport Fee
     */
    public double getOriginAirportFee() {
        return this.originAirportFee;
    }

    /**
     * Setter for Origin Airport Fee
     * @param originAirportFeeIn Origin Airport Fee
     */
    public void setOriginAirportFee(double originAirportFeeIn) {
        this.originAirportFee = originAirportFeeIn;
    }

    /**
     * Getter for Destination Airport Fee
     * @return Double that contains Destination Airport Fee
     */
    public double getDestinationAirportFee() {
        return this.destinationAirportFee;
    }

    /**
     * Setter for Destination Airport Fee
     * @param destinationAirportFeeIn Destination Airport Fee
     */
    public void setDestinationAirportFee(double destinationAirportFeeIn) {
        this.destinationAirportFee = destinationAirportFeeIn;
    }

    /**
     * Getter for Origin Airport Fee Charged
     * @return Double that contains Origin Airport Fee Charged
     */
    public double getOriginAirportFeeCharged() {
        return this.originAirportFeeCharged;
    }

    /**
     * Setter for Origin Airport Fee Charged
     * @param originAirportFeeChargedIn Origin Airport Fee Charged
     */
    public void setOriginAirportFeeCharged(double originAirportFeeChargedIn) {
        this.originAirportFeeCharged = originAirportFeeChargedIn;
    }

    /**
     * Getter for Destination Airport Fee Charged
     * @return Double that contains Destination Airport Fee Charged
     */
    public double getDestinationAirportFeeCharged() {
        return this.destinationAirportFeeCharged;
    }

    /**
     * Setter for Destination Airport Fee Charged
     * @param destinationAirportFeeChargedIn Destination Airport Fee Charged
     */
    public void setDestinationAirportFeeCharged(double destinationAirportFeeChargedIn) {
        this.destinationAirportFeeCharged = destinationAirportFeeChargedIn;
    }

    /**
     * Getter for Origin Airport Lounge
     * @return Boolean that contains Origin Airport Lounge
     */
    public boolean getOriginAirportLounge() {
        return this.originAirportLounge;
    }

    /**
     * Setter for Origin Airport Lounge
     * @param originAirportLoungeIn Origin Airport Lounge
     */
    public void setOriginAirportLounge(boolean originAirportLoungeIn) {
        this.originAirportLounge = originAirportLoungeIn;
    }

    /**
     * Getter for Destination Airport Lounge
     * @return Boolean that contains Destination Airport Lounge
     */
    public boolean getDestinationAirportLounge() {
        return this.destinationAirportLounge;
    }

    /**
     * Setter for Destination Airport Lounge
     * @param destinationAirportLoungeIn Destination Airport Lounge
     */
    public void setDestinationAirportLounge(boolean destinationAirportLoungeIn) {
        this.destinationAirportLounge = destinationAirportLoungeIn;
    }
}