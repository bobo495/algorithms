package pers.yibo.algorithms.leetcode;

import java.util.Date;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * <p>
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 *
 * @author yibo
 * @date 2021-09-18 15:28
 **/
public class CountTargetNum {
    public static int search(int[] nums, int target) {

        if (nums.length == 0) {
            return 0;
        }

        int left = nums.length / 2;
        int maxLow = 0;
        int minHigh = nums.length - 1;

        while (true) {
            if (nums[left] < target) {

                if (left == nums.length - 1) {
                    return 0;
                }

                if (nums[left + 1] == target) {
                    left += 1;
                    break;
                } else if (nums[left + 1] > target) {
                    return 0;
                } else if (nums[left + 1] < target) {
                    maxLow = left + 1;
                    left = (left + 1 + minHigh) / 2;
                }
            } else if (nums[left] == target) {
                if (left == 0) {
                    break;
                } else {
                    if (nums[left - 1] < target) {
                        break;
                    } else {
                        left = (left - 1 + maxLow) / 2;
                    }
                }
            } else {
                if (left == 0) {
                    return 0;
                } else {
                    if (nums[left - 1] < target) {
                        return 0;
                    } else {
                        minHigh = left;
                        left = (left - 1 + maxLow) / 2;
                    }
                }
            }
        }
        int right = (left + nums.length) / 2;

        while (true) {
            if (nums[right] > target) {
                if (nums[right - 1] == target) {
                    return right - left;
                } else {
                    right = (maxLow + right) / 2;
                }
            } else {
                if (right == minHigh) {
                    return right - left + 1;
                } else {
                    if (nums[right + 1] > target) {
                        return right - left + 1;
                    } else {
                        maxLow=right+1;
                        right = (right + 1 + minHigh) / 2;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        int[] test = {3, 3, 3};
//        System.out.println(search(test, 3));
        int[] test = new int[1000000];
        for(int i=0;i<10;i++){
            for(int j=0;j<100000;j++){
                test[i*100000+j]=i;
            }
        }
        System.out.println(new Date());
        System.out.println(search(test,3));
    }
}
