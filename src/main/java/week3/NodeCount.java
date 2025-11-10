package week3;

import utils.Node;

/**
 * Node class that stores an integer value along with its count
 * 
 * @author Bhavya Jain
 */

public class NodeCount extends Node {
    public int count;

    public NodeCount(int val, int count) {
        super(val);
        this.count = count;
    }

    public NodeCount(int val, int count, NodeCount next) {
        super(val, next);
        this.count = count;
    }

    @Override
    public String toString() {
        NodeCount temp = this;
        StringBuilder sb = new StringBuilder();
        while (temp != null) {
            // Display as character if it's a printable ASCII character (65-90 for A-Z, 97-122 for a-z)
            if ((temp.val >= 65 && temp.val <= 90) || (temp.val >= 97 && temp.val <= 122)) {
                sb.append("(").append((char) temp.val).append(", ").append(temp.count).append(")");
            } else {
                sb.append("(").append(temp.val).append(", ").append(temp.count).append(")");
            }
            if (temp.next != null) {
                sb.append(" -> ");
            }
            temp = (NodeCount) temp.next;
        }
        return sb.toString();
    }
}
