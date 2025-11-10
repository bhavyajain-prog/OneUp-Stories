package week3;

/**
 * Stack implementation that supports average retrieval in O(1) time.
 * 
 * @author Bhavya Jain
 */

public class AverageStack {
    private static final int SIZE = 100;
    private final int[] stack;
    private int top;
    private int sum;

    public AverageStack() {
        stack = new int[SIZE];
        top = -1;
        sum = 0;
    }

    public float getAverage() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return (float) sum / (top + 1);
    }

    public void push(int value) {
        if (top == SIZE - 1) {
            throw new StackOverflowError("Stack is full");
        }
        stack[++top] = value;
        sum += value;
    }

    public int pop() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        int value = stack[top--];
        sum -= value;
        return value;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == SIZE - 1;
    }

    public int size() {
        return top + 1;
    }

    public int top() {
        if (top == -1) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }

}
