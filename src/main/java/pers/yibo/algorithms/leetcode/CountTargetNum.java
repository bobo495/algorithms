package pers.yibo.algorithms.leetcode;

import static jdk.nashorn.internal.objects.Global.println;

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

        if (nums.length == 1) {
            return nums[0] == target ? 1 : 0;
        }

        int left = -1;
        int right = -1;

        int mid = nums.length / 2;

        while (mid > 0 && mid < nums.length - 1) {
            if (nums[mid] > target) {
                mid = mid / 2;
            } else if (nums[mid] < target) {
                mid = (mid + nums.length) / 2;
            } else {
                left = right = mid;

                while (true) {

                    int tmp = (left + 1) / 2;
                    if (nums[tmp] != target) {
                        if (nums[tmp + 1] == target) {
                            left = tmp + 1;
                            break;
                        } else {
                            left = (tmp + left) / 2;
                        }
                    } else {
                        left = tmp / 2;
                    }
                }

                while (right < nums.length - 1) {

                    int tmp = (right + nums.length) / 2;
                    if (nums[tmp] != target) {
                        if (nums[tmp - 1] == target) {
                            right = tmp;
                            break;
                        } else {
                            right = (tmp - 1 + right) / 2;
                        }
                    } else {
                        right = (tmp + nums.length) / 2;
                    }
                }

                break;
            }
        }
        if (left == -1) {
            return 0;
        } else {
            return right - left;
        }
    }

    public static void main(String[] args) {
        int[] test = {5, 7, 7, 8, 8, 10};
        int[] test2 = {1};
        System.out.println(search(test, 6));
//        System.out.println(search(test2, 1));
    }
}
