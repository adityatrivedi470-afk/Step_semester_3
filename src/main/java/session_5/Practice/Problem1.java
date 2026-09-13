public class Problem1 {

    public static class AccessRuleEngine {

        public static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
                case "default":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
                case "protected":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        public static String summarizeBatch(String[][] attempts) {
            int allowed = 0;
            int denied = 0;
            for (String[] attempt : attempts) {
                String modifier = attempt[0];
                String context = attempt[1];
                if ("ALLOWED".equals(classifyAccess(modifier, context))) {
                    allowed++;
                } else {
                    denied++;
                }
            }
            return "Allowed: " + allowed + " | Denied: " + denied;
        }
    }

    public static class PatientRecord {
        private String patientId;
        String wardCode;
        protected double vitalsScore;
        public String facilityName;

        public PatientRecord(String patientId, String wardCode, double vitalsScore, String facilityName) {
            if (patientId == null || patientId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.patientId = patientId;
            this.wardCode = wardCode;
            this.vitalsScore = vitalsScore;
            this.facilityName = facilityName;
        }

        public String getPatientId() {
            return patientId;
        }
    }
}
