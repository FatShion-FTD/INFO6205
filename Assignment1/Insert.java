import java.util.*;

public class Insert {
    // 5. Insert into a Sorted Circular Linked List: https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        
        Node maxNode = detectMax(head), minNode = maxNode.next;
        if (insertVal <= minNode.val || insertVal >= maxNode.val) {
            Node node = new Node(insertVal, minNode);
            maxNode.next = node;
            return head;
        }
        
        while (minNode.val < insertVal) {
            minNode = minNode.next;
            maxNode = maxNode.next;
        }
        Node node = new Node(insertVal, minNode);
        maxNode.next = node;
        return head;
    }
    
    private Node detectMax(Node node) {
        Set<Node> v = new HashSet<>();
        while (node.val <= node.next.val && !v.contains(node)) {
            v.add(node);
            node = node.next;
        }
        return node;
    }
}
