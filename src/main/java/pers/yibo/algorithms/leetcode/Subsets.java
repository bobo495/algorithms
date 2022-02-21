package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * <p>
 * https://leetcode-cn.com/problems/subsets/
 *
 * @author yibo
 * 2022/2/21 20:29
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, nums, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, List<Integer> subset, int index) {
        if (index == nums.length) {
            ans.add(subset);
            return;
        }
        // 每个元素选择加或不加两条路
        dfs(ans, nums, new ArrayList<>(subset), index + 1);
        subset.add(nums[index]);
        dfs(ans, nums, subset, index + 1);
    }
}
