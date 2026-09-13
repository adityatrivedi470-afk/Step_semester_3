class RaceEntry {
    private static int bibCounter = 0;

    private final String entryCode;
    private final String bibNumber;
    protected double entryFee;
    protected double paidAmount = 0.0;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;

        bibCounter++;
        this.entryCode = "ENTRY-" + bibCounter;
    }

    public String getEntryCode() { return entryCode; }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public static boolean isValidDiscountCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'M') return false;
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getBibCounter() {
        return bibCounter;
    }

    public static String settleNight(RaceEntry[] entries) {
        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        if (entries != null) {
            for (RaceEntry entry : entries) {
                if (entry == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (entry instanceof RelayTeamEntry) {
                    relayCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed | " + 
               nullSkipped + " null skipped | " + 
               relayCount + " relay | " + 
               individualCount + " individual";
    }
}

class EliteRunnerEntry extends RaceEntry {
    public EliteRunnerEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

class RelayTeamEntry extends RaceEntry {
    public RelayTeamEntry(String bibNumber, double entryFee) {
        super(bibNumber, entryFee);
    }
}

public class Problem5 {
    public static void main(String[] args) {
        System.out.println("M123A valid: " + RaceEntry.isValidDiscountCode("M123A"));
        System.out.println("M12A valid: " + RaceEntry.isValidDiscountCode("M12A"));
        System.out.println("X123A valid: " + RaceEntry.isValidDiscountCode("X123A"));

        EliteRunnerEntry elite = new EliteRunnerEntry("BIB1001", 150);
        RelayTeamEntry relay = new RelayTeamEntry("BIB2002", 300);
        elite.pay(10, "UPI");

        RaceEntry[] nightBatch = { elite, null, relay };
        System.out.println(RaceEntry.settleNight(nightBatch));

        System.out.println("Total Counter: " + RaceEntry.getBibCounter());
    }
}
