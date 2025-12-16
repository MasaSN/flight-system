package com.example;


import java.util.HashMap;

public class Airport {
    private String airportCode;
    private String airportName;
    private String location;
    private int numTerminals;
    private HashMap<String, String> terminalAssignments; // flightId -> terminal

    // Static database of airports (simulated)
    private static HashMap<String, AirportInfo> airportDB;

    static class AirportInfo {
        String name;
        String location;
        int terminals;

        AirportInfo(String name, String location, int terminals) {
            this.name = name;
            this.location = location;
            this.terminals = terminals;
        }
    }

    static {
        airportDB = new HashMap<>();
        airportDB.put("IST", new AirportInfo("Istanbul Airport", "Istanbul, Turkey", 1));
        airportDB.put("SAW", new AirportInfo("Sabiha Gokcen Airport", "Istanbul, Turkey", 1));
        airportDB.put("JFK", new AirportInfo("John F. Kennedy International", "New York, USA", 6));
        airportDB.put("LAX", new AirportInfo("Los Angeles International", "Los Angeles, USA", 9));
        airportDB.put("LHR", new AirportInfo("London Heathrow", "London, UK", 5));
        airportDB.put("DXB", new AirportInfo("Dubai International", "Dubai, UAE", 3));
        airportDB.put("CDG", new AirportInfo("Charles de Gaulle", "Paris, France", 3));
    }

    public Airport(String airportCode, String airportName, String location, int numTerminals) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.location = location;
        this.numTerminals = numTerminals;
        this.terminalAssignments = new HashMap<>();
    }

    // Method 1: Get airport information by code
    public boolean getAirportInfo(String code) {
        if (code == null || code.trim().isEmpty()) {
            System.out.println("ERROR: Airport code cannot be empty");
            return false;
        }

        code = code.trim().toUpperCase();

        if (!airportDB.containsKey(code)) {
            System.out.println("ERROR: Airport with code '" + code + "' not found");
            return false;
        }

        // Retrieve airport info
        AirportInfo info = airportDB.get(code);
        
        System.out.println("----- AIRPORT INFORMATION -----");
        System.out.println("Code: " + code);
        System.out.println("Name: " + info.name);
        System.out.println("Location: " + info.location);
        System.out.println("Number of Terminals: " + info.terminals);
        System.out.println("------------------------------");

        return true;
    }

    // Method 2: Check which terminal a flight departs from
    public String checkTerminal(String flightId) {
        if (flightId == null || flightId.trim().isEmpty()) {
            System.out.println("ERROR: Flight ID cannot be empty");
            return null;
        }

        // Check if terminal is already assigned
        if (terminalAssignments.containsKey(flightId)) {
            String terminal = terminalAssignments.get(flightId);
            System.out.println("Flight " + flightId + " departs from " + terminal);
            return terminal;
        }

        // Auto-assign terminal based on flight ID pattern
        String terminal = assignTerminal(flightId);
        terminalAssignments.put(flightId, terminal);
        
        System.out.println("Flight " + flightId + " assigned to " + terminal);
        return terminal;
    }

    // Helper method to assign terminal based on flight ID
    private String assignTerminal(String flightId) {
        // Use first 2 characters to determine terminal
        if (flightId.startsWith("TK")) {
            return "Terminal 1"; // Turkish Airlines
        } else if (flightId.startsWith("AA") || flightId.startsWith("UA")) {
            return "Terminal 2"; // American carriers
        } else if (flightId.startsWith("BA") || flightId.startsWith("LH")) {
            return "Terminal 3"; // European carriers
        } else {
            // Default: distribute based on last digit
            try {
                char lastChar = flightId.charAt(flightId.length() - 1);
                if (Character.isDigit(lastChar)) {
                    int digit = Character.getNumericValue(lastChar);
                    return "Terminal " + ((digit % numTerminals) + 1);
                }
            } catch (Exception e) {
            }
            return "Terminal 1";
        }
    }

    // Method 3: List all available airports
    public static void listAllAirports() {
        System.out.println("----- AVAILABLE AIRPORTS -----");
        
        for (String code : airportDB.keySet()) {
            AirportInfo info = airportDB.get(code);
            System.out.println(code + " - " + info.name + " (" + info.location + ")");
        }
        
        System.out.println("------------------------------");
    }

    // Method 4: Check if airport code exists
    public static boolean isValidAirportCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return false;
        }
        return airportDB.containsKey(code.trim().toUpperCase());
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfTerminals() {
        return numTerminals;
    }

    public HashMap<String, String> getTerminalAssignments() {
        return terminalAssignments;
    }

    // toString
    @Override
    public String toString() {
        return "Airport{" +
                "Code='" + airportCode + '\'' +
                ", Name='" + airportName + '\'' +
                ", Location='" + location + '\'' +
                ", Terminals=" + numTerminals +
                '}';
    }
}