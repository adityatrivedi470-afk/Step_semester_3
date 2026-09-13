package oop_basics.class_problems;

public class FeeHostelCapstone {

    public static void main(String[] args) {
        HostelRoom r1 = new HostelRoom("C-214", 3, 2);
        HostelRoom r2 = new HostelRoom("C-507", 2, 1);
        HostelRoom[] rooms = {r1, r2};

        HostelFeeAccount acc1 = new HostelFeeAccount("RA231100301011", 200000.0, 60000.0);
        HostelFeeAccount acc2 = new HostelFeeAccount("RA231100301012", 200000.0, 20000.0);
        HostelFeeAccount acc3 = new HostelFeeAccount("RA231100301013", 200000.0, 0.0);

        acc1.pay(-5000.0);

        HostelRoom allotted1 = HostelRoom.findAvailableRoom(rooms);
        if (allotted1 != null) {
            allotted1.allot("Ravi");
        }

        HostelRoom allotted2 = HostelRoom.findAvailableRoom(rooms);
        if (allotted2 != null) {
            allotted2.allot("Anitha");
        }

        HostelRoom allotted3 = null;

        SrmStudent s1 = new SrmStudent("Ravi", "RA231100301011", acc1, allotted1);
        SrmStudent s2 = new SrmStudent("Anitha", "RA231100301012", acc2, allotted2);
        SrmStudent s3 = new SrmStudent("Karthik", "RA231100301013", acc3, allotted3);

        System.out.println(s1.fullStatus());
        System.out.println(s2.fullStatus());
        System.out.println(s3.fullStatus());
        System.out.println("Total students: " + SrmStudent.totalStudents);
    }
}
