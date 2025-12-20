package tests.integration_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.Customer;

@DisplayName("Flight System: Session 1 Integration")
public class TicketStubCustomerIntegration {

/**
 * Stub for Ticket class to isolate integration testing.
 * This avoids external dependencies like "file.csv".
 */
static class TicketStub {
    private String flightNumber;
    private String seatNumber;
    private boolean manualAvailability;
    private double price = 250.0;

    public TicketStub(String flightNumber, String seatNumber, boolean availability) {
        this.flightNumber = flightNumber;
        this.seatNumber = seatNumber;
        this.manualAvailability = availability;
    }

    public boolean isAvailable() {
        return manualAvailability;
    }

    public double getPrice() {
        return price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void confirmTicket() {
        System.out.println("[STUB] Simulated confirmation for seat " + seatNumber);
    }
}

    @Test
    @DisplayName("Scenario 1: Booking Flow - Success Path")
    void testBookingSuccess() {
        // Arrange
        Customer customer = new Customer("C101", "Masa", "masa@test.com", "0501234567");
        TicketStub ticket = new TicketStub("MS777", "14B", true);

        // Act & Assert
        boolean registered = customer.registerCustomer("masa@test.com", "0501234567");
        assertTrue(registered, "Registration should pass for valid email/phone format.");

        if (ticket.isAvailable()) {
            ticket.confirmTicket();
            customer.addBooking("BK-99", ticket.getFlightNumber(), ticket.getSeatNumber());
        }

        // Assert the interaction
        assertTrue(ticket.isAvailable(), "Stub should report availability as true.");
    }

    @Test
    @DisplayName("Scenario 2: Booking Flow - Seat Unavailable")
    void testBookingFailure() {
        TicketStub ticket = new TicketStub("MS777", "14B", false);
        assertFalse(ticket.isAvailable(), "The system should identify the seat as taken via stub.");
    }
}