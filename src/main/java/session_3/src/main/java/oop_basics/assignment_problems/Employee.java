package oop_basics.assignment_problems;

public class Employee {
    private String empId;
    private String empName;
    private double salary;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public static void main(String[] args) {
        Employee plain = new Employee("EMP101", "Karan", 40000.0);
        ManagerEmployee manager = new ManagerEmployee("EMP102", "Divya", 70000.0, 8000.0);
        InternEmployee intern = new InternEmployee("EMP103", "Meera", 12000.0, 10000.0);

        Employee[] employees = {plain, manager, intern};

        for (Employee emp : employees) {
            if (emp instanceof ManagerEmployee) {
                ManagerEmployee mgr = (ManagerEmployee) emp;
                System.out.println("Manager effective pay: Rs " + mgr.effectiveSalary());
            } else if (emp instanceof InternEmployee) {
                InternEmployee itn = (InternEmployee) emp;
                System.out.println("Intern effective pay: Rs " + itn.effectiveSalary());
            } else {
                System.out.println("Plain employee pay: Rs " + emp.getSalary());
            }
        }
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    public ManagerEmployee(String empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    public double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    public InternEmployee(String empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    public double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}
