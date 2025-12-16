package com.example;
public class Main {
    public static void main(String[] args) {
        Payment p = new Payment(150.0, "USD");
        TicketStorage storage = new CsvTicketStorage("tickets.csv");
        // Create a ticket object
        Ticket t = new Ticket("Masa", "TK123", "12A", p, storage);

        // Test method 1: print ticket
        t.checkAndConfirmTicket();

        System.out.println("Test completed!");
    }
}
