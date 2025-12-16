
package com.example;
public class Ticket {

    private String passengerName;
    private String seatNumber;
    private paymentInterface payment;
    private TicketStorage storage;

    public Ticket(String passengerName,
                  String flightNumber,
                  String seatNumber,
                  paymentInterface payment,
                  TicketStorage storage) {
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.payment = payment;
        this.storage = storage;
    }

    public boolean checkAndConfirmTicket() {
        if (payment.processPayment()) {
            if (storage.isAvailable(seatNumber)) {
                storage.confirmSeat(seatNumber, passengerName);
                return true;
            }
            System.out.println("Seat not available");
        }
        return false;
    }
}
