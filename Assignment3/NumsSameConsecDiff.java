package Assignment3;

import java.util.*;

public class NumsSameConsecDiff {
    public int[] numsSameConsecDiff(int n, int k) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            dfs(res, i, 1, n, k);

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(List<Integer> t, int cur, int index, int n, int k) {
        if (index == n) {
            t.add(cur);
            return;
        }
        if (cur % 10 + k < 10)
            dfs(t, 10 * cur + (cur % 10 + k), index + 1, n, k);

        if (k != 0 && cur % 10 - k >= 0)
            dfs(t, 10 * cur + (cur % 10 - k), index + 1, n, k);
    }
}
