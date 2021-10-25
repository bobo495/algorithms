package pers.yibo.algorithms.leetcode;

/**
 * 1736. 替换隐藏数字得到的最晚时间
 * <p>
 * https://leetcode-cn.com/problems/latest-time-by-replacing-hidden-digits/
 * <p>
 * 输入：time = "2?:?0"
 * 输出："23:50"
 * 解释：以数字 '2' 开头的最晚一小时是 23 ，以 '0' 结尾的最晚一分钟是 50 。
 *
 * @author yibo
 * @date 2021-10-25 17:28
 **/
public class LatestTimeByReplacingHiddenDigits {
    public String maximumTime(String time) {
        char[] chars = new char[5];
        chars[0] = time.charAt(0);
        chars[1] = time.charAt(1);
        if (chars[0] == '?' && chars[1] != '?') {
            if (chars[1] - '0' <= 3) {
                chars[0] = '2';
            } else {
                chars[0] = '1';
            }
        } else if (chars[0] != '?' && chars[1] == '?') {
            if (chars[0] - '0' < 2) {
                chars[1] = '9';
            } else {
                chars[1] = '3';
            }
        } else if (chars[0] == '?') {
            chars[0] = '2';
            chars[1] = '3';
        }

        chars[3] = time.charAt(3);
        chars[4] = time.charAt(4);
        if (chars[3] == '?') {
            chars[3] = '5';
        }
        if (chars[4] == '?') {
            chars[4] = '9';
        }
        chars[2] = ':';
        return String.valueOf(chars);
    }
}
