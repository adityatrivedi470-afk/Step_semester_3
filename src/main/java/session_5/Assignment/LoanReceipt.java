import java.util.Arrays;

public class LoanReceipt {
    private final String memberId;
    private final String[] bookIds;

    static {
    }

    public LoanReceipt(String memberId, String[] bookIds) {
        if (bookIds == null) {
            throw new IllegalArgumentException("construction rejected");
        }
        for (String id : bookIds) {
            if (id == null || !id.matches("^BK-\\d{3}$")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }
        this.memberId = memberId;
        this.bookIds = Arrays.copyOf(bookIds, bookIds.length);
    }

    public String getMemberId() {
        return memberId;
    }

    public String[] getBookIds() {
        return Arrays.copyOf(bookIds, bookIds.length);
    }

    public LoanReceipt withCorrectedBookId(int index, String newId) {
        if (index < 0 || index >= bookIds.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String[] updatedIds = Arrays.copyOf(bookIds, bookIds.length);
        updatedIds[index] = newId;
        return new LoanReceipt(this.memberId, updatedIds);
    }

    public static class ReferenceOnlyLoanReceipt extends LoanReceipt {
        private final String roomNumber;

        public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
            super(memberId, bookIds);
            this.roomNumber = roomNumber;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnly++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return String.format("%d processed | %d null skipped | %d reference-only | %d regular",
                processed, nullSkipped, referenceOnly, regular);
    }
}
