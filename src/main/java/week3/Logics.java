package week3;

import java.util.Stack;

import utils.Node;

/**
 * Week 3 Logic Implementations
 * Contains solutions to given problems using linked lists, stacks, pointers,
 * queues, and deque.
 * 
 * @author Bhavya Jain
 */

public class Logics {

    /**
     * Check if the given lines of code have valid indentation with default
     * {@code tabSpaces=1}.
     * 
     * @param lines array of strings representing lines of code
     * @return {@code boolean} true if indentation is valid, false otherwise
     */
    public boolean isValidIndentation(String[] lines) {
        return isValidIndentation(lines, 1);
    }

    /**
     * Check if the given lines of code have valid indentation with custom tabSpaces.
     * 
     * @param lines     array of strings representing lines of code
     * @param tabSpaces number of spaces representing one indentation level
     * @return {@code boolean} true if indentation is valid, false otherwise
     */
    public boolean isValidIndentation(String[] lines, int tabSpaces) {
        if (lines == null || lines.length == 0)
            return true;
        AverageStack stack = new AverageStack();
        stack.push(0);
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].isBlank())
                continue;
            int indent = getIndent(lines[i]);
            if (i == 0 && indent != 0)
                return false;
            int currentLevel = stack.top();
            if (indent == currentLevel) {
                if (i > 0 && endsWithColon(lines[i - 1])) {
                    return false;
                }
            } else if (indent == currentLevel + tabSpaces) {
                if (i == 0 || !endsWithColon(lines[i - 1]))
                    return false;
                stack.push(indent);
            } else if (indent < currentLevel) {
                while (!stack.isEmpty() && stack.top() > indent)
                    stack.pop();
                if (stack.isEmpty() || stack.top() != indent)
                    return false;
            } else
                return false;
        }
        return true;
    }

    /**
     * Helper method to get the indentation level of a line.
     * 
     * @param line the line of code
     * @return the number of leading spaces (indentation level)
     */
    private int getIndent(String line) {
        int count = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ')
                count++;
            else
                break;
        }
        return count;
    }

    /**
     * Helper method to check if a line ends with a colon (':'), ignoring trailing
     * spaces.
     * 
     * @param line the line of code
     * @return {@code boolean} true if the line ends with a colon, false otherwise
     */
    private boolean endsWithColon(String line) {
        if (line == null || line.isEmpty())
            return false;
        int i = line.length() - 1;
        while (i >= 0 && line.charAt(i) == ' ')
            i--;
        return i >= 0 && line.charAt(i) == ':';
    }

    /**
     * Transpose a 2D linked list.
     * 
     * @param head the head of the 2D linked list
     * @return the head of the transposed 2D linked list
     */
    public Node2D transpose2DList(Node2D head) {
        if (head == null) {
            return null;
        }
        Node2D row = head;
        while (row != null) {
            Node2D col = row;
            while (col != null) {
                Node2D temp = (Node2D) col.next;
                col.next = col.down;
                col.down = temp;
                col = (Node2D) col.next;
            }
            row = row.down;
        }

        return head;
    }

    /**
     * Merge two linked lists in a k-zipline fashion.
     * 
     * @param head1 the head of the first linked list
     * @param head2 the head of the second linked list
     * @param k     the number of nodes to take from each list alternately
     * @return the head of the merged linked list
     */
    public Node kZiplineMerge(Node head1, Node head2, int k) {
        if (head1 == null)
            return head2;
        if (head2 == null)
            return head1;
        Node newHead = new Node(0);
        Node dummy = newHead;
        int cnt = k;
        while (head1 != null && head2 != null) {
            while (cnt > 0 && head1 != null) {
                dummy.next = head1;
                dummy = dummy.next;
                head1 = head1.next;
                cnt--;
            }
            cnt = k;
            while (cnt > 0 && head2 != null) {
                dummy.next = head2;
                dummy = dummy.next;
                head2 = head2.next;
                cnt--;
            }
            cnt = k;
        }
        if (head1 != null)
            dummy.next = head1;
        if (head2 != null)
            dummy.next = head2;
        return newHead.next;
    }

    /**
     * Partition a linked list into primes, composites, and ones.
     * 
     * @param head the head of the linked list
     * @return the head of the partitioned linked list
     */
    public Node partitionPrimes(Node head) {
        if (head == null)
            return null;
        Node primes = new Node(0);
        Node composites = new Node(0);
        int ones = 0;
        Node pTail = primes;
        Node cTail = composites;

        while (head != null) {
            if (head.val == 1) {
                ones++;
            } else if (isPrime(head.val)) {
                pTail.next = new Node(head.val);
                pTail = pTail.next;
            } else {
                cTail.next = new Node(head.val);
                cTail = cTail.next;
            }
            head = head.next;
        }
        pTail.next = composites.next;
        while (ones > 0) {
            cTail.next = new Node(1);
            cTail = cTail.next;
            ones--;
        }
        return primes.next;
    }

    /**
     * Helper method to check if a number is prime.
     * 
     * @param num the number to check
     * @return {@code boolean} true if the number is prime, false otherwise
     */
    private boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i * i <= num; i++)
            if (num % i == 0)
                return false;
        return true;
    }

    /**
     * Collapse consecutive nodes with the same value in a linked list of NodeCount.
     * 
     * @param head the head of the linked list
     * @return the head of the collapsed linked list
     */
    public NodeCount collapse(NodeCount head) {
        if (head == null)
            return null;
        NodeCount prev = head, curr = (NodeCount) head.next;
        while (curr != null) {
            while (curr != null && curr.val == prev.val) {
                prev.count += curr.count;
                prev.next = curr.next;
                curr = (NodeCount) curr.next;
            }
            prev = curr;
            if (curr != null) {
                curr = (NodeCount) curr.next;
            }
        }
        return head;
    }

    /**
     * Flatten a multi-level doubly linked list with alternating reversal of child
     * lists.
     * 
     * @param head the head of the multi-level doubly linked list
     * @return the head of the flattened linked list
     */
    public MultiNode flatten(MultiNode head) {
        MultiNode curr = head;
        Stack<MultiNode> st = new Stack<>();
        boolean oddLevel = true;
        while (curr != null) {
            if (curr.child != null) {
                if (oddLevel)
                    curr.child = reverse(curr.child);
                curr.child.prev = curr;
                if (curr.next != null) {
                    MultiNode temp = (MultiNode) curr.next;
                    temp.prev = null;
                    st.push((MultiNode) curr.next);
                }
                curr.next = curr.child;
                curr.child = null;
                oddLevel = !oddLevel;
            } else {
                if (curr.next == null && !st.isEmpty()) {
                    curr.next = st.pop();
                    if (curr.next != null) {
                        MultiNode temp = (MultiNode) curr.next;
                        temp.prev = curr;
                    }
                    oddLevel = !oddLevel;
                }
                curr = (MultiNode) curr.next;
            }
        }
        return head;
    }

    /**
     * Helper method to reverse a doubly linked list.
     * 
     * @param head the head of the doubly linked list
     * @return the head of the reversed doubly linked list
     */
    private MultiNode reverse(MultiNode head) {
        MultiNode prev = null;
        MultiNode curr = head;
        while (curr != null) {
            MultiNode next = (MultiNode) curr.next;
            curr.next = prev;
            if (prev != null) {
                prev.prev = curr;
            }
            prev = curr;
            curr = next;
        }
        return prev;
    }
}