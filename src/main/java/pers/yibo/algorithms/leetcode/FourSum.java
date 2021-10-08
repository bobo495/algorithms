package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 8. 四数之和
 * <p>
 * https://leetcode-cn.com/problems/4sum
 * <p>
 * 给你一个由 n 个整数组成的数组nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]] ：
 * <p>
 * 0 <= a, b, c, d< n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * @author yibo
 * @date 2021-10-08 16:21
 **/
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums.length < 4) {
            return results;
        }

        // 排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {

            int first = nums[i];

            if (i > 0 && nums[i - 1] == first) {
                continue;
            }

            // 比较极值
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if (nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                int second = nums[j];

                if (j > i + 1 && nums[j - 1] == second) {
                    continue;
                }

                // 比较极值
                if (nums[i] + nums[j] + nums[j + 1] + nums[j+2] > target) {
                    break;
                }
                if (nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
                    continue;
                }

                int thirdIndex = j + 1;
                int fourthIndex = nums.length - 1;

                while (thirdIndex < fourthIndex) {
                    int third = nums[thirdIndex];
                    int fourth = nums[fourthIndex];
                    int sum = first + second + third + fourth;
                    if (sum == target) {
                        results.add(Arrays.asList(first, second, third, fourth));
                        // 去重
                        while (thirdIndex != fourthIndex && nums[thirdIndex] == third) {
                            thirdIndex++;
                        }
                        while (thirdIndex != fourthIndex && nums[fourthIndex] == fourth) {
                            fourthIndex--;
                        }
                    } else if (sum > target) {
                        fourthIndex--;
                    } else {
                        thirdIndex++;
                    }
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        FourSum f = new FourSum();
        int[] nums = new int[]{2, 2, 2, 2, 2};
        int target = 8;
        System.out.println(f.fourSum(nums, target));
    }
}
