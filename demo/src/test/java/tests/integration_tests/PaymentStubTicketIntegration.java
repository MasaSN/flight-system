package tests.integration_tests;

import com.example.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentStubTicketIntegration {

    class TicketStorageStub implements TicketStorage {
        boolean confirmed = false;

        @Override
        public boolean isAvailable(String seatNumber) {
            return true;
        }

        @Override
        public void confirmSeat(String seatNumber, String passengerName) {
            confirmed = true;
        }
    }

    class PaymentStubSuccess implements paymentInterface {
        public boolean processPayment() {
            return true;
        }

        public void setCurrency(String currency) {}
        public void setAmount(double amount) {}
    }

    class PaymentStubFailure implements paymentInterface {
        public boolean processPayment() {
            return false;
        }

        public void setCurrency(String currency) {}
        public void setAmount(double amount) {}
    }

    @Test
    void ticketIsConfirmed_whenPaymentSucceeds() {
        paymentInterface paymentStub = new PaymentStubSuccess();
        TicketStorageStub storageStub = new TicketStorageStub();

        Ticket ticket = new Ticket(
            "John Doe",
            "FL456",
            "12B",
            paymentStub,
            storageStub
        );

        boolean result = ticket.checkAndConfirmTicket();

        assertTrue(result);
        assertTrue(storageStub.confirmed);
    }

    @Test
    void ticketIsNotConfirmed_whenPaymentFails() {
        paymentInterface paymentStub = new PaymentStubFailure();
        TicketStorageStub storageStub = new TicketStorageStub();

        Ticket ticket = new Ticket(
            "John Doe",
            "FL456",
            "12B",
            paymentStub,
            storageStub
        );

        boolean result = ticket.checkAndConfirmTicket();

        assertFalse(result);
        assertFalse(storageStub.confirmed);
    }
}
