package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class TreeToDoublyList {
    public Node treeToDoublyList(Node root) {
        List<Node> inOrders = new ArrayList<>();
        inorderTrav(inOrders, root);

        // edge case
        if (root == null)
            return null;
        if (inOrders.size() == 1) {
            root.left = root;
            root.right = root;
            return root;
        }

        // link smallest and largest
        Node smallest = inOrders.get(0);
        Node largest = inOrders.get(inOrders.size() - 1);
        smallest.left = largest;
        smallest.right = inOrders.get(1);
        largest.left = inOrders.get(inOrders.size() - 2);
        largest.right = smallest;

        // link rest
        for (int i = 1; i < inOrders.size() - 1; i++) {
            Node n = inOrders.get(i);
            n.left = inOrders.get(i - 1);
            n.right = inOrders.get(i + 1);
        }

        return smallest;
    }

    private void inorderTrav(List<Node> list, Node node) {
        if (node == null)
            return;
        inorderTrav(list, node.left);
        list.add(node);
        inorderTrav(list, node.right);
        return;
    }
}
