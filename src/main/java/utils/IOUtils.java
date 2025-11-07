package utils;

import java.util.Scanner;

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
}