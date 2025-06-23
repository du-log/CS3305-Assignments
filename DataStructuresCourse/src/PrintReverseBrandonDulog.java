// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim

import java.util.Scanner;

public class PrintReverseBrandonDulog {
    public static void main(String[] args) {
        char confirmChar; // This will take a char that will be used to end do-while loop.
        String baseString; // This will hold the entered string that will get reversed.
        Scanner scan = new Scanner(System.in); // We need a scanner for getting the inputs.

        do {
            System.out.print("Entered string: ");
            baseString = scan.nextLine();
            System.out.print("Reversed string: ");
            printCharsReverse(baseString);
            System.out.println();
            System.out.print("Try again(Y/N): ");
            confirmChar = scan.next().charAt(0); // Please use a char, not a string.
            scan.nextLine();
            if(confirmChar == 'Y') { // I assume that any test will enter Y or N, and nothing else.
                System.out.println();
            }
        } while (confirmChar != 'N');
    }

    public static void printCharsReverse(String str) {
        // First, check for an empty string in case there is no input.
        if (str.isEmpty()) { // could also do "str.length() == 0", I guess?
            return;
        }
        // Then we can print the last char in the string using charAt().
        System.out.print(str.charAt(str.length() - 1));
        // Recurse the method but we need to not reprint the same char, so we will use substring()
        // to remove the last character of the current string.
        printCharsReverse(str.substring(0, str.length() - 1));
    }
}
