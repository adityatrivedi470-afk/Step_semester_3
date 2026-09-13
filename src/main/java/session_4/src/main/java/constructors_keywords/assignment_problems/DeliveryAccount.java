package constructors_keywords.assignment_problems;

public class DeliveryAccount {
    private static double defaultMinSurgePercent;

    static {
        defaultMinSurgePercent = 1.0;
    }

    private String studentId;
    private double orderValue;
    private SurgeFeeCalculator calculator;

    public DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
        this.calculator = new SurgeFeeCalculator(defaultMinSurgePercent);
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0.0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        return calculator.calculateSurgeFee(orderValue, delayMinutes);
    }

    public String getStudentId() {
        return studentId;
    }

    public double getOrderValue() {
        return orderValue;
    }

    public void processAccount(DeliveryAccount account, double amount, int delayMinutes) {
        if (account != null) {
            account.calculateSurgeFee(delayMinutes);
        }
    }

    public static void processBatch(DeliveryAccount[] accounts, double[] amounts, int[] delayMinutesArray) {
        if (accounts == null || amounts == null || delayMinutesArray == null) {
            return;
        }

        int length = Math.min(accounts.length, Math.min(amounts.length, delayMinutesArray.length));
        int processed = 0;
        int nullSkipped = 0;
        int premiumCount = 0;
        int regularCount = 0;
        double grandTotalSurgeFees = 0.0;

        for (int i = 0; i < length; i++) {
            DeliveryAccount acc = accounts[i];
            if (acc == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (acc instanceof Premium) {
                premiumCount++;
            } else {
                regularCount++;
            }

            double fee = acc.calculateSurgeFee(delayMinutesArray[i]);
            grandTotalSurgeFees += fee;
        }

        grandTotalSurgeFees = Math.round(grandTotalSurgeFees * 100.0) / 100.0;
        System.out.println(processed + " processed | " + nullSkipped + " null skipped | "
                + premiumCount + " premium | " + regularCount + " regular | grand total surge fees = " + grandTotalSurgeFees);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };
        double[] amounts = {500, 400, 300};
        int[] delayMinutesArray = {10, 5, 0};

        processBatch(accounts, amounts, delayMinutesArray);
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}
