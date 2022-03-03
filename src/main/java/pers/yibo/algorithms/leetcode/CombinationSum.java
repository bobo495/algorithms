package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * <p>
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author yibo
 * 2022/3/3 15:31
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        addElement(candidates, target, 0, ans, new ArrayList<Integer>(), 0);
        return ans;
    }

    private void addElement(int[] candidates, int target, int index, List<List<Integer>> ans, List<Integer> list, int sum) {
        for (int i = index; i < candidates.length; i++) {
            int c = candidates[i];
            if (sum + c > target) {
                return;
            }
            // 找到结果时，不用执行clone，可以直接将list添加到ans中
            if (sum + c == target) {
                list.add(c);
                ans.add(list);
                return;
            }
            if (sum + c < target) {
                // 复制一个新数组
                List<Integer> newList = new ArrayList<>(list);
                newList.add(c);
                // 下一个可添加的数字从i开始搜索，避免重复
                addElement(candidates, target, i, ans, newList, sum + c);
            }

        }
    }
}
