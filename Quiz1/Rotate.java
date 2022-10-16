package Quiz1;

import java.util.*;

public class Rotate {
    // Method1
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int startSwapIndex = n - k;
        List<Integer> remain = new ArrayList<>();
        for (int i = 0; i < startSwapIndex; i++) {
            remain.add(nums[i]);
        }
        for (int i = 0; i < k; i++) {
            nums[i] = nums[startSwapIndex++];
        }
        for (int i = k; i < n; i++) {
            nums[i] = remain.get(i - k);
        }
    }

    // Method2: O(1) space
    public void rotate(int[] nums, int k) {      
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }
    
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int t = nums[left];
            nums[left++] = nums[right];
            nums[right--] = t;
        }
    }
}
