package pers.yibo.algorithms.leetcode;

/**
 * 268. 丢失的数字
 * <p>
 * https://leetcode-cn.com/problems/missing-number/
 * <p>
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 * @author aoyb
 * 2021/11/6 15:55
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }
}
