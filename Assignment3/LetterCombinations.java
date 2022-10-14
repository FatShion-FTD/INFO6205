package Assignment3;

import java.util.*;

public class LetterCombinations {
    Map<Character, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return res;

        map.put('2', new char[] { 'a', 'b', 'c' });
        map.put('3', new char[] { 'd', 'e', 'f' });
        map.put('4', new char[] { 'g', 'h', 'i' });
        map.put('5', new char[] { 'j', 'k', 'l' });
        map.put('6', new char[] { 'm', 'n', 'o' });
        map.put('7', new char[] { 'p', 'q', 'r', 's' });
        map.put('8', new char[] { 't', 'u', 'v' });
        map.put('9', new char[] { 'w', 'x', 'y', 'z' });

        dfs(0, digits, new StringBuilder());
        return res;
    }

    private void dfs(int index, String digits, StringBuilder sb) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        char c = digits.charAt(index);
        for (char alph : map.get(c)) {
            sb.append(alph);
            dfs(index + 1, digits, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
