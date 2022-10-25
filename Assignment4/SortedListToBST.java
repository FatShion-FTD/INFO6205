package Assignment4;

import Assignment2.TreeNode;
import Assignment1.ListNode;
import java.util.*;

public class SortedListToBST {
    ListNode node;

    public TreeNode sortedListToBST(ListNode head) {
        int size = 0;
        node = head;
        while (head != null) {
            size++;
            head = head.next;
        }
        return arrToBST(0, size - 1);
    }

    public TreeNode arrToBST(int left, int right) {
        if (left > right)
            return null;
        int mid = left + (right - left) / 2;
        TreeNode leftNode = arrToBST(left, mid - 1); // find the smallest node first
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        root.right = arrToBST(mid + 1, right);
        root.left = leftNode;
        return root;
    }
}
