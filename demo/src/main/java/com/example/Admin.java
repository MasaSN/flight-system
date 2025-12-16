package com.example;

import java.util.ArrayList;

public class Admin {
    // Attributes
    private String adminId;
    private ArrayList<String> permissions;
    private ArrayList<String> activityLog;

    private ArrayList<String> flights;   // store flights as text
    private ArrayList<String> bookings;  // store bookings as text

    // Constructor
    public Admin(String adminId, ArrayList<String> permissions) {
        this.adminId = adminId;
        this.permissions = permissions;
        this.activityLog = new ArrayList<>();
        this.flights = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    // Add a flight Method 1
    public boolean addFlight(String flightId, String origin, String destination, String time) {

        if (!permissions.contains("ADD_FLIGHT")) {
            activityLog.add("No permission to add flights");
            return false;
        }

        if (isEmpty(flightId) || isEmpty(origin) || isEmpty(destination) || isEmpty(time)) {
            activityLog.add("Invalid flight data");
            return false;
        }

        for (String f : flights) {
        //f-flight
            String[] p = f.split(",");
            if (p[0].trim().equals(flightId) && p[3].trim().equals(time)) {
                activityLog.add("Flight conflict: " + flightId);
                return false;
            }
        }

        String record = flightId + ", " + origin + ", " + destination + ", " + time;
        flights.add(record);
        activityLog.add("Flight added: " + record);
        return true;
    }

    // View all bookings method2
    public void viewAllBookings() {
        System.out.println("----- ALL BOOKINGS -----");

        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
         // b-booking
            for (String b : bookings) {
                System.out.println(b);
            }
        }

        activityLog.add("Viewed all bookings");
    }
    // Add a booking  method3
public void addBooking(String passenger, String flightId, String seat) {
    String record = passenger + ", " + flightId + ", " + seat;
    System.out.println("New Booking: " + record);
    bookings.add(record);
    activityLog.add("Booking added: " + record);
}
    // Helper
    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // Getters
    public String getAdminId() {
        return adminId;
    }

    public ArrayList<String> getPermissions() {
        return permissions;
    }

    public ArrayList<String> getActivityLog() {
        return activityLog;
    }

    public ArrayList<String> getFlights() {
        return flights;
    }

    public ArrayList<String> getBookings() {
        return bookings;
    }
}


