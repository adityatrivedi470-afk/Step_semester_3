class RaceEntry {
    private final String bibNumber;
    protected double entryFee;

    public RaceEntry(String bibNumber, double entryFee) {
        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
    }

    public String getBibNumber() { return bibNumber; }

    public String announce() {
        return "Race Entry | Bib: " + bibNumber + " | Balance: " + entryFee;
    }

    public static String announceAll(RaceEntry[] entries) {
        if (entries == null) return "";
        StringBuilder sb = new StringBuilder();
        for (RaceEntry entry : entries) {
            if (entry == null) continue;

            sb.append(entry.announce());

            if (entry instanceof RelayTeamEntry) {
                RelayTeamEntry relay = (RelayTeamEntry) entry;
                sb.append(" [Team size via downcast: ").append(relay.getTeamSize()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }
}

class RunnerEntry extends RaceEntry {
    private final String category;

    public RunnerEntry(String bibNumber, double entryFee, String category) {
        super(bibNumber, entryFee);
        this.category = category;
    }

    @Override
    public String announce() {
        return "Runner Entry | Bib: " + getBibNumber() + " | Category: " + category + " | Balance: " + entryFee;
    }
}

class RelayTeamEntry extends RaceEntry {
    private final int teamSize;

    public RelayTeamEntry(String bibNumber, double entryFee, int teamSize) {
        super(bibNumber, entryFee);
        this.teamSize = teamSize;
    }

    public int getTeamSize() { return teamSize; }

    @Override
    public String announce() {
        return "Relay Team | Bib: " + getBibNumber() + " | Team Size: " + teamSize + " | Balance: " + entryFee;
    }
}

public class Problem4 {
    public static void main(String[] args) {
        RunnerEntry runner = new RunnerEntry("BIB2001", 90, "Open 10K");
        RelayTeamEntry relay = new RelayTeamEntry("BIB4001", 300, 4);

        RaceEntry[] fleet = { runner, relay };
        System.out.println(RaceEntry.announceAll(fleet));
    }
}
