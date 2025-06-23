import java.util.Stack;
import java.util.Scanner;

public class testStack {
    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<>();
        Stack<Double> doubleStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        Stack<Integer> integerStackSearch = new Stack<>();
        int target;
        Scanner scan = new Scanner(System.in);

        System.out.print("Testing function topToBottom:\nEnter integers to push onto the stack (space-separated): ");
        String integer = scan.nextLine();
        String[] integerS = integer.split(" ");
        for (String s : integerS) {
            integerStack.push(Integer.parseInt(s));
        }
        System.out.println("Stack content: " + reversedString(integer));
        topToBottom(integerStack);

        System.out.print("Testing function bottomTopTop:\nEnter doubles to push onto the stack (space-separated): ");
        String doubles = scan.nextLine();
        String[] doubleS = doubles.split(" ");
        for (String s : doubleS) {
            doubleStack.push(Double.parseDouble(s));
        }
        System.out.println("Stack content: " + topBottomDouble(doubleStack));
        bottomToTop(doubleStack);

        System.out.print("Testing function flipStack:\nEnter strings to push onto the stack (space-separated): ");
        String strings = scan.nextLine();
        String[] stringS = strings.split(" ");
        for(String s : stringS) {
            stringStack.push(s);
        }
        System.out.print("Stack content before calling flipStack: ");
        printStack(stringStack);
        System.out.print("Stack content after calling flipStack: ");
        stringStack = flipStack(stringStack);
        printStack(stringStack);
        System.out.println();

        System.out.print("Testing function searchStack:\nEnter integers to push onto the stack (space-separated): ");
        String searchInt = scan.nextLine();
        String[] searchIntS = searchInt.split(" ");
        for (String s : searchIntS) {
            integerStackSearch.push(Integer.parseInt(s));
        }
        System.out.println("Stack content: " + reversedString(searchInt));
        System.out.print("Enter target value to search for: ");
        target = scan.nextInt();
        scan.nextLine();
        System.out.print("Function output: " + searchStack(integerStackSearch, target));
        System.out.println();
    }

    public static void topToBottom(Stack<Integer> stack) {
        System.out.print("Function output: ");
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (i == 0) {
                System.out.print(stack.get(i));
            } else {
                System.out.print(stack.get(i) + " ");
            }
        }
        System.out.println();
    }

    // Assignment Methods

    public static void bottomToTop(Stack<Double> stack) {
        System.out.print("Function output: ");
        for (int i = 0; i < stack.size(); i++) {
            if (i == stack.size() - 1) {
                System.out.print(stack.get(i));
            } else {
                System.out.print(stack.get(i) + " ");
            }
        }
        System.out.println();
    }

    public static Stack<String> flipStack(Stack<String> stack) {
        Stack<String> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        return tempStack;
    }

    public static Boolean searchStack(Stack<Integer> stack, int target) {
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) == target) {
                return true;
            }
        }
        return false;
    }

    // Other Methods

    public static String reversedString(String string) {
        String reversed = "";
        for (int i = string.length() - 1; i >= 0; i--) {
            reversed += string.charAt(i);
        }
        return reversed;
    }

    public static String topBottomDouble(Stack<Double> stack) {
        String topToBottom = "";
        for (int i = stack.size() - 1; i >= 0; i--) {
            if (i == 0) {
                topToBottom += stack.get(i);
            } else {
                topToBottom += stack.get(i) + " ";
            }
        }
        return topToBottom;
    }

    public static void printStack(Stack<String> stack) {
        for(int i = 0; i < stack.size(); i++) {
            if (i == stack.size() - 1) {
                System.out.print(stack.get(i) +"\n");
            } else {
                System.out.print(stack.get(i) + " ");
            }
        }
    }
}
