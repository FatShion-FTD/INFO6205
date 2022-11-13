import java.util.*;

public class Question5 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int[] arr1 = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 5, 5, 5, 8, 9, 10, 11 };
        int[] arr2 = new int[] { 1, 4, 5, 10 };
        int[] res = getStartOfEachValues(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }

    // arr = [0,0,0,0,0,1,1,1,1,2,2,5,5,5,8,9,10,11]
    // values = [1,4,5,10]
    // output = [5, -1, 12, 17]
    private static int[] getStartOfEachValues(int[] arr1, int[] arr2) {
        int n = arr2.length;
        int[] res = new int[n];
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            if (map.containsKey(arr1[i]))
                continue;
            map.put(arr1[i], i);
        }
        for (int i = 0; i < n; i++) {
            res[i] = map.containsKey(arr2[i]) ? map.get(arr2[i]) : -1;
        }
        return res;
    }
}
