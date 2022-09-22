import java.util.*;

public class RotateRight {
    // 1. Rotate List: https://leetcode.com/problems/rotate-list/
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null)
            return null;

        ListNode node = head;
        int cnt = 1;
        // connect head and tail
        while (node.next != null) {
            node = node.next;
            cnt++;
        }
        node.next = head;

        for (int i = 0; i < cnt - k % cnt; i++) {
            node = node.next;
        }
        head = node.next;
        node.next = null;

        return head;
    }
}
