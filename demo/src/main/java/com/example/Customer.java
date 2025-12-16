package com.example;


import java.util.ArrayList;

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private ArrayList<String> bookingHistory;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.bookingHistory = new ArrayList<>();
    }

    // Method 1: Register customer with validation
    public boolean registerCustomer(String email, String phone) {
        // Email validation - check for @ and .
        if (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.println("ERROR: Invalid email format");
            return false;
        }

        // Check email has text before @ and after .
        int atIndex = email.indexOf("@");
        int dotIndex = email.lastIndexOf(".");
        
        if (atIndex < 1 || dotIndex < atIndex + 2 || dotIndex >= email.length() - 1) {
            System.out.println("ERROR: Invalid email structure");
            return false;
        }

        // Phone validation - at least 10 digits
        if (phone == null || phone.length() < 10) {
            System.out.println("ERROR: Phone number must be at least 10 digits");
            return false;
        }

        // Check if customer already exists using email
        if (customerExists(email)) {
            System.out.println("ERROR: Customer with this email already exists");
            return false;
        }

        // Update customer info
        this.email = email;
        this.phone = phone;
        System.out.println("SUCCESS: Customer registered successfully");
        return true;
    }

    // Helper method to check if customer exists
    private boolean customerExists(String email) {
        // Simulated database check - list of existing customers
        ArrayList<String> existingCustomers = new ArrayList<>();
        existingCustomers.add("john@example.com");
        existingCustomers.add("jane@example.com");
        existingCustomers.add("ali@example.com");

        return existingCustomers.contains(email);
    }

    // Method 2: View booking history
    public ArrayList<String> viewBookingHistory() {
        if (bookingHistory.isEmpty()) {
            System.out.println("No booking history found for customer: " + name);
            return new ArrayList<>(); // Return empty list
        }

        System.out.println("----- BOOKING HISTORY for " + name + " -----");
        for (String booking : bookingHistory) {
            System.out.println(booking);
        }

        return bookingHistory;
    }

    // Method 3: Add booking to history
    public void addBooking(String ticketId, String flightNumber, String seatNumber) {
        String bookingRecord = "Ticket: " + ticketId + ", Flight: " + flightNumber + ", Seat: " + seatNumber;
        bookingHistory.add(bookingRecord);
        System.out.println("Booking added to customer history: " + bookingRecord);
    }

    // Method 4: Update customer profile
    public boolean updateProfile(String newName, String newPhone) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }

        if (newPhone != null && newPhone.length() >= 10) {
            this.phone = newPhone;
            System.out.println("Profile updated successfully");
            return true;
        } else if (newPhone != null) {
            System.out.println("ERROR: Invalid phone number");
            return false;
        }

        System.out.println("Profile updated successfully");
        return true;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<String> getBookingHistory() {
        return bookingHistory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID='" + customerId + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phone + '\'' +
                ", Total Bookings=" + bookingHistory.size() +
                '}';
    }
}