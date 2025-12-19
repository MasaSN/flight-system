package com.example;

public class NotificationStub {

    private boolean stubShouldSucceed = true;
    private boolean paymentEmailSent = false;

    public void setStubShouldSucceed(boolean shouldSucceed) {
        this.stubShouldSucceed = shouldSucceed;
    }

    public boolean wasPaymentEmailSent() {
        return paymentEmailSent;
    }

    public boolean sendPaymentConfirmation(String email, String paymentId) {
        validateEmail(email);

        System.out.println("[STUB] Sending payment confirmation email to: " + email);

        if (!stubShouldSucceed) {
            System.out.println("[STUB] Email FAILED to send.");
            return false;
        }

        paymentEmailSent = true;
        System.out.println("[STUB] Email SENT successfully, PaymentID= " + paymentId);
        return true;
    }

    public boolean sendBookingConfirmation(String email, String bookingInfo) {
        validateEmail(email);

        System.out.println("[STUB] Sending confirmation of booking email to: " + email);

        if (!stubShouldSucceed) {
            System.out.println("[STUB] Email FAILED to send.");
            return false;
        }

        System.out.println("[STUB] Email Sent successfully, BookingInfo= " + bookingInfo);
        return true;
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid email: empty/null");
        }
        String e = email.trim();
        if (!e.contains("@") || !e.contains(".") || e.startsWith("@") || e.endsWith("@")) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
    }
}
