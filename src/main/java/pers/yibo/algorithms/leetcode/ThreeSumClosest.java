package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * <p>
 * https://leetcode-cn.com/problems/3sum-closest
 * <p>
 * 给定一个包括n 个整数的数组nums和 一个目标值target。找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * @author yibo
 * @date 2021-10-08 14:59
 **/
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);

        int result = nums[0]+nums[1]+nums[2];

        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];

            int secondIndex = i + 1;
            int thirdIndex = nums.length - 1;

            while (secondIndex < thirdIndex) {
                int second = nums[secondIndex];
                int third = nums[thirdIndex];
                int sum = first + second + third;
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if (target - sum < Math.abs(result - target)) {
                        result = sum;
                    }
                    secondIndex++;
                } else {
                    if (sum - target < Math.abs(result - target)) {
                        result = sum;
                    }
                    thirdIndex--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSumClosest threeSumClosest = new ThreeSumClosest();
        int[] nums = new int[]{-3, -2, -5, 3, -4};
        int target = -1;
        System.out.println(threeSumClosest.threeSumClosest(nums, target));
    }
}
