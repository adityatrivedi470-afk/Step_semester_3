public class PatientProfile {
    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPinHash;
    private boolean idIsSet = false;

    public PatientProfile() {
        this(null, null);
    }

    public PatientProfile(String name) {
        this(null, name);
    }

    public PatientProfile(String patientId, String name) {
        if (patientId != null) {
            this.patientId = patientId;
            this.idIsSet = true;
        }
        this.name = name;
        this.discharged = false;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String id) {
        if (!idIsSet) {
            this.patientId = id;
            this.idIsSet = true;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDischarged() {
        return discharged;
    }

    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    public void setLockerPin(String pin) {
        if (pin != null && pin.matches("\\d{4,6}")) {
            this.lockerPinHash = String.valueOf(pin.hashCode());
        }
    }
}
