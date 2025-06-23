// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 2

import java.util.Scanner;

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        EmployeeLinkedList employeesList = new EmployeeLinkedList(); //Initialize the LinkedList
        int selector; // Will use this to determine what option to pick, or just end the do-while loop

        System.out.println("Opening Dashboard");
        do {
            System.out.print("System Options:" +
                    "\n1) Add an employee to the roster" +
                    "\n2) Delete an employee from the roster" +
                    "\n3) Display all employees on the roster" +
                    "\n0) Close Dashboard" +
                    "\nEnter: ");
            selector = scan.nextInt();
            scan.nextLine();

            // I'm using a switch statement for choosing which option to initiate.
            // I originally wanted to make case 1 its own method, but as far as I know it would require
            // initializing the scanner outside of main() and setting it as a static, probably private it too.
            // Perhaps, I can just make a new scanner inside the method, but the way it is implemented here
            // still works fine.
            switch (selector) {
                case 1:
                    String name, position;
                    double salary;
                    System.out.print("Employee Name: ");
                    name = scan.nextLine();
                    System.out.print("Employee's Position: ");
                    position = scan.nextLine();
                    System.out.print("Employee's Salary: $");
                    salary = scan.nextDouble();
                    scan.nextLine();
                    while (salary <= 0) { // Assuming a salary cannot also be $0.00
                        System.out.print("Employee's Salary cannot be a negative amount or $0." +
                                "\nRe-enter salary: $");
                        salary = scan.nextDouble();
                        scan.nextLine();
                    }
                    employeesList.addEmployee(new Employee(name, position, salary));
                    break;
                case 2:
                    System.out.print("Enter the ID of the employee to remove from the roster: ");
                    employeesList.deleteEmployee(scan.nextInt());
                    scan.nextLine();
                    break;
                case 3:
                    employeesList.displayEmployees(); // See displayEmployees() in EmployeeLinkedList
                    break;
                case 0:
                    System.out.println("Closing Dashboard.");
                    break;
                default: // I guess if you enter some other number, then we can just re-print the options.
                    System.out.println("Not a valid input.\n");
                    break;
            }
        } while (selector != 0);
    }
}

/*
If I could improve anything or even make additions, I would do setters for the Employee class to set new values for the
salary and position. I guess the salary value could have a decimal format included when I do the toString so that it
would say $40.00 rather than $40.0. Also stated in one of my comment lines, but I would've liked to change case 1 to a
method rather than throw the instructions into the case. I'm not sure how to deal with another scanner, though, and
perhaps I had worked with it in the past by closing it or something at the end of the method before the return. I also
would have liked to implement the tail node and fix the delete method to reassign the tail properly and delete the
original tail.
One challenge I had was getting my print statements correct when a node was deleted. It was quite obvious and simple,
but it didn't click for me that Node current in the method would be reassigned until the while loop stopped. Then, I
could run the print statement after that current.next got reassigned to either another node or a null node, whichever
was in current.next.next.
 */
