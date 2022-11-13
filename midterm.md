## 138. Copy List with Random Pointer
https://leetcode.com/problems/copy-list-with-random-pointer/

```java
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        
        List<Node> list = new ArrayList<>();
        Map<Node, Integer> map = new HashMap<>();
        Node node = head;
        int cnt = 0;    
        
        while (node != null) {
            Node n = new Node(node.val);
            if (!list.isEmpty())
                list.get(list.size() - 1).next = n;
            
            map.put(node, cnt++);
            list.add(n);
            node = node.next;
        }
        
        node = head;
        cnt = 0;
        
        while (node != null) {
            if (node.random != null) {
                int index = map.get(node.random);
                Node random = list.get(index);
                list.get(cnt).random = random;
            }
            node = node.next;
            cnt++;
        }
        
        return list.get(0);
    }
```
## 19. Remove Nth Node From End of List
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

```java
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return null;
        ListNode dummy = new ListNode(-1, head);
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        head = dummy;
        while (fast != null) {
            head = head.next;
            fast = fast.next;
        }
        ListNode next = head.next;
        head.next = next.next;
        next = null;        // help GC
        
        return dummy.next;
    }
```
## 124. Binary Tree Maximum Path Sum
https://leetcode.com/problems/binary-tree-maximum-path-sum/

```java
class Solution {
    int res = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    
    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        
        int left = dfs(node.left);
        int right = dfs(node.right);
        
        // 已当前为根节点
        int sum1 = node.val + Math.max(0, left) + Math.max(0, right);   // 和可能为负数
        // 当前 node 不是根节点, 接着去parent
        int sum2 = node.val + Math.max(left, right);
        
        res = Math.max(res, Math.max(sum1, node.val));
        
        return Math.max(sum2, node.val);
    }
}
```
## 543. Diameter of Binary Tree
https://leetcode.com/problems/diameter-of-binary-tree/


```java
    int res = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return res - 1;
    }
    
    private int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        
        int rootLen = 1 + left + right;
        int nonRootLen = 1 + Math.max(left, right);
        res = Math.max(res, rootLen);
        return nonRootLen;
    }
```

## 951. Flip Equivalent Binary Trees
https://leetcode.com/problems/flip-equivalent-binary-trees/

```java
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
            flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }
```

## 22. Generate Parentheses
https://leetcode.com/problems/generate-parentheses/

```java
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n, new StringBuilder());
        return res;
    }
    
    private void dfs(int left, int right, int n, StringBuilder sb) {
        if (left + right == 2 * n) {
            res.add(sb.toString());
        }
        if (left < n) {
            sb.append('(');
            dfs(left + 1, right, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            sb.append(')');
            dfs(left, right + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
```
## 34. Find First and Last Position of Element in Sorted Array
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/


```java
    public int[] searchRange(int[] nums, int target) {
        int index = binarySearch(nums, target);
        int[] res = new int[]{index, index};
        if (index == -1)
            return res;
        
        while (res[0] > 0 && nums[res[0]] == nums[res[0] - 1]) res[0]--;
        while (res[1] < nums.length - 1 && nums[res[1]] == nums[res[1] + 1]) res[1]++;
        return res;
    }
    
    private int binarySearch(int[] nums, int tar) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == tar)
                return mid;
            if (nums[mid] > tar) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
```
## 17. Letter Combinations of a Phone Number
https://leetcode.com/problems/letter-combinations-of-a-phone-number/


```java
    Map<Integer, List<Character>> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return res;
        
        char c = 'a';
        for (int i = 2; i < 10; i++) {
            List<Character> list = new ArrayList<>();
            if (i == 7 || i == 9) {
                for (int j = 0; j < 4; j++) {
                    list.add(c);
                    c++;
                }
            } else {
                for (int j = 0; j < 3; j++) {
                    list.add(c);
                    c++;
                }
            }
            map.put(i, list);
        }    
        dfs(0, digits, new StringBuilder());
        return res;
    }
    
    private void dfs(int index, String n, StringBuilder sb) {
        if (index == n.length()) {
            res.add(sb.toString());
            return;
        }
        List<Character> chars = map.get(n.charAt(index) - '0');
        for (char c : chars) {
            sb.append(c);
            dfs(index + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
```

## 852. Peak Index in a Mountain Array
https://leetcode.com/problems/peak-index-in-a-mountain-array/

```java
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid])
                return mid;
            
            if (arr[mid - 1] > arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
```
## 56. Merge Intervals
https://leetcode.com/problems/merge-intervals/

```java
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<int[]> res = new ArrayList<>();
        int index = 0, n = intervals.length, soFar = 0;;
        while (index < n) {
            int[] t = new int[2];
            t[0] = intervals[index][0];
            soFar = intervals[index][1];
            while (index < n - 1 && intervals[index + 1][0] <= soFar) {
                soFar = Math.max(soFar, intervals[index + 1][1]);
                index++;
            }
            t[1] = soFar;
            res.add(t);
            index++;
        }
        
        int[][] fin = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            fin[i] = res.get(i);
        }
        return fin;
    }
```
## 114. Flatten Binary Tree to Linked List
https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

```java
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        
        while (root != null) {
            if (root.left != null) {
                TreeNode left = root.left;
                while (left.right != null) {
                    left = left.right;
                }
                left.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }
```
## 143. Reorder List
https://leetcode.com/problems/reorder-list/
```java
    public void reorderList(ListNode head) {
        if (head == null)
            return;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        int n = stack.size();
        node = head;
        for (int i = 0; i < n / 2; i++) {
            ListNode t = stack.pop();
            t.next = node.next;
            node.next = t;
            node = t.next;
        }
        node.next = null;
    }
```
## 46. Permutations
https://leetcode.com/problems/permutations/

```java
    Set<Integer> v = new HashSet<>();
    List<List<Integer>> res = new ArrayList<>();
    int n;
    
    public List<List<Integer>> permute(int[] nums) {
        this.n = nums.length;
        dfs(new ArrayList<>(), nums, 0);
        return res;
    }
    
    private void dfs(List<Integer> t, int[] nums, int len) {
        if (len == n) {
            res.add(new ArrayList<>(t));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!v.contains(i)) {
                v.add(i);
                t.add(nums[i]);
                dfs(t, nums, len + 1);
                t.remove(t.size() - 1);
                v.remove(i);
            }
        }
    }
```
## 78. Subsets
https://leetcode.com/problems/subsets/
```java
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> t = new ArrayList<>();
    
    public List<List<Integer>> subsets(int[] nums) {
        dfs(0, nums);
        return res;
    }
    
    private void dfs(int index, int[] nums) {
        res.add(new ArrayList<>(t));
        for (int i = index; i < nums.length; i++) {
            t.add(nums[i]);
            dfs(i + 1, nums);
            t.remove(t.size() - 1);
        }
    }
```

## 98. Validate Binary Search Tree
https://leetcode.com/problems/validate-binary-search-tree/
```java
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }
    private boolean check(TreeNode node, long max, long min) {
        if (node == null)
            return true;
        return node.val > min && node.val < max && 
            check(node.left, node.val, min) && check(node.right, max, node.val);
    }
```
## 426. Convert Binary Search Tree to Sorted Doubly Linked List
https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

```java
    public Node treeToDoublyList(Node root) {
        List<Node> inOrders = new ArrayList<>();
        inorderTrav(inOrders, root);
        
        // edge case
        if (root == null)
            return null;
        if (inOrders.size() == 1) {
            root.left = root;
            root.right = root;
            return root;
        }
            
        
        // link smallest and largest
        Node smallest = inOrders.get(0);
        Node largest = inOrders.get(inOrders.size() - 1);
        smallest.left = largest;
        smallest.right = inOrders.get(1);
        largest.left = inOrders.get(inOrders.size() - 2);
        largest.right = smallest;
        
        // link rest
        for (int i = 1; i < inOrders.size() - 1; i++) {
            Node n = inOrders.get(i);
            n.left = inOrders.get(i-1);
            n.right = inOrders.get(i+1);
        }
        
        return smallest;
    }
    
    private void inorderTrav(List<Node> list, Node node) {
        if (node == null)
            return;
        inorderTrav(list, node.left);
        list.add(node);
        inorderTrav(list, node.right);
        return;
    }
```
## 314. Binary Tree Vertical Order Traversal
https://leetcode.com/problems/binary-tree-vertical-order-traversal/
```java
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();        
        Deque<Pair<TreeNode, Integer>> dq = new LinkedList<>();
        dq.offer(new Pair(root, 0));
        
        while (!dq.isEmpty()) {
            Pair<TreeNode, Integer> p = dq.poll();
            TreeNode node = p.getKey();
            int shift = p.getValue();
            List<Integer> list = map.getOrDefault(shift, new ArrayList<>());
            list.add(node.val);
            map.put(shift, list);
            
            if (node.left != null) 
                dq.offer(new Pair(node.left, shift - 1));
            if (node.right != null) 
                dq.offer(new Pair(node.right, shift + 1));
        }
        
        for (Integer k : map.keySet()) {
            res.add(map.get(k));    
        }
        return res;
    }
```
## 23. Merge k Sorted Lists
https://leetcode.com/problems/merge-k-sorted-lists/
```java
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0)  return null;
        return mergeK(lists, 0, lists.length - 1);
    }
    // divide and conquar
    private ListNode mergeK(ListNode[] lists, int left, int right){
        if(right > left){
            int mid = left + (right - left) / 2;
            return mergeTwo(mergeK(lists, left, mid), mergeK(lists, mid+1, right));
        }
        return lists[left];
    }
    
    private ListNode mergeTwo(ListNode list1, ListNode list2){
        ListNode head = new ListNode();
        ListNode dummy = head;
        while(list1 != null && list2 != null){
            if(list1.val > list2.val){
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }else{
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            }
        }
        if(list1 != null){
            head.next = list1;
        }
        if(list2 != null){
            head.next = list2;
        }
        return dummy.next;
    }
```
## 21. Merge Two Sorted Lists
https://leetcode.com/problems/merge-two-sorted-lists/
```java
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        while(l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }
        if (l1 != null) node.next = l1;
        if (l2 != null) node.next = l2;
        return dummy.next;
    }
```
## 16. 3Sum Closest
https://leetcode.com/problems/3sum-closest/
```java
public int threeSumClosest(int[] nums, int target) {
    if (nums == null || nums.length == 0)
        return 0;
    
    Arrays.sort(nums);
    int n = nums.length, left = 0, right = 0, sum = nums[0] + nums[1] + nums[2];
    for (int i = 0; i < n - 2; i++) {
        left = i + 1;   right = n - 1;
        while (left < right) {
            int curSum = nums[i] + nums[left] + nums[right];
            if (curSum == target)
                return target;
            
            if (Math.abs(target - curSum) < Math.abs(target - sum))
                sum = curSum;
            
            if (curSum < target)
                left++;
            else
                right--;
        }
    }
    return sum;
}
```
## 35. Search Insert Position
https://leetcode.com/problems/search-insert-position/
```java
public int searchInsert(int[] nums, int target) {
    int left = 0, right = nums.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```
## 404. Sum of Left Leaves
https://leetcode.com/problems/sum-of-left-leaves/
```java
public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)
        return 0;
    if (root.left != null && root.left.left == null && root.left.right == null)
        return root.left.val + sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
}
```
## 451. Sort Characters By Frequency
https://leetcode.com/problems/sort-characters-by-frequency/
```java
public String frequencySort(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
        map.put(c, map.getOrDefault(c, 0) + 1);
    }
    List<Character> list = new ArrayList<>(map.keySet());
    Collections.sort(list, (o1, o2) -> map.get(o2) - map.get(o1));
    StringBuilder sb = new StringBuilder();
    for (char c : list) {
        int n = map.get(c);
        while (n > 0) {
            sb.append(c);
            n--;
        }
    }
    return sb.toString();
}
```
## 199. Binary Tree Right Side View
https://leetcode.com/problems/binary-tree-right-side-view/
```java
public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null)
        return res;
    
    Deque<TreeNode> dq = new LinkedList<>();
    dq.offer(root);
    while (!dq.isEmpty()) {
        int size = dq.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = dq.poll();
            if (i == size - 1)
                res.add(node.val);
            
            if (node.left != null)
                dq.offer(node.left);
            
            if (node.right != null)
                dq.offer(node.right);
        }
    }
    return res;
}
```
## 116. Populating in Each Node
https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
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
            
            if (node.left != null)
                q.offer(node.left);
            if (node.right != null)
                q.offer(node.right);
        }
        prev.next = null;
    }
    return root;
}
```
## 230. Kth Smallest Element in a BST
https://leetcode.com/problems/kth-smallest-element-in-a-bst/
```java
public int kthSmallest(TreeNode root, int k) {
    int cnt = countNode(root.left);
    if (cnt + 1 == k)
        return root.val;
    
    if (cnt < k) {
        return kthSmallest(root.right, k - cnt - 1);
    }
    return kthSmallest(root.left, k);
}

private int countNode(TreeNode node) {
    if (node == null)
        return 0;
    return 1 + countNode(node.left) + countNode(node.right);
}
```
## 443. String Compression
https://leetcode.com/problems/string-compression/
```java
    public int compress(char[] chars) {
        int index = 0, i = 0, n = chars.length;
        Deque<Integer> dq = new ArrayDeque<>();
        
        while (i < n) {
            char c = chars[i];
            int cnt = 1;
            while (i + 1 < n && chars[i] == chars[i + 1]) {
                i++;
                cnt++;
            }
            
            chars[index++] = c;
            if (cnt > 1) {
                while (cnt != 0) {
                    dq.push(cnt % 10);
                    cnt /= 10;
                }
                while(!dq.isEmpty()) {
                    chars[index++] = (char)(dq.pop() + '0');
                }
            }
            i++;
        }
        return index;
    }
```
## 238. Product of Array Except Self
https://leetcode.com/problems/product-of-array-except-self/
```java
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0)
            return new int[]{};
        
        int n = nums.length, sufSum = 1;
        int[] res = new int[n];
        res[0] = 1;
        // prefix
        for (int i = 1; i < n; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }
        // suffix
        for (int i = n - 2; i >= 0; i--) {
            sufSum *= nums[i + 1];
            res[i] *= sufSum;
        }
        return res;
    }
```
## 973. K Closest Points to Origin
https://leetcode.com/problems/k-closest-points-to-origin/
```java
public int[][] kClosest(int[][] points, int k) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
        @Override
        public int compare(int[] o1, int[] o2) {
            long dis1 = o1[0] * o1[0] + o1[1] * o1[1];
            long dis2 = o2[0] * o2[0] + o2[1] * o2[1];
            return (int)(dis2 - dis1);
        }
    });
    
    for (int[] pts : points) {
        pq.offer(pts);
        if (pq.size() > k)
            pq.poll();
    }
    
    int[][] res = new int[k][2];
    for (int i = 0; i < k; i++) {
        res[i] = pq.poll();
    }
    return res;
}
```
## 350. Intersection of Two Arrays II
https://leetcode.com/problems/intersection-of-two-arrays-ii/
```java
public int[] intersect(int[] nums1, int[] nums2) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums1) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    List<Integer> res = new ArrayList<>();
    for (int num : nums2) {
        if (map.containsKey(num)) {
            res.add(num);
            int cnt = map.get(num) - 1;
            if (cnt != 0)
                map.put(num, cnt);
            else
                map.remove(num);
        }
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
}
```
## 79. Word Search
https://leetcode.com/problems/word-search/
```java
    final static int[] DIRS = new int[]{-1, 0, 1, 0, -1};
    
    public boolean exist(char[][] board, String word) {
        boolean res = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                res |= dfs(i, j, 0, board, word);
            }
        }
        return res;
    }
    
    public boolean dfs(int row, int col, int index, char[][] board, String word) {
        if (index == word.length())
            return true;
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return false;
        
        boolean res = false;
        if (board[row][col] == word.charAt(index)) {
            char ori = board[row][col];
            board[row][col] = '!';
            for (int i = 1; i < DIRS.length; i++) {
                int newRow = row + DIRS[i-1], newCol = col + DIRS[i];
                res |= dfs(newRow, newCol, index + 1, board, word);
            }
            board[row][col] = ori;
        }
        return res;
    }
```
## 26. Remove Duplicates from Sorted Array
https://leetcode.com/problems/remove-duplicates-from-sorted-array/
```java
public int removeDuplicates(int[] nums) {
    int index = 0;
    for (int i = 0; i < nums.length; i++) {
        if (i + 1 < nums.length && nums[i] == nums[i + 1])
            continue;
        nums[index++] = nums[i];
    }
    return index;
}
```
## 104. Maximum Depth of Binary Tree
https://leetcode.com/problems/maximum-depth-of-binary-tree/
```java
public int maxDepth(TreeNode root) {
    if (root == null)
        return 0;
    
    int depth = 0;
    Deque<TreeNode> dq = new LinkedList<>();
    dq.offer(root);
    while (!dq.isEmpty()) {
        int size = dq.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = dq.poll();
            if (node.left != null)
                dq.offer(node.left);
            if (node.right != null)
                dq.offer(node.right);
        }
        depth++;
    }
    return depth;
}
```
## 18. 4Sum
https://leetcode.com/problems/4sum/
```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    // sort: O(NlogN), find: O(n^3) -> most time complexity: 8 * 10 ^ 6
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length == 0)
        return res;
    
    int n = nums.length;
    Arrays.sort(nums);          // ascending order
    for (int i = 0; i <= n - 3; i++) {          // first number
        if (i > 0 && nums[i - 1] == nums[i])
            continue;
        
        for (int j = i + 1; j <= n - 2; j++) {  // second number     
            if (j > i + 1 && nums[j - 1] == nums[j])
                continue;
            
            long rem = target - (long)(nums[i] + nums[j]);
            int left = j + 1, right = n - 1;
            while (left < right) {
                long cur = nums[left] + nums[right];
                if (cur == rem) {
                    List<Integer> t = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                    res.add(t);
                    while (left < right && nums[left] == nums[left + 1])    left++;
                    left++;
                    
                    while (left < right && nums[right] == nums[right - 1])  right--;
                    right--;
                    
                } else if (cur > rem) {
                    while (left < right && nums[right] == nums[right - 1])  right--;
                    right--;
                } else {
                    while (left < right && nums[left] == nums[left + 1])    left++;
                    left++;
                }
            }
        }
    }
    return res;
}
```
## 229. Majority Element II
https://leetcode.com/problems/majority-element-ii/
```java
public List<Integer> majorityElement(int[] nums) {
    List<Integer> res = new ArrayList<>();
    if (nums == null || nums.length == 0)
        return res;
    
    int n = nums.length;
    int len = n / 3; 
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
    for (int k : map.keySet()) {
        if (map.get(k) > len)
            res.add(k);
    }
    
    return res;
}
```
## 200. Number of Islands
https://leetcode.com/problems/number-of-islands/
```java
final static int[] DIRS = new int[]{-1, 0, 1, 0, -1};

public int numIslands(char[][] grid) {
    int m = grid.length, n = grid[0].length;
    UnionFind uf = new UnionFind(m * n);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (grid[i][j] == '0') {
                uf.size--;
            } else {
                for (int k = 1; k < DIRS.length; k++) {
                    int row = i + DIRS[k - 1], col = j + DIRS[k];
                    if (row < 0 || row >= m || col < 0 || col >= n || grid[i][j] != grid[row][col])
                        continue;
                    uf.union(n * i + j, n * row + col);
                }
            }
        }
    }
    
    return uf.size;
}

class UnionFind {
    int[] p;
    int[] r;
    int size;
    
    public UnionFind (int n) {
        size = n;
        p = new int[n];
        r = new int[n];
        for (int i = 0; i < size; i++) {
            p[i] = i;
            r[i] = 0;
        }
    }
    
    public int find (int x) {
        if (p[x] == x)
            return x;
        return p[x] = find(p[x]);
    }
    
    public void union (int x, int y) {
        int px = find(x), py = find(y);
        if (px != py) {
            if (r[px] > r[py]) {
                p[py] = px;
            } else if (r[px] < r[py]) {
                p[px] = py;
            } else {
                p[py] = px;
                r[px] ++;
            }
            size--;
        }
    }
}
```
## 39. Combination Sum
https://leetcode.com/problems/combination-sum/
```java
List<List<Integer>> res;

public List<List<Integer>> combinationSum(int[] candidates, int target) {
    res = new ArrayList<>();
    Arrays.sort(candidates);
    dfs(new ArrayList<>(), 0, target, candidates);
    return res;
}

private void dfs(List<Integer> t, int index, int rem, int[] candidates) {
    if (rem < 0)
        return;
    
    if (rem == 0) {
        res.add(new ArrayList<>(t));
        return;
    }
    for (int i = index; i < candidates.length; i++) {
        int c = candidates[i];
        t.add(c);
        dfs(t, i, rem - c, candidates);
        t.remove(t.size() - 1);
    }
}
```
## 692. Top K Frequent Words
https://leetcode.com/problems/top-k-frequent-words/
```java
public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> map = new HashMap<>();
    for (String w : words) {
        map.put(w, map.getOrDefault(w, 0) + 1);
    }
    PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>(){
        @Override
        public int compare(String s1, String s2) {
            if (map.get(s1) == map.get(s2))
                return s1.compareTo(s2);
            return map.get(s2) - map.get(s1);
        }
    });
    
    for (String key : map.keySet()) {
        pq.offer(key);
    }
    
    List<String> res = new ArrayList<>();
    for (int i = 0; i < k; i++) {
        res.add(pq.poll());
    }
    return res;
}
```
## 50. Pow(x, n)
https://leetcode.com/problems/powx-n/
```java
public double myPow(double x, int n) {
    if (n == 0)
        return 1;
    long N = n;
    if (N < 0) {
        x = 1/x;
        N = -N;            
    }
    double res = 1;
    while (N != 0) {
        if (N % 2 == 1) {
            res *= x;
        }
        x *= x;
        N /= 2;
    }
    return res;
}
```
## 15. 3Sum
https://leetcode.com/problems/3sum/
```java
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    if (nums == null || nums.length == 0)
        return res;
    
    Arrays.sort(nums);
    int n = nums.length;
    for (int start = 0; start < n - 2; start++) {
        if (start > 0 && nums[start] == nums[start - 1])
            continue;
        if (nums[start] > 0)
            break;
        
        int left = start + 1, right = n - 1;
        while (left < right) {
            int sum = nums[start] + nums[left] + nums[right];
            if (sum == 0) {
                res.add(Arrays.asList(nums[start], nums[left], nums[right]));
                while (left < right && nums[left] == nums[left + 1])    left++;
                while (left < right && nums[right] == nums[right - 1])    right--;
                left++;
                right--;
            }else if (sum < 0)
                left++;
            else
                right--;
        }
    }
    return res;
}
```
## 49. Group Anagrams
https://leetcode.com/problems/group-anagrams/
```java
public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> res = new ArrayList<>();
    if (strs == null || strs.length == 0)
        return res;
    
    Map<Integer, List<String>> map = new HashMap<>();
    for (String s : strs) {
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        int hash = Arrays.hashCode(freq);
        List<String> t = map.getOrDefault(hash, new ArrayList<>());
        t.add(s);
        map.put(hash, t);
    }
    for (List<String> v : map.values()) {
        res.add(v);
    }
    return res;
}
```
## 165. Compare Version Numbers
https://leetcode.com/problems/compare-version-numbers/
```java
public int compareVersion(String v1, String v2) {
    String[] strs1 = v1.split("\\.");
    String[] strs2 = v2.split("\\.");
    int i1 = 0, i2 = 0;
    
    while (i1 < strs1.length || i2 < strs2.length) {
        int rv1 = i1 < strs1.length ? getVersion(strs1[i1++]) : 0;
        int rv2 = i2 < strs2.length ? getVersion(strs2[i2++]) : 0;
        if (rv1 > rv2)
            return 1;
        if (rv2 > rv1)
            return -1;
    }
    return 0;
}

private int getVersion (String s) {
    char[] chars = s.toCharArray();
    int n = chars.length, i = 0;
    int v = 0;
    for (; i < n; i++) {
        if (chars[i] != '0')
            break;
    }
    for (; i < n; i++) {
        v = 10 * v + (int)(chars[i] - '0');
    }
    return v;
}
```
## 937. Reorder Data in Log Files
https://leetcode.com/problems/reorder-data-in-log-files/
```java
public String[] reorderLogFiles(String[] logs) {
    // 1. letter logs first, digit logs second
    // 2. letter logs in lexico order, 
    // 3. digits order in oringnal 
    
    List<String> letters = new ArrayList<>();
    List<String> digits = new ArrayList<>();
    for (String log : logs) {
        String[] words = log.split(" ");
        if (Character.isDigit(words[1].charAt(0)))
            digits.add(log);
        else 
            letters.add(log);
    }
    
    Collections.sort(letters, new Comparator<String>(){
        @Override
        public int compare(String s1, String s2) {
            int n1 = s1.indexOf(" ");
            int n2 = s2.indexOf(" ");
            if (s1.substring(n1).equals(s2.substring(n2)))
                return s1.substring(0, n1).compareTo(s2.substring(0, n2));
            return s1.substring(n1).compareTo(s2.substring(n2));
        }
    });
    letters.addAll(digits);
    return letters.toArray(new String[0]);
}
```
## 206. Reverse Linked List
https://leetcode.com/problems/reverse-linked-list/
```java
public ListNode reverseList(ListNode head) {
    ListNode dummy = null;
    while(head != null){
        ListNode next = head.next;
        head.next = dummy;
        dummy = head;
        head = next;
    }
    return dummy;
}
```
## 215. Kth Largest Element in an Array
https://leetcode.com/problems/kth-largest-element-in-an-array/
```java
public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();  
    for(int i = 0; i < k; i++)  pq.offer(nums[i]);
    for(int i = k; i < nums.length; i++){
        pq.offer(nums[i]);
        if (pq.size() > k) 
            pq.poll();
    }
    return pq.poll();
}
```
## 253. Meeting Rooms II
https://leetcode.com/problems/meeting-rooms-ii/
```java
public int minMeetingRooms(int[][] intervals) {
    Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
    TreeMap<Integer, Integer> tm = new TreeMap<>();
    for (int[] interval : intervals) {
        int start = interval[0], end = interval[1];
        Integer closeEnd = tm.floorKey(start);
        
        if (closeEnd != null) {
            int rem = tm.get(closeEnd) - 1;
            if (rem == 0)
                tm.remove(closeEnd);
            else
                tm.put(closeEnd, rem);
        }
            
        
        tm.put(end, tm.getOrDefault(end, 0) + 1);
    }
    
    int res = 0;
    for (Map.Entry<Integer, Integer> e : tm.entrySet()) {
        res += e.getValue();
    }
    return res;
}
```
## 167. Two Sum II - Input Array Is Sorted
https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
```java
public int[] twoSum(int[] nums, int target) {   
    int left = 0, right = nums.length - 1;
    while(right > left){
        int tar = nums[left] + nums[right];
        if(tar == target){
            return new int[]{left + 1, right + 1};
        }else if(tar > target){
            right--;
        }else{
            left++;
        }
    }
    return new int[2];
}
```
## 103. Binary Tree Zigzag Level Order Traversal
https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null)
        return res;
    
    Deque<TreeNode> dq = new LinkedList<>();
    boolean isZig = false;
    dq.offer(root);
    while (!dq.isEmpty()) {
        List<Integer> t = new LinkedList<>();
        int size = dq.size();
        for (int i = 0; i < size; i++) {
            TreeNode node =  dq.poll();
            t.add(node.val);
            
            if (node.left != null)
                dq.offer(node.left);
            if (node.right != null)
                dq.offer(node.right);
        }
        if (isZig) 
            Collections.reverse(t);
            
        res.add(t);
        isZig = !isZig;
    }
    return res;
}
```
## 25. Reverse Nodes in k-Group
https://leetcode.com/problems/reverse-nodes-in-k-group/
```java
public ListNode reverseKGroup(ListNode head, int k) {
    if(head == null || head.next == null || k == 1)  return head;
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode begin = dummy;
    int index = 0;
    while(head != null){
        index++;
        if(index % k == 0){
            begin = reverse(begin, head.next);
            head = begin.next;
        }else{
            head = head.next;
        }
    }
    return dummy.next;
}

private ListNode reverse(ListNode head, ListNode tail){
    ListNode curr = head.next;
    ListNode next, first;
    ListNode prev = head;
    first = curr;
    while(curr!= tail){
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    head.next = prev;
    first.next = curr;
    return first;
}
```
## 445. Add Two Numbers II
https://leetcode.com/problems/add-two-numbers-ii/
```java
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        while (l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }
        Collections.reverse(list1);
        Collections.reverse(list2);
        
        ListNode dummy = new ListNode(-1);
        ListNode node = dummy;
        int i1 = 0, i2 = 0, carr = 0;
        List<Integer> resList = new ArrayList<>();
        
        while (i1 < list1.size() || i2 < list2.size()) {
            int val1 = i1 < list1.size() ? list1.get(i1) : 0;
            int val2 = i2 < list2.size() ? list2.get(i2) : 0;
            int sum = carr + val1 + val2;
            if (sum >= 10) {
                sum -= 10;
                carr = 1;
            } else
                carr = 0;
            resList.add(sum);
            i1++; i2++;
        }
        if (carr != 0)
            resList.add(carr);
        
        for (int i = resList.size() - 1; i >= 0; i--) {
            ListNode newNode = new ListNode(resList.get(i));
            node.next = newNode;
            node = newNode;
        }
        
        return dummy.next;
    }
```
## 105. Construct Binary Tree from Preorder and Inorder Traversal
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
```java
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] pre, int[] in) {
        if(pre.length == 0 || in.length == 0)
            return null;
        
        for(int i = 0; i < in.length; i++)
            map.put(in[i], i);
        return helper(pre, 0, pre.length - 1, in.length - 1);
    }
    
    private TreeNode helper(int[] pre, int preStart, int preEnd, int inEnd){
        if(preStart > preEnd)
            return null;
        
        TreeNode root = new TreeNode(pre[preStart]);
        int rightSubSize = inEnd - map.get(root.val);
        root.left = helper(pre, preStart + 1, preEnd - rightSubSize, map.get(root.val) - 1);
        root.right = helper(pre, preEnd - rightSubSize + 1, preEnd, inEnd);
        return root;
    }
```
## 235. Lowest Common Ancestor of a Binary Search Tree
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null)
        return null;
    
    int pVal = p.val, qVal = q.val;
    while (root != null) {
        if (pVal > root.val && qVal > root.val) {
            root = root.right;
        } else if (pVal < root.val && qVal < root.val) {
            root = root.left;
        } else {
            break;
        }
    }
    return root;
}
```
## 160. Intersection of Two Linked Lists
https://leetcode.com/problems/intersection-of-two-linked-lists/
```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int lenA = getLen(headA), lenB = getLen(headB);
    if(lenA > lenB){
        headA = alignHead(headA, lenA - lenB);
    }else{
        headB = alignHead(headB, lenB - lenA);            
    }
    
    while(headA != null){
        if(headA == headB)  return headA;
        headA = headA.next;
        headB = headB.next;
    }
    return null;
}

private ListNode alignHead(ListNode node, int len){
    while(len != 0){
        node = node.next;
        len--;
    }
    return node;
}

private int getLen(ListNode node){
    int len = 0;
    while(node != null){
        node = node.next;
        len++;
    }
    return len;
}
```
## 33. Search in Rotated Sorted Array
https://leetcode.com/problems/search-in-rotated-sorted-array/
```java
public int search(int[] nums, int target) {
    if(nums.length == 0 || nums == null)    return -1;
    
    int lo = 0, hi = nums.length - 1;
    while(hi >= lo){
        int mid = lo + (hi - lo) / 2;
        if(nums[mid] == target){
            return mid;
        }else if(nums[hi] > nums[lo]){ // in same trend
            if(nums[mid] < target){
                lo = mid + 1;
            } else  hi = mid - 1;
        }else{
            if(nums[mid] > nums[hi]){
                if(nums[mid] > target && target > nums[hi]){
                    hi = mid - 1;
                }else   lo = mid + 1;
            }else{
                if(nums[mid] < target && target < nums[lo]){
                    lo = mid + 1;
                }else   hi = mid - 1;
            }
        }
    }
    return -1;
}
```
## 75. Sort Colors
https://leetcode.com/problems/sort-colors/
```java
    public void sortColors(int[] nums) {
        // 3 ptrs
        int zeros = 0, ones = 0, twos = 0;
        for (int num : nums) {
            if (num == 0)
                zeros++;
            if (num == 1)
                ones++;
            if (num == 2)
                twos++;
        }
        place(nums, 0, zeros, 0);
        place(nums, zeros, ones, 1);
        place(nums, zeros + ones, twos, 2);
    }
    
    private void place(int[] nums, int index, int len, int tar) {
        for (int i = index; i < index + len; i++) {
            nums[i] = tar;
        }
    }
```
## 153. Find Minimum in Rotated Sorted Array
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
```java
public int findMin(int[] nums) {
    if (nums == null || nums.length == 0)
        return -1;
    
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] >= nums[left] && nums[left] > nums[right]) {       // 往右搜
            left = mid + 1;
        } else {                            // 往左搜
            right = mid;
        }
    }
    return nums[left];
}
```
## 154. Find Minimum in Rotated Sorted Array II
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
```java
public int findMin(int[] nums) {
    if (nums == null || nums.length == 0)
        return -1;
    
    int left = 0, right = nums.length - 1;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] < nums[right]) {
            right = mid;
        } else if (nums[mid] > nums[right]) {
            left = mid + 1;
        } else {
            right--;
        }
    }
    return nums[left];
}
```
## 4. Median of Two Sorted Arrays
https://leetcode.com/problems/median-of-two-sorted-arrays/
```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int len1 = nums1.length, len2 = nums2.length;
    int isOdd = (len1 + len2) % 2;
    // edge cases if one length is 0
    if (len1 == 0)
        return isOdd == 1 ? nums2[len2 / 2] : 1.0 * (nums2[len2 / 2 - 1] + nums2[len2 / 2]) / 2;
    if (len2 == 0)
        return isOdd == 1 ? nums1[len1 / 2] : 1.0 * (nums1[len1 / 2 - 1] + nums1[len1 / 2]) / 2;
    int rem = (len1 + len2 - 1) / 2;        // the remain 
    while(rem != 0) {
        int div = (rem + 1) / 2;
        int i = Math.max(0, len1 - div), j = Math.max(0, len2 - div);
        if (nums1[i] > nums2[j]) {      // 把大的那个裁了
            rem -= (len1 - i);
            len1 = i;
        } else {
            rem -= (len2 - j);
            len2 = j;
        }
        if (len1 == 0)
            return isOdd == 1 ? nums2[len2 - 1 - rem] : 1.0 * (nums2[len2 - 1 - rem] + nums2[len2 - 1 - rem - 1]) / 2;
        if (len2 == 0)
            return isOdd == 1 ? nums1[len1 - 1 - rem] : 1.0 * (nums1[len1 - 1 - rem] + nums1[len1 - 1 - rem - 1]) / 2;
    }
    if (isOdd == 0) {
        int larger = Math.max(nums1[len1 - 1], nums2[len2 - 1]);
        int smaller = (nums1[len1 - 1] + nums2[len2 - 1] - larger);
        if (len1 - 2 >= 0) smaller = Math.max(smaller, nums1[len1 - 2]);
        if (len2 - 2 >= 0) smaller = Math.max(smaller, nums2[len2 - 2]);
        return 1.0 * (larger + smaller) / 2;
    }
    return nums1[len1 - 1] > nums2[len2 - 1] ? nums1[len1 - 1] : nums2[len2 - 1];
}
```
## 301. Remove Invalid Parentheses
https://leetcode.com/problems/remove-invalid-parentheses/
```java
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        
        if (s == null || s.length() == 0)
            return res;
        
        Set<String> v = new HashSet<>();
        Deque<String> q = new ArrayDeque<>();
        
        q.offer(s);
        v.add(s);
        
        boolean found = false;
        
        while (!q.isEmpty()) {
            s = q.poll();
            
            if (isValid(s)) {
                res.add(s);
                found = true;
            }
            
            if (found)
                continue;
            
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')')
                    continue;
                
                String t = s.substring(0, i) + s.substring(i + 1);
                if (!v.contains(t)) {
                    q.offer(t);
                    v.add(t);
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String s) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                dq.push(1);
            if (s.charAt(i) == ')') {
                if (dq.isEmpty())
                    return false;
                dq.poll();
            }
        }
        return dq.isEmpty();
    }
```