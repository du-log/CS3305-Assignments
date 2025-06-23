// Name: Brandon Dulog
// Class: CS/Section 03
// Term: Spring 2025
// Instructor: Umama Tasnim
// Assignment: 4

/*
I have a static class Queue utilizing a Linked List for implementing enqueue() and dequeue(). isEmpty() is isEmpty().
This isn't really necessary since the interface Queue will do the same thing, the methods just have different naming.

I have a Scanner for the inputs, an integer choice for the loop and selection, and a switch statement for the options.

The methods for the palindrome comparing are characterPalindrome and wordPalindrome, respectively. Both take in a
string that we want to check if it's a palindrome. Within both methods, we have a String base that will take the input
string and do a replaceAll() function. I had to search up the regex, but using [^a-zA-Z0-9] for the regex will save
all the letters in the string while replacing/removing the symbols using ""; this is for characterPalindrome(...).
For wordPalindrome(...), replaceAll() is identical but includes a space [^a-zA-Z0-9 ] to separate the words later.
*I included 0-9 because I did not know if digit palindromes should also be tested, so I put it just in case.*

Both methods contain an array; characterPalindrome has a char array and wordPalindrome has a string array.
The char array will take in the input string for characterPalindrome. The input will get rewritten to lower-case,
then converted to the char array.
The string array will take in the String base in wordPalindrome. base will be rewritten to lower-case,
then is converted to the string array using string.split(" "). The original String base is not changed, but does
get rewritten to lower-case later for comparison. Most likely is redundant and could just be done at the start.

Finally, I initialize a Queue in both of the methods and a StringBuilder.

characterPalindrome contains a for loop checking each char c in charArray. It will check if c is a letter or digit,
then enqueue c if it is. After the for loop is finished, we run a while loop while the queue is not empty. Inside the
while loop, we will append the character that is dequeued into the StringBuilder. Then, we set 'base' to lower-case,
and reverse the StringBuilder with reverse(). Finally, we use an if-else statement to compare the two strings with
base.equals(StringBuilder.toString()) and print their corresponding judgments.

wordPalindrome contains a for loop that will enqueue each word in stringArray into the queue. It will then do a
while loop, and while the queue is not empty we will insert each word into the StringBuilder, putting each one at
the front by setting the offset to 0. We also add a space " " to the inserted word. 'base' will then be set to
lower-case and also trimmed in case there were extra spaces at the end of the input string. Finally, we compare the
strings in an if-else statement using base.equals(StringBuilder.toString().trim()) and print their corresponding
judgments. I trim the StringBuilder.toString() as there will be a trailing space " " at the end, and we do not want
that included when doing the comparison.
*/

import java.util.Scanner;
import java.util.LinkedList;

public class testPalindrome {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int choice;

        do {
            System.out.print("-----MAIN MENU-----\n" +
                    "1. Test character-by-character palindrome\n" +
                    "2. Test word-by-word palindrome\n" +
                    "3. Exit program\n" +
                    "\n" +
                    "Enter option number: ");
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("You selected: Option 1");
                    System.out.print("You entered: ");
                    characterPalindrome(scan.nextLine());
                    break;
                case 2:
                    System.out.println("You selected: Option 2");
                    System.out.print("You entered: ");
                    wordPalindrome(scan.nextLine());
                    break;
                case 3:
                    System.out.println("Closing program.");
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }

    private static class Queue<T> {
        private LinkedList<T> queueList;

        public Queue() {
            queueList = new LinkedList<>();
        }

        public void enqueue(T thing) {
            queueList.addLast(thing);
        }

        public T dequeue() {
            if (isEmpty()) {
                System.out.println("Queue is empty");
                return null;
            }
            return queueList.removeFirst();
        }

        public boolean isEmpty() {
            return queueList.isEmpty();
        }
    }

    public static void characterPalindrome(String string) {
        String base = string.replaceAll("[^a-zA-Z0-9]", "");
        char[] charArray = string.toLowerCase().toCharArray();
        Queue<Character> queue = new Queue<>();
        StringBuilder reverseString = new StringBuilder();

        for (char c : charArray) {
            if (Character.isLetterOrDigit(c)) {
                queue.enqueue(c);
            }
        }
        while (!queue.isEmpty()) {
            reverseString.append(queue.dequeue());
        }
        base = base.toLowerCase();
        reverseString.reverse();
        if (base.equals(reverseString.toString())) {
            System.out.println("Judgment: Palindrome\n");
        } else {
            System.out.println("Judgment: Not Palindrome\n");
        }
    }

    public static void wordPalindrome(String string) {
        String base = string.replaceAll("[^a-zA-Z0-9 ]", "");
        String[] wordArray = base.toLowerCase().split(" ");
        Queue<String> queue = new Queue<>();
        StringBuilder reverseOrder = new StringBuilder();

        for (String s : wordArray) {
            queue.enqueue(s);
        }
        while (!queue.isEmpty()) {
            reverseOrder.insert(0, queue.dequeue() + " ");
        }
        base = base.toLowerCase().trim();
        if (base.equals(reverseOrder.toString().trim())) {
            System.out.println("Judgment: Palindrome\n");
        } else {
            System.out.println("Judgment: Not Palindrome\n");
        }
    }
}
