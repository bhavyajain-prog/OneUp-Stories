package week3;

import utils.Node;

/**
 * Node class for 2D linked list
 * 
 * @author Bhavya Jain
 */

public class Node2D extends Node {
    public Node2D down;

    public Node2D(int val) {
        super(val);
        this.down = null;
    }

    public Node2D(int val, Node2D next, Node2D down) {
        super(val, next);
        this.down = down;
    }

    /**
     * Constructor that builds a 2D linked list from a matrix
     * 
     * @param matrix 2D array of integers
     */
    public Node2D(int[][] matrix) {
        super(0);

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be empty");
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        Node2D[][] nodes = new Node2D[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nodes[i][j] = new Node2D(matrix[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < cols - 1) {
                    nodes[i][j].next = nodes[i][j + 1];
                }
                if (i < rows - 1) {
                    nodes[i][j].down = nodes[i + 1][j];
                }
            }
        }
        this.val = nodes[0][0].val;
        this.next = nodes[0][0].next;
        this.down = nodes[0][0].down;
    }

    @Override
    public String toString() {
        Node2D temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            Node2D row = temp;
            while (row != null) {
                sb.append(row.val).append(" ");
                row = (Node2D) row.next;
            }
            sb.append("\n");
            temp = temp.down;
        }
        return sb.toString();
    }
}
