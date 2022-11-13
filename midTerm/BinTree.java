import java.util.*;

public class BinTree {
    public Node root;

    public BinTree() {

    }

    public void viewParent() {
        Map<Node, Node> map = new HashMap<>();
        if (root == null)
            return;

        Deque<Node> dq = new LinkedList<>();
        dq.offer(root);
        map.put(root, null);
        while (!dq.isEmpty()) {
            int size = dq.size();
            for (int i = 0; i < size; i++) {
                Node node = dq.poll();

                if (node.left != null) {
                    dq.offer(node.left);
                    map.put(node.left, node);
                }

                if (node.right != null) {
                    dq.offer(node.right);
                    map.put(node.right, node);
                }
            }
        }
        for (Node child : map.keySet()) {
            String p = map.get(child) == null ? "NULL" : String.valueOf(map.get(child).data);
            System.out.println("Child:  " + child.data + "   Parent: " + p);
        }
    }

    public void populateNextRight() {
        // BFS, layer traversal, O(logN) space
        // output format?
        if (root == null)
            return;

        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (prev != null) {
                    prev.nextLeft = node;
                }
                prev = node;

                if (node.right != null)
                    q.offer(node.right);
                if (node.left != null)
                    q.offer(node.left);
            }
            prev.nextLeft = null;
        }
        return;
    }
}
