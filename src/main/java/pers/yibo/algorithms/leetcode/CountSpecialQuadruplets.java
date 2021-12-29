package pers.yibo.algorithms.leetcode;

/**
 * 1995. 统计特殊四元组
 * <p>
 * https://leetcode-cn.com/problems/count-special-quadruplets
 * <p>
 * 输入：nums = [1,1,1,3,5]
 * 输出：4
 * 解释：满足要求的 4 个四元组如下：
 * - (0, 1, 2, 3): 1 + 1 + 1 == 3
 * - (0, 1, 3, 4): 1 + 1 + 3 == 5
 * - (0, 2, 3, 4): 1 + 1 + 3 == 5
 * - (1, 2, 3, 4): 1 + 1 + 3 == 5
 *
 * @author yibo
 * @date 2021-12-29 09:29
 **/
public class CountSpecialQuadruplets {
    public int countQuadruplets(int[] nums) {
        // 2-200，前两个置空，记录前两个数之和的数量
        int[] hash = new int[201];

        int ans = 0;
        for (int i = 1; i < nums.length - 2; i++) {
            // i+1左侧为前两数和
            for (int j = 0; j < i; j++) {
                hash[nums[i] + nums[j]]++;
            }
            // nums[i+1]为第三个数，此处遍历第四个数
            for (int j = i + 2; j < nums.length; j++) {
                // 此时得到：前两数之和 = nums[j]-nums[i+1]
                ans += hash[Math.max(0, nums[j] - nums[i + 1])];
            }
        }
        return ans;
    }
}
