// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 2

// I will just include the methods addEmployee(...), deleteEmployee(...), displayEmployees()

public class EmployeeLinkedList {

    // I'll implement a Node class rather than use the library for the Linked List.
    // I'll just have it inside this class rather than make it its own.
    private static class Node {
        // Need to store an Employee object and a Node for next
        Employee emp;
        Node next;

        Node(Employee e) {
            this.emp = e;
            this.next = null;
        }
    }

    // Originally tried working with a tail node, but gave up and removed it. See deleteEmployee()
    private Node head;

    public EmployeeLinkedList() { // Make an empty Linked List
        this.head = null;
    }

    // In the menu-driven system, I'll take inputs for the name, position, salary of the Employee,
    // then just do addEmployee(new Employee(...));
    public void addEmployee(Employee e) {
        Node newNode = new Node(e);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) { // Traverse until we reach the last non-null node.
                current = current.next;
            }
            current.next = newNode; // Set that node's next node as the new node with the new Employee object.
        } // The next node for the newNode will be the new null.
        System.out.println("Employee added to roster.\n");
    }

    // I attempted to make a case for when the employee of given ID is in the tail node, but
    // I could not make it work properly and displayEmployees still showed the given employee.
    // I also did a try-catch just in case, but I realized that there was no need
    // for it when I never reached an exception, at least from the tests I tried.
    public void deleteEmployee(int employeeID) {
        // Case 1: No employees in the list
        if (head == null) {
            System.out.println("There are no employees found on the roster.\n");
            return;
        }
        // Case 2: Employee is in the head node, comparing the employee's ID with the input ID
        if (head.emp.getEmployeeID() == employeeID) {
            head = head.next;
            System.out.println("Successfully removed employee of ID " + employeeID +".\n");
            return;
        }
        // Case 3: EmployeeID found somewhere else in the linked list. Otherwise, will print not found.
        Node current = head;
        // Move to next node while still available AND the employee ID != the input ID
        while (current.next != null && current.next.emp.getEmployeeID() != employeeID) {
            current = current.next;
        }
        // When we find the same employee ID as the input, current becomes next node's next
        if (current.next != null) {
            // Overwrites with next.next node, just goes null if it's the last node in the list. nullNode.next = null
            current.next = current.next.next;
            System.out.println("Successfully removed employee of ID " + employeeID + ".\n");
        } else {
            System.out.println("Employee not found.\n"); // When we reach the end of the list and an ID != input ID
        }
    }

    // As linked lists have to always start from the head node, this starts from the head node and prints the
    // information of each employee until current == null. This also will print that there are no employees
    // when the linked list is empty.
    public void displayEmployees() {
        if (head == null) {
            System.out.println("There are no employees found on the roster.\n");
            return;
        }
        Node current = head; // Start from the head
        System.out.println("List of Employees:");
        while (current != null) { // Print the info of the node's Employee object until we reach last non-null node.
            System.out.println(current.emp); // See overridden toString() in Employee class
            current = current.next;
        }
        System.out.println();
    }
}
