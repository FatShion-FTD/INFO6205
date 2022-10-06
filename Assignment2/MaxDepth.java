package Assignment2;

import java.util.*;

public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int depth = 0;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();
                if (node.left != null)
                    dq.offer(node.left);
                if (node.right != null)
                    dq.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}
