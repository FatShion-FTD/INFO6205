package Final;

import java.util.*;

public class Question1 {
    public Question1() {
    }

    public static List<List<String>> q1(String[] strs) {
        // N: strs.length, M: longest str in strs, str.length()
        // Time complexity: O(NM), Space Complexity: O(NM)
        Map<Integer, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] freq = new int[26];
            for (char c : str.toCharArray()) {
                freq[c - 'a']++;
            }
            int hash = Arrays.hashCode(freq);
            List<String> list = map.getOrDefault(hash, new ArrayList<>());
            list.add(str);
            map.put(hash, list);
        }
        List<List<String>> res = new ArrayList<>();
        for (int k : map.keySet()) {
            res.add(map.get(k));
        }
        return res;
    }

    public static void main(String[] args) {
        String[] test1 = new String[] { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> res = q1(test1);
        System.out.println(res.toString());
    }
}
