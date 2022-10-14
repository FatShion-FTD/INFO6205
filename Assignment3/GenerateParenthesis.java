package Assignment3;

import java.util.*;

public class GenerateParenthesis {
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(0, 0, n, new StringBuilder());
        return res;
    }

    private void dfs(int lefts, int rights, int n, StringBuilder sb) {
        if (lefts == n && rights == n) {
            res.add(sb.toString());
            return;
        }
        if (lefts < n) {
            sb.append('(');
            dfs(lefts + 1, rights, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (lefts > rights) {
            sb.append(')');
            dfs(lefts, rights + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
