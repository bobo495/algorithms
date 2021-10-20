package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 453. 最小操作次数使数组元素相等
 * <p>
 * https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * <p>
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * @author yibo
 * @date 2021-10-20 10:07
 **/
public class MinimumMovesToEqualArrayElements {
    public int minMoves(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            if (num < min) {
                min = num;
            }
        }

        return sum - min * nums.length;
    }
}
