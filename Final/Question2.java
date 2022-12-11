package Final;

public class Question2 {
    public Question2() {
    }

    final static int[] DIRS = new int[] { 0, -1, 0, 1, 0 };

    public int q2(char[][] grid) {
        // M: grid.length, N: grid[0].length
        // Time complexity: O(4MN) -> O(MN), space complextiy: O(MN)
        if (grid == null || grid.length == 0)
            return 0;

        int m = grid.length, n = grid[0].length;
        UnionFind uf = new UnionFind(n * m);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    uf.size--;
                } else {
                    int cur = i * n + j;
                    for (int k = 1; k < DIRS.length; k++) {
                        int row = i + DIRS[k - 1], col = j + DIRS[k];
                        int next = row * n + col;
                        if (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == '1') {
                            uf.union(cur, next);
                        }
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

        public UnionFind(int n) {
            p = new int[n];
            r = new int[n];
            size = n;
            for (int i = 0; i < n; i++) {
                p[i] = i;
            }
        }

        public int find(int x) {
            if (p[x] == x)
                return x;
            return p[x] = find(p[x]);
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);
            if (px != py) {
                if (r[px] > r[py]) {
                    p[py] = px;
                } else if (r[py] > r[px]) {
                    p[px] = py;
                } else {
                    p[px] = py;
                    r[py]++;
                }
                size--;
            }
        }
    }
}
