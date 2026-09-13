package constructors_keywords.class_problems;

public class BusTicketAccount {
    private static double defaultMinPenaltyPercent;

    static {
        defaultMinPenaltyPercent = 1.0;
    }

    private String bookingId;
    private double ticketFare;
    private BoardingPenaltyCalculator calculator;

    public BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        this.calculator = new BoardingPenaltyCalculator(defaultMinPenaltyPercent);
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0.0);
    }

    public final double calculatePenalty(int minutesLate) {
        return calculator.calculatePenalty(ticketFare, minutesLate);
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getTicketFare() {
        return ticketFare;
    }

    public void processAccount(BusTicketAccount account, double amount, int minutesLate) {
        if (account != null) {
            account.calculatePenalty(minutesLate);
        }
    }

    public static void processBatch(BusTicketAccount[] accounts, double[] amounts, int[] minutesLateArray) {
        if (accounts == null || amounts == null || minutesLateArray == null) {
            return;
        }

        int length = Math.min(accounts.length, Math.min(amounts.length, minutesLateArray.length));
        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double grandTotalPenalties = 0.0;

        for (int i = 0; i < length; i++) {
            BusTicketAccount acc = accounts[i];
            if (acc == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (acc instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }
            double penalty = acc.calculatePenalty(minutesLateArray[i]);
            grandTotalPenalties += penalty;
        }

        grandTotalPenalties = Math.round(grandTotalPenalties * 100.0) / 100.0;
        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + sleeperCount + " sleeper | " + regularCount + " regular | grand total penalties = " + grandTotalPenalties);
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };
        double[] amounts = {1200, 900, 700};
        int[] minutesLateArray = {10, 5, 0};

        processBatch(accounts, amounts, minutesLateArray);
    }
}

class Sleeper extends BusTicketAccount {
    public Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public Sleeper(String bookingId) {
        super(bookingId);
    }
}
