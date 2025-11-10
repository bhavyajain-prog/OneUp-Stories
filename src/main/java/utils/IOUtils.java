package utils;

import java.util.Scanner;
import week3.NodeCount;
import week3.MultiNode;

/**
 * Utility class for input/output operations.
 * 
 * @author Bhavya Jain
 */

public class IOUtils {

    // Reads and returns a valid integer from the scanner
    public int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid integer: ");
            sc.nextLine();
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }

    // Reads and returns an array of integers
    public int[] readArray(Scanner sc, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = readInt(sc);
        }
        return arr;
    }

    // Reads and returns a linked list of integers
    public Node readLinkedList(Scanner sc) {
        Node head = new Node(readInt(sc));
        Node curr = head;
        while (sc.hasNextInt()) {
            curr.next = new Node(readInt(sc));
            curr = curr.next;
        }
        return head;
    }

    // Reads and returns a 2D array of integers
    public int[][] read2DArray(Scanner sc, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = readInt(sc);
            }
        }
        return matrix;
    }

    // Read multiple lines of input until an empty line is encountered
    public String[] readMultipleLines(Scanner sc) {
        String[] lines = new String[100];
        int count = 0;
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            lines[count++] = line;
        }
        return lines;
    }

    // Reads and returns a NodeCount linked list
    // Format: value count value count ...
    // Example: A 2 A 3 B 1 C 4
    public NodeCount readNodeCountList(Scanner sc) {
        String input = sc.nextLine();
        if (input.length() == 0) {
            return null;
        }

        NodeCount head = new NodeCount(0, 0);
        NodeCount current = head;

        int i = 0;
        while (i < input.length()) {
            // Skip spaces
            while (i < input.length() && input.charAt(i) == ' ') {
                i++;
            }

            if (i >= input.length()) {
                break;
            }

            // Read value (character)
            char valueChar = input.charAt(i);
            int value = valueChar;
            i++;

            // Skip spaces
            while (i < input.length() && input.charAt(i) == ' ') {
                i++;
            }

            if (i >= input.length()) {
                break;
            }

            // Read count (number)
            int count = 0;
            while (i < input.length() && input.charAt(i) >= '0' && input.charAt(i) <= '9') {
                count = count * 10 + (input.charAt(i) - '0');
                i++;
            }

            current.next = new NodeCount(value, count);
            current = (NodeCount) current.next;

        }

        return (NodeCount) head.next;
    }

    // Reads and returns a multi-level linked list
    // Simple format: Enter each level separately
    // For each level, provide: parent_index values...
    // Example for level 0: 1 2 3 4
    // Example for level 1 under node 0: 0 5 6 7
    public MultiNode readMultiLevelList(Scanner sc) {
        System.out.println("Enter number of levels: ");
        int numLevels = readInt(sc);

        if (numLevels == 0) {
            return null;
        }

        // Array to store all nodes created
        MultiNode[] allNodes = new MultiNode[100];
        int nodeCount = 0;

        // Read main level (level 0)
        System.out.println("Enter main level values (space-separated): ");
        String line = sc.nextLine();
        int i = 0;
        MultiNode head = null;
        MultiNode prevNode = null;

        // Parse main level
        while (i < line.length()) {
            // Skip spaces
            while (i < line.length() && line.charAt(i) == ' ') {
                i++;
            }
            if (i >= line.length()) {
                break;
            }

            // Read number
            int num = 0;
            while (i < line.length() && line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                num = num * 10 + (line.charAt(i) - '0');
                i++;
            }

            // Create node
            MultiNode node = new MultiNode(num);
            allNodes[nodeCount] = node;
            nodeCount++;

            if (head == null) {
                head = node;
            }
            if (prevNode != null) {
                prevNode.next = node;
                node.prev = prevNode;
            }
            prevNode = node;
        }

        // Read child levels
        for (int level = 1; level < numLevels; level++) {
            System.out.println("Enter level " + level + " (format: parent_index values...): ");
            System.out.println("Example: 0 5 6 7 means attach 5->6->7 as child of node at index 0");
            line = sc.nextLine();
            i = 0;

            // Skip spaces
            while (i < line.length() && line.charAt(i) == ' ') {
                i++;
            }
            if (i >= line.length()) {
                continue;
            }

            // Read parent index
            int parentIndex = 0;
            while (i < line.length() && line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                parentIndex = parentIndex * 10 + (line.charAt(i) - '0');
                i++;
            }

            // Skip spaces
            while (i < line.length() && line.charAt(i) == ' ') {
                i++;
            }

            // Create child list
            MultiNode childHead = null;
            prevNode = null;

            while (i < line.length()) {
                // Skip spaces
                while (i < line.length() && line.charAt(i) == ' ') {
                    i++;
                }
                if (i >= line.length()) {
                    break;
                }

                // Read number
                int num = 0;
                while (i < line.length() && line.charAt(i) >= '0' && line.charAt(i) <= '9') {
                    num = num * 10 + (line.charAt(i) - '0');
                    i++;
                }

                // Create node
                MultiNode node = new MultiNode(num);
                allNodes[nodeCount] = node;
                nodeCount++;

                if (childHead == null) {
                    childHead = node;
                }
                if (prevNode != null) {
                    prevNode.next = node;
                    node.prev = prevNode;
                }
                prevNode = node;
            }

            // Attach child to parent
            if (parentIndex < nodeCount && childHead != null) {
                allNodes[parentIndex].child = childHead;
            }
        }

        return head;
    }
}