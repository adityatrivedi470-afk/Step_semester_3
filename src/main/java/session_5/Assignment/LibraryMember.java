public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;
    private boolean idIsSet = false;

    public LibraryMember() {
        this(null, null);
    }

    public LibraryMember(String name) {
        this(null, name);
    }

    public LibraryMember(String membershipId, String name) {
        if (membershipId != null) {
            this.membershipId = membershipId;
            this.idIsSet = true;
        }
        this.name = name;
        this.premiumMember = false;
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (!idIsSet) {
            this.membershipId = id;
            this.idIsSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String answer) {
        if (answer != null) {
            this.securityAnswerHash = String.valueOf(answer.hashCode());
        }
    }
}
