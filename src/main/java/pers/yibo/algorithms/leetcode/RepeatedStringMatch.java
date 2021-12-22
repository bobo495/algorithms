package pers.yibo.algorithms.leetcode;

/**
 * 686. 重复叠加字符串匹配
 * <p>
 * https://leetcode-cn.com/problems/repeated-string-match
 * <p>
 * 给定两个字符串a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * <p>
 * 注意：字符串 "abc"重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是"abcabc"。
 *
 * @author yibo
 * @date 2021-12-22 09:25
 **/
public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        int first = b.indexOf(a);

        if (first == -1) {
            // a长于b时，只有两种情况：a包含b或aa包含b
            if (a.contains(b)) {
                return 1;
            }
            if ((a + a).contains(b)) {
                return 2;
            }
            return -1;
        }

        // 校验是否可以通过重叠a实现b
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) != a.charAt((i + a.length() - first % a.length()) % a.length())) {
                return -1;
            }
        }
        if (first == 0) {
            return b.length() / a.length() + (b.length() % a.length() == 0 ? 0 : 1);
        } else {
            return (b.length() - first) / a.length() + 1 + ((b.length() - first) % a.length() == 0 ? 0 : 1);
        }
    }

    public static void main(String[] args) {
        RepeatedStringMatch r = new RepeatedStringMatch();

        String a = "aba";
        String b = "babbbbaabbababbaaaaababbaaabbbbaaabbbababbbbabaabababaabaaabbbabababbbabababaababaaaaabbabaaaabaaaab";

        System.out.println(r.repeatedStringMatch(a, b));
    }
}
