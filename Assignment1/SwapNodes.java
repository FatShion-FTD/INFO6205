import java.util.*;
import Assignment1.ListNode;;

public class SwapNodes {
    // 3. Swapping Nodes in a Linked List:
    // https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null)
            return null;

        if (k == 1) {
            int val1 = head.val;
            ListNode tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            head.val = tail.val;
            tail.val = val1;
            return head;

        } else {
            ListNode tar1 = head;
            for (int i = 1; i < k; i++) {
                tar1 = tar1.next;
            }
            ListNode tail = tar1;
            ListNode tar2 = head;
            while (tail.next != null) {
                tail = tail.next;
                tar2 = tar2.next;
            }
            int t = tar2.val;
            tar2.val = tar1.val;
            tar1.val = t;
        }

        return head;
    }
}
