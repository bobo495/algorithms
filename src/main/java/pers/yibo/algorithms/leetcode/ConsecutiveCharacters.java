package pers.yibo.algorithms.leetcode;

/**
 * 1446. 连续字符
 * <p>
 * https://leetcode-cn.com/problems/consecutive-characters/
 * <p>
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * <p>
 * 请你返回字符串的能量。
 *
 * @author yibo
 * @date 2021-12-01 10:54
 **/
public class ConsecutiveCharacters {
    public int maxPower(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char last = 0;
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (last == s.charAt(i)) {
                tmp++;
            } else {
                last = s.charAt(i);
                max = Math.max(max, tmp);
                tmp = 1;
            }
        }
        max = Math.max(max, tmp);
        return max;
    }
}
