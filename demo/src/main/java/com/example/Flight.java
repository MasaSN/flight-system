package com.example;
// package flightpart;

import java.util.*;

public class Flight {
	
    private String flightID;
    private String from;
    private String destination;
    private String departureDate; 
    private String departureTime; 
    private String arrivalTime;
    private int ecoSeats;
    private int businessSeats;
    private double ecoPrice;
    private double businessPrice;

    public Flight(String flightID, String from, String destination,
                  String departureDate, String departureTime, String arrivalTime,
                  int ecoSeats, int businessSeats, double ecoPrice, double businessPrice) {

        this.flightID = flightID;
        this.from = from;
        this.destination = destination;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.ecoSeats = ecoSeats;
        this.businessSeats = businessSeats;
        this.ecoPrice = ecoPrice;
        this.businessPrice = businessPrice;
    }

    //>>>>>>>>>>>>>>>>>>>>>Seat methods<<<<<<<<<<<<<<<<<<<<<<<<<<

    //Checks and returns seats for each class ECO and Business
    public boolean checkAvailability(String type) {
        if (type.equalsIgnoreCase("Eco")) {
            return ecoSeats > 0;
        } else {
            return businessSeats > 0;
        }
    }

    //This (when booking) checks if there are seats from the very first place then book
    public void bookSeat(String type) {
        if (!checkAvailability(type)) {
            System.out.println("Flights are Full!");
            return;
        }

        if (type.equalsIgnoreCase("Eco")) {
            ecoSeats--;
            System.out.println("Your seat is successfully booked!");
        } else {
            businessSeats--;
            System.out.println("Your seat is successfully booked!");
        }
    }

    //Canceling the seat booking
    public void cancelSeat(String type) {
        if (type.equalsIgnoreCase("Eco")) {
            ecoSeats++;
        } else {
            businessSeats++;
        }

        System.out.println("Your seat has been cancelled.");
    }

    
    //>>>>>>>>>>>>>>>>>>>>>>Price method<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    public double getPrice(String passengerType, String flightClass) {

        double basePrice = flightClass.equalsIgnoreCase("Eco") ? ecoPrice : businessPrice;
        double finalPrice = basePrice;

        switch (passengerType.toLowerCase()) {
            case "student":
                finalPrice *= 0.85;  // 15% discount
                break;
            case "child":
                finalPrice *= 0.50;  // 50% discount
                break;
            case "bald":
                finalPrice *= 0.75;  // 25% discount - for hairline warriors
                break;
            case "65+":
                finalPrice *= 0.80;  // 20% discount
                break;
            case "adult":
            default:
                finalPrice = basePrice;
        }

        return finalPrice;
    }

    //>>>>>>>>>>>>>>>>>>>>>>Print method<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    //Call this method in a loop to display all flights information
    public void display() {

    	//Print basic info about flight
        System.out.println("Flight: " + flightID);
        System.out.println(from + " → " + destination);
        System.out.println("Departure: " + departureDate + " " + departureTime);
        System.out.println("Arrival:   " + arrivalTime);

        //Prints prices and show how much seats left each class 
        //like in ECO (HURRY 10 SEATS LEFT) book now!! (only when less than 10 seats left)
        System.out.println("Eco Price: " + ecoPrice);
        if (ecoSeats <= 10)
            System.out.println("Eco Seats Left: " + ecoSeats + " (Last 10 seats at this price!)");

        System.out.println("Business Price: " + businessPrice);        
        if (businessSeats <= 10)
            System.out.println("Business Seats Left: " + businessSeats + " (Last 10 seats at this price!)");

        System.out.println("----------------------------------------------");
    }

    
    //>>>>>>>>>>>>>>>>>>>>>>Sort methods<<<<<<<<<<<<<<<<<<<<<<<<<<<<
   
    //Compare two flights and sort them from nearest to further 
    public static void sortByDate(List<Flight> flights) {
        flights.sort((a, b) -> a.departureDate.compareTo(b.departureDate));
    }

    //Compare departure times and sort closest first
    public static void sortByTime(List<Flight> flights) {
        flights.sort((a, b) -> a.departureTime.compareTo(b.departureTime));
    }

    
    //>>>>>>>>>>>>>>>>>>>>>>Search method<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    
    //Search Flights
    public static void searchFlights(List<Flight> flights, String from, String destination) {
        boolean found = false;
        
        System.out.println("\nAvailable Flights: \n");
        for (Flight f : flights) {
            if (f.from.equalsIgnoreCase(from) &&
                f.destination.equalsIgnoreCase(destination)) {
                f.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No flights found.");
        }
    }
    
 // Find flight by ID - User wants to search for specific flight
    public static Flight findById(List<Flight> flights, String id) {
        for (Flight f : flights) {
            if (f.flightID.equalsIgnoreCase(id)) {
                return f;
            }
        }
        return null;
    }

    public String getFlightID() {
        return flightID;
    }
    
}
