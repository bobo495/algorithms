package pers.yibo.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * <p>
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 *
 * @author yibo
 * 2022/2/20 11:26
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int ans = 1;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            if (!set.contains(num + ans)) {
                continue;
            }
            int tmp = 1;
            while (set.contains(num + tmp)) {
                tmp++;
            }
            ans = Math.max(tmp, ans);
        }
        return ans;
    }
}
