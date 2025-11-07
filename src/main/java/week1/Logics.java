package week1;

/**
 * Week 1 Logic Implementations.
 * Contains solutions to given problems using basic algorithms.
 * 
 * @author Bhavya Jain
 */
public class Logics {

    private static final int DEFAULT_ARRAY_SIZE = 5;
    int[] nums = new int[DEFAULT_ARRAY_SIZE];

    public Logics() {
        nums = new int[] { 1, 2, 3, 4, 5 };
    }

    public Logics(int[] nums) {
        this.nums = nums;
    }

    /**
     * Efficient Traveler Problem.
     * Calculates how far a traveler can go before running out of energy.
     * 
     * @param initialEnergy Starting energy.
     * @return Index of the farthest city reachable (0-based).
     */
    public int theEfficientTravelerSolver(int initialEnergy) {
        int i;
        for (i = 0; i < nums.length; i++) {
            if (initialEnergy < nums[i])
                break;
            initialEnergy -= nums[i];
        }
        return i;
    }

    /**
     * Sum That Stands Out Problem.
     * Finds the index where the sum of elements on the left equals
     * the sum of elements on the right.
     * 
     * @return Index where left sum equals right sum, or -1 if none found.
     */
    public int theSumThatStandsOutSolver() {
        int suffixSum = 0;
        for (int num : nums) {
            suffixSum += num;
        }

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (prefixSum == suffixSum)
                return i;
            suffixSum -= nums[i];
        }
        return -1;
    }

    /**
     * Flip The Switch Problem.
     * Determines the minimum number of flips needed to make all switches equal.
     * 
     * @return Minimum number of flips required.
     */
    public int flipTheSwitchSolver() {
        int cnt0 = 0, cnt1 = 0;
        for (int val : nums) {
            switch (val) {
                case 0 -> cnt0++;
                case 1 -> cnt1++;
                default -> throw new IllegalArgumentException("Array must contain only 0s and 1s.");
            }
        }
        return Math.min(cnt0, cnt1);
    }

    /**
     * The Odd One Out Problem.
     * Finds the element that appears only once when every other element appears
     * twice.
     * 
     * @return The unique element in the array.
     */
    public int theOddOneOutSolver() {
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    /**
     * Smart Pair Finder Problem.
     * Checks if there exists a pair of elements whose sum equals the given target.
     * 
     * @param target Target sum value.
     * @return {@code true} if a valid pair exists, otherwise {@code false}.
     */
    public boolean smartPairFinderSolver(int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target)
                    return true;
            }
        }
        return false;
    }

    /**
     * The Minimalist Painter Problem.
     * Calculates the minimum amount of paint needed to paint all walls,
     * 
     * @return Minimum time required.
     */
    public int theMinimalistPainterSolver() {
        int sum = 0, max = 0;
        for (int i : nums) {
            sum += i;
            max = max > i ? max : i;
        }
        return sum - max;
    }
}
