package pers.yibo.algorithms.leetcode;

/**
 * 1218. 最长定差子序列
 * <p>
 * https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
 * <p>
 * 给你一个整数数组arr和一个整数difference，请你找出并返回 arr中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 * <p>
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 * <p>
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 *
 * @author yibo
 * @date 2021-11-05 09:29
 **/
public class LongestArithmeticSubsequenceOfGivenDifference {

    public int longestSubsequence(int[] arr, int difference) {
        // 存储所有可能的数列最后一个数字和长度
        int[] hash = new int[40001];
        for (int num : arr) {
            hash[num + 20000] = hash[num + 20000 - difference] + 1;
        }
        int maxLength = 1;
        for (int i = 10000; i <= 30001; i++) {
            maxLength = Math.max(hash[i], maxLength);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestArithmeticSubsequenceOfGivenDifference l = new LongestArithmeticSubsequenceOfGivenDifference();
        int[] arr = new int[]{1, 5, 7, 8, 5, 3, 4, 2, 1};
        int difference = -2;
        System.out.println(l.longestSubsequence(arr, difference));
    }
}
