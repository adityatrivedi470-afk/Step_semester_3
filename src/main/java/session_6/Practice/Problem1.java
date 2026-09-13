import java.util.Arrays;

class EventTicket {
    private final String attendeeId;
    protected double basePrice;
    protected double paidAmount = 0.0;

    public EventTicket(String attendeeId, double basePrice) {
        if (attendeeId == null || attendeeId.trim().isEmpty() || attendeeId.length() < 4) {
            throw new IllegalArgumentException("Invalid attendee ID.");
        }
        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - paidAmount);
    }

    public static String registerBatch(String[] attendeeIds, double basePrice) {
        int registered = 0;
        int rejected = 0;

        for (String id : attendeeIds) {
            try {
                new EventTicket(id, basePrice);
                registered++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }
        return "Registered: " + registered + " Rejected: " + rejected;
    }
}

class WorkshopTicket extends EventTicket {
    private final String track;

    public WorkshopTicket(String attendeeId, double basePrice, String track) {
        super(attendeeId, basePrice);
        this.track = track;
    }
}

public class Problem1 {
    public static void main(String[] args) {
        WorkshopTicket w = new WorkshopTicket("STU2", 1200, "AI/ML");
        w.pay(500);
        System.out.println("Balance Due: " + w.getBalanceDue());

        String batchResult = EventTicket.registerBatch(new String[]{"STU1", "ST1", "STU2", "STU3", "ST1"}, 500);
        System.out.println(batchResult);
    }
}
