package week1;

import utils.IOUtils;
import java.util.Scanner;

/**
 * Main Class for week 1 stories
 * 
 * @author Bhavya Jain
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            IOUtils io = new IOUtils();

            while (true) {
                System.out.println("\n=== Solutions ===");
                System.out.println("1. The Efficient Traveler");
                System.out.println("2. The Sum That Stands Out");
                System.out.println("3. Flip The Switch");
                System.out.println("4. The Odd One Out");
                System.out.println("5. Smart Pair Finder");
                System.out.println("6. The Minimalist Painter");
                System.out.println("0. Exit");

                System.out.print("\nEnter choice: ");
                int choice = io.readInt(sc);

                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter number of cities: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter energy costs:");
                        int[] costs = io.readArray(sc, n);
                        System.out.print("Enter initial energy: ");
                        int energy = io.readInt(sc);
                        Logics logics = new Logics(costs);
                        System.out.println("Result: " + logics.theEfficientTravelerSolver(energy));
                    }
                    case 2 -> {
                        System.out.print("Enter number of elements: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter elements:");
                        int[] nums = io.readArray(sc, n);
                        Logics logics = new Logics(nums);
                        System.out.println("Result: " + logics.theSumThatStandsOutSolver());
                    }
                    case 3 -> {
                        System.out.print("Enter number of switches: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter switch states (0 or 1):");
                        int[] switches = io.readArray(sc, n);
                        Logics logics = new Logics(switches);
                        try {
                            System.out.println("Result: " + logics.flipTheSwitchSolver());
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter number of elements: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter elements:");
                        int[] nums = io.readArray(sc, n);
                        Logics logics = new Logics(nums);
                        System.out.println("Result: " + logics.theOddOneOutSolver());
                    }
                    case 5 -> {
                        System.out.print("Enter number of elements: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter elements:");
                        int[] nums = io.readArray(sc, n);
                        System.out.print("Enter target sum: ");
                        int target = io.readInt(sc);
                        Logics logics = new Logics(nums);
                        System.out.println("Result: " + logics.smartPairFinderSolver(target));
                    }
                    case 6 -> {
                        System.out.print("Enter number of walls: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter time for each wall:");
                        int[] times = io.readArray(sc, n);
                        Logics logics = new Logics(times);
                        System.out.println("Result: " + logics.theMinimalistPainterSolver());
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}
