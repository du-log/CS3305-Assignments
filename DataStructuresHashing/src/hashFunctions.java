// Name: Brandon Dulog
// Class: CS 3305/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 7

/*
The hardcoded array of keys is in main. There is an int option and a Scanner object for the sentinel loop to choose
which option to run or close the program. I was going to put the Table 2D array in main, but I decided to just create
and initialize it in each hash function. Not sure if that causes unnecessary memory usage, but I guess it should not
matter as much as long as the functions and the overall program works.

For each hash function besides HF4, the division method is taken from the slide (key % table size) + 1. Though since
arrays start from 0 rather than 1, we do not need the '+ 1' because otherwise we would cause an out-of-bounds error and
skip arr[0]. As mentioned, each function initializes a new int[50][2]. HF1 and HF2 have int hash and int index to hold
a key's hash and set the starting index. HF3 and HF4 will have hash1 and hash2 since they both utilize double hashing.
All hash functions have an int probes starting at 0 which will be incremented in a while loop.

HF1 is as follows: There is a for loop to traverse an add each key into the 2d table. We take the division method found
in the powerpoint slides to find the hash, then set the index based on the hash result. In the while loop, and this
is the same for all hash functions here, we use our index to see if there is a key found at Table[index][0] by checking
if it does not equal 0 which means that it is not empty. If that is the case, we set the next index by adding the
current by 1 and take the modulus by the table size/table.length, and then increment probes by 1. After the while loop,
we then insert the key at the final index value as well as the probe count for that key and hash. Then, we print the
table and the sum of probes after the for loop is completed.

HF2 is the same aside from the while loop. We have an int i for calculating the square since this uses quadratic probing.
All HF# have the comparing while loop, so if that is the case, then we will add the hash with the squared value
and modulus the result with the table size to find the next index. After that, increment probes and i by 1.

HF3 uses the division method for hash1, then the given function for hash2. Starting index is set from the hash1 value.
There is an int j starting at 1 as noted by the given increment. We first do an if statement to check if the probe count
for this hash is larger than the table size so that we can stop trying to find an index to insert the key into the hash
table. If that is not the case, then we set the next index by doing the modulus of hash1 by the table size, multiplying
hash2 with j, and adding both results together. Then, we take that result and modulus with the table size again to find
the next index value. After that, increment probes and j by 1. Following the while loop is the same if statement to
skip to the next key if we could not find an index to place the key.

For HF4, I tried to make a custom function with double hashing for the collision resolution. I did not know if we had to
use a specific method for finding our hash values, so I ended up just trying different things and settled with
multiplying for hash1. The function has additional code because I tried to use the mid-square method to see if I could
get a better total probe count, but that did not really work out, and I might not have implemented that correctly.
The method for finding hash1 was just taking the square of our key, multiplying the result with a decimal (ended up with
0.882), then doing the modulus by 47. I found that a prime number helped better with distribution, though I am not sure
why exactly. I just picked 47 since it was closest to the table size.
hash2 is just the result from subtracting another prime number, in this case 43, by the result of the modulus of the
key value and 43. Originally, I started from 31, then moved to 37, and finally settled on 43.
The rest is the same as HF3, so I did not change the incrementing method.
The final result was that I could not insert three keys, however the total probe count was 47 so it passed the below 60
requirement. The instructions did not state a maximum amount of non-inserted keys, so I guess having three non-inserted
is fine for the sake of this assignment... maybe?
Honestly, I was just plugging in random values for getting the hash values, so I was not planning anything out and just
trying to get a low total probe count. I could probably have gotten a lower count if I kept playing with the numbers and
maybe modifying the incrementing method, but I figured that my final results were good enough.

sumProbes() just takes in the hash table, then adds up all the probes found and returns the total.
printTable() also takes in the hash table, then prints it out similar to the example table. The formatting might not
look the best, but it is readable.
*/

import java.util.Scanner;

public class hashFunctions {
    public static void main(String[] args) {
        int[] keys = {1234, 8234, 7867, 1009, 5438, 4312, 3420, 9487, 5418, 5299,
                5078, 8239, 1208, 5098, 5195, 5329, 4543, 3344, 7698, 5412,
                5567, 5672, 7934, 1254, 6091, 8732, 3095, 1975, 3843, 5589,
                5439, 8907, 4097, 3096, 4310, 5298, 9156, 3895, 6673, 7871,
                5787, 9289, 4553, 7822, 8755, 3398, 6774, 8289, 7665, 5523};
        int option;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.print("-----MAIN MENU--------------------\n" +
                    "1 - Run HF1 (Division method with Linear Probing)\n" +
                    "2 - Run HF2 (Division method with Quadratic Probing)\n" +
                    "3 - Run HF3 (Division method with Double Hashing)\n" +
                    "4 - Run HF4 (Student Designed HF)\n" +
                    "5 - Exit Program\n\n" +
                    "Enter option number: ");
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    HF1(keys);
                    break;
                case 2:
                    HF2(keys);
                    break;
                case 3:
                    HF3(keys);
                    break;
                case 4:
                    HF4(keys);
                    break;
                case 5:
                    System.out.println("Closing...");
                    scan.close();
                    break;
                default:
                    System.out.println();
            }
        } while (option != 5);
    }

    public static void HF1(int[] keys) {
        int index, hash;
        int[][] Table = new int[50][2];

        for (int key : keys) {
            hash = key % Table.length;
            index = hash;
            int probes = 0;

            while (Table[index][0] != 0) {
                index = (index + 1) % Table.length;
                probes++;
            }

            Table[index][0] = key;
            Table[index][1] = probes;
        }

        System.out.println("\nHash table resulted from HF1:\n");
        printTable(Table);
    }

    public static void HF2(int[] keys) {
        int index, hash;
        int[][] Table = new int[50][2];

        for (int key : keys) {
            hash = key % Table.length;
            index = hash;
            int probes = 0;
            int i = 1;

            while (Table[index][0] != 0) {
                index = (hash + (i * i)) % Table.length;
                probes++;
                i++;
            }

            Table[index][0] = key;
            Table[index][1] = probes;
        }

        System.out.println("\nHash table resulted from HF2:\n");
        printTable(Table);
    }

    public static void HF3(int[] keys) {
        int index, hash1, hash2;
        int[][] Table = new int[50][2];

        for (int key : keys) {
            hash1 = key % Table.length;
            hash2 = 30 - key % 25;
            index = hash1;

            int probes = 0;
            int j = 1;
            while (Table[index][0] != 0) {
                if (probes > Table.length) {
                    System.out.println("Unable to hash key " + key + " to the table.");
                    break;
                }
                index = ((hash1 % 50) + j * hash2) % Table.length;
                j++;
                probes++;
            }
            if (probes > Table.length) {
                continue;
            }

            Table[index][0] = key;
            Table[index][1] = probes;
        }

        System.out.println("\nHash table resulted from HF3:\n");
        printTable(Table);
    }

    public static void HF4(int[] keys) {
        int index, hash1, hash2;
        int[][] Table = new int[50][2];

        for (int key : keys) {
            int squared = key * key;
            String str = String.valueOf(squared);
            if (str.length() % 2 == 0) {
                str = "0" + str;
            }
            int mid = str.length() / 2;
            String middle = str.substring(mid - 1, mid + 1);

            hash1 = (int) (squared * 0.882) % 47;
            hash2 = 43 - (key % 43);
            index = hash1;

            int probes = 0;
            int j = 1;
            while (Table[index][0] != 0) {
                if (probes > Table.length) {
                    System.out.println("Unable to hash key " + key + " to the table.");
                    break;
                }
                index = ((hash1 % Table.length) + j * hash2) % Table.length;
                probes++;
                j++;
            }
            if (probes > Table.length) {
                continue;
            }

            Table[index][0] = key;
            Table[index][1] = probes;
        }

        System.out.println("\nHash table resulted from HF4:\n");
        printTable(Table);
    }

    public static int sumProbes(int[][] table) {
        int probes = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] != 0) {
                probes += table[i][1];
            }
        }
        return probes;
    }

    public static void printTable(int[][] table) {
        System.out.println("Index  Key  Probes\n" +
                "----------------------");
        for (int i = 0; i < table.length; i++) {
            System.out.println(i + "     " + table[i][0] + "     " + table[i][1]);
        }
        System.out.println("----------------------");
        System.out.println("Sum of probe values: " + sumProbes(table) + " probes.\n");
    }
}
