package week2;

import utils.IOUtils;
import java.util.Scanner;

/**
 * Main Class for week 2 stories
 * 
 * @author Bhavya Jain
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            IOUtils io = new IOUtils();

            while (true) {
                System.out.println("\n=== Week 2 Solutions ===");
                System.out.println("1. The Trendsetter Score");
                System.out.println("2. Encoded String Subsets");
                System.out.println("3. E-Commerce Funnel Analysis");
                System.out.println("4. Maximal Pair Matching");
                System.out.println("5. Subarray Color Balance");
                System.out.println("6. Text Editor Non-Overlapping Replace");
                System.out.println("7. Log File Compaction");
                System.out.println("8. E-commerce SKU Normalization");
                System.out.println("9. Chat Message Redaction");
                System.out.println("10. Resume Keyword Versioning");
                System.out.println("0. Exit");

                System.out.print("\nEnter choice: ");
                int choice = io.readInt(sc);

                if (choice == 0) {
                    System.out.println("Exiting...");
                    break;
                }

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter number of posts: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter post IDs:");
                        int[] posts = io.readArray(sc, n);
                        System.out.print("Enter window size k: ");
                        int k = io.readInt(sc);
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.theTrendsetterScoreSolver(posts, k));
                    }
                    case 2 -> {
                        System.out.print("Enter superstring: ");
                        String superstring = sc.nextLine();
                        System.out.print("Enter substring: ");
                        String substring = sc.nextLine();
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.encodedStringSubsetsSolver(superstring, substring));
                    }
                    case 3 -> {
                        System.out.print("Enter number of actions: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter actions (format: userID,pageID):");
                        String[] actions = new String[n];
                        for (int i = 0; i < n; i++) {
                            actions[i] = sc.nextLine();
                        }
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.ecommerceFunnelAnalysisSolver(actions));
                    }
                    case 4 -> {
                        System.out.print("Enter number of elements: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter elements:");
                        int[] nums = io.readArray(sc, n);
                        System.out.print("Enter target sum k: ");
                        int k = io.readInt(sc);
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.maximalPairMatchingSolver(nums, k));
                    }
                    case 5 -> {
                        System.out.print("Enter number of elements: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter elements (0, 1, or 2):");
                        int[] nums = io.readArray(sc, n);
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.subarrayColorBalanceSolver(nums));
                    }
                    case 6 -> {
                        System.out.print("Enter text: ");
                        String text = sc.nextLine();
                        System.out.print("Enter find string: ");
                        String findStr = sc.nextLine();
                        System.out.print("Enter replace string: ");
                        String replaceStr = sc.nextLine();
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.textEditorReplaceSolver(text, findStr, replaceStr));
                    }
                    case 7 -> {
                        System.out.print("Enter logs (space-separated): ");
                        String logs = sc.nextLine();
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.logFileCompactionSolver(logs));
                    }
                    case 8 -> {
                        System.out.print("Enter SKU: ");
                        String sku = sc.nextLine();
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.skuNormalizationSolver(sku));
                    }
                    case 9 -> {
                        System.out.print("Enter message: ");
                        String message = sc.nextLine();
                        System.out.print("Enter number of banned words: ");
                        int n = io.readInt(sc);
                        System.out.println("Enter banned words:");
                        String[] bannedWords = new String[n];
                        for (int i = 0; i < n; i++) {
                            bannedWords[i] = sc.nextLine();
                        }
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.chatMessageRedactionSolver(message, bannedWords));
                    }
                    case 10 -> {
                        System.out.print("Enter number of keywords in resume V1: ");
                        int n1 = io.readInt(sc);
                        System.out.println("Enter resume V1 keywords:");
                        String[] resumeV1 = new String[n1];
                        for (int i = 0; i < n1; i++) {
                            resumeV1[i] = sc.nextLine();
                        }
                        System.out.print("Enter number of keywords in resume V2: ");
                        int n2 = io.readInt(sc);
                        System.out.println("Enter resume V2 keywords:");
                        String[] resumeV2 = new String[n2];
                        for (int i = 0; i < n2; i++) {
                            resumeV2[i] = sc.nextLine();
                        }
                        Logics logics = new Logics();
                        System.out.println("Result: " + logics.resumeKeywordVersioningSolver(resumeV1, resumeV2));
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}
