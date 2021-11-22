package pers.yibo.algorithms.leetcode;

import java.util.Random;

/**
 * 384. 打乱数组
 * <p>
 * https://leetcode-cn.com/problems/shuffle-an-array
 * <p>
 * 给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
 * <p>
 * 实现 Solution class:
 * <p>
 * Solution(int[] nums) 使用整数数组 nums 初始化对象
 * int[] reset() 重设数组到它的初始状态并返回
 * int[] shuffle() 返回数组随机打乱后的结果
 *
 * @author yibo
 * @date 2021-11-22 09:39
 **/
public class ShuffleAnArray {

    int[] nums;
    int[] shuffleNums;
    int length;
    Random random = new Random();

    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        this.shuffleNums = nums.clone();
        this.length = nums.length;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        for (int i = 0; i < length; i++) {
            swap(shuffleNums, i, random.nextInt(length));
        }
        return shuffleNums;
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
