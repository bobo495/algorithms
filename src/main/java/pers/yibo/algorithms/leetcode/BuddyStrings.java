package pers.yibo.algorithms.leetcode;

/**
 * 859. 亲密字符串
 * <p>
 * https://leetcode-cn.com/problems/buddy-strings/
 * <p>
 * 给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。
 *
 * @author yibo
 * @date 2021-11-23 10:43
 **/
public class BuddyStrings {
    public boolean buddyStrings(String s, String goal) {

        if (s.length() != goal.length()) {
            return false;
        }

        // 相等的情况单独拿出来
        if (s.equals(goal)) {
            // 重复字符串
            int[] charCount = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int index = s.charAt(i) - 'a';
                charCount[index]++;
                // 最多执行27次
                if (charCount[index] > 1) {
                    return true;
                }
            }
            return false;
        }

        char charS = 0;
        char charG = 0;
        boolean findSwap = false;
        for (int i = 0; i < s.length(); i++) {
            char tmpS = s.charAt(i);
            char tmpG = goal.charAt(i);
            if (tmpS != tmpG) {
                if (charS == 0) {
                    charS = tmpS;
                    charG = tmpG;
                } else {
                    if (findSwap || !(charG == tmpS && charS == tmpG)) {
                        return false;
                    }
                    findSwap = true;
                }
            }
        }

        return findSwap;
    }
}
