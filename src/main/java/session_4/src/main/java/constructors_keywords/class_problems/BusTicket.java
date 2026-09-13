package constructors_keywords.class_problems;

import java.util.ArrayList;
import java.util.List;

public class BusTicket {
    private String passengerName;
    private String destination;
    private boolean checkedIn;

    public BusTicket(String passengerName, String destination) {
        String trimmedName = passengerName.trim();
        String trimmedDest = destination.trim();

        if (trimmedName.isEmpty() || trimmedDest.isEmpty()) {
            throw new IllegalArgumentException("Passenger name and destination cannot be empty or whitespace-only");
        }

        this.passengerName = trimmedName;
        this.destination = trimmedDest;
        this.checkedIn = false;
    }

    public void markCheckedIn() {
        if (checkedIn) {
            System.out.println("Ticket already checked in.");
        } else {
            checkedIn = true;
            System.out.println("Check-in successful.");
        }
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getDestination() {
        return destination;
    }

    public static void processBatch(String[][] rawBookings) {
        int valid = 0;
        int rejected = 0;
        int duplicates = 0;

        List<String> seenBookings = new ArrayList<>();

        for (String[] booking : rawBookings) {
            if (booking == null || booking.length < 2) {
                rejected++;
                continue;
            }

            try {
                BusTicket ticket = new BusTicket(booking[0], booking[1]);
                String key = ticket.getPassengerName() + "|" + ticket.getDestination();

                if (seenBookings.contains(key)) {
                    duplicates++;
                } else {
                    seenBookings.add(key);
                    valid++;
                }
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected + " | Duplicates skipped: " + duplicates);
    }

    public static void main(String[] args) {
        String[][] batch = {
            {"Divya", "Chennai"},
            {"", "Bangalore"},
            {"Ravi123", "Pune"},
            {"Divya", "Chennai"},
            {" ", " "}
        };
        processBatch(batch);
    }
}
