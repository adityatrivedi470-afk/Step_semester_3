import java.util.Arrays;

class EventTicket {
    private final String attendeeId;
    protected double basePrice;
    protected double paidAmount = 0.0;

    private double[] lateFeeHistory = new double[10];
    private int lateFeeCount = 0;

    public EventTicket(String attendeeId, double basePrice) {
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    public EventTicket(double basePrice) {
        this("UNKNOWN", basePrice);
    }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - paidAmount);
    }

    protected void applyLateFee(double amount) {
        if (lateFeeCount < lateFeeHistory.length) {
            lateFeeHistory[lateFeeCount++] = amount;
        }
        this.basePrice += amount;
    }

    public double[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, lateFeeCount);
    }
}

class WorkshopTicket extends EventTicket {
    public WorkshopTicket(double basePrice) {
        super(basePrice);
    }

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {
        super.applyLateFee(amount * 2);
    }
}

public class Problem3 {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket(1200);
        w.pay(1200);
        w.applyLateFee(100);

        System.out.println("Balance Due: " + w.getBalanceDue());

        double[] history = w.getLateFeeHistory();
        System.out.println("Late Fee History: " + Arrays.toString(history));

        // Attempt tamper
        history[0] = 999;
        System.out.println("Defensive Check History: " + Arrays.toString(w.getLateFeeHistory()));
    }
}
