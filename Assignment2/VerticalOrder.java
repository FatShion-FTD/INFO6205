package Assignment2;

import java.util.*;
import wheels.Pair;

public class VerticalOrder {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Deque<Pair<TreeNode, Integer>> dq = new LinkedList<>();
        dq.offer(new Pair(root, 0));

        while (!dq.isEmpty()) {
            Pair<TreeNode, Integer> p = dq.poll();
            TreeNode node = p.getKey();
            int shift = p.getValue();
            List<Integer> list = map.getOrDefault(shift, new ArrayList<>());
            list.add(node.val);
            map.put(shift, list);

            if (node.left != null)
                dq.offer(new Pair(node.left, shift - 1));
            if (node.right != null)
                dq.offer(new Pair(node.right, shift + 1));
        }

        for (Integer k : map.keySet()) {
            res.add(map.get(k));
        }
        return res;
    }
}
