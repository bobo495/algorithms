package pers.yibo.algorithms.leetcode;

import java.util.HashSet;
import java.util.List;

/**
 * 139. 单词拆分
 * <p>
 * https://leetcode-cn.com/problems/word-break/
 *
 * @author yibo
 * 2022/2/20 11:09
 */
public class WordBreak {
    /**
     * DP: dp[i]为true时，检查dp[i+j]是否为true
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        // dp[i]表示长度为i的子字符串
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
