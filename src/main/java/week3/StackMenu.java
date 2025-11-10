package week3;

import utils.IOUtils;
import java.util.Scanner;

/**
 * Menu-driven program for Average Stack operations
 * 
 * @author Bhavya Jain
 */
public class StackMenu {
    public static void run(Scanner sc) {
        IOUtils io = new IOUtils();
        AverageStack stack = new AverageStack();

        while (true) {
            System.out.println("\n=== Average Stack Menu ===");
            System.out.println("1. Push Element");
            System.out.println("2. Pop Element");
            System.out.println("3. Get Top Element");
            System.out.println("4. Get Average");
            System.out.println("0. Exit");

            System.out.print("\nEnter choice: ");
            int choice = io.readInt(sc);

            if (choice == 0) {
                System.out.println("Returning to main menu...");
                break;
            }

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter value to push: ");
                        int value = io.readInt(sc);
                        stack.push(value);
                        System.out.println("Value pushed successfully!");
                    }
                    case 2 -> {
                        int value = stack.pop();
                        System.out.println("Popped value: " + value);
                    }
                    case 3 -> {
                        int top = stack.top();
                        System.out.println("Top element: " + top);
                    }
                    case 4 -> {
                        float average = stack.getAverage();
                        System.out.println("Average: " + average);
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            run(sc);
        }
    }
}
