package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 2006. 差的绝对值为 K 的数对数目
 * <p>
 * https://leetcode-cn.com/problems/count-number-of-pairs-with-absolute-difference-k
 * <p>
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 *
 * @author yibo
 * @date 2021-10-19 17:43
 **/
public class CountNumberOfPairsWithAbsoluteDifferenceK {
    public int countKDifference(int[] nums, int k) {
        if (nums.length < 2) {
            return 0;
        }
        int result = 0;

        Arrays.sort(nums);

        int index;
        for (int i = 0; i < nums.length - 1; i++) {
            int val1 = nums[i];

            int l = i + 1;
            if (nums[l] - val1 > k) {
                continue;
            }
            int r = nums.length - 1;
            if (nums[r] - val1 < k) {
                continue;
            }
            index = (l + r) / 2;
            while (l <= r) {
                int val2 = nums[index];
                if (val2 - val1 == k) {
                    // 左右相等的均算上
                    result++;
                    int tmp = index - 1;
                    while (tmp >= l && nums[tmp] == val2) {
                        result++;
                        tmp--;
                    }
                    tmp = index + 1;
                    while (tmp <= r && nums[tmp] == val2) {
                        result++;
                        tmp++;
                    }
                    break;
                } else if (val2 - val1 > k) {
                    r = index - 1;
                    index = (l + r) / 2;
                } else {
                    l = index + 1;
                    index = (l + r) / 2;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        CountNumberOfPairsWithAbsoluteDifferenceK c = new CountNumberOfPairsWithAbsoluteDifferenceK();
        int[] nums = new int[]{3,2,1,5,4};
        int k = 2;
        System.out.println(c.countKDifference(nums, k));
    }
}
