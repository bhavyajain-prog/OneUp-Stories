package week3;

import java.util.Scanner;

import utils.*;

/**
 * Main class for week 3 stories
 * 
 * @author Bhavya Jain
 */
public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            IOUtils io = new IOUtils();
            while (true) {
                System.out.println("\n=== Week 3 Solutions ===");
                System.out.println("1. Average Stack Operations");
                System.out.println("2. Validate Python Indentation");
                System.out.println("3. Transpose 2D Linked List");
                System.out.println("4. Zipline merge of two lists in k chunks");
                System.out.println("5. Partition list on prime/composite");
                System.out.println("6. Leaky Bucket Operations");
                System.out.println("7. Interweave 2 C-Strings");
                System.out.println("8. Collapse Count List");
                System.out.println("9. Flatten multi-level linked list (zigzag)");
                System.out.println("0. Exit");

                System.out.print("\nEnter choice: ");
                int choice = io.readInt(sc);

                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                }

                try {
                    switch (choice) {
                        case 1 -> StackMenu.run(sc);
                        case 2 -> {
                            System.out.print("Enter tab spaces (default 1, press Enter to use default): ");
                            String tabInput = sc.nextLine();
                            int tabSpaces = tabInput.isEmpty() ? 1 : Integer.parseInt(tabInput);

                            System.out.println("\nEnter Python code (press Enter on empty line to finish):");
                            String[] lines = io.readMultipleLines(sc);
                            int totalLines = 0;
                            for (int i = 0; i < 100; i++) {
                                if (lines[i] == null || lines[i].isEmpty()) {
                                    break;
                                }
                                totalLines++;
                            }
                            String[] actualLines = new String[totalLines];
                            System.arraycopy(lines, 0, actualLines, 0, totalLines);
                            Logics solver = new Logics();
                            boolean isValid = solver.isValidIndentation(actualLines, tabSpaces);
                            if (isValid) {
                                System.out.println("Valid Python indentation!");
                            } else {
                                System.out.println("Invalid Python indentation!");
                            }
                        }
                        case 3 -> {
                            System.out.print("Enter number of rows: ");
                            int rows = io.readInt(sc);
                            System.out.print("Enter number of columns: ");
                            int cols = io.readInt(sc);
                            System.out.println("\nEnter matrix values:");
                            int[][] matrix = io.read2DArray(sc, rows, cols);
                            Node2D head = new Node2D(matrix);
                            System.out.println("\n--- Original 2D Linked List ---");
                            System.out.println(head);
                            Logics solver = new Logics();
                            Node2D transposed = solver.transpose2DList(head);

                            System.out.println("\n--- Transposed 2D Linked List ---");
                            System.out.println(transposed);
                        }
                        case 4 -> {
                            System.out.println("Enter chunk size (k): ");
                            int k = io.readInt(sc);
                            System.out.println("Enter list 1 elements (space-separated): ");
                            Node list1 = io.readLinkedList(sc);
                            System.out.println("Enter list 2 elements (space-separated): ");
                            Node list2 = io.readLinkedList(sc);
                            Logics solver = new Logics();
                            Node mergedList = solver.kZiplineMerge(list1, list2, k);
                            System.out.println("\n--- Merged List ---");
                            System.out.println(mergedList);
                        }
                        case 5 -> {
                            System.out.println("Enter list elements (space-separated): ");
                            Node list1 = io.readLinkedList(sc);
                            Logics solver = new Logics();
                            Node partitioned = solver.partitionPrimes(list1);
                            System.out.println("\n--- Partitioned List ---");
                            System.out.println(partitioned);
                        }
                        case 6 -> LeakyBucketMenu.run(sc);
                        case 7 -> {
                            RunCProgram interweaveRunner = new RunCProgram();
                            interweaveRunner.run("Logics.c");
                        }
                        case 8 -> {
                            System.out.println("Enter list as: value count value count ...");
                            System.out.println("Example: A 2 A 3 B 1 C 4");
                            System.out.print("Input: ");
                            NodeCount list = io.readNodeCountList(sc);

                            if (list == null) {
                                System.out.println("Empty list provided.");
                            } else {
                                System.out.println("\n--- Original List ---");
                                System.out.println(list);

                                Logics solver = new Logics();
                                NodeCount collapsed = solver.collapse(list);

                                System.out.println("\n--- Collapsed List ---");
                                System.out.println(collapsed);
                            }
                        }
                        case 9 -> {
                            MultiNode list = io.readMultiLevelList(sc);

                            if (list == null) {
                                System.out.println("Empty list provided.");
                            } else {
                                System.out.println("\n--- Original Multi-Level List ---");
                                System.out.println(list);

                                Logics solver = new Logics();
                                MultiNode flattened = solver.flatten(list);

                                System.out.println("--- Flattened List (Zigzag Order) ---");
                                System.out.println(flattened);
                            }
                        }
                        default -> System.out.println("Invalid choice!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ERROR: Invalid number format - " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        }
    }
}
