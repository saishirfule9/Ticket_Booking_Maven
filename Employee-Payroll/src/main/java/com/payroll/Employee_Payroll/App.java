package com.payroll.Employee_Payroll;

import java.util.*;

class Employee {
    private int id;
    private String name;
    private double basicSalary;
    private double hra;
    private double da;
    private double deductions;

    public Employee(int id, String name, double basicSalary, double hra, double da, double deductions) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.deductions = deductions;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double calculateNetSalary() {
        return (basicSalary + hra + da) - deductions;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Net Salary: " + calculateNetSalary();
    }
}

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Employee Payroll System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 4);
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Basic Salary: ");
        double basic = sc.nextDouble();
        System.out.print("Enter HRA (House Rent Allowance): ");
        double hra = sc.nextDouble();
        System.out.print("Enter DA (Dearness Allowance): ");
        double da = sc.nextDouble();
        System.out.print("Enter Deductions: ");
        double deductions = sc.nextDouble();

        employees.add(new Employee(id, name, basic, hra, da, deductions));
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records available.");
            return;
        }
        System.out.println("\n--- Employee List ---");
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private static void searchEmployee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();
        for (Employee e : employees) {
            if (e.getId() == id) {
                System.out.println("Employee found: " + e);
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
