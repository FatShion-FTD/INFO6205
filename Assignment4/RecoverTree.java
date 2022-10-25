package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class RecoverTree {
    List<TreeNode[]> errors = new ArrayList<>();
    TreeNode prev = null;

    public void recoverTree(TreeNode root) {
        inOrder(root);

        // find error pairs
        if (errors.size() == 1) { // only one pair error, just swap
            TreeNode[] p = errors.get(0);
            int t = p[0].val;
            p[0].val = p[1].val;
            p[1].val = t;
        } else { // 2 pair error
            TreeNode[] p1 = errors.get(0), p2 = errors.get(1);
            int t = p1[0].val;
            p1[0].val = p2[1].val;
            p2[1].val = t;
        }
    }

    private void inOrder(TreeNode node) {
        if (node == null)
            return;

        inOrder(node.left);

        if (prev != null && node.val < prev.val)
            errors.add(new TreeNode[] { prev, node });
        prev = node;

        inOrder(node.right);
    }
}
