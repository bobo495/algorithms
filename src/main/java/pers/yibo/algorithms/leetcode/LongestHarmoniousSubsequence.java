package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 594. 最长和谐子序列
 * <p>
 * https://leetcode-cn.com/problems/longest-harmonious-subsequence
 * <p>
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * <p>
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 * <p>
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 * @author aoyb
 * 2021/11/20 16:37
 */
public class LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int max = 0;
        int low = nums[0];
        int countLow = 0;
        int countHigh = 0;

        for (int num : nums) {
            if (num == low) {
                countLow++;
            } else if (num == low + 1) {
                countHigh++;
            } else {
                if (countHigh != 0) {
                    max = Math.max(max, countLow + countHigh);
                    if (num == low + 2) {
                        low = num - 1;
                        countLow = countHigh;
                        countHigh = 1;
                    } else {
                        low = num;
                        countLow = 1;
                        countHigh = 0;
                    }
                } else {
                    low = num;
                    countLow = 1;
                }
            }
        }
        if (countLow > 0 && countHigh > 0) {
            max = Math.max(max, countLow + countHigh);
        }
        return max;
    }
}
