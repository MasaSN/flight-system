package com.example;
// package SoftwareTesting;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {

    private final String notificationID;
    private String customerID;
    private String message;
    private String type;
    private LocalDateTime timestamp;

    public Notification(String customerID) {
        this.notificationID = UUID.randomUUID().toString();
        this.customerID = customerID;
        this.timestamp = LocalDateTime.now();
    }

    public void sendBookingConfirmation(String customerEmail, String flightInfo) {

        if (customerEmail == null || !customerEmail.contains("@")) {
            System.out.println("Error: Invalid email address.");
            return;
        }

        if (flightInfo == null || flightInfo.isEmpty()) {
            System.out.println("Error: Missing flight information.");
            return;
        }

        this.type = "Booking Confirmation";
        this.message = "Booking confirmed for flight: " + flightInfo;
        this.timestamp = LocalDateTime.now();

        System.out.println("Sending booking confirmation to: " + customerEmail);
        System.out.println("Message: " + this.message);
    }

    public void sendCancellationAlert(String customerEmail, String bookingID) {

        if (customerEmail == null || !customerEmail.contains("@")) {
            System.out.println("Error: Invalid email address.");
            return;
        }

        if (bookingID == null || bookingID.isEmpty()) {
            System.out.println("Error: Booking ID is required.");
            return;
        }

        this.type = "Cancellation Alert";
        this.message = "Your booking (ID: " + bookingID + ") has been cancelled.";
        this.timestamp = LocalDateTime.now();

        System.out.println("Sending cancellation alert to: " + customerEmail);
        System.out.println("Message: " + this.message);
    }
 
    public String getNotificationID() { return notificationID; }
    public String getCustomerID() { return customerID; }
    public String getMessage() { return message; }
    public String getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
