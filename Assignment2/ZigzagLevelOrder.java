package Assignment2;

import java.util.*;

public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Deque<TreeNode> dq = new LinkedList<>();
        boolean isZig = false;
        dq.offer(root);
        while (!dq.isEmpty()) {
            List<Integer> t = new LinkedList<>();
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();
                t.add(node.val);

                if (node.left != null)
                    dq.offer(node.left);
                if (node.right != null)
                    dq.offer(node.right);
            }
            if (isZig)
                Collections.reverse(t);

            res.add(t);
            isZig = !isZig;
        }
        return res;
    }
}
