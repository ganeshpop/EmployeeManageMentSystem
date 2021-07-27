
import java.util.Scanner;
import java.util.TreeMap;

class Employee {
    private String name;
    private String designation;
    private double salary;
    private int age;
    private String empID;
    Scanner s = new Scanner(System.in);


    public void create(int id) {
        //Employee ID generation
        if (id < 10) this.empID = "Wil0" + id;
        else this.empID = "Wil" + id;
        //Code For Name
        while (true) {
            System.out.println("Enter Name: ");
            String InputName = s.nextLine();
            if (InputName.length() - InputName.replaceAll(" ", "").length() <= 2) {
                this.name = InputName;
                break;

            } else System.out.println("Upto two spaces are allowed");
        }
        //Code For Age
        while (true) {
            System.out.println("Enter Age: ");
            String inputAge = s.nextLine();
            if (inputAge.matches("[0-9]+")) {
                int intInputAge = Integer.parseInt(inputAge);
                if (18 <= intInputAge && intInputAge <= 60){
                    this.age = intInputAge;
                    break;
                }
                System.out.println("Age should be in the range 18 to 60");
                continue;
            } System.out.println("Only Integers are allowed");
        }


        //Code For Designation
        while (true) {
            System.out.println("Enter Designation: 1.Programmer 2.Manager 3.Tester");

            String inputDesignation = s.nextLine();
            switch (inputDesignation.toLowerCase()) {
                case "tester" -> {
                    this.salary = 25000;
                    this.designation = inputDesignation;
                }
                case "manager" -> {
                    this.salary = 35000;
                    this.designation = inputDesignation;
                }
                case "programmer" -> {
                    this.salary = 30000;
                    this.designation = inputDesignation;
                }
                default -> System.out.println("Invalid Input ");
            }
            if (this.designation == null) continue;
            break;


        }

    }
    // Code For Display
    public void display(){
        System.out.println("Employee ID: " + this.empID);
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Designation: " + this.designation);
        System.out.println("Salary: " + this.salary);
    }

    // Code For Raise Salary
    public void raiseSalary() {
        System.out.println("Current Salary is " + this.salary);
        while (true) {
            System.out.println("Enter percentage to be raised: ");
            String percentage = s.nextLine();
            if (percentage.matches("[0-9]+")) {
                double hike = Double.parseDouble(percentage);
                if (hike <= 30) {
                    this.salary += (this.salary * hike) / 100 ;
                    System.out.println("Salary after Hike is " + this.salary);
                    break;
                }
                System.out.println("Upto 30% of hike is allowed");
                continue;
            }
            System.out.println("Only Integers are allowed");
        }
    }
}

public class EmployeeList {
    private int count = 0;
    private int lastId = 0;
    final private TreeMap<Integer,Employee> emp = new TreeMap<>();
    //final private Employee[] emp = new Employee[10];
    Scanner scan = new Scanner(System.in);
    private boolean hasDisplayed = false;

    //Code for employee object creation
    private void create() {
        while (count < 10) {
            Employee e = new Employee();
            emp.put(lastId,e);
            e.create(lastId + 1);
            hasDisplayed = false;
            count++;
            lastId++;
            System.out.println("Do you want to continue: YES/NO");
            String choice = scan.nextLine();
            if (choice.equalsIgnoreCase("yes")){
                if (count < 10) {
                    continue;
                } else {
                    System.out.println("Employees List is Full!");
                    break;
                }
            }
            break;
        }
        if (count == 10) System.out.println("Cannot Create Employees List is Full");

    }

    //Code for displaying employees
    private void display(){
        for (Integer e:emp.keySet()) {
            if(count == 0) {
                System.out.println("Please Create Employees before Displaying.");
                break;
            }
            if (emp.get(e) != null) {
                System.out.println("-----------------------");
                emp.get(e).display();
                hasDisplayed = true;
                System.out.println("-----------------------");
            }
        }
        System.out.println("Total Employees :" + this.count );
    }

    //Code for getting Id
    private int getId(){
        while (true) {
            System.out.println("Enter Employee ID: ");
            String selectedEmp = scan.nextLine();
            String[] split = selectedEmp.toLowerCase().split("l");
            if(split.length < 2) {
                System.out.println("Enter a valid Employee ID (Eg:'Wil4')");
                continue;
            }
            if (split[1].matches("[0-9]+")) {
                int index = Integer.parseInt(split[1]) - 1;
                if (0 <= index && index < 10 && emp.get(index) != null)
                    return index;
                else System.out.println("Enter a valid Employee ID (Eg:'Wil4')");
                continue;
            }
            System.out.println("Enter a valid Employee ID (Eg:'Wil4')");
        }
    }

    //Code to Raise Salary
    private void raiseSalary() {
        if(hasDisplayed) emp.get(getId()).raiseSalary();
        else if(count == 0) System.out.println("No Employees are created yet");
        else System.out.println("Employee list has been updated please display it before raising salary");
    }

    //Code to Delete Employee
    private void deleteEmployee() {
        if(hasDisplayed) {
            emp.remove(getId());
            System.out.println("Employee Successfully Removed");
            count--;
        }
        else if(count == 0) System.out.println("No Employees are created yet");
        else System.out.println("Employee list has been updated please display it before deleting employee");
    }

    public static void main(String[] args) {
        EmployeeList list = new EmployeeList();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu - \t1.Create\t2.Display\t3.Raise Salary\t4.Delete\t5.Exit");
            String selection = scanner.nextLine();
            if (selection.matches("[0-9]+")) {
                if (Integer.parseInt(selection) == 5) break;
                switch (Integer.parseInt(selection)) {
                    case 1 -> list.create();
                    case 2 -> list.display();
                    case 3 -> list.raiseSalary();
                    case 4 -> list.deleteEmployee();
                    default -> System.out.println("Invalid Input");
                }

            } else System.out.println("Only Integers are supported");
        }
    }



}



