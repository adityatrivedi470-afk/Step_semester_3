public class Problem1 {

    public static class MembershipChecker {

        public static String classifyAccess(String fieldModifier, String accessorContext) {
            switch (fieldModifier) {
                case "private":
                    return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
                case "default":
                case "protected":
                    return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
                case "public":
                    return "ALLOWED";
                default:
                    return "DENIED";
            }
        }

        public static String summarizeByModifier(String[][] attempts) {
            int privAllowed = 0, privDenied = 0;
            int defAllowed = 0, defDenied = 0;
            int protAllowed = 0, protDenied = 0;
            int pubAllowed = 0, pubDenied = 0;

            for (String[] attempt : attempts) {
                String modifier = attempt[0];
                String context = attempt[1];
                boolean isAllowed = "ALLOWED".equals(classifyAccess(modifier, context));

                switch (modifier) {
                    case "private":
                        if (isAllowed) privAllowed++; else privDenied++;
                        break;
                    case "default":
                        if (isAllowed) defAllowed++; else defDenied++;
                        break;
                    case "protected":
                        if (isAllowed) protAllowed++; else protDenied++;
                        break;
                    case "public":
                        if (isAllowed) pubAllowed++; else pubDenied++;
                        break;
                }
            }

            return String.format("private: %d allowed / %d denied | default: %d allowed / %d denied | protected: %d allowed / %d denied | public: %d allowed / %d denied",
                    privAllowed, privDenied, defAllowed, defDenied, protAllowed, protDenied, pubAllowed, pubDenied);
        }
    }

    public static class LibraryMember {
        private String membershipId;
        String branchCode;
        protected double finesOwed;
        public String displayName;

        public LibraryMember(String membershipId, String branchCode, double finesOwed, String displayName) {
            if (membershipId == null || membershipId.trim().length() < 4) {
                throw new IllegalArgumentException("construction rejected");
            }
            this.membershipId = membershipId;
            this.branchCode = branchCode;
            this.finesOwed = finesOwed;
            this.displayName = displayName;
        }

        public String getMembershipId() {
            return membershipId;
        }
    }
}
