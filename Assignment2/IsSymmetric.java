package Assignment2;

import java.util.Deque;
import java.util.LinkedList;

class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Deque<TreeNode> dq = new LinkedList<>();
        dq.push(root.left);
        dq.push(root.right);
        while (!dq.isEmpty()) {
            TreeNode n1 = dq.pop(), n2 = dq.pop();
            if (n1 == null && n2 == null)
                continue;
            if (n1 == null || n2 == null) // one is null
                return false;
            if (n1.val != n2.val) // not same value
                return false;
            dq.push(n1.left);
            dq.push(n2.right);
            dq.push(n1.right);
            dq.push(n2.left);
        }
        return true;
    }
}