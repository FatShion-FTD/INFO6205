package Assignment2;

import java.util.*;

public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;

        Map<TreeNode, TreeNode> map = new HashMap<>();
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        map.put(root, null);
        while (!dq.isEmpty()) {
            TreeNode n = dq.poll();
            if (n.left != null) {
                dq.offer(n.left);
                map.put(n.left, n);
            }
            if (n.right != null) {
                dq.offer(n.right);
                map.put(n.right, n);
            }
        }
        Set<TreeNode> path = new HashSet<>();
        while (p != null) {
            path.add(p);
            p = map.get(p);
        }
        while (q != null && !path.contains(q)) {
            q = map.get(q);
        }
        return q;
    }
}
