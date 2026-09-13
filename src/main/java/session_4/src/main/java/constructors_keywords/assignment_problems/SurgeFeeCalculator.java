package constructors_keywords.assignment_problems;

public final class SurgeFeeCalculator {
    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    public final double calculateSurgeFee(double orderValue, int delayMinutes) {
        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Order value and delay minutes cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0.0;
        }

        double surgeRate = 0.0;
        if (delayMinutes <= 5) {
            surgeRate = delayMinutes * 0.005;
        } else if (delayMinutes <= 15) {
            surgeRate = 5 * 0.005 + (delayMinutes - 5) * 0.01;
        } else {
            surgeRate = 5 * 0.005 + 10 * 0.01 + (delayMinutes - 15) * 0.02;
        }

        double tieredSurge = orderValue * surgeRate;
        double floorSurge = orderValue * (minimumSurgePercent / 100.0);

        return Math.max(tieredSurge, floorSurge);
    }

    public static void main(String[] args) {
        SurgeFeeCalculator calc = new SurgeFeeCalculator(1.0);

        System.out.println("Rs " + calc.calculateSurgeFee(500, 0));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 1));
        System.out.println("Rs " + calc.calculateSurgeFee(500, 16));
    }
}
