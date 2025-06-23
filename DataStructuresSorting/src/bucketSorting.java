// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 5

// For the bucket sort, I will have a hardcoded integer array for a consistent showing that the
// bucket sort function works.
// I also have another array that will take a randomArray function. The function will take in a
// specified length and a Random object. These are commented out, but are available for use.
// I will use recursion for the sorting method.
// The instructions do not mention using a sentinel loop and inputting the length and integers, so
// if we were supposed to make the arrays ourselves using Scanner... well, clarify that in
// the instructions, please.

// The bucket sort will take in an integer array, an integer representing the current exponent,
// and the maximum integer of said array. It will be based on how we learned it in lecture when sorting
// manually, so it'll make a stack of 10 buckets. The base case will be assuming an empty integer array.
// Another case will take the maximum integer in the array and divide it by the exponent, then
// end the sort if the integer equals 0. In this case, it should divide 5120 by 10000, and
// that will end up with a number below 1, so removing/truncating the decimal = 0.

// From here on, stack = bucket.

// The process begins with distributing the elements to their respective buckets. We divide the element
// by the exponent, then modulus the result by 10 to find the bucketNumber in the bucket array.
// Using that bucketNumber, we push the element at i into the corresponding bucket.
// For example: 5120 / 10 = 512 % 10 = 2, then we push 5120 into bucket[2].
// This part of the method uses a for loop, starting int i at the end of the array and iterating down
// until we reach and push arr[0].

// The next step is collecting all the elements from their respective buckets. We use an integer arrIndex
// for reassigning the array position with the new element based on how we distributed them in that cycle.
// Using exponent 1 cycle, for example, we pop the elements in bucket[0]. For arrIndex of 0 / arr[0],
// the element will be 100. No change, but after popping the next element in bucket[0] and increasing to arr[1],
// the element will now be 650 instead of 62.
// This will continue until all the buckets are empty.

// We have a println that will print the array based on the exponent cycle, so after exponent of 1, of 10, etc.
// It helps visualize what the array looks like after each cycle if you were to manually sort by hand.
// You can comment it out if you want.

// We then recurse the bucketSort method until the max / exponent = 0.

import java.util.Random;
import java.util.Stack;

public class bucketSorting {
    public static void main(String[] args) {
        // Random random = new Random();
        // int[] arrRand = randomArray(30, random); // random array
        int[] arr = {100, 62, 567, 2444, 12, 9, 852, 23, 95, 902, 4503, 650, 423, 5120, 55};
        int exponent = 1;

        System.out.println("Unsorted Array: " + printArray(arr) +"\n");
        bucketSort(arr, exponent, findMaxVal(arr));
        System.out.println("\nSorted Array: " + printArray(arr));
        /* System.out.println("----------");
        System.out.println("Unsorted Random Array: " + printArray(arrRand) + "\n");
        bucketSort(arrRand, exponent, findMaxVal(arrRand));
        System.out.println("\nSorted Random Array: " + printArray(arrRand)); */
    }

    // Bucket/Radix Sorting Method
    public static void bucketSort(int[] arr, int exponent, int max) {
        if (arr.length == 0) return; //If the array is a length of 0
        if (max / exponent == 0) return; // Determine when we completely finish the sorting

        Stack<Integer>[] buckets = new Stack[10]; // Making our stacks/buckets
        for(int i = 0; i < buckets.length; i++) {
            buckets[i] = new Stack<>();
        }

        for (int i = arr.length - 1; i >= 0; i--) { // Using stacks, so read array from end to start
            int bucketNumber = (arr[i] / exponent) % 10;
            buckets[bucketNumber].push(arr[i]);
        }

        int arrIndex = 0;
        for (int i = 0; i < buckets.length; i++) { // Sorting array based on the current exponent cycle
            while (!buckets[i].isEmpty()) {
                arr[arrIndex] = buckets[i].pop();
                arrIndex++;
            }
        }
        System.out.println("After Exponent of " + exponent + ": " + printArray(arr)); // Optional println

        bucketSort(arr, exponent * 10, max); // Recurse
    }

    // Find largest value in integer array
    public static int findMaxVal(int[] arr) {
        int max = arr[0];
        for (int n : arr) {
            if (n > max) {
                max = n;
            }
        }
        return max;
    }

    // Make a random integer array of specified length
    /* public static int[] randomArray(int length, Random random) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000001); //From 0 to 1,000,000
        }
        return arr;
    } */

    // Make string of array using StringBuilder
    public static String printArray(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            if(i == arr.length - 1) {
                builder.append(arr[i]);
            } else {
                builder.append(arr[i]).append(", ");
            }
        }
        return builder.toString();
    }
}
