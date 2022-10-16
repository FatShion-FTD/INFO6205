package Quiz1;

import java.util.*;

public class Rotate {
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
}
