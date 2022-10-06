package Assignment2;

import java.util.*;

public class FindLeaves {
    List<List<Integer>> res;

    public List<List<Integer>> findLeaves(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return -1;

        int leftDep = dfs(node.left);
        int rightDep = dfs(node.right);
        int depth = Math.max(leftDep, rightDep) + 1;

        if (depth == res.size()) // 自底向上, 新增leaf space
            res.add(new ArrayList<>());

        res.get(depth).add(node.val);
        return depth;
    }
}
