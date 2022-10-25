package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class BSTIterator {
    Deque<TreeNode> s;

    public BSTIterator(TreeNode root) {
        s = new ArrayDeque<>();
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = s.pop();
        TreeNode res = node;

        node = node.right;
        while (node != null) {
            s.push(node);
            node = node.left;
        }

        return res.val;
    }

    public boolean hasNext() {
        return !s.isEmpty();
    }
}
