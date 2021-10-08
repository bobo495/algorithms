package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 15. 三数之和
 * <p>
 * https://leetcode-cn.com/problems/3sum
 * <p>
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 * <p>
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[]
 *
 * @author yibo
 * @date 2021-10-08 10:19
 **/
public class ThreeSum {

    /**
     * 第一层循环遍历first
     * <p>
     * 第二层循环固定遍历second和third，每次比较，sum>0则移动third，sum<0则移动second
     * <p>
     * 为避免重复，得到结果后再移动时，保证下一个数不一样。
     *
     * @param nums 无序数组
     * @return 和为0的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums.length < 3) {
            return results;
        }

        // 排序
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int first = nums[i];
            if (first > 0) {
                return results;
            }

            if (i > 0 && first == nums[i - 1]) {
                continue;
            }

            int leftIndex = i + 1;
            int rightIndex = nums.length - 1;
            while (leftIndex < rightIndex) {
                int left = nums[leftIndex];
                int right = nums[rightIndex];
                int sum = first + left + right;
                if (sum == 0) {
                    results.add(Arrays.asList(first, left, right));
                    // 去重
                    while (leftIndex != rightIndex && nums[leftIndex] == left) {
                        leftIndex++;
                    }
                    while(leftIndex != rightIndex && nums[rightIndex]==right){
                        rightIndex--;
                    }
                } else if (sum > 0) {
                    // 此处使用增加去重比较更耗时
                    rightIndex--;
                } else {
                    leftIndex++;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        int[] nums = new int[]{0,0,0,0};
//        int[] nums = new int[]{-2,0,0,2,2};
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
//        int[] nums = new int[]{-1, 0, 1, 0};
//        int[] nums = new int[]{3, -2, 1, 0};
        ThreeSum threeSum = new ThreeSum();
        System.out.println(threeSum.threeSum(nums));
    }
}
