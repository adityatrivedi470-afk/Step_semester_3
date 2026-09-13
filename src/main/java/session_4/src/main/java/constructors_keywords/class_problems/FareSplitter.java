package constructors_keywords.class_problems;

import java.util.Arrays;

public class FareSplitter {
    private String tripId;
    private double totalFare;
    private int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0) {
            throw new IllegalArgumentException("Negative fare rejected");
        }
        if (passengerCount <= 0) {
            throw new IllegalArgumentException("Passenger count must be positive");
        }
        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0.0);
    }

    public double[] fareBreakdown() {
        long totalPaise = Math.round(totalFare * 100.0);
        long basePaise = totalPaise / passengerCount;
        long remainder = totalPaise % passengerCount;

        double[] shares = new double[passengerCount];
        for (int i = 0; i < passengerCount; i++) {
            long sharePaise = basePaise + (i >= passengerCount - remainder ? 1 : 0);
            shares[i] = sharePaise / 100.0;
        }
        return shares;
    }

    public String getTripId() {
        return tripId;
    }

    public double getTotalFare() {
        return totalFare;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public static void main(String[] args) {
        FareSplitter f1 = new FareSplitter("TRIP001", 100000, 3);
        System.out.println(Arrays.toString(f1.fareBreakdown()));

        FareSplitter f2 = new FareSplitter("TRIP003");
        System.out.println(Arrays.toString(f2.fareBreakdown()));
    }
}
