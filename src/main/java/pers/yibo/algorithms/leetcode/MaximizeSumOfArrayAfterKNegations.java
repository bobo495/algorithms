package pers.yibo.algorithms.leetcode;

/**
 * 1005. K 次取反后最大化的数组和
 * <p>
 * https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 *
 * @author yibo
 * @date 2021-12-03 09:25
 **/
public class MaximizeSumOfArrayAfterKNegations {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 统计所有数字的数量, 0,99下标对应-100,-1  100,200 对应0,100
        int[] count = new int[201];
        for (int num : nums) {
            count[num + 100]++;
        }

        // 负数没有了或翻转结束了则停止
        for (int i = 0; i < 100; i++) {
            while (count[i] > 0) {
                k--;
                count[i]--;
                count[200 - i]++;
                if (k == 0) {
                    return getSum(count);
                }
            }
        }

        // 负数都翻转了，判断k是否还有剩余，以及是否为奇数
        if (k % 2 != 0) {
            // 翻转最小的一个数字
            for (int i = 100; i < 201; i++) {
                if (count[i] > 0) {
                    count[i]--;
                    count[200 - i]++;
                    return getSum(count);
                }
            }
        }
        return getSum(count);
    }

    public int getSum(int[] count) {

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                System.out.println(i + " " + count[i]);
            }
        }

        int sum = 0;
        for (int i = 0; i < 201; i++) {
            for (int j = 0; j < count[i]; j++) {
                sum += i - 100;
            }
        }
        return sum;
    }

}
