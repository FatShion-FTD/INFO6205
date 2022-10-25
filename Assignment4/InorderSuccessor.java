package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 1. node.right != null, return node.right subtree min;
        // 2. node.right == null, return dfs pop/null;
        if (root == null || p == null)
            return null;

        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode node = root;

        while (node != null || !s.isEmpty()) {
            // inorder traversal
            if (node != null) {
                s.push(node);
                node = node.left;
            } else {
                node = s.pop();
                if (node == p) { // find p
                    if (node.right != null) {
                        node = node.right;
                        while (node.left != null)
                            node = node.left;
                        return node;
                    } else {
                        if (s.isEmpty())
                            return null;
                        return s.pop();
                    }
                }
                node = node.right;
            }
        }
        return null;
    }
}
