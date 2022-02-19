package pers.yibo.algorithms.leetcode;

/**
 * 1614. 括号的最大嵌套深度
 * <p>
 * https://leetcode-cn.com/problems/maximum-nesting-depth-of-the-parentheses/
 *
 * @author yibo
 * @date 2022-01-07 09:46
 **/
public class MaximumNestingDepthOfTheParentheses {
    public int maxDepth(String s) {
        int max = 0;
        int tmp = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                tmp++;
                max = Math.max(tmp, max);
            }
            if (c == ')') {
                tmp--;
            }
        }
        return max;
    }
}
