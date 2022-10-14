package Assignment3;

import java.util.*;

public class CombinationSum {
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
}
