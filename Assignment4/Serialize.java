package Assignment4;

import Assignment2.TreeNode;
import java.util.*;

public class Serialize {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
            return "#";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        int[] arr = new int[strs.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = strs[i].equals("#") ? -1 : Integer.parseInt(strs[i]);
        }
        return arrToBST(arr, 0, strs.length - 1);
    }

    private TreeNode arrToBST(int[] arr, int left, int right) {
        if (left >= right || arr[left] == -1)
            return null;
        TreeNode root = new TreeNode(arr[left]);
        int mid = left;
        while (mid < arr.length && arr[mid] <= arr[left]) {
            mid++;
        }
        root.left = arrToBST(arr, left + 1, mid);
        root.right = arrToBST(arr, mid, right);
        return root;
    }
}
