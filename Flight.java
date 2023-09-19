/*
 * Alondra N Gonzalez-Ayala
 * 
 * March 21, 2023
 * 
 * CS 3331 - Advanced Object-Oriented Programming
 * 
 * Dr. Daniel Mejia
 * 
 * Programming Assignment 3
 * 
 * Flight.java
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract Class that contains attributes with setters and getters, a constructor for the attributes, print 
 * methods to print all flights, specific flights, amount of seats sold for specific flight, methods to set amount 
 * of seats available and flight status, tax, fees and discounts, this class also receives attributes from the Airport Class
 */
public abstract class Flight extends Airport {
    // Attributes
    private String departureDate, departureTime, arrivalDate, arrivalTime, flightType, flightStatus;
    private int flightID, flightNumber, duration, distance, timeZoneDifference, firstClassPrice, businessClassPrice, mainCabinPrice, firstClassSeats,
                businessClassSeats, mainCabinSeats, totalSeats, firstClassSeatsAvailable, businessClassSeatsAvailable, mainCabinSeatsAvailable, 
                totalSeatsAvailable, firstClassSeatsSold, businessClassSeatsSold, mainCabinSeatsSold, totalSeatsSold, surcharge, routeCost, minerPoints;
    private double firstClassRevenue, businessClassRevenue, mainCabinRevenue, totalRevenue, minerAirlinesFee, minerAirlinesFeeCharged, securityFee, 
                   securityFeeCharged, tax, taxCharged, minerAirlinesDiscount, discounted, employeeDiscountFirst, employeeDiscountBusinessMain;
    private boolean foodServed;
    private ArrayList<Ticket> ticketsPurchased = new ArrayList<>();

    /**
     * Default Constructor
     */
    public Flight() {

    }

    /**
     * Constructor that receives attributes
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
     */
    public Flight(int flightIDIn, int flightNumberIn, String originAirportIn, String originCodeIn, String originAirportCityIn, String originAirportStateIn,
                  String originAirportCountryIn, String destinationAirportIn, String destinationCodeIn, String destinationAirportCityIn, String destinationAirportStateIn, 
                  String destinationAirportCountryIn, double originAirportFeeIn, double destinationAirportFeeIn, boolean originAirportLoungeIn, boolean destinationAirportLoungeIn, 
                  String departureDateIn, String departureTimeIn, int durationIn, int distanceIn, int timeZoneDifferenceIn, String arrivalDateIn, String arrivalTimeIn, String flightTypeIn, 
                  int surchargeIn, boolean foodServedIn, int routeCostIn, int minerPointsIn, int totalSeatsIn, int firstClassSeatsIn, int businessClassSeatsIn, int mainCabinSeatsIn, 
                  int firstClassPriceIn, int businessClassPriceIn, int mainCabinPriceIn) {
        super(originAirportIn, originCodeIn, originAirportCityIn, originAirportStateIn, originAirportCountryIn, 
              destinationAirportIn, destinationCodeIn, destinationAirportCityIn, destinationAirportStateIn, 
              destinationAirportCountryIn, originAirportFeeIn, destinationAirportFeeIn, originAirportLoungeIn, destinationAirportLoungeIn);
        this.flightID = flightIDIn;
        this.flightNumber = flightNumberIn;
        this.departureDate = departureDateIn;
        this.departureTime = departureTimeIn;
        this.duration = durationIn;
        this.distance = distanceIn;
        this.timeZoneDifference = timeZoneDifferenceIn;
        this.arrivalDate = arrivalDateIn;
        this.arrivalTime = arrivalTimeIn;
        this.flightType = flightTypeIn;
        this.surcharge = surchargeIn;
        this.foodServed = foodServedIn;
        this.routeCost = routeCostIn;
        this.minerPoints = minerPointsIn;
        this.totalSeats = totalSeatsIn;
        this.firstClassSeats = firstClassSeatsIn;
        this.businessClassSeats = businessClassSeatsIn;
        this.mainCabinSeats = mainCabinSeatsIn;
        this.firstClassPrice = firstClassPriceIn;
        this.businessClassPrice = businessClassPriceIn;
        this.mainCabinPrice = mainCabinPriceIn;
    }

    /**
     * Getter for Departure Date
     * @return String that contains Departure Date
     */
    public String getDepartureDate() {
        return this.departureDate;
    }

    /**
     * Setter for Departure Date
     * @param departureDateIn Departure Date
     */
    public void setDepartureDate(String departureDateIn) {
        this.departureDate = departureDateIn;
    }

    /**
     * Getter for Departure Time
     * @return String that contains Departure Time
     */
    public String getDepartureTime() {
        return this.departureTime;
    }
    
    /**
     * Setter for Departure Time
     * @param departureTimeIn Departure Time
     */
    public void setDepartureTime(String departureTimeIn) {
        this.departureTime = departureTimeIn;
    }

    /**
     * Getter for Arrival Date
     * @return String that contains Arrival Date
     */
    public String getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * Setter for Arrival Date
     * @param arrivalDateIn Arrival Date
     */
    public void setArrivalDate(String arrivalDateIn) {
        this.arrivalDate = arrivalDateIn;
    }

    /**
     * Getter for Arrival Time
     * @return String that contains Arrival Time
     */
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Setter for Arrival Time
     * @param arrivalTimeIn Arrival Time
     */
    public void setArrivalTime(String arrivalTimeIn) {
        this.arrivalTime = arrivalTimeIn;
    }

    /**
     * Getter for Flight Type
     * @return String that contains Flight Type
     */
    public String getFlightType() {
        return this.flightType;
    }

    /**
     * Setter for Flight Type
     * @param flightTypeIn Flight Type
     */
    public void setFlightType(String flightTypeIn) {
        this.flightType = flightTypeIn;
    }

    /**
     * Getter for Flight Status
     * @return String that contains Flight Status
     */
    public String getFlightStatus() {
        return this.flightStatus;
    }

    /**
     * Setter for Flight Status
     * @param flightStatusIn Flight Status
     */
    public void setFlightStatus(String flightStatusIn) {
        this.flightStatus = flightStatusIn;
    }

    /**
     * Getter for Flight ID
     * @return Integer that contains Flight ID
     */
    public int getFlightID() {
        return this.flightID;
    }

    /**
     * Setter for Flight ID
     * @param flightIDIn Flight ID
     */
    public void setFlightID(int flightIDIn) {
        this.flightID = flightIDIn;
    }

    /**
     * Getter for Flight Number
     * @return Integer that contains Flight Number
     */
    public int getFlightNumber() {
        return this.flightNumber;
    }

    /**
     * Setter for Flight Number
     * @param flightNumberIn Flight Number
     */
    public void setFlightNumber(int flightNumberIn) {
        this.flightNumber = flightNumberIn;
    }

    /**
     * Getter for Duration
     * @return Integer that contains Duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Setter for Duration
     * @param durationIn Duration
     */
    public void setDuration(int durationIn) {
        this.duration = durationIn;
    }

    /**
     * Getter for Distance
     * @return Integer for Distance
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * Setter for Distance
     * @param distanceIn Distance
     */
    public void setDistance(int distanceIn) {
        this.distance = distanceIn;
    }

    /**
     * Getter for Time Zone Difference
     * @return Integer that contains Time Zone Difference
     */
    public int getTimeZoneDifference() {
        return this.timeZoneDifference;
    }

    /**
     * Setter for Time Zone Difference
     * @param timeZoneDifferenceIn Time Zone Difference
     */
    public void setTimeZoneDifference(int timeZoneDifferenceIn) {
        this.timeZoneDifference = timeZoneDifferenceIn;
    }

    /**
     * Getter for First Class Price
     * @return Integer that contains First Class Price
     */
    public int getFirstClassPrice() {
        return this.firstClassPrice;
    }

    /**
     * Setter for First Class Price
     * @param firstClassPriceIn First Class Price
     */
    public void setFirstClassPrice(int firstClassPriceIn) {
        this.firstClassPrice = firstClassPriceIn;
    }

    /**
     * Getter for Business Class Price
     * @return Integer that contains Business Class Price
     */
    public int getBusinessClassPrice() {
        return this.businessClassPrice;
    }

    /**
     * Setter for Business Class Price
     * @param businessClassPriceIn Business Class Price
     */
    public void setBusinessClassPrice(int businessClassPriceIn) {
        this.businessClassPrice = businessClassPriceIn;
    }

    /**
     * Getter for Main Cabin Price
     * @return Integer that contains Main Cabin Price
     */
    public int getMainCabinPrice() {
        return this.mainCabinPrice;
    }

    /**
     * Setter for Main Cabin Price
     * @param mainCabinPriceIn Main Cabin Price
     */
    public void setMainCabinPrice(int mainCabinPriceIn) {
        this.mainCabinPrice = mainCabinPriceIn;
    }

    /**
     * Getter for First Class Seats
     * @return Integer that contains First Class Seats
     */
    public int getFirstClassSeats() {
        return this.firstClassSeats;
    }

    /**
     * Setter for First Class Seats
     * @param firstClassSeatsIn First Class Seats
     */
    public void setFirstClassSeats(int firstClassSeatsIn) {
        this.firstClassSeats = firstClassSeatsIn;
    }

    /**
     * Getter for Business Class Seats
     * @return Integer that contains Business Class Seats
     */
    public int getBusinessClassSeats() {
        return this.businessClassSeats;
    }

    /**
     * Setter for Business Class Seats
     * @param businessClassSeatsIn Business Class Seats
     */
    public void setBusinessClassSeats(int businessClassSeatsIn) {
        this.businessClassSeats = businessClassSeatsIn;
    }

    /**
     * Getter for Main Cabin Seats
     * @return Integer that contains Main Cabin Seats
     */
    public int getMainCabinSeats() {
        return this.mainCabinSeats;
    }

    /**
     * Setter for Main Cabin Seats
     * @param mainCabinSeatsIn Main Cabin Seats
     */
    public void setMainCabinSeats(int mainCabinSeatsIn) {
        this.mainCabinSeats = mainCabinSeatsIn;
    }

    /**
     * Getter for Total Seats
     * @return Integer that contains Total Seats
     */
    public int getTotalSeats() {
        return this.totalSeats;
    }

    /**
     * Setter for Total Seats
     * @param totalSeatsIn Total Seats
     */
    public void setTotalSeats(int totalSeatsIn) {
        this.totalSeats = totalSeatsIn;
    }

    /**
     * Getter for First Class Seats Available
     * @return Integer that contains First Class Seats Available
     */
    public int getFirstClassSeatsAvailable() {
        return this.firstClassSeatsAvailable;
    }

    /**
     * Setter for First Class Seats Available
     * @param firstClassSeatsAvailableIn First Class Seats Available
     */
    public void setFirstClassSeatsAvailable(int firstClassSeatsAvailableIn) {
        this.firstClassSeatsAvailable = firstClassSeatsAvailableIn;
    }

    /**
     * Getter for Business Class Seats Available
     * @return Integer that contains Business Class Seats Available
     */
    public int getBusinessClassSeatsAvailable() {
        return this.businessClassSeatsAvailable;
    }

    /**
     * Setter for Business Class Seats Available
     * @param businessClassSeatsAvailableIn Business Class Seats Available
     */
    public void setBusinessClassSeatsAvailable(int businessClassSeatsAvailableIn) {
        this.businessClassSeatsAvailable = businessClassSeatsAvailableIn;
    }

    /**
     * Getter for Main Cabin Seats Available
     * @return Integer that contains Main Cabin Seats Available
     */
    public int getMainCabinSeatsAvailable() {
        return this.mainCabinSeatsAvailable;
    }

    /**
     * Setter for Main Cabin Seats Available
     * @param mainCabinSeatsAvailableIn Main Cabin Seats Available
     */
    public void setMainCabinSeatsAvailable(int mainCabinSeatsAvailableIn) {
        this.mainCabinSeatsAvailable = mainCabinSeatsAvailableIn;
    }

    /**
     * Getter for Total Seats Available
     * @return Integer that contains Total Seats Available
     */
    public int getTotalSeatsAvailable() {
        return this.totalSeatsAvailable;
    }

    /**
     * Setter for Total Seats Available
     * @param totalSeatsAvailableIn Total Seats Available
     */
    public void setTotalSeatsAvailable(int totalSeatsAvailableIn) {
        this.totalSeatsAvailable = totalSeatsAvailableIn;
    }

    /**
     * Getter for First Class Seats Sold
     * @return Integer that contains First Class Seats Sold
     */
    public int getFirstClassSeatsSold() {
        return this.firstClassSeatsSold;
    }

    /**
     * Setter for First Class Seats Sold
     * @param firstClassSeatsSoldIn First Class Seats Sold
     */
    public void setFirstClassSeatsSold(int firstClassSeatsSoldIn) {
        this.firstClassSeatsSold = firstClassSeatsSoldIn;
    }

    /**
     * Getter for Business Class Seats Sold
     * @return Integer that contains Business Class Seats Sold
     */
    public int getBusinessClassSeatsSold() {
        return this.businessClassSeatsSold;
    }

    /**
     * Setter for Business Class Seats Sold
     * @param businessClassSeatsSoldIn Business Class Seats Sold
     */
    public void setBusinessClassSeatsSold(int businessClassSeatsSoldIn) {
        this.businessClassSeatsSold = businessClassSeatsSoldIn;
    }

    /**
     * Getter for Main Cabin Seats Sold
     * @return Integer that contains Main Cabin Seats Sold
     */
    public int getMainCabinSeatsSold() {
        return this.mainCabinSeatsSold;
    }

    /**
     * Setter for Main Cabin Seats Sold
     * @param mainCabinSeatsSoldIn Main Cabin Seats Sold
     */
    public void setMainCabinSeatsSold(int mainCabinSeatsSoldIn) {
        this.mainCabinSeatsSold = mainCabinSeatsSoldIn;
    }

    /**
     * Getter for Total Seats Sold
     * @return Integer that contains Total Seats Sold
     */
    public int getTotalSeatsSold() {
        return this.totalSeatsSold;
    }

    /**
     * Setter for Total Seats Sold
     * @param totalSeatsSoldIn Total Seats Sold
     */
    public void setTotalSeatsSold(int totalSeatsSoldIn) {
        this.totalSeatsSold = totalSeatsSoldIn;
    }

    /**
     * Getter for Surcharge
     * @return Integer that contains Surcharge
     */
    public int getSurcharge() {
        return this.surcharge;
    }

    /**
     * Setter for Surcharge
     * @param surchargeIn Surcharge
     */
    public void setSurcharge(int surchargeIn) {
        this.surcharge = surchargeIn;
    }

    /**
     * Getter for Route Cost
     * @return Integer that contains Route Cost
     */
    public int getRouteCost() {
        return this.routeCost;
    }

    /**
     * Setter for Route Cost
     * @param routeCostIn Route Cost
     */
    public void setRouteCost(int routeCostIn) {
        this.routeCost = routeCostIn;
    }

    /**
     * Getter for Miner Points
     * @return Integer that contains Miner Points
     */
    public int getMinerPoints() {
        return this.minerPoints;
    }

    /**
     * Setter for Miner Points
     * @param minerPointsIn Miner Points
     */
    public void setMinerPoints(int minerPointsIn) {
        this.minerPoints = minerPointsIn;
    }

    /**
     * Getter for First Class Revenue
     * @return Double that contains First Class Revenue
     */
    public double getFirstClassRevenue() {
        return this.firstClassRevenue;
    }

    /**
     * Setter for First Class Revenue
     * @param firstClassRevenueIn First Class Revenue
     */
    public void setFirstClassRevenue(double firstClassRevenueIn) {
        this.firstClassRevenue = Math.round(firstClassRevenueIn * 100.0) / 100.0;
    }

    /**
     * Getter for Business Class Revenue
     * @return Double that contains Business Class Revenue
     */
    public double getBusinessClassRevenue() {
        return this.businessClassRevenue;
    }

    /**
     * Setter for Business Class Revenue
     * @param businessClassRevenueIn Business Class Revenue
     */
    public void setBusinessClassRevenue(double businessClassRevenueIn) {
        this.businessClassRevenue = Math.round(businessClassRevenueIn * 100.0) / 100.0;
    }

    /**
     * Getter for Main Cabin Revenue
     * @return Double that contains Main Cabin Revenue
     */
    public double getMainCabinRevenue() {
        return this.mainCabinRevenue;
    }

    /**
     * Setter for Main Cabin Revenue
     * @param mainCabinRevenueIn Main Cabin Revenue
     */
    public void setMainCabinRevenue(double mainCabinRevenueIn) {
        this.mainCabinRevenue = Math.round(mainCabinRevenueIn * 100.0) / 100.0;
    }

    /**
     * Getter for Total Revenue
     * @return Double that contains Total Revenue
     */
    public double getTotalRevenue() {
        return this.totalRevenue;
    }

    /**
     * Setter for Total Revenue
     * @param totalRevenueIn Total Revenue
     */
    public void setTotalRevenue(double totalRevenueIn) {
        this.totalRevenue = Math.round(totalRevenueIn * 100.0) / 100.0;
    }
    
    /**
     * Getter for MinerAirlines Fee
     * @return Double that contains MinerAirlines Fee
     */
    public double getMinerAirlinesFee() {
        return this.minerAirlinesFee;
    }

    /**
     * Setter for MinerAirlines Fee
     * @param minerAirlinesFeeIn MinerAirlines Fee
     */
    public void setMinerAirlinesFee(double minerAirlinesFeeIn) {
        this.minerAirlinesFee = minerAirlinesFeeIn;
    }

    /**
     * Getter for MinerAirlines Fee Charged
     * @return Double that contains MinerAirlines Fee Charged
     */
    public double getMinerAirlinesFeeCharged() {
        return this.minerAirlinesFeeCharged;
    }

    /**
     * Setter for MinerAirlines Fee Charged
     * @param minerAirlinesFeeChargedIn MinerAirlines Fee Charged
     */
    public void setMinerAirlinesFeeCharged(double minerAirlinesFeeChargedIn) {
        this.minerAirlinesFeeCharged = Math.round(minerAirlinesFeeChargedIn * 100.0) / 100.0;
    }

    /**
     * Getter for Security Fee
     * @return Double that contains Security Fee
     */
    public double getSecurityFee() {
        return this.securityFee;
    }

    /**
     * Setter for Security Fee
     * @param securityFeeIn Security Fee
     */
    public void setSecurityFee(double securityFeeIn) {
        this.securityFee = securityFeeIn;
    }

    /**
     * Getter for Security Fee Charged
     * @return Double that contains Security Fee Charged
     */
    public double getSecurityFeeCharged() {
        return this.securityFeeCharged;
    }

    /**
     * Setter for Security Fee Charged
     * @param securityFeeChargedIn Security Fee Charged
     */
    public void setSecurityFeeCharged(double securityFeeChargedIn) {
        this.securityFeeCharged = Math.round(securityFeeChargedIn * 100.0) / 100.0;
    }

    /**
     * Getter for Tax
     * @return Double that contains Tax
     */
    public double getTax() {
        return this.tax;
    }

    /**
     * Setter for Tax
     * @param taxIn Tax
     */
    public void setTax(double taxIn) {
        this.tax = taxIn;
    }

    /**
     * Getter for Tax Charged
     * @return Double that contains Tax Charged
     */
    public double getTaxCharged() {
        return this.taxCharged;
    }

    /**
     * Setter for Tax Charged
     * @param taxChargedIn Tax Charged
     */
    public void setTaxCharged(double taxChargedIn) {
        this.taxCharged = Math.round(taxChargedIn * 100.0) / 100.0;
    }
    
    /**
     * Getter for MinerAirlines Discount
     * @return Double that contains MinerAirlines Discount
     */
    public double getMinerAirlinesDiscount() {
        return minerAirlinesDiscount;
    }

    /**
     * Setter for MinerAirlines Discount
     * @param minerAirlinesDiscount MinerAirlines Discount
     */
    public void setMinerAirlinesDiscount(double minerAirlinesDiscount) {
        this.minerAirlinesDiscount = minerAirlinesDiscount;
    }

    /**
     * Getter for Discounted
     * @return Double that contains Discounted
     */
    public double getDiscounted() {
        return this.discounted;
    }

    /**
     * Setter for Discounted
     * @param discountedIn Discounted
     */
    public void setDiscounted(double discountedIn) {
        this.discounted = Math.round(discountedIn * 100.0) / 100.0;
    }

    /**
     * Getter for Employee Discount First
     * @return Double that contains Employee Discount First
     */
    public double getEmployeeDiscountFirst() {
        return this.employeeDiscountFirst;
    }

    /**
     * Setter for Employee Discount First
     * @param employeeDiscountFirstIn Employee Discount First
     */
    public void setEmployeeDiscountFirst(double employeeDiscountFirstIn) {
        this.employeeDiscountFirst = employeeDiscountFirstIn;
    }

    /**
     * Getter for Employee Discount Business Main
     * @return Double that contains Employee Discount Business Main
     */
    public double getEmployeeDiscountBusinessMain() {
        return this.employeeDiscountBusinessMain;
    }

    /**
     * Setter for Employee Discount Business Main
     * @param employeeDiscountBusinessMainIn Employee Discount Business Main
     */
    public void setEmployeeDiscountBusinessMain(double employeeDiscountBusinessMainIn) {
        this.employeeDiscountBusinessMain = employeeDiscountBusinessMainIn;
    }

    /**
     * Getter for Food Served
     * @return Boolean that contains Food Served
     */
    public boolean getFoodServed() {
        return this.foodServed;
    }

    /**
     * Setter for Food Served
     * @param foodServedIn Food Served
     */
    public void setFoodServed(boolean foodServedIn) {
        this.foodServed = foodServedIn;
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
     * Method to print all of the flight info
     */
    public void printFlight() {
        System.out.println("Flight Number: " + this.flightNumber
                         + "\nStatus: " + getFlightStatus()
                         + "\nOrigin Airport & Code: " + getOriginAirport() + " - " + getOriginCode()
                         + "\nOrigin Airport Location: " + getOriginAirportCity() + ", " + getOriginAirportState() + ", " + getOriginAirportCountry()
                         + "\nOrigin Airport Fee: $" + getOriginAirportFee()
                         + "\nOrigin Airport Lounge: " + getOriginAirportLounge()
                         + "\n\nDestination Airport & Code: " + getDestinationAirport() + " - " + getDestinationCode()
                         + "\nDestination Airport Location: " + getDestinationAirportCity() + ", " + getDestinationAirportState() + ", " + getDestinationAirportCountry()
                         + "\nDestination Airport Fee: $" + getDestinationAirportFee()
                         + "\nDestination Airport Lounge: " + getDestinationAirportLounge()
                         + "\n\nFlight Type: " + this.flightType
                         + "\nDeparture Date & Time: " + this.departureDate + " " + this.departureTime
                         + "\nArrival Date & Time: " + this.arrivalDate + " " + this.arrivalTime
                         + "\nFight Duration: " + this.duration + " minutes"
                         + "\nFlight Distance: " + this.distance + " miles"
                         + "\nTime Zone Difference: " + this.timeZoneDifference + " hour(s)"
                         + "\nSurcharge: $" + this.surcharge
                         + "\nFood Served: " + this.foodServed
                         + "\nMiner Points: " + this.minerPoints + " points"
                         + "\nFirst Class Price: $" + this.firstClassPrice
                         + "\nBusiness Class Price: $" + this.businessClassPrice
                         + "\nMain Cabin Price: $" + this.mainCabinPrice
                         + "\nNumber of Seats Available in First Class: " + this.firstClassSeats + " seats"
                         + "\nNumber of Seats Available in Business Class: " + this.businessClassSeats + " seats"
                         + "\nNumber of Seats Available in Main Cabin: " + this.mainCabinSeats + " seats"
                         + "\nTotal Seats Available: " + this.totalSeats + " seats");
    }

    /**
     * Method to print all flights
     * @param flightList Hash Map that contains all flight information
     */
    public static void printAllFlights(HashMap<Integer, Flight> flightList) {
        for (int i = 1; i < flightList.size() + 1; i++) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Flight ID: " + flightList.get(i).getFlightID()
                             + "\nStatus: " + flightList.get(i).getFlightStatus()
                             + "\nFlight Number: " + flightList.get(i).getFlightNumber()
                             + "\nOrigin Airport & Code: " + flightList.get(i).getOriginAirport() + " - " + flightList.get(i).getOriginCode() 
                             + "\nDestination Airport & Code: " + flightList.get(i).getDestinationAirport() + " - " + flightList.get(i).getDestinationCode()
                             + "\nDeparture Date & Time: " + flightList.get(i).getDepartureDate() + " at " + flightList.get(i).getDepartureTime()
                             + "\nArrival Date & Time: " + flightList.get(i).getArrivalDate() + " at " + flightList.get(i).getArrivalTime());
        }
    }

    /**
     * Method to print seats sold in specific flights
     * @param flightList Hash Map that contains all flight information
     * @param ID Flight ID
     */
    public static void printSeatsSold(HashMap<Integer, Flight> flightList, int ID) {
        System.out.println("Flight ID " + ID
                        + "\nStatus: " + flightList.get(ID).getFlightStatus()
                        + "\nFlight Number: " + flightList.get(ID).getFlightNumber()
                        + "\n" + flightList.get(ID).getOriginCode() + " - " + flightList.get(ID).getDestinationCode()
                        + "\n" + flightList.get(ID).getDepartureDate() + " at " + flightList.get(ID).getDepartureTime() + " - " + flightList.get(ID).getArrivalDate() + " at " + flightList.get(ID).getArrivalTime()
                        + "\nFirst Class Seats Sold: " + flightList.get(ID).getFirstClassSeatsSold()
                        + "\nBusiness Class Seats Sold: " + flightList.get(ID).getBusinessClassSeatsSold()
                        + "\nMain Cabin Seats Sold: " + flightList.get(ID).getMainCabinSeatsSold()
                        + "\nTotal Seats Sold: " + flightList.get(ID).getTotalSeatsSold());
    }

    /**
     * Method to set Seats Available to the original amount in the CSV file
     * @param flightList Hash Map that contains all flight information
     */
    public static void setSeatsAvailable(HashMap<Integer, Flight> flightList) {
        for (int i = 1; i < flightList.size() + 1; i++) {
            flightList.get(i).setTotalSeatsAvailable(flightList.get(i).getTotalSeats());
            flightList.get(i).setFirstClassSeatsAvailable(flightList.get(i).getFirstClassSeats());
            flightList.get(i).setBusinessClassSeatsAvailable(flightList.get(i).getBusinessClassSeats());
            flightList.get(i).setMainCabinSeatsAvailable(flightList.get(i).getMainCabinSeats());
        }
    }

    /**
     * Method to set Status of flights to Scheduled
     * @param flightList Hash Map that contains all flight information
     */
    public static void setStatusFlights(HashMap<Integer, Flight> flightList) {
        for (int i = 1; i < flightList.size() + 1; i++) {
            flightList.get(i).setFlightStatus("Scheduled");
        }
    }

    /**
     * Method to set Fees, Tax and Discount for all flights
     * @param flightList Hash Map that contains all flight information
     */
    public static void setFlightFeesTaxDiscount(HashMap<Integer, Flight> flightList) {
        for (int i = 1; i < flightList.size() + 1; i++) {
            flightList.get(i).setMinerAirlinesFee(9.15);
            flightList.get(i).setSecurityFee(5.60);
            flightList.get(i).setTax(0.0825);
            flightList.get(i).setMinerAirlinesDiscount(0.05);
            flightList.get(i).setEmployeeDiscountFirst(0.50);
            flightList.get(i).setEmployeeDiscountBusinessMain(0.75);
        }
    }

    /**
     * Method to print Airport Info
     * @param userViewAirport Airport Code that user input
     * @param flightList Hash Map that contains all flight information
     */
    public static void printAirportInfo(String userViewAirport, HashMap<Integer,Flight> flightList){
        while (!userViewAirport.equalsIgnoreCase("EXIT")) {

            String airportCode = userViewAirport;

            for (int i = 1; i <= flightList.size(); i++) {
                if(flightList.get(i).getOriginCode().equals(airportCode)) {
                    double feesCharged = getAirportFees(flightList, airportCode);
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Airport Name: " + flightList.get(i).getOriginAirport()
                                    + "\nAirport Code: " + flightList.get(i).getOriginCode()
                                    + "\nAirport Location: " + flightList.get(i).getOriginAirportCity() + ", " + flightList.get(i).getOriginAirportState() + ", " + flightList.get(i).getOriginAirportCountry()
                                    + "\nFees: $" + flightList.get(i).getOriginAirportFee()
                                    + "\nLounge: " + flightList.get(i).getOriginAirportLounge()
                                    + "\nFees Charged: $" + feesCharged);
                    break;
                } else if (flightList.get(i).getDestinationCode().equals(airportCode)) {
                    double feesCharged = getAirportFees(flightList, airportCode);
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("Airport Name: " + flightList.get(i).getDestinationAirport()
                                    + "\nAirport Code: " + flightList.get(i).getDestinationCode()
                                    + "\nAirport Location: " + flightList.get(i).getDestinationAirportCity() + ", " + flightList.get(i).getDestinationAirportState() + ", " + flightList.get(i).getDestinationAirportCountry()
                                    + "\nFees: $" + flightList.get(i).getDestinationAirportFee()
                                    + "\nLounge: " + flightList.get(i).getDestinationAirportLounge()
                                    + "\nFees Charged: $" + feesCharged);
                    break;
                }
            }
            break;
        }
    }
 
    /**
     * Method to get the Total Fees Charged for a soecific Airport
     * @param flightList Hash Map that contains all flight information
     * @param airportCode Airport Code that user input
     * @return Double that contains Total Fees Charged
     */
    public static double getAirportFees(HashMap<Integer,Flight> flightList, String airportCode) {
        double allFees = 0.0;
        for (int i = 1; i <= flightList.size(); i++) {
            if(flightList.get(i).getOriginCode().equals(airportCode)) {
                allFees += flightList.get(i).getOriginAirportFeeCharged();
            } else if(flightList.get(i).getDestinationCode().equals(airportCode)) {
                allFees += flightList.get(i).getDestinationAirportFeeCharged();
            }
        }
        return allFees;
    }

    /**
     * Method to find specific Flights
     * @param flightList Hash Map that contains all flight information
     * @param flightsFound Empty ArrayList
     * @param originCode Origin Code that user input
     * @param destinationCode Destination Code that user input
     * @param departureDate Departure Date that user input
     * @return ArrayList of type Flight that contains flights found
     */
    public static ArrayList<Flight> findFlight(HashMap<Integer, Flight> flightList, ArrayList<Flight> flightsFound, String originCode, String destinationCode, String departureDate) {

        // Create new factory
        FlightCreator factory = new FlightCreator();

        // Look through hashmap to find flights
        for (int i = 1; i < flightList.size() + 1; i++) {
            // Create a new flight depending on the flight type
            Flight foundFlight = factory.createFlight(flightList.get(i).getFlightType());
            foundFlight = flightList.get(i);

            // If the Origin Code, Destination Code & Deoarture Date, return the flight
            if(foundFlight.getOriginCode().equals(originCode) && foundFlight.getDestinationCode().equals(destinationCode) && foundFlight.getDepartureDate().equals(departureDate)){
                flightsFound.add(foundFlight);
            }
        }
        // Return flights found
        return flightsFound;
    }
}