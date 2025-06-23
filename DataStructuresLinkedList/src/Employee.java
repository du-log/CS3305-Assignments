// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 2

public class Employee {
    // employeeID should be unique, so I'll use a static int to count it starting from 1.
    private int employeeID;
    private static int uniqueID = 1;
    private String name;
    private String position;
    private double salary;

    public Employee() {
    }

    // I'll just use n, pos, s for name, position, salary
    public Employee(String n, String pos, double s) {
        this.name = n;
        this.position = pos;
        this.salary = s;
        this.employeeID = uniqueID;
        uniqueID++; // Iterate the static int so each employee's ID will be unique and there won't be overlaps.
    }

    // We want to be able to get the employeeID for when we remove them from the linked list.
    public int getEmployeeID() {
        return this.employeeID;
    }

    // I'll make an Override toString to display the all the values.
    // Not sure what format it should be in, but it will display the information of an employee.
    @Override
    public String toString() {
        return "ID: " + this.employeeID + ", Name: " + this.name +
                "\nPosition: " + this.position + ", Salary: $" + this.salary;
    }
}
