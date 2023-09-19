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
 * Writer.java 
 * 
 * I completed this work entirely on my own without any outside sources including peers, experts, or online sources.
 */

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that writes changes and actions in a text file
 */
public class Writer {
    // Singleton instance for writer
    private static Writer writer;

    /**
     * Private Default Constructor
     */
    private Writer() {

    }

    /**
     * Method to get the singleton instance
     * @return Writer Instance
     */
    public static Writer getInstance() {
        if (writer == null) {
            writer = new Writer();
        }
        return writer;
    }

     /**
     * Method to write user actions to a text file
     * @param fileName Name of the file
     * @param actions Actions made by user
     * @throws Exception Exception
     */
    public void writeLog(String fileName, String actions) throws Exception {
        // Create a File object
        FileWriter logger = new FileWriter(fileName, true);

        // Write the change to the file and add new line
        logger.write(actions + "\n");

        // Close
        logger.close();
   }

    /**
     * Method to write an updated flight csv file
     * @param fileName File Name
     * @param flights Hash Map that contains all flight information
     * @throws Exception Exception
     */
    public void writeFlightList(String fileName, HashMap<Integer, Flight> flights) throws Exception {
        // Create new File object
        FileWriter writeCSV = new FileWriter(fileName, true);
        
        // Write header
        writeCSV.write("ID,Flight Number,Origin Airport,Origin Code,Origin Airport City,Origin Airport State,Origin Airport Country,Origin Airport Fee,Origin Airport Lounge,Destination Airport,Destination Code,Destination Airport City,Destination Airport State,Destination Airport Country,Destination Airport Fee,Destination Airport Lounge,Departing Date,Departing Time,Duration,Distance,Time Zone Difference,Arrival Date,Arrival Time,Flight Type,Surcharge,Food Served,Route Cost,Miner Points,Total Seats,First Class Seats,Business Class Seats,Main Cabin Seats,First Class Price,Business Class Price,Main Cabin Price,First Class Seats Sold,Business Class Seats Sold,Main Cabin Seats Sold,Total First Class Revenue,Total Business Class Revenue,Total Main Cabin Revenue,Total Revenue,MinerAirlines Fee, MinerAirlines Fee Charged,Security Fee, Security Fee Charged,Tax,Tax Charged,MinerAirlines Discount,Discounted,Employee Discount First,Employee Discount Business & Main\n");

        // For every flight in the hash map, write the info in the updated csv file
        for (int i = 1; i < flights.size() + 1; i++) {
            writeCSV.write(flights.get(i).getFlightID() + "," + flights.get(i).getFlightNumber() + "," + flights.get(i).getOriginAirport() + "," + flights.get(i).getOriginCode() + "," + flights.get(i).getOriginAirportCity() + "," + flights.get(i).getOriginAirportState() + ","
                         + flights.get(i).getOriginAirportCountry() + "," + flights.get(i).getOriginAirportFee() + "," + flights.get(i).getOriginAirportLounge() + "," + flights.get(i).getDestinationAirport() + "," + flights.get(i).getDestinationCode() + ","
                         + flights.get(i).getDestinationAirportCity() + "," + flights.get(i).getDestinationAirportState() + "," + flights.get(i).getDestinationAirportCountry() + "," + flights.get(i).getDestinationAirportFee() + "," + flights.get(i).getDestinationAirportLounge() + ","
                         + flights.get(i).getDepartureDate() + "," + flights.get(i).getDepartureTime() + "," + flights.get(i).getDuration() + "," + flights.get(i).getDistance() + "," + flights.get(i).getTimeZoneDifference() + "," + flights.get(i).getArrivalDate() + ","
                         + flights.get(i).getArrivalTime() + "," + flights.get(i).getFlightType() + "," + flights.get(i).getSurcharge() + "," + flights.get(i).getFoodServed() + "," + flights.get(i).getRouteCost() + "," + flights.get(i).getMinerPoints() + ","
                         + flights.get(i).getTotalSeats() + "," + flights.get(i).getFirstClassSeats() + "," + flights.get(i).getBusinessClassSeats() + "," + flights.get(i).getMainCabinSeats() + "," + flights.get(i).getFirstClassPrice() + ","
                         + flights.get(i).getBusinessClassPrice() + "," + flights.get(i).getMainCabinPrice() + "," + flights.get(i).getFirstClassSeatsSold() + "," + flights.get(i).getBusinessClassSeatsSold() + "," + flights.get(i).getMainCabinSeatsSold() + ","
                         + flights.get(i).getFirstClassRevenue() + "," + flights.get(i).getBusinessClassRevenue() + "," + flights.get(i).getMainCabinRevenue() + "," + flights.get(i).getTotalRevenue() + "," + flights.get(i).getMinerAirlinesFee() + "," 
                         + flights.get(i).getMinerAirlinesFeeCharged() + "," + flights.get(i).getSecurityFee() + "," + flights.get(i).getSecurityFeeCharged() + "," + flights.get(i).getTax() + "," + flights.get(i).getTaxCharged() + "," + flights.get(i).getMinerAirlinesDiscount() + ","
                         + flights.get(i).getDiscounted() + "," + flights.get(i).getEmployeeDiscountFirst() + "," + flights.get(i).getEmployeeDiscountBusinessMain());
            // Write a new line
            writeCSV.write("\n");
        }
        // Close
        writeCSV.close();
    }

    /**
     * Method to write an updated customer csv file
     * @param fileName File Name
     * @param people Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */
    public void writeCustomerList(String fileName, HashMap<Integer, Person> people) throws Exception {
        // Create new File object
        FileWriter writeCSV = new FileWriter(fileName, true);

        // Write header
        writeCSV.write("ID,First Name,Last Name,DOB,Role,Money Available,Flights Purchased,MinerAir Membership,Username,Password,Savings\n");

        // For every person in the hash map, write the info in the updated csv file
        for (int i = 1; i < people.size(); i++) {
            writeCSV.write(people.get(i).getID() + "," + people.get(i).getFirstName() + "," + people.get(i).getLastName() + "," + people.get(i).getDob() + "," 
                         + people.get(i).getRole() + "," + people.get(i).getMoneyAvailable() + "," + people.get(i).getFlightsPurchased() + "," 
                         + people.get(i).getMinerAirMembership() + "," + people.get(i).getUsername() + "," + people.get(i).getPassword() + "," + people.get(i).getSavings());
            // Write a new line
            writeCSV.write("\n");
        }
        // Close
        writeCSV.close();
    }

    /**
     * Method to write Electronic Ticket Summary for customer requested by employee
     * @param fileName File Name
     * @param people Hash Map that contains all customer/employee information
     * @param firstName First Name of Customer
     * @param lastName Last Name of Customer
     * @param employee Employee requesting Summary
     * @throws Exception Exception
     */
    public void ElectronicTicketSummary(String fileName, HashMap<Integer,Person> people, String firstName, String lastName, Employee employee) throws Exception {
        // Create new File object
        FileWriter summaryWriter = new FileWriter(fileName, true);

        // Find person in hash map
        Person person = Person.findByName(people, firstName, lastName);

        // If no person is found, let user know
        if (person == null) {
            System.out.println("No Customer Found");
            writeLog("LogActions.txt", employee.getFirstName() + " " + employee.getLastName() + " requested an Electronic Ticket Summary for " + firstName + " " + lastName + " but this person was not found");
        } else {
            // Write header
            summaryWriter.write("-----------------------------------------------------------------\n");
            summaryWriter.write(person.getFirstName() + " " + person.getLastName() + "'s Electronic Ticket Summary\n");
            
            // Create a list with the tickets of the customer
            ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();

            for (Ticket ticket : customerTicketList) {
                summaryWriter.write("\nStatus: " + ticket.getTicketStatus()
                                  + "\nConfirmation Number: " + ticket.getConfirmationNumber() 
                                  + "\nFlight ID " + ticket.getFlightID()
                                  + "\nOrigin Airport & Code: " + ticket.getOriginAirport() + " - " + ticket.getOriginCode()
                                  + "\nDestination Airport & Code: " + ticket.getDestinationAirport() + " - " + ticket.getDestinationCode()
                                  + "\nDeparture Date & Time: " + ticket.getDepartureDate() + " at " + ticket.getDepartureTime()
                                  + "\nArrival Date & Time: " + ticket.getArrivalDate() + " at " + ticket.getArrivalTime()
                                  + "\n" + ticket.getAmountOfTickets() + " " + ticket.getTicketType() + " ticket(s)"
                                  + "\nTotal: $" + ticket.getTotalPrice());
                // Write a new line
                summaryWriter.write("\n");
            }
            System.out.println("Electronic Ticket Summary for " + person.getFirstName() + " " + person.getLastName() + " was successfully written");
            writeLog("LogActions.txt", employee.getFirstName() + " " + employee.getLastName() + " requested an Electronic Ticket Summary for " + person.getFirstName() + " " + person.getLastName());
        }
        // Close
        summaryWriter.close();
    }

    /**
     * Method to write Electronic Ticket Summary for Alondra
     * @param fileName File Name
     * @param people Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */
    public void AlondraTicketSummary(String fileName, HashMap<Integer,Person> people) throws Exception {
        // Create new File object
        FileWriter summaryWriter = new FileWriter(fileName, true);

        // Find person in hash map
        Person person = Person.findByName(people, "Alondra", "GonzalezAyala");

        // Write header
        summaryWriter.write(person.getFirstName() + " " + person.getLastName() + "'s Electronic Ticket Summary\n");
        
        // Create a list with the tickets of the customer
        ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();

        for (Ticket ticket : customerTicketList) {
            summaryWriter.write("-----------------------------------------------------------------");
            summaryWriter.write("\nStatus: " + ticket.getTicketStatus()
                              + "\nConfirmation Number: " + ticket.getConfirmationNumber() 
                              + "\nFlight ID " + ticket.getFlightID()
                              + "\nOrigin Airport & Code: " + ticket.getOriginAirport() + " - " + ticket.getOriginCode()
                              + "\nDestination Airport & Code: " + ticket.getDestinationAirport() + " - " + ticket.getDestinationCode()
                              + "\nDeparture Date & Time: " + ticket.getDepartureDate() + " at " + ticket.getDepartureTime()
                              + "\nArrival Date & Time: " + ticket.getArrivalDate() + " at " + ticket.getArrivalTime()
                              + "\n" + ticket.getAmountOfTickets() + " " + ticket.getTicketType() + " ticket(s)"
                              + "\nTotal: $" + ticket.getTotalPrice());
            // Write a new line
            summaryWriter.write("\n");
        }
        // Close
        summaryWriter.close();
    }

    /**
     * Method to write Electronic Ticket Summary for Minnie
     * @param fileName File Name
     * @param people Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */
    public void MinnieTicketSummary(String fileName, HashMap<Integer,Person> people) throws Exception {
        // Create new File object
        FileWriter summaryWriter = new FileWriter(fileName, true);

        // Find person in hash map
        Person person = Person.findByName(people, "Minnie", "Mouse");

        // Write header
        summaryWriter.write(person.getFirstName() + " " + person.getLastName() + "'s Electronic Ticket Summary\n");
        
        // Create a list with the tickets of the customer
        ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();

        for (Ticket ticket : customerTicketList) {
            summaryWriter.write("-----------------------------------------------------------------");
            summaryWriter.write("\nStatus: " + ticket.getTicketStatus()
                              + "\nConfirmation Number: " + ticket.getConfirmationNumber() 
                              + "\nFlight ID " + ticket.getFlightID()
                              + "\nOrigin Airport & Code: " + ticket.getOriginAirport() + " - " + ticket.getOriginCode()
                              + "\nDestination Airport & Code: " + ticket.getDestinationAirport() + " - " + ticket.getDestinationCode()
                              + "\nDeparture Date & Time: " + ticket.getDepartureDate() + " at " + ticket.getDepartureTime()
                              + "\nArrival Date & Time: " + ticket.getArrivalDate() + " at " + ticket.getArrivalTime()
                              + "\n" + ticket.getAmountOfTickets() + " " + ticket.getTicketType() + " ticket(s)"
                              + "\nTotal: $" + ticket.getTotalPrice());
            // Write a new line
            summaryWriter.write("\n");
        }
        // Close
        summaryWriter.close();
    }

    /**
     * Method to write Electronic Ticket Summary for Stitch
     * @param fileName File Name
     * @param people Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */
    public void StitchTicketSummary(String fileName, HashMap<Integer,Person> people) throws Exception {
        // Create new File object
        FileWriter summaryWriter = new FileWriter(fileName, true);

        // Find person in hash map
        Person person = Person.findByName(people, "Stitch", "Disney");

        // Write header
        summaryWriter.write(person.getFirstName() + " " + person.getLastName() + "'s Electronic Ticket Summary\n");
        
        // Create a list with the tickets of the customer
        ArrayList<Ticket> customerTicketList = person.getTicketsPurchased();

        for (Ticket ticket : customerTicketList) {
            summaryWriter.write("-----------------------------------------------------------------");
            summaryWriter.write("\nStatus: " + ticket.getTicketStatus()
                              + "\nConfirmation Number: " + ticket.getConfirmationNumber() 
                              + "\nFlight ID " + ticket.getFlightID()
                              + "\nOrigin Airport & Code: " + ticket.getOriginAirport() + " - " + ticket.getOriginCode()
                              + "\nDestination Airport & Code: " + ticket.getDestinationAirport() + " - " + ticket.getDestinationCode()
                              + "\nDeparture Date & Time: " + ticket.getDepartureDate() + " at " + ticket.getDepartureTime()
                              + "\nArrival Date & Time: " + ticket.getArrivalDate() + " at " + ticket.getArrivalTime()
                              + "\n" + ticket.getAmountOfTickets() + " " + ticket.getTicketType() + " ticket(s)"
                              + "\nTotal: $" + ticket.getTotalPrice());
            // Write a new line
            summaryWriter.write("\n");
        }
        // Close
        summaryWriter.close();
    }

    /**
     * Method to write files upon program termination
     * @param flights Hash Map that contains all flight information
     * @param people Hash Map that contains all customer/employee information
     * @throws Exception Exception
     */
    public void writeFiles(HashMap<Integer, Flight> flights, HashMap<Integer, Person> people) throws Exception {
        writer.writeFlightList("UpdatedFlightSchedule.csv", flights);
        writer.writeCustomerList("UpdatedCustomerList.csv", people);
        writer.AlondraTicketSummary("AlondraGonzalezAyalaElectronicTicketSummary.txt", people);
        writer.MinnieTicketSummary("MinnieMouseElectronicTicketSummary.txt", people);
        writer.StitchTicketSummary("StitchDisneyElectronicTicketSummary.txt", people);
    }

    /**
     * Method to overwrite files
     * @throws Exception Exception
     */ 
    public void overwriteFiles() throws Exception {
        // Overwrite files
        File log = new File("LogActions.txt");
        log.delete();
        log.createNewFile();

        File flightCSV = new File("UpdatedFlightSchedule.csv");
        flightCSV.delete();
        flightCSV.createNewFile();

        File customerCSV = new File("UpdatedCustomerList.csv");
        customerCSV.delete();
        customerCSV.createNewFile();

        File ticketSummary = new File("ElectronicTicketSummary.txt");
        ticketSummary.delete();
        ticketSummary.createNewFile();

        File alondraSummary = new File("AlondraGonzalezAyalaElectronicTicketSummary.txt");
        alondraSummary.delete();
        alondraSummary.createNewFile();

        File minnieSummary = new File("MinnieMouseElectronicTicketSummary.txt");
        minnieSummary.delete();
        minnieSummary.createNewFile();

        File stitchSummary = new File("StitchDisneyElectronicTicketSummary.txt");
        stitchSummary.delete();
        stitchSummary.createNewFile();
    }
}