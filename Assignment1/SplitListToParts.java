import java.util.*;
import Assignment1.ListNode;;

public class SplitListToParts {
    // 4. Split Linked List in Parts:
    // https://leetcode.com/problems/split-linked-list-in-parts/
    public ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null)
            return new ListNode[k];

        int cnt = 0;
        ListNode node = head;
        while (node != null) {
            node = node.next;
            cnt++;
        }

        ListNode[] res = new ListNode[k];
        int len = cnt / k;
        int rem = cnt % k;
        if (len == 0) {
            for (int i = 0; i < k; i++) {
                if (head == null) {
                    res[i] = null;
                    continue;
                }
                ListNode next = head.next;
                res[i] = head;
                head.next = null;
                head = next;
            }
            return res;
        }

        for (int i = 0; i < k; i++) {
            ListNode start = head;
            res[i] = start;
            if (rem != 0) {
                node = start;
                for (int j = 1; j < len + 1; j++) {
                    node = node.next;
                }
                rem--;
                head = node.next;
                node.next = null;
            } else {
                node = start;
                for (int j = 1; j < len; j++) {
                    node = node.next;
                }
                head = node.next;
                node.next = null;
            }
        }

        return res;
    }
}
