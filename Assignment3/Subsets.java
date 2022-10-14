package Assignment3;

import java.util.*;

public class Subsets {
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
}
