package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class IsValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null)
            return true;
        return root.val > min && root.val < max &&
                isValid(root.left, min, (long) root.val) && isValid(root.right, (long) root.val, max);
    }
}
