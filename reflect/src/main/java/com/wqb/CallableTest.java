package callable;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by benwq on 2017/6/23.
 */
public class CallableTest {
    public static void main(String[] args) {
        int nums[] = new int[]{1, -2, -3, 4, 5, 6, 7};
        int length = lengthOfLongestSubstring("ohvhjdml");
        System.out.println(length);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int a = 1;
        double b = (double) a;

        char[] charArr = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Set<String> charSet = new HashSet<>();
        for (int i = 0; i < charArr.length; i++) {
            charSet.add(String.valueOf(charArr[i]));
        }
        int length = charSet.size();
        if (charArr.length == length) return length;
        System.out.println(length);
        int i = 0;
        int max = 0;
        while (i < charArr.length - length + 1) {
            Set<String> tempSet = new HashSet<>();
            for (int k = 0; k < length; k++) {
                if (tempSet.contains(String.valueOf(charArr[i + k]))) {
                    max = (max < tempSet.size() ? tempSet.size() : max);
                    tempSet.clear();
                } else {
                    tempSet.add(String.valueOf(charArr[i + k]));
                }
            }
            max = (max < tempSet.size() ? tempSet.size() : max);
            i++;
        }
        return max;
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        int a[][] = new int[2][];
        System.out.println(5 - 5 % 10);
        String e = "adsav";
//        a.substring();
        Map<String, Integer> wordCountMap = new HashMap<>();
        e.contains("sfdfs");
        char[] b = e.toCharArray();
        for (int i = 0; i < b.length; i++) {

        }
        Set<String> set = new HashSet<>();
        set.size();
        String c = String.valueOf(b[0]) + b[1] + b[2];
        System.out.println(c);
        if (nums == null || nums.length < 3) return null;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) continue;
            if (i > 0 && nums[i] == nums[i] - 1) continue;
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[l]);
                    temp.add(nums[r]);
                    result.add(temp);
                    l++;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return result;
    }
}
