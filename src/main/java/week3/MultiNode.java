package week3;

import utils.Node;

/**
 * Multi-level doubly linked list node
 * 
 * @author Bhavya Jain
 */

public class MultiNode extends Node {
    public MultiNode prev;
    public MultiNode child;

    public MultiNode(int val) {
        super(val);
        this.prev = null;
        this.child = null;
    }

    public MultiNode(int val, MultiNode next, MultiNode prev, MultiNode child) {
        super(val, next);
        this.prev = prev;
        this.child = child;
    }

    @Override
    public String toString() {
        MultiNode level = this;
        StringBuilder sb = new StringBuilder();
        int currentIndent = 0;

        while (level != null) {
            MultiNode current = level;
            MultiNode childNode = null;
            int childIndent = 0;

            for (int i = 0; i < currentIndent; i++) {
                sb.append(" ");
            }

            int position = currentIndent;
            while (current != null) {
                sb.append(current.val);
                position += String.valueOf(current.val).length();

                if (current.child != null) {
                    childNode = current.child;
                    childIndent = position;
                }

                if (current.next != null) {
                    sb.append(" -> ");
                    position += 4;
                } else {
                    sb.append(" -> NULL");
                }
                current = (MultiNode) current.next;
            }
            sb.append("\n");

            if (childNode != null) {
                for (int i = 0; i < childIndent; i++) {
                    sb.append(" ");
                }
                sb.append("|\n");
                currentIndent = childIndent;
            }

            level = childNode;
        }

        return sb.toString();
    }
}
