package Final;

import java.util.*;

public class Question3 {
    public Question3() {
    }

    public String q3(String s) {
        // N: s.length()
        // Time complexity: O(N), Space complexity: O(N)
        Set<Integer> invalids = new HashSet<>();
        List<Integer> lefts = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lefts.add(i);
            } else if (c == ')') {
                if (lefts.isEmpty()) {
                    invalids.add(i);
                } else {
                    lefts.remove(lefts.size() - 1);
                }
            }
        }
        invalids.addAll(lefts);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!invalids.contains(i))
                sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
