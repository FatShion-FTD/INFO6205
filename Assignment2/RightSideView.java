package Assignment2;

import java.util.*;

public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;

        Deque<TreeNode> dq = new LinkedList<>();
        dq.offer(root);
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.poll();
                if (i == size - 1)
                    res.add(node.val);

                if (node.left != null)
                    dq.offer(node.left);

                if (node.right != null)
                    dq.offer(node.right);
            }
        }
        return res;
    }
}
