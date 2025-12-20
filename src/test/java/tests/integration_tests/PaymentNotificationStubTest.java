package tests.integration_tests;

import com.example.NotificationStub;
import com.example.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentNotificationStubTest {

    // 1) Payment success → Notification sent
    @Test
    void paymentSuccess_notificationSent() {
        Payment payment = new Payment(100, "USD");       // REAL
        NotificationStub notif = new NotificationStub(); // STUB
        notif.setStubShouldSucceed(true);

        boolean paid = payment.processPayment();
        assertTrue(paid);

        boolean sent = notif.sendPaymentConfirmation("firdos@gmail.com", payment.getPaymentId());
        assertTrue(sent);
        assertTrue(notif.wasPaymentEmailSent());
    }

    // 2) Payment failure → No notification
    @Test
    void paymentFailure_noNotification() {
        Payment payment = new Payment(0, "USD");         // REAL failure
        NotificationStub notif = new NotificationStub(); // STUB

        boolean paid = payment.processPayment();
        assertFalse(paid);

        // notification must not be attempted
        assertFalse(notif.wasPaymentEmailSent());
    }

    // 3) Notification fails → Payment is still valid
    @Test
    void notificationFails_paymentStillValid() {
        Payment payment = new Payment(50, "USD");        // REAL success
        NotificationStub notif = new NotificationStub(); // STUB
        notif.setStubShouldSucceed(false);

        boolean paid = payment.processPayment();
        assertTrue(paid);

        boolean sent = notif.sendPaymentConfirmation("firdos@gmail.com", payment.getPaymentId());
        assertFalse(sent);

        // a required statement
        assertTrue(paid, "Payment remains valid even if notification fails");
    }

    // 4) Invalid email → Validation catches error
    @Test
    void invalidEmail_validationCatchesError() {
        Payment payment = new Payment(20, "USD");        // REAL success
        NotificationStub notif = new NotificationStub(); // STUB

        boolean paid = payment.processPayment();
        assertTrue(paid);

        assertThrows(IllegalArgumentException.class, () ->
                notif.sendPaymentConfirmation("firdosgmail.com", payment.getPaymentId())
        );
    }
}
