import java.util.Arrays;

public class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static {
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("construction rejected");
        }
        for (String code : medicationCodes) {
            if (code == null || !code.matches("^MED-[A-Z]$")) {
                throw new IllegalArgumentException("construction rejected");
            }
        }
        this.patientId = patientId;
        this.medicationCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public String getPatientId() {
        return patientId;
    }

    public String[] getMedicationCodes() {
        return Arrays.copyOf(medicationCodes, medicationCodes.length);
    }

    public DischargeSummary withCorrectedMedication(int index, String newCode) {
        if (index < 0 || index >= medicationCodes.length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        String[] updatedCodes = Arrays.copyOf(medicationCodes, medicationCodes.length);
        updatedCodes[index] = newCode;
        return new DischargeSummary(this.patientId, updatedCodes);
    }

    public static class CriticalCareDischargeSummary extends DischargeSummary {
        private final int icuDays;

        public CriticalCareDischargeSummary(String patientId, String[] medicationCodes, int icuDays) {
            super(patientId, medicationCodes);
            this.icuDays = icuDays;
        }

        public int getIcuDays() {
            return icuDays;
        }
    }

    public static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int criticalCare = 0;
        int routine = 0;

        if (summaries != null) {
            for (DischargeSummary summary : summaries) {
                if (summary == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (summary instanceof CriticalCareDischargeSummary) {
                        criticalCare++;
                    } else {
                        routine++;
                    }
                }
            }
        }

        return String.format("%d processed | %d null skipped | %d critical-care | %d routine",
                processed, nullSkipped, criticalCare, routine);
    }
}
