package week3;

/**
 * Stack implementation that supports average retrieval in O(1) time.
 * Available max capacity is 100 elements.
 * 
 * @author Bhavya Jain
 */

public class AverageStack {
    private final int SIZE = 100;
    private final int[] stack;
    private int top;
    private int sum;

    public AverageStack() {
        stack = new int[SIZE];
        top = -1;
        sum = 0;
    }

    /**
     * Get the average of all elements in the stack in O(1) time.
     * 
     * @return {@code float} the average of the stack elements
     */
    public float getAverage() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return (float) sum / (top + 1);
    }

    /**
     * Push a value onto the stack.
     * 
     * @param value the integer value to push
     */
    public void push(int value) {
        if (top == SIZE - 1) {
            throw new StackOverflowError("Stack is full");
        }
        stack[++top] = value;
        sum += value;
    }

    /**
     * Pop a value from the stack.
     * 
     * @return the {@code int} value popped from the stack
     */
    public int pop() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = stack[top--];
        sum -= value;
        return value;
    }

    /**
     * Check if the stack is empty.
     * 
     * @return {@code boolean} {@code true} if the stack is empty, {@code false}
     *         otherwise
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Check if the stack is full.
     * 
     * @return {@code boolean} {@code true} if the stack is full, {@code false}
     *         otherwise
     */
    public boolean isFull() {
        return top == SIZE - 1;
    }

    /**
     * Get the current size of the stack.
     * 
     * @return {@code int} the number of elements in the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Get the top element of the stack without removing it.
     * 
     * @return {@code int} the top element of the stack
     */
    public int top() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

}
