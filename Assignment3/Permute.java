package Assignment3;

import java.util.*;

public class Permute {
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
}
