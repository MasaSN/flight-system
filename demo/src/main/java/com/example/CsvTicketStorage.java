package com.example;

import java.io.*;

public class CsvTicketStorage implements TicketStorage {

    private final String filePath;

    public CsvTicketStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean isAvailable(String seatNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(seatNumber)) {
                    return parts[1].equalsIgnoreCase("true");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void confirmSeat(String seatNumber, String passengerName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println(seatNumber + ",false," + passengerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
