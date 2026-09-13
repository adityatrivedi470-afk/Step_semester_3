class EventTicket {
    protected double basePrice;

    public EventTicket(double basePrice) {
        this.basePrice = basePrice;
    }

    public String printTicket() {
        return "Standard Balance: " + basePrice;
    }

    public static String batchPrint(EventTicket[] tickets) {
        if (tickets == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tickets.length; i++) {
            EventTicket ticket = tickets[i];
            if (ticket == null) continue;

            sb.append(ticket.printTicket());

            if (ticket instanceof WorkshopTicket) {
                WorkshopTicket w = (WorkshopTicket) ticket;
                sb.append(" [Track via downcast: ").append(w.getTrack()).append("]");
            }
            if (i < tickets.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }
}

class WorkshopTicket extends EventTicket {
    private final String track;

    public WorkshopTicket(double basePrice, String track) {
        super(basePrice);
        this.track = track;
    }

    public String getTrack() { return track; }

    @Override
    public String printTicket() {
        return "Workshop | Track: " + track + " | Balance: " + basePrice;
    }
}

public class Problem4 {
    public static void main(String[] args) {
        EventTicket[] tickets = { new EventTicket(500), new WorkshopTicket(1200, "AI/ML") };
        System.out.println(EventTicket.batchPrint(tickets));
    }
}
