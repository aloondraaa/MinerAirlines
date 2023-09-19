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
 * Ticket.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

/**
 * Ticket Class that contains attributes with setters and getters, a constructor for the attributes and methods to
 * print customers tickets and flights ticket
 */
public class Ticket {
    // Attributes
    private String ticketType, originAirport, originCode, destinationAirport, destinationCode, departureDate, 
                   departureTime, arrivalDate, arrivalTime, ticketStatus, firstName, lastName;
    private int amountOfTickets, confirmationNumber, flightID;
    private double subtotal, tax, totalPrice, discount;

    /**
     * Default Constructor
     */
    public Ticket() {
        
    }

    /**
     * Constructor that receives attributes for customer ticket
     * @param ticketTypeIn Ticket Type
     * @param originAirportIn Flight Origin Airport
     * @param originCodeIn Flight Origin Code
     * @param destinationAirportIn Flight Destination Airport
     * @param destinationCodeIn Flight Destination Code
     * @param departureDateIn Flight Departure Date
     * @param departureTimeIn Flight Departure Time
     * @param arrivalDateIn Flight Arrival Date
     * @param arrivalTimeIn Flight Arrival Time
     * @param amountOfTicketsIn Amount of Tickets Purchased
     * @param confirmationNumberIn Confirmation Number
     * @param flightIDIn Flight ID
     * @param subtotalIn Subtotal
     * @param totalPriceIn Total
     * @param ticketStatusIn Ticket Status
     * @param discountIn Discount
     * @param taxIn Tax
     */
    public Ticket(String ticketTypeIn, String originAirportIn, String originCodeIn, String destinationAirportIn, String destinationCodeIn, String departureDateIn, String departureTimeIn,
                  String arrivalDateIn, String arrivalTimeIn, int amountOfTicketsIn, int confirmationNumberIn, int flightIDIn, double subtotalIn, 
                  double totalPriceIn, String ticketStatusIn, double discountIn, double taxIn) {
        this.ticketType = ticketTypeIn;
        this.originAirport = originAirportIn;
        this.originCode = originCodeIn;
        this.destinationAirport = destinationAirportIn;
        this.destinationCode = destinationCodeIn;
        this.departureDate = departureDateIn;
        this.departureTime = departureTimeIn;
        this.arrivalDate = arrivalDateIn;
        this.arrivalTime = arrivalTimeIn;
        this.amountOfTickets = amountOfTicketsIn;
        this.confirmationNumber = confirmationNumberIn;
        this.flightID = flightIDIn;
        this.subtotal = subtotalIn;
        this.totalPrice = totalPriceIn;
        this.ticketStatus = ticketStatusIn;
        this.discount = discountIn;
        this.tax = taxIn;
    }

    /**
     * Constructor that receives attributes for flight ticket
     * @param firstNameIn First Name of Customer/Employee
     * @param lastNameIn Last Name of Customer/Employee
     * @param ticketTypeIn Ticket Type
     * @param amountOfTicketsIn Amount of Tickets Purchased
     * @param subtotalIn Subtotal
     * @param totalPriceIn Total
     * @param confirmationNumberIn Confirmation Number
     * @param discountIn Discount
     * @param taxIn Tax
     */
    public Ticket(String firstNameIn, String lastNameIn, String ticketTypeIn, int amountOfTicketsIn, double subtotalIn, double totalPriceIn, 
                  int confirmationNumberIn, double discountIn, double taxIn) {
        this.firstName = firstNameIn;
        this.lastName = lastNameIn;
        this.ticketType = ticketTypeIn;
        this.amountOfTickets = amountOfTicketsIn;
        this.subtotal = subtotalIn;
        this.totalPrice = totalPriceIn;
        this.confirmationNumber = confirmationNumberIn;
        this.discount = discountIn;
        this.tax = taxIn;
    }

    /**
     * Getter for Ticket Type
     * @return String that contains Ticket Type
     */
    public String getTicketType() {
        return this.ticketType;
    }

    /**
     * Setter for Ticket Type
     * @param ticketTypeIn Ticket Type
     */
    public void setTicketType(String ticketTypeIn) {
        this.ticketType = ticketTypeIn;
    }
    
    /**
     * Getter for Flight Origin Airport
     * @return String that contains Flight Origin Airport
     */
    public String getOriginAirport() {
        return this.originAirport;
    }

    /**
     * Setter for Flight Origin Airport
     * @param originAirportIn Flight Origin Airport
     */
    public void setOriginAirport(String originAirportIn) {
        this.originAirport = originAirportIn;
    }

    /**
     * Getter for Flight Origin Code
     * @return String that contains Flight Origin Code
     */
    public String getOriginCode() {
        return this.originCode;
    }

    /**
     * Setter for Flight Origin Code
     * @param originCodeIn Flight Origin Code
     */
    public void setOriginCode(String originCodeIn) {
        this.originCode = originCodeIn;
    }

    /**
     * Getter for Flight Destination Airport
     * @return String that contains Flight Destination Airport
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Setter for Flight Destination Airport
     * @param destinationAirport Flight Destination Airport
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * Getter for Flight Destination Code
     * @return String that contains Flight Destination Code
     */
    public String getDestinationCode() {
        return this.destinationCode;
    }

    /**
     * Setter for Flight Destination Code
     * @param destinationCodeIn Flight Destination Code
     */
    public void setDestinationCode(String destinationCodeIn) {
        this.destinationCode = destinationCodeIn;
    }

    /**
     * Getter for Flight Departure Date
     * @return String that contains Flight Departure Date
     */
    public String getDepartureDate() {
        return this.departureDate;
    }

    /**
     * Setter for DFlight eparture Date
     * @param departureDateIn Departure Date
     */
    public void setDepartureDate(String departureDateIn) {
        this.departureDate = departureDateIn;
    }

    /**
     * Getter for Flight Departure Time
     * @return String that contains Flight Departure Time
     */
    public String getDepartureTime() {
        return this.departureTime;
    }

    /**
     * Setter for Flight Departure Time
     * @param departureTimeIn Flight Departure Time
     */
    public void setDepartureTime(String departureTimeIn) {
        this.departureTime = departureTimeIn;
    }

    /**
     * Getter for Flight Arrival Date
     * @return String that contains Flight Arrival Date
     */
    public String getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * Setter for Flight Arrival Date
     * @param arrivalDateIn Flight Arrival Date
     */
    public void setArrivalDate(String arrivalDateIn) {
        this.arrivalDate = arrivalDateIn;
    }

    /**
     * Getter for Flight Arrival Time
     * @return String that contains Flight Arrival Time
     */
    public String getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Setter for Flight Arrival Time
     * @param arrivalTimeIn Flight Arrival Time
     */
    public void setArrivalTime(String arrivalTimeIn) {
        this.arrivalTime = arrivalTimeIn;
    }

    /**
     * Getter for Ticket Status
     * @return String that contains Ticket Status
     */
    public String getTicketStatus() {
        return this.ticketStatus;
    }

    /**
     * Setter for Ticket Status
     * @param ticketStatusIn Ticket Status
     */
    public void setTicketStatus(String ticketStatusIn) {
        this.ticketStatus = ticketStatusIn;
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
     * Getter for Amount of Tickets
     * @return Integer that contains Amount of Tickets
     */
    public int getAmountOfTickets() {
        return this.amountOfTickets;
    }

    /**
     * Setter for Amount of Tickets
     * @param amountOfTicketsIn Amount of Tickets
     */
    public void setAmountOfTickets(int amountOfTicketsIn) {
        this.amountOfTickets = amountOfTicketsIn;
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
     * Getter for Confirmation Number
     * @return Integer that contains Confirmation Number
     */
    public int getConfirmationNumber() {
        return this.confirmationNumber;
    }

    /**
     * Setter for Confirmation Number
     * @param confirmationNumberIn Confirmation Number
     */
    public void setConfirmationNumber(int confirmationNumberIn) {
        this.confirmationNumber = confirmationNumberIn;
    }

    /**
     * Getter for Subtotal
     * @return Double that contains Subtotal
     */
    public double getSubtotal() {
        return this.subtotal;
    }

    /**
     * Setter for Subtotal
     * @param subtotalIn Subtotal
     */
    public void setSubtotal(double subtotalIn) {
        this.subtotal = subtotalIn;
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
     * Getter for Total Price
     * @return Double that contains Total Price
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Setter for Total Price
     * @param totalPriceIn Total Price
     */
    public void setTotalPrice(double totalPriceIn) {
        this.totalPrice = totalPriceIn;
    }

    /**
     * Getter for Discount
     * @return Double that contains Discount
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Setter for Discount
     * @param discountIn Discount
     */
    public void setDiscount(double discountIn) {
        this.discount = discountIn;
    }

    /**
     * Method to print customer ticket information
     */
    public void printCustomerTicketInfo() {
        System.out.println("Status: " + getTicketStatus()
                         + "\nConfirmation number: " + getConfirmationNumber()
                         + "\nFlight ID " + getFlightID()
                         + "\n" + getOriginCode() + " - " + getDestinationCode()
                         + "\nDeparture Date & Time: " + getDepartureDate() + " at " + getDepartureTime()
                         + "\nArrival Date & Time: " + getArrivalDate() + " at " + getArrivalTime()
                         + "\n" + getAmountOfTickets() + " " + getTicketType() + " ticket(s)"
                         + "\nTotal: $" + getTotalPrice());
    }

    /**
     * Method to print flight ticket information
     */
    public void printFlightTicketInfo() {
        System.out.println(getFirstName() + " " + getLastName()
                         + "\n" + getTicketType() + ": " + getAmountOfTickets() + " seat(s)"
                         + "\nTotal Price: $" + getTotalPrice());
                         
    }
} 