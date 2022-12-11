package Final;

import java.util.*;

public class Question4 {
    public int q4(int[][] o) {
        // N: o.length, M: o[0].length
        // Time Complexity: O(NM), Space Complexity: O(NM)
        int m = o.length, n = o[0].length;
        int[][] dp = new int[m][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, 0);
        }

        for (int i = 0; i < m; i++) {
            if (o[i][0] == 1)
                break;
            dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (o[0][i] == 1)
                break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (o[i][j] == 1)
                    continue;

                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
