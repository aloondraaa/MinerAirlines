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
 * FlightCreator.java
 *  
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

/**
 * Class that contains factory to create Flight objects depending on the flight type
 */
public class FlightCreator {
    /**
     * Default Constructor
     */
    public FlightCreator() {

    }

    /**
     * create empty flight objects depending on type
     * @param flightTypeIn Flight Type
     * @return Flight depending on the type
     */
    public Flight createFlight(String flightTypeIn) {
        // If the flight type is Domestic, create a Domestic flight object
        if (flightTypeIn.equalsIgnoreCase("Domestic")) {
            return new Domestic();
        } else {
            return new International();
        }
    }

    /**
     * Method to create flight objects depending on type
     * @param flightIDIn Flight ID
     * @param flightNumberIn Flight Number
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
     * @param departureDateIn Departure Date
     * @param departureTimeIn Departure Time
     * @param durationIn Duration
     * @param distanceIn Distance
     * @param timeZoneDifferenceIn Time Zone Difference
     * @param arrivalDateIn Arrival Date
     * @param arrivalTimeIn Arrival Time
     * @param flightTypeIn Flight Type
     * @param surchargeIn Surcharge
     * @param foodServedIn Food Served
     * @param routeCostIn Route Cost
     * @param minerPointsIn Miner Points
     * @param totalSeatsIn Total Seats
     * @param firstClassSeatsIn First Class Seats
     * @param businessClassSeatsIn Business Class Seats
     * @param mainCabinSeatsIn Main Cabin Seats
     * @param firstClassPriceIn First Class Price
     * @param businessClassPriceIn Business Class Price
     * @param mainCabinPriceIn Main Cabin Price
     * @return Flight with all attributes, depending on the type
     */
    public Flight createFlight(int flightIDIn, int flightNumberIn, String originAirportIn, String originCodeIn, String originAirportCityIn, String originAirportStateIn,
                               String originAirportCountryIn, String destinationAirportIn, String destinationCodeIn, String destinationAirportCityIn, String destinationAirportStateIn, 
                               String destinationAirportCountryIn, double originAirportFeeIn, double destinationAirportFeeIn, boolean originAirportLoungeIn, boolean destinationAirportLoungeIn, 
                               String departureDateIn, String departureTimeIn, int durationIn, int distanceIn, int timeZoneDifferenceIn, String arrivalDateIn, String arrivalTimeIn, String flightTypeIn, 
                               int surchargeIn, boolean foodServedIn, int routeCostIn, int minerPointsIn, int totalSeatsIn, int firstClassSeatsIn, int businessClassSeatsIn, int mainCabinSeatsIn, 
                               int firstClassPriceIn, int businessClassPriceIn, int mainCabinPriceIn) {
        // If the flight type is Domestic, create a Domestic flight object
        if (flightTypeIn.equalsIgnoreCase("Domestic")) {
            return new Domestic(flightIDIn, flightNumberIn, originAirportIn, originCodeIn, originAirportCityIn, originAirportStateIn, originAirportCountryIn, destinationAirportIn, 
                                destinationCodeIn, destinationAirportCityIn, destinationAirportStateIn, destinationAirportCountryIn, originAirportFeeIn, destinationAirportFeeIn, 
                                originAirportLoungeIn, destinationAirportLoungeIn, departureDateIn, departureTimeIn, durationIn, distanceIn, timeZoneDifferenceIn, arrivalDateIn, 
                                arrivalTimeIn, flightTypeIn, surchargeIn, foodServedIn, routeCostIn, minerPointsIn, totalSeatsIn, firstClassSeatsIn, businessClassSeatsIn, mainCabinSeatsIn, 
                                firstClassPriceIn, businessClassPriceIn, mainCabinPriceIn);
        // Else, return an International flight object
        } else {
            return new International(flightIDIn, flightNumberIn, originAirportIn, originCodeIn, originAirportCityIn, originAirportStateIn, originAirportCountryIn, destinationAirportIn, 
                                destinationCodeIn, destinationAirportCityIn, destinationAirportStateIn, destinationAirportCountryIn, originAirportFeeIn, destinationAirportFeeIn, 
                                originAirportLoungeIn, destinationAirportLoungeIn, departureDateIn, departureTimeIn, durationIn, distanceIn, timeZoneDifferenceIn, arrivalDateIn, 
                                arrivalTimeIn, flightTypeIn, surchargeIn, foodServedIn, routeCostIn, minerPointsIn, totalSeatsIn, firstClassSeatsIn, businessClassSeatsIn, mainCabinSeatsIn, 
                                firstClassPriceIn, businessClassPriceIn, mainCabinPriceIn);
        }
    }
} 