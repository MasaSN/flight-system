package com.example;

public interface TicketStorage {
    boolean isAvailable(String seatNumber);
    void confirmSeat(String seatNumber, String passengerName);
}
