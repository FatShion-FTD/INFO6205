package Assignment2;

import java.util.*;
import wheels.Pair;

public class WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;

        Deque<Pair<TreeNode, Long>> dq = new ArrayDeque<>();
        dq.offer(new Pair(root, 0l));
        int res = 1;
        while (!dq.isEmpty()) {
            int size = dq.size();
            long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = dq.peek().getKey();
                long shift = dq.poll().getValue();
                max = Math.max(max, shift);
                min = Math.min(min, shift);

                if (shift * 2 < 0)
                    System.out.println(shift);

                if (node.left != null)
                    dq.offer(new Pair(node.left, 2 * shift));

                if (node.right != null)
                    dq.offer(new Pair(node.right, 2 * shift + 1));
            }
            res = Math.max(res, (int) (max - min + 1));
        }
        return res;
    }
}
