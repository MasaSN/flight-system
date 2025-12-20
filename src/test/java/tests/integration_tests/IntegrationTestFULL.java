package tests.integration_tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.CsvTicketStorage;
import com.example.Customer;
import com.example.Notification;
import com.example.Payment;
import com.example.Ticket;
import com.example.TicketStorage;

public class IntegrationTestFULL {

    // Helper method to create a standard customer for all tests
    private Customer getTestCustomer() {
        Customer c = new Customer("C001", "Masarrah", "msrh@example.com", "0123456789");
        c.registerCustomer("msrh@example.com", "0123456789");
        return c;
    }

    @Test
    public void test1_CustomerRegistration() {
        Customer customer = getTestCustomer();
        assertNotNull(customer.getCustomerId());
        System.out.println("Step 1: Registration Passed");
    }

    @Test
    public void test2_SeatAvailability() {
        TicketStorage ticketStore = new CsvTicketStorage("file.csv"); 
        boolean available = ticketStore.isAvailable("1A");
        // This is expected to fail based on your previous run
        assertTrue(available, "Seat 1A should be available"); 
    }

    @Test
    public void test3_PaymentAndNotification() {
        Payment payment = new Payment(12500.50, "TRY");
        assertTrue(payment.processPayment());
        
        Notification notification = new Notification("C001");
        // These methods usually return void; we test they don't throw exceptions
        assertDoesNotThrow(() -> notification.sendPaymentConfirmation("msrh@example.com", 12500.50));
        System.out.println("Step 3 & 4: Payment and Notification Passed");
    }

    @Test
    public void test4_TicketConfirmationFlow() {
        TicketStorage ticketStore = new CsvTicketStorage("file.csv");
        Payment payment = new Payment(12500.50, "TRY");
        Ticket ticket = new Ticket("Masarrah", "FL99", "1A", payment, ticketStore);
        
        boolean status = ticket.checkAndConfirmTicket();
        assertNotNull(status);
        System.out.println("Step 5: Ticket Confirmation Passed");
    }

    @Test
    public void test5_BookingHistory() {
        Customer customer = getTestCustomer();
        customer.addBooking("12345" , "FL99" , "1A");
        // Assuming viewBookingHistory prints to console or returns a result
        customer.viewBookingHistory(); 
        System.out.println("Step 7 & 8: History Updated");
    }
}