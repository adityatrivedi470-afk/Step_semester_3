import java.util.Arrays;

class RaceEntry {
    private final String bibNumber;
    protected double entryFee;
    protected double paidAmount = 0.0;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, entryFee - paidAmount);
    }

    protected void applyLateFee(double amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
        this.entryFee += amount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class RunnerEntry extends RaceEntry {
    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);

        System.out.println("Balance Due: " + r.getBalanceDue());

        double[] history = r.getLateFeeHistory();
        System.out.println("Late Fee History: " + Arrays.toString(history));

        // Attempt tamper
        history[0] = 999;
        System.out.println("Defensive Check History: " + Arrays.toString(r.getLateFeeHistory()));
    }
}
