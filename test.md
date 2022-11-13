```java
public Node connect(Node root) {
    // BFS, layer traversal, O(logN) space
    if (root == null)
        return null;
    
    Deque<Node> q = new ArrayDeque<>();
    q.offer(root);
    
    while (!q.isEmpty()) {
        int size = q.size();
        Node prev = null;
        for (int i = 0; i < size; i++) {
            Node node = q.poll();
            if (prev != null) {
                prev.next = node;
            }
            prev = node;

            if (node.right != null)
                q.offer(node.right);
            if (node.left != null)
                q.offer(node.left);
        }
        prev.next = null;
    }
    return root;
}

```

```java
public void rightSideView(TreeNode root) {
    Map<TreeNode, TreeNode> map = new HashMap<>();
    if (root == null)
        return;
    
    Deque<TreeNode> dq = new LinkedList<>();
    dq.offer(root);
    while (!dq.isEmpty()) {
        int size = dq.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = dq.poll();
            
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
    for (TreeNode child : map.keySet()) {
        System.out.println("Child:  " + child.data + "   Parent: " + map.get(map.get(child).data));
    }
}
```

```java
public void inRange(Node node, int left, int right) {
    if (node == null || node.data < left || node.data > right) 
        return;
    if (node.data > left)
        inRange(node.left, left, right);
    if (node.data >= left && node.data <= right)
        System.out.println(node.data + ", ");
    if (node.data < right)
        inRange(node.left, left, right);
}
```




```java
public int[] checkPosition(int[] arr1, int[] arr2) {
    int n = arr2.length;
    int[] res = new int[n];
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < arr1.length; i++) {
        if (map.containsKey(arr1[i]))
            continue;
        map.put(arr1[i], i);
    }
    for (int i = 0; i < n; i++) {
        res[i] = map.containsKey(arr2[i]) ? map.get(arr2[i]) : -1;
    }
    Arrays.toString(res);
    return res;
}
```