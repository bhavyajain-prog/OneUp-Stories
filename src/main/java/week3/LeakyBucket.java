package week3;

import utils.Node;

/**
 * Leaky Bucket implementation for rate limiting in simulation of network
 * buffer.
 * 
 * @author Bhavya Jain
 */

public class LeakyBucket {
    private final int capacity;
    private Node head, rear;
    private int size;

    public LeakyBucket(int capacity) {
        this.capacity = capacity;
        this.head = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Enqueue an item into the leaky bucket.
     * If the bucket is full, the oldest item is dropped.
     * 
     * @param item the integer item to enqueue
     */
    public void enqueue(int item) {
        if (isFull()) {
            head = head.next;
            size--;
        }
        if (head == null) {
            head = new Node(item);
            rear = head;
        } else {
            rear.next = new Node(item);
            rear = rear.next;
        }
        size++;
    }

    /**
     * Check if the leaky bucket is full.
     * 
     * @return {@code boolean} true if the bucket is full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Process up to k items from the leaky bucket and drop them from the buffer.
     * 
     * @param k the maximum number of items to process
     * @return {@code int[]} array of processed items
     */
    public int[] process(int k) {
        int[] result = new int[k > size ? size : k];
        int i = 0;
        while (i < result.length && head != null) {
            result[i++] = head.val;
            head = head.next;
            size--;
        }
        if (head == null) {
            rear = null;
        }
        return result;
    }
}
