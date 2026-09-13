package constructors_keywords.class_problems;

public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Ticket fare and minutes late cannot be negative");
        }

        if (minutesLate == 0) {
            return 0.0;
        }

        double penaltyRate = 0.0;
        if (minutesLate <= 5) {
            penaltyRate = minutesLate * 0.005;
        } else if (minutesLate <= 15) {
            penaltyRate = 5 * 0.005 + (minutesLate - 5) * 0.01;
        } else {
            penaltyRate = 5 * 0.005 + 10 * 0.01 + (minutesLate - 15) * 0.02;
        }

        double tieredPenalty = ticketFare * penaltyRate;
        double floorPenalty = ticketFare * (minimumPenaltyPercent / 100.0);

        return Math.max(tieredPenalty, floorPenalty);
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator calc = new BoardingPenaltyCalculator(1.0);

        System.out.println("Rs " + calc.calculatePenalty(1000, 0));
        System.out.println("Rs " + calc.calculatePenalty(1000, 1));
        System.out.println("Rs " + calc.calculatePenalty(1000, 16));
    }
}
