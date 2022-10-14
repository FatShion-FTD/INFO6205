package Assignment3;

import java.util.*;

public class AllPathsSourceTarget {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[][] g;
    int n;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.g = graph;
        this.n = graph.length;
        path.add(0);
        dfs(0);
        return res;
    }

    private void dfs(int index) {
        if (index == n - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        int[] nexts = g[index];
        for (int next : nexts) {
            path.add(next);
            dfs(next);
            path.remove(path.size() - 1);
        }
    }
}
