package pers.yibo.algorithms.leetcode;

/**
 * 1800. 最大升序子数组和
 * <p>
 * https://leetcode-cn.com/problems/maximum-ascending-subarray-sum/
 * <p>
 * 给你一个正整数组成的数组 nums ，返回 nums 中一个 升序 子数组的最大可能元素和。
 *
 * @author yibo
 * @date 2021-11-23 17:40
 **/
public class MaximumAscendingSubarraySum {

    public int maxAscendingSum(int[] nums) {
        int max = 0;
        int tmp = 0;
        int last = nums[0];
        for (int num : nums) {
            if (num <= last) {
                max = Math.max(max, tmp);
                tmp = num;
            } else {
                tmp += num;
            }
            last = num;
        }
        max = Math.max(max, tmp);
        return max;
    }
}
