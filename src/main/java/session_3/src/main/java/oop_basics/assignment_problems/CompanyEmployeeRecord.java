package oop_basics.assignment_problems;

public class CompanyEmployeeRecord {
    private String name;
    private String empId;
    private Employee employee;
    private ParkingSlot slot;

    public static int totalRecords = 0;

    public CompanyEmployeeRecord(String name, String empId, Employee employee, ParkingSlot slot) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;
        totalRecords++;
    }

    public String fullProfile() {
        double pay;
        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotStr = (slot == null) ? "no parking assigned" : slot.getSlotNo();
        return name + " | Pay: Rs " + pay + " | Slot: " + slotStr;
    }

    public static void main(String[] args) {
        ParkingSlot s1 = new ParkingSlot("A1", 4, 3);
        ParkingSlot s2 = new ParkingSlot("A2", 5, 4);
        ParkingSlot[] slots = {s1, s2};

        ManagerEmployee divyaEmp = new ManagerEmployee("EMP101", "Divya", 70000.0, 8000.0);
        Employee karanEmp = new Employee("EMP102", "Karan", 40000.0);
        InternEmployee meeraEmp = new InternEmployee("EMP103", "Meera", 12000.0, 10000.0);

        ParkingSlot allotted1 = ParkingSlot.findAvailableSlot(slots);
        if (allotted1 != null) {
            allotted1.allot("TN01AA1111");
        }

        ParkingSlot allotted2 = ParkingSlot.findAvailableSlot(slots);
        if (allotted2 != null) {
            allotted2.allot("TN02BB2222");
        }

        ParkingSlot allotted3 = null;

        CompanyEmployeeRecord r1 = new CompanyEmployeeRecord("Divya", "EMP101", divyaEmp, allotted1);
        CompanyEmployeeRecord r2 = new CompanyEmployeeRecord("Karan", "EMP102", karanEmp, allotted2);
        CompanyEmployeeRecord r3 = new CompanyEmployeeRecord("Meera", "EMP103", meeraEmp, allotted3);

        System.out.println(r1.fullProfile());
        System.out.println(r2.fullProfile());
        System.out.println(r3.fullProfile());
        System.out.println("Total records: " + CompanyEmployeeRecord.totalRecords);
    }
}
