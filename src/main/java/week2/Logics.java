package week2;

/**
 * Week 2 Logic Implementations.
 * Contains solutions to given problems using arrays, strings, and hashmaps.
 * 
 * @author Bhavya Jain
 */
public class Logics {

    /**
     * The Trendsetter Score Problem.
     * Finds the number of windows where a single post is the most frequent.
     * 
     * @param posts array of post IDs
     * @param k     window size
     * @return count of windows with a sole trendsetter
     */
    public int theTrendsetterScoreSolver(int[] posts, int k) {
        if (posts.length < k)
            return 0;

        int trendsetterCount = 0;

        for (int i = 0; i <= posts.length - k; i++) {
            int[] frequency = new int[1001];
            int maxFreq = 0;

            for (int j = i; j < i + k; j++) {
                frequency[posts[j]]++;
                maxFreq = Math.max(maxFreq, frequency[posts[j]]);
            }

            int maxFreqCount = 0;
            for (int f : frequency) {
                if (f == maxFreq && f > 0) {
                    maxFreqCount++;
                }
            }

            if (maxFreqCount == 1 && maxFreq > 1) {
                trendsetterCount++;
            }
        }

        return trendsetterCount;
    }

    /**
     * Encoded String Subsets Problem.
     * Checks if any permutation of substring exists in superstring.
     * 
     * @param superstring the main string to search in
     * @param substring   the string whose permutation to find
     * @return true if a permutation exists, false otherwise
     */
    public boolean encodedStringSubsetsSolver(String superstring, String substring) {
        if (substring.length() > superstring.length())
            return false;

        // Count characters in substring
        int[] targetFreq = new int[256];
        for (int i = 0; i < substring.length(); i++) {
            targetFreq[substring.charAt(i)]++;
        }

        // Sliding window over superstring
        int windowSize = substring.length();
        for (int i = 0; i <= superstring.length() - windowSize; i++) {
            int[] windowFreq = new int[256];

            // Count characters in current window
            for (int j = i; j < i + windowSize; j++) {
                windowFreq[superstring.charAt(j)]++;
            }

            // Compare frequencies
            boolean match = true;
            for (int k = 0; k < 256; k++) {
                if (targetFreq[k] != windowFreq[k]) {
                    match = false;
                    break;
                }
            }

            if (match)
                return true;
        }

        return false;
    }

    /**
     * E-Commerce Funnel Analysis Problem.
     * Finds the most frequent 2-step path taken by users.
     * 
     * @param actions array of user actions in format "userID,pageID"
     * @return the most frequent path in format "pageA,pageB"
     */
    public String ecommerceFunnelAnalysisSolver(String[] actions) {
        // Store the last page visited by each user
        String[] lastPage = new String[1000]; // Assuming reasonable number of users

        // Store path frequencies
        String[] paths = new String[10000];
        int[] pathCounts = new int[10000];
        int pathIndex = 0;

        for (String action : actions) {
            // Parse userID and pageID
            int commaIndex = 0;
            for (int i = 0; i < action.length(); i++) {
                if (action.charAt(i) == ',') {
                    commaIndex = i;
                    break;
                }
            }

            String userIdStr = action.substring(0, commaIndex);
            String pageId = action.substring(commaIndex + 1);

            // Convert userId to int (assume format like "u1", "u2")
            int userId = 0;
            for (int i = 1; i < userIdStr.length(); i++) {
                userId = userId * 10 + (userIdStr.charAt(i) - '0');
            }

            // If user has a previous page, record the path
            if (lastPage[userId] != null) {
                String path = lastPage[userId] + "," + pageId;

                // Find or add this path
                int foundIndex = -1;
                for (int i = 0; i < pathIndex; i++) {
                    if (paths[i].equals(path)) {
                        foundIndex = i;
                        break;
                    }
                }

                if (foundIndex >= 0) {
                    pathCounts[foundIndex]++;
                } else {
                    paths[pathIndex] = path;
                    pathCounts[pathIndex] = 1;
                    pathIndex++;
                }
            }

            lastPage[userId] = pageId;
        }

        // Find the most frequent path (lexicographically first on tie)
        String result = "";
        int maxCount = 0;

        for (int i = 0; i < pathIndex; i++) {
            if (pathCounts[i] > maxCount ||
                    (pathCounts[i] == maxCount && paths[i].compareTo(result) < 0)) {
                maxCount = pathCounts[i];
                result = paths[i];
            }
        }

        return result;
    }

    /**
     * Maximal Pair Matching Problem.
     * Finds maximum number of pairs that sum to target k.
     * 
     * @param nums array of integers
     * @param k    target sum
     * @return maximum number of pairs
     */
    public int maximalPairMatchingSolver(int[] nums, int k) {
        // Count frequency of each number
        int[] frequency = new int[100001]; // Assuming values in reasonable range
        int offset = 50000; // Handle negative numbers

        for (int num : nums) {
            frequency[num + offset]++;
        }

        int pairCount = 0;

        // For each unique number, try to pair it with (k - num)
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = k - num;

            // Check if we can form a pair
            if (frequency[num + offset] > 0 && frequency[complement + offset] > 0) {
                if (num == complement) {
                    // Same number pairs with itself - need at least 2
                    if (frequency[num + offset] >= 2) {
                        frequency[num + offset] -= 2;
                        pairCount++;
                    }
                } else {
                    // Different numbers
                    if (frequency[num + offset] > 0 && frequency[complement + offset] > 0) {
                        frequency[num + offset]--;
                        frequency[complement + offset]--;
                        pairCount++;
                    }
                }
            }
        }

        return pairCount;
    }

    /**
     * Subarray Color Balance Problem.
     * Finds longest subarray with equal red (1) and blue (2) elements.
     * 
     * @param nums array containing 0, 1, and 2
     * @return length of longest balanced subarray
     */
    public int subarrayColorBalanceSolver(int[] nums) {
        int maxLength = 0;

        // Try all possible subarrays
        for (int i = 0; i < nums.length; i++) {
            int redCount = 0;
            int blueCount = 0;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1)
                    redCount++;
                else if (nums[j] == 2)
                    blueCount++;

                // Check if balanced
                if (redCount == blueCount && redCount > 0) {
                    int length = j - i + 1;
                    if (length > maxLength) {
                        maxLength = length;
                    }
                }
            }
        }

        return maxLength;
    }

    /**
     * Text Editor Non-Overlapping Replace Problem.
     * Replaces all non-overlapping occurrences of find_str with replace_str.
     * 
     * @param text       the input text
     * @param findStr    string to find
     * @param replaceStr string to replace with
     * @return modified text
     */
    public String textEditorReplaceSolver(String text, String findStr, String replaceStr) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            // Check if findStr matches at current position
            boolean match = true;
            if (i + findStr.length() <= text.length()) {
                for (int j = 0; j < findStr.length(); j++) {
                    if (text.charAt(i + j) != findStr.charAt(j)) {
                        match = false;
                        break;
                    }
                }
            } else {
                match = false;
            }

            if (match) {
                // Append replacement and skip past the found string
                for (int j = 0; j < replaceStr.length(); j++) {
                    result.append(replaceStr.charAt(j));
                }
                i += findStr.length();
            } else {
                // Append current character and move forward
                result.append(text.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Log File Compaction Problem.
     * Performs run-length encoding on space-separated log levels.
     * 
     * @param logs space-separated log levels
     * @return compacted string with counts
     */
    public String logFileCompactionSolver(String logs) {
        if (logs.length() == 0)
            return "";

        // Split by spaces manually
        String[] logArray = new String[1000];
        int logCount = 0;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < logs.length(); i++) {
            if (logs.charAt(i) == ' ') {
                if (current.length() > 0) {
                    logArray[logCount++] = current.toString();
                    current = new StringBuilder();
                }
            } else {
                current.append(logs.charAt(i));
            }
        }
        if (current.length() > 0) {
            logArray[logCount++] = current.toString();
        }

        // Run-length encoding
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < logCount) {
            String currentLog = logArray[i];
            int count = 1;

            // Count consecutive occurrences
            while (i + count < logCount && logArray[i + count].equals(currentLog)) {
                count++;
            }

            // Append to result
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(currentLog).append("(").append(count).append(")");

            i += count;
        }

        return result.toString();
    }

    /**
     * E-commerce SKU Normalization Problem.
     * Validates and normalizes SKU format.
     * 
     * @param sku input SKU string
     * @return normalized SKU or "INVALID"
     */
    public String skuNormalizationSolver(String sku) {
        // Convert to uppercase
        String normalized = "";
        for (int i = 0; i < sku.length(); i++) {
            char c = sku.charAt(i);
            if (c >= 'a' && c <= 'z') {
                normalized += (char) (c - 32);
            } else {
                normalized += c;
            }
        }

        // Split by hyphens
        String[] parts = new String[3];
        int partIndex = 0;
        StringBuilder current = new StringBuilder();

        for (int i = 0; i < normalized.length(); i++) {
            if (normalized.charAt(i) == '-') {
                if (partIndex < 3) {
                    parts[partIndex++] = current.toString();
                    current = new StringBuilder();
                }
            } else {
                current.append(normalized.charAt(i));
            }
        }
        if (partIndex < 3) {
            parts[partIndex] = current.toString();
        }

        if (partIndex != 2)
            return "INVALID";

        String category = parts[0];
        String id = parts[1];
        String size = parts[2];

        // Validate category: 3-4 letters
        if (category.length() < 3 || category.length() > 4)
            return "INVALID";
        for (int i = 0; i < category.length(); i++) {
            if (category.charAt(i) < 'A' || category.charAt(i) > 'Z') {
                return "INVALID";
            }
        }

        // Validate ID: 4-6 digits
        if (id.length() < 4 || id.length() > 6)
            return "INVALID";
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) < '0' || id.charAt(i) > '9') {
                return "INVALID";
            }
        }

        // Validate size: S, M, L, or XL
        if (!size.equals("S") && !size.equals("M") &&
                !size.equals("L") && !size.equals("XL")) {
            return "INVALID";
        }

        return normalized;
    }

    /**
     * Chat Message Redaction Problem.
     * Replaces banned words with asterisks of same length.
     * 
     * @param message     the chat message
     * @param bannedWords array of banned words
     * @return redacted message
     */
    public String chatMessageRedactionSolver(String message, String[] bannedWords) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < message.length()) {
            // Try to match each banned word at current position
            boolean foundBanned = false;

            for (String banned : bannedWords) {
                if (i + banned.length() <= message.length()) {
                    boolean match = true;

                    // Check if banned word matches at position i
                    for (int j = 0; j < banned.length(); j++) {
                        if (message.charAt(i + j) != banned.charAt(j)) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        // Replace with asterisks
                        for (int j = 0; j < banned.length(); j++) {
                            result.append('*');
                        }
                        i += banned.length();
                        foundBanned = true;
                        break;
                    }
                }
            }

            if (!foundBanned) {
                result.append(message.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Resume Keyword Versioning Problem.
     * Calculates similarity score based on common keywords.
     * 
     * @param resumeV1 first resume keywords
     * @param resumeV2 second resume keywords
     * @return similarity score
     */
    public int resumeKeywordVersioningSolver(String[] resumeV1, String[] resumeV2) {
        // Count frequency in each resume
        String[] uniqueWords = new String[1000];
        int[] freqV1 = new int[1000];
        int[] freqV2 = new int[1000];
        int uniqueCount = 0;

        // Count in resume V1
        for (String word : resumeV1) {
            int index = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueWords[i].equals(word)) {
                    index = i;
                    break;
                }
            }

            if (index >= 0) {
                freqV1[index]++;
            } else {
                uniqueWords[uniqueCount] = word;
                freqV1[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // Count in resume V2
        for (String word : resumeV2) {
            int index = -1;
            for (int i = 0; i < uniqueCount; i++) {
                if (uniqueWords[i].equals(word)) {
                    index = i;
                    break;
                }
            }

            if (index >= 0) {
                freqV2[index]++;
            } else {
                uniqueWords[uniqueCount] = word;
                freqV2[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        // Calculate similarity score
        int score = 0;
        for (int i = 0; i < uniqueCount; i++) {
            int minFreq = freqV1[i] < freqV2[i] ? freqV1[i] : freqV2[i];
            score += minFreq;
        }

        return score;
    }
}
