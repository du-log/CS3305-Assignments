// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 5

// Since this part requires user input for the array size and the array elements,
// I have a scanner object. I also have two ints: one is check which will help with
// the do-while loop (not sure if this was required, but I added it anyway); the other
// is size which will be used for getting the size of the array.

// The sorting is split into two functions: one is mergeSort which be constitute the
// actual splitting of the array into subarrays; the other is merge which will be the
// merging part of the sort. mergeSort will take in our array, integer 0 for the first
// element of the original array, and the last element in the array. In this case, I used
// arr.length - 1 to indicate the last element.
// Inside mergeSort, we have an if statement as the base case where if int left is greater
// than or equal to int right, then we can stop splitting. Though, int left likely will not be
// greater than int right anyway, but that is just in case. The recursion needs to break the
// array and subsequent subarrays into two parts until they're all individual segments, so
// we have to set a midpoint for making the left and right subarrays.

// The merge function is, in a way, being queued as mergeSort recurses to break apart the array.
// The function then initializes when we hit pairs of elements as once those split, the mergeSort
// function ends because the base case occurs due to the left and right ints being the same.

// For the merging part, we create two temporary arrays for the left and right subarrays. These will
// hold elements from their respective position. Then we create three more ints: i, j, k. i and j
// will equal 0 to indicate their respective first elements in their arrays. k will equal int left
// as this will indicate where in the original array the elements will be sorted, so say we are merging
// the last two elements, arr[6] and arr[7], then left will equal 6.
// We then do a while loop while both ints are still within bounds of their respective arrays.
// In this loop, we compare the i and j elements, and then set arr[k] as whatever is the smallest element.
// We then increment both k and the int of the smaller element until the while loop condition is false.
// Then we have two more while loops in case there are still elements in a temporary array that have
// not been merged.

// After one merge is complete, another merge will happen until all merge functions have completed.

// Based on int left, we can say that in an array of 8 elements, we first do pairs so left will equal
// 0, 2, 4 and 6. Once (0,1), (2,3), (4,5), and (6,7) have been sorted, then left will be 0 and 4.
// Those merges will happen, and then we'll have (0,1,2,3) and (4,5,6,7). The last merge will start from 0,
// and then we'll have a complete sorted array after the function is complete, which then ends the
// base iteration of the mergeSort function.

// After writing this, I realized that I might have messed up on the merge sort in the quiz. Whoops.

import java.util.Scanner;

public class mergeSorting {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int check = 1;
        int size;

        do {
            System.out.print("Enter the size of the array (1-1000): ");
            size = scan.nextInt();
            scan.nextLine();
            while (size < 1 || size > 1000) {
                System.out.print(" Invalid size, please enter a number between 1 and 1000: ");
                size = scan.nextInt();
                scan.nextLine();
            }

            int[] arr = new int[size];
            System.out.println("Enter the elements in the array of size " + size +
                    ".\nElement must be between 1 and 10000:");
            for(int i = 0; i < arr.length; i++) {
                System.out.print("Element " + (i) + ": ");
                arr[i] = scan.nextInt();
                scan.nextLine();
                while(arr[i] < 1 || arr[i] > 10000) {
                    System.out.print("Invalid element, must be between 1 and 10000: ");
                    arr[i] = scan.nextInt();
                    scan.nextLine();
                }
            }

            System.out.println("Unsorted Array: " + printArray(arr));
            mergeSort(arr, 0, arr.length - 1);
            System.out.println("Sorted Array: " + printArray(arr));
            System.out.print("Enter 0 to quit, any other number to restart: ");
            check = scan.nextInt();
            scan.nextLine();
        } while (check != 0);
        scan.close();
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(arr, left, middle);
        mergeSort(arr, middle + 1, right);

        mergeArray(arr, left, middle, right);
    }

    public static void mergeArray(int[] arr, int left, int middle, int right) {
        int leftArrSize = middle - left + 1;
        int rightArrSize = right - middle;

        int[] leftArr = new int[leftArrSize];
        int[] rightArr = new int[rightArrSize];

        for (int i = 0; i < leftArrSize; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightArrSize; i++) {
            rightArr[i] = arr[middle + 1 + i];
        }

        int i = 0, j = 0, k = left;
        while (i < leftArrSize && j < rightArrSize) {
            if(leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                k++;
                i++;
            } else {
                arr[k] = rightArr[j];
                k++;
                j++;
            }
        }

        while (i < leftArrSize) {
            arr[k] = leftArr[i];
            k++;
            i++;
        } while (j < rightArrSize) {
            arr[k] = rightArr[j];
            k++;
            j++;
        }
    }

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
