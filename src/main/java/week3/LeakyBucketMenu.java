package week3;

import utils.IOUtils;
import java.util.Scanner;

/**
 * Menu-driven program for Leaky Bucket operations
 * 
 * @author Bhavya Jain
 */
public class LeakyBucketMenu {

    /**
     * Runs the Leaky Bucket menu.
     * 
     * @param sc the Scanner object for user input
     */
    public static void run(Scanner sc) {
        IOUtils io = new IOUtils();
        LeakyBucket bucket = null;

        while (true) {
            System.out.println("\n=== Leaky Bucket Menu ===");
            System.out.println("1. Create New Leaky Bucket");
            System.out.println("2. Enqueue Item");
            System.out.println("3. Process Items");
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
                        System.out.print("Enter bucket capacity: ");
                        int capacity = io.readInt(sc);
                        bucket = new LeakyBucket(capacity);
                        System.out.println("Leaky Bucket created successfully!");
                    }
                    case 2 -> {
                        if (bucket == null) {
                            System.out.println("Please create a bucket first!");
                        } else {
                            System.out.print("Enter item to enqueue: ");
                            int item = io.readInt(sc);
                            bucket.enqueue(item);
                            System.out.println("Item enqueued successfully!");
                        }
                    }
                    case 3 -> {
                        if (bucket == null) {
                            System.out.println("Please create a bucket first!");
                        } else {
                            System.out.print("Enter number of items to process: ");
                            int k = io.readInt(sc);
                            int[] processedItems = bucket.process(k);
                            System.out.print("Processed items: ");
                            for (int i = 0; i < processedItems.length; i++) {
                                System.out.print(processedItems[i] + " ");
                            }
                            System.out.println();
                        }
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
