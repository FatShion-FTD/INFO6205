import java.util.*;
import Assignment1.ListNode;;

public class RemoveElements {
    // 2. Remove Linked List Elements:
    // https://leetcode.com/problems/remove-linked-list-elements/
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1, head);
        removeElements(dummy, head, val);
        return dummy.next;
    }

    private void removeElements(ListNode prev, ListNode node, int val) {
        if (node == null)
            return;

        if (node.val == val) {
            prev.next = node.next;
            node = null;
            removeElements(prev, prev.next, val);
        } else {
            removeElements(node, node.next, val);
        }
        return;
    }

}
