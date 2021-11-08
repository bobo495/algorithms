package pers.yibo.algorithms.leetcode;

/**
 * 1608. 特殊数组的特征值
 * <p>
 * https://leetcode-cn.com/problems/special-array-with-x-elements-greater-than-or-equal-x
 * <p>
 * 输入：nums = [3,5]
 * 输出：2
 * 解释：有 2 个元素（3 和 5）大于或等于 2 。
 * <p>
 * 输入：nums = [0,0]
 * 输出：-1
 * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
 * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
 * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
 * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
 * x 不能取更大的值，因为 nums 中只有两个元素。
 *
 * @author yibo
 * @date 2021-11-08 14:14
 **/
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public int specialArray(int[] nums) {
        int l = 0;
        int r = 1000;
        int x = (l + r) / 2;
        while (l <= r) {
            int count = isX(nums, x);
            if (count == 0) {
                return x;
            } else if (count == 1) {
                l = x + 1;
                x = (l + r) / 2;
            } else {
                r = x - 1;
                x = (l + r) / 2;
            }
        }
        return -1;
    }

    public int isX(int[] nums, int x) {
        int count = 0;
        for (int num : nums) {
            if (num >= x) {
                count++;
                if (count > x) {
                    return 1;
                }
            }
        }
        return count == x ? 0 : -1;
    }
}
