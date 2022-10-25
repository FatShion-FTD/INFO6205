package Assignment4;

import Assignment2.TreeNode;

public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;
        if (root.val < low)
            return rangeSumBST(root.right, low, high);
        if (root.val > high)
            return rangeSumBST(root.left, low, high);

        int sub = rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        return root.val >= low && root.val <= high ? root.val + sub : sub;
    }
}
