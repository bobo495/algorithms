package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 31. 下一个排列
 * <p>
 * https://leetcode-cn.com/problems/next-permutation/
 * <p>
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * @author yibo
 * @date 2021-10-14 16:58
 **/
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }

        // 11543 13
        // 找到需要更换的索引
        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            } else {
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            }
        }

        // 找到比nums[index]大的最小的元素
        if (index != -1) {
            int indexValue = nums[index];
            int l = index + 1;
            int r = nums.length - 1;
            int tmp = (l + r) / 2;
            while (l < r) {
                if (nums[tmp] == indexValue) {
                    tmp = tmp - 1;
                    break;
                } else if (nums[tmp] < indexValue) {
                    if (nums[tmp - 1] > indexValue) {
                        tmp = tmp - 1;
                        break;
                    }
                    r = tmp - 1;
                    tmp = (l + r) / 2;
                } else {
                    if (nums[tmp + 1] <= indexValue) {
                        break;
                    }
                    l = tmp + 1;
                    tmp = (l + r) / 2;
                }
            }
            // 交换tmp和index
            nums[index] = nums[tmp];
            nums[tmp] = indexValue;
            Arrays.sort(nums, index + 1, nums.length);
        } else {
            Arrays.sort(nums);
        }
    }


    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();
        int[] nums = new int[]{1,2,3,5,7,6,4,3,2,1};
        n.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
