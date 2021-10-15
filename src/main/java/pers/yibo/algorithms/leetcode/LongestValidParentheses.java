package pers.yibo.algorithms.leetcode;

/**
 * 32. 最长有效括号
 * <p>
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * <p>
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * @author yibo
 * @date 2021-10-15 16:37
 **/
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int result = 0;

        // 左括号剩余数量
        int leftCount = 0;
        // 当前最大子串长度
        int tmp = 0;
        // 最后一个有效右括号索引
        int lastEndIndex = 0;
        // 最后一个有效右括号索引对应的子串长度
        int lastTmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else {
                // 右括号多了一个说明当前已经截断，初始化变量
                if (leftCount == 0) {
                    if (result < tmp) {
                        result = tmp;
                    }
                    tmp = 0;
                    lastEndIndex = 0;
                    lastTmp = 0;
                    continue;
                }
                leftCount--;

                System.out.println(i + " " + lastEndIndex + " " + tmp + " " + lastTmp + " " + leftCount);
                if (i - lastEndIndex == tmp) {
                    // 左括号剩余0，且当前子串正好能接上上一个子串，则拼接字符串
                    tmp += lastTmp + 2;
                    lastTmp = 0;
                } else {
                    lastTmp = tmp;
                    tmp += 2;
                }
                if (lastTmp > result) {
                    result = lastTmp;
                }
                lastEndIndex = i;
            }
        }
        return Math.max(result, tmp);
    }

    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();
//        String s = ")()(())";
        String s = "(()(((()";
//        String s = ")()())";
//        String s = "(()()";
        System.out.println(l.longestValidParentheses(s));
    }
}
