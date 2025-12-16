package com.example;
import java.time.LocalDateTime;
import java.util.UUID;


public class Payment implements paymentInterface{
	private final String paymentId;
	private double amount;
	private String currency;
	private final LocalDateTime timestamp;
	private boolean isProcessed;
	
	
	public Payment(double amount, String currency) {
		this.paymentId = UUID.randomUUID().toString();
		this.amount = amount;
		this.currency = currency;
		this.timestamp = LocalDateTime.now();
		this.isProcessed = false;
		
	}
	@Override
	public boolean processPayment() {
		if(amount <= 0) {
			System.out.print("Payment amount must be greater than zero.");
			return false;
		} else { 
			System.out.println("Payment of " + amount + " " + currency + " processed successfully.");
			return true;
		}
		
		
	
	}

	public String getPaymentId() {
		return paymentId;
	}

	public double getAmount() {
		return amount;
	}

	public String getCurrency() {
		return currency;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public boolean isProcessed() {
		return isProcessed;
	}
	@Override
	public void setAmount(double amount) {
		if(!this.isProcessed) {
			this.amount = amount;	
		} else {
			System.out.println("Can not change the amount, payment is already processed.");
		}
	}
	@Override
	public void setCurrency(String currency) {
		if(!this.isProcessed) {
			this.currency = currency;
		}else {
			System.out.println("Can not change currency, payment is already processed.");
		}
	}

}
