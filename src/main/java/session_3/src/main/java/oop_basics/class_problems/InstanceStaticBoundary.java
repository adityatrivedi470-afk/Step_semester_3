package oop_basics.class_problems;

public class InstanceStaticBoundary {

    static class BrokenSrmStudent {
        static String name;
        static String regNo;
        static int attendance;

        BrokenSrmStudent(String name, String regNo, int attendance) {
            BrokenSrmStudent.name = name;
            BrokenSrmStudent.regNo = regNo;
            BrokenSrmStudent.attendance = attendance;
        }
    }

    static class SrmStudentModel {
        String name;
        String regNo;
        int attendance;

        static String university = "SRM";
        static int admissionCount = 0;

        SrmStudentModel(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            admissionCount++;
            this.regNo = "RA23110030101" + admissionCount;
        }

        void printIdCard() {
            System.out.println(name + " | " + regNo + " | " + university);
        }

        static void printTotalAdmissions() {
            System.out.println("Students admitted so far: " + admissionCount);
        }
    }

    public static void main(String[] args) {
        BrokenSrmStudent b1 = new BrokenSrmStudent("Ravi", "RA01", 85);
        BrokenSrmStudent b2 = new BrokenSrmStudent("Meera", "RA02", 90);
        System.out.println(BrokenSrmStudent.name);
        System.out.println(BrokenSrmStudent.name);
        System.out.println("(Ravi's data was overwritten — both students now show “Meera”)");

        SrmStudentModel s1 = new SrmStudentModel("Ravi", 85);
        SrmStudentModel s2 = new SrmStudentModel("Meera", 90);
        s1.printIdCard();
        s2.printIdCard();
        SrmStudentModel.printTotalAdmissions();
    }
}
