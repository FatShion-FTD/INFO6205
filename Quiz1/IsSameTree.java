package Quiz1;

import Assignment2.TreeNode;

public class IsSameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null)
            return false;

        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(q.right, p.right);
    }
}
