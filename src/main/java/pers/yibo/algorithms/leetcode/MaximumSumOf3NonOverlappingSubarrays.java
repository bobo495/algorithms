package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 689. 三个无重叠子数组的最大和
 * <p>
 * https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且3 * k 项的和最大的子数组，并返回这三个子数组。
 * <p>
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 *
 * @author yibo
 * @date 2021-12-08 09:11
 **/
public class MaximumSumOf3NonOverlappingSubarrays {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        int max1 = 0, max2 = 0, max3 = 0;
        int maxIndex1 = 0, max2Index1 = 0, max2Index2 = 0;
        int sum1 = 0, sum2 = 0, sum3 = 0;

        for (int i = 2 * k; i < nums.length; i++) {
            // 每次滑动窗口时，计算当前的sum
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            // 窗口填充满后
            if (i >= 3 * k - 1) {
                if (sum1 > max1) {
                    max1 = sum1;
                    maxIndex1 = i - 3 * k + 1;
                }
                // sum2与max1是没有重叠的
                // 此处更新1和2不重叠的索引
                if (max1 + sum2 > max2) {
                    max2 = max1 + sum2;
                    max2Index1 = maxIndex1;
                    max2Index2 = i - 2 * k + 1;
                }
                // max2与sum3是没有重叠的
                // 0和1必须在此处更新，因为maxIndex2改变时，2和3的窗口可能有重叠，此处2和3一定没有重叠
                if (max2 + sum3 > max3) {
                    max3 = max2 + sum3;
                    ans[0] = max2Index1;
                    ans[1] = max2Index2;
                    ans[2] = i - k + 1;
                }
                // 移除窗口首位，确保下一次新增一位时，窗口大小为k
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaximumSumOf3NonOverlappingSubarrays m = new MaximumSumOf3NonOverlappingSubarrays();
        int[] nums = new int[]{1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        System.out.println(Arrays.toString(m.maxSumOfThreeSubarrays(nums, k)));
    }
}
