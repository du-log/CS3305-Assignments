// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim

import java.util.Scanner;

public class AverageGradeBrandonDulog {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); // We need a scanner, or we can't do anything, right?
        int[] classArray; // Will initialize after an integer for the size is passed in the loop.
        int endLoop; // Well, it doesn't end the do-while loop until the value passed is 0, but I digress.

        do {
            System.out.print("Class size: ");
            classArray = new int[scan.nextInt()]; // Initialize using the integer value
            scan.nextLine();
            System.out.print("Entered grades: ");
            for (int i = 0; i < classArray.length; i++) {
                classArray[i] = scan.nextInt();
                scan.nextLine();
            }
            System.out.print("Class average: " + computeAverage(classArray, 0, 0)
                    + "\nEnter 0 to end, any other value to continue: ");
            endLoop = scan.nextInt();
            scan.nextLine();
        } while(endLoop != 0);
    }

    // There is another method below that works, the only differences are that the other method implements the spoiler
    // found on the Assignment 1 PDF and takes a double for the average rather than an int for the sum of the array.
    public static double computeAverage(int[] arr, int index, int sum) {
        // Base case 1: If the array is only of length 1, then just return the sole integer.
        if(arr.length == 1) {
            return arr[index];
        }
        // Base case 2: If the index value equals the array length, then end method by dividing the sum by the
        // current index value and returning the result as a double.
        if(index == arr.length) {
            return (double) sum / index;
        }
        // Add the value at the index position in the array to the sum.
        sum += arr[index];
        // Recurse the method by increasing the current index value by one and taking in the new sum value.
        return computeAverage(arr, index + 1, sum);
    }

    /* public static double computeAverage(int[] arr, int index, double average) {
        if(arr.length == 1) {
            return arr[index];
        }
        if(index == arr.length) {
            return average;
        }
        average = (average * index + arr[index]) / (index + 1);
        return computeAverage(arr, index + 1, average);
    } */
}
