class EventTicket {
    private static int ticketsIssued = 1000;

    public final String ticketId;
    protected double basePrice;
    protected double paidAmount = 0.0;

    public EventTicket(double basePrice) {
        ticketsIssued++;
        this.ticketId = "TCK-" + ticketsIssued;
        this.basePrice = basePrice;
    }

    public void pay(double amount) {
        this.paidAmount += amount;
    }

    public void pay(double amount, String mode) {
        System.out.println("Paying via " + mode);
        pay(amount);
    }

    public double getBalanceDue() {
        return Math.max(0.0, basePrice - paidAmount);
    }

    public static boolean isValidPromoCode(String code) {
        if (code == null || code.length() != 5) return false;
        if (code.charAt(0) != 'F') return false;
        if (!Character.isDigit(code.charAt(1)) || 
            !Character.isDigit(code.charAt(2)) || 
            !Character.isDigit(code.charAt(3))) {
            return false;
        }
        return Character.isUpperCase(code.charAt(4));
    }

    public static int getTicketsIssued() {
        return ticketsIssued - 1000;
    }

    public static String processNightlySettlement(EventTicket[] tickets) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (tickets != null) {
            for (EventTicket ticket : tickets) {
                if (ticket == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (ticket instanceof GroupTicket) {
                    groupCount++;
                } else {
                    individualCount++;
                }
            }
        }

        return processed + " processed " + 
               nullSkipped + " null skipped | " + 
               groupCount + " group | " + 
               individualCount + " individual";
    }
}

class GroupTicket extends EventTicket {
    private final int groupSize;

    public GroupTicket(double basePrice, int groupSize) {
        super(basePrice);
        this.groupSize = groupSize;
    }

    public int getGroupSize() { return groupSize; }
}

public class Problem5 {
    public static void main(String[] args) {
        EventTicket t1 = new EventTicket(500);
        System.out.println("Ticket ID: " + t1.ticketId);
        System.out.println("Tickets Issued: " + EventTicket.getTicketsIssued());

        System.out.println("F123A valid: " + EventTicket.isValidPromoCode("F123A"));
        System.out.println("F12A valid: " + EventTicket.isValidPromoCode("F12A"));
        System.out.println("X123A valid: " + EventTicket.isValidPromoCode("X123A"));

        t1.pay(200);
        t1.pay(200, "UPI");
        System.out.println("Balance Due: " + t1.getBalanceDue());

        EventTicket[] nightlyBatch = { new GroupTicket(2000, 5), null, new EventTicket(500) };
        System.out.println(EventTicket.processNightlySettlement(nightlyBatch));
    }
}
