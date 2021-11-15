package pers.yibo.algorithms.leetcode;

/**
 * 375. 猜数字大小 II
 * <p>
 * https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii
 * <p>
 * 我们正在玩一个猜数游戏，游戏规则如下：
 * <p>
 * 我从1到 n 之间选择一个数字。
 * 你来猜我选了哪个数字。
 * 如果你猜到正确的数字，就会 赢得游戏 。
 * 如果你猜错了，那么我会告诉你，我选的数字比你的 更大或者更小 ，并且你需要继续猜数。
 * 每当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。如果你花光了钱，就会 输掉游戏 。
 * 给你一个特定的数字 n ，返回能够 确保你获胜 的最小现金数，不管我选择那个数字 。
 *
 * @author yibo
 * @date 2021-11-12 10:09
 **/
public class GuessNumberHigherOrLower2 {
    /**
     * 参考：https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/cai-shu-zi-da-xiao-ii-by-leetcode-soluti-a7vg/
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                dp[i][j] = j + dp[i][j - 1];
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower2 g = new GuessNumberHigherOrLower2();
        System.out.println(g.getMoneyAmount(5));
    }
}
