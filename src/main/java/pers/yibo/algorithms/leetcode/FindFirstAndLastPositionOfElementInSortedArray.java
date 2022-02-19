package pers.yibo.algorithms.leetcode;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * <p>
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @author yibo
 * 2022/2/19 9:42
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int length = nums.length;
        if (length == 0 || nums[0] > target || nums[length - 1] < target) {
            return new int[]{-1, -1};
        }
        // find target
        int left = 0;
        int right = length - 1;
        int mid = (left + right) / 2;

        while (left <= right) {
            if (nums[mid] == target) {
                break;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }

        // miss
        if (nums[mid] != target) {
            return new int[]{-1, -1};
        }

        int leftMax = mid;
        int leftMid = (left + leftMax) / 2;
        while (left <= leftMid) {
            if (nums[leftMid] == target) {
                if (leftMid == left || nums[leftMid - 1] < target) {
                    break;
                }
                leftMax = leftMid - 1;
            } else {
                left = leftMid + 1;
            }
            leftMid = (left + leftMax) / 2;
        }

        int rightMin = mid;
        int rightMid = (rightMin + right) / 2;
        while (rightMin <= right) {
            if (nums[rightMid] == target) {
                if (rightMid == right || nums[rightMid + 1] > target) {
                    break;
                }
                rightMin = rightMid + 1;
            } else {
                right = rightMid - 1;
            }
            rightMid = (rightMin + right) / 2;
        }

        return new int[]{leftMid, rightMid};
    }
}
