package oop_basics.class_problems;

public class SrmStudent {
    private String name;
    private String regNo;
    private int attendance;

    private HostelFeeAccount feeAccount;
    private HostelRoom room;
    public static int totalStudents = 0;

    public SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    public SrmStudent(String name, String regNo, HostelFeeAccount feeAccount, HostelRoom room) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;
        totalStudents++;
    }

    public boolean isEligible() {
        return attendance >= 75;
    }

    public void addAttendanceUpdate(int newAttendance) {
        this.attendance = newAttendance;
    }

    public static double classAverage(SrmStudent[] students) {
        if (students.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (SrmStudent s : students) {
            sum += s.attendance;
        }
        return (double) sum / students.length;
    }

    public String fullStatus() {
        String roomStr = (room == null) ? "unallotted" : room.getRoomNo();
        double due = (feeAccount == null) ? 0.0 : feeAccount.getDue();
        return name + " | Due: Rs " + due + " | Room: " + roomStr;
    }

    public static void main(String[] args) {
        SrmStudent[] students = {
            new SrmStudent("Ravi", "RA01", 82),
            new SrmStudent("Anitha", "RA02", 68),
            new SrmStudent("Karthik", "RA03", 91),
            new SrmStudent("Meera", "RA04", 74),
            new SrmStudent("Suresh", "RA05", 60)
        };

        for (SrmStudent s : students) {
            String status = s.isEligible() ? "Eligible" : "Detained";
            System.out.println(s.name + " - " + s.attendance + "% - " + status);
        }

        System.out.printf("Class average: %.1f%%%n", classAverage(students));
    }
}
