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
    public int getMoneyAmount(int n) {
        if (n <= 5) {
            int[] dp = new int[5];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 5;
            dp[4] = 7;
            return dp[n - 1];
        }

        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        dp[4] = 7;

        // 根节点逐个遍历，左侧子树最小值为dp[head - 2],右侧子树最小值为完全二叉树的最大父节点值之和
        for (int i = 5; i < n; i++) {
            int d = 4;
            dp[i] = Integer.MAX_VALUE;
            int searchIndex = i + 2 - d;
            // 每次right构建完全二叉树，找到此时符合条件的minIndex
            int cost;
            int minIndex = searchIndex;
            while (searchIndex > 2) {
                cost = searchIndex + Math.max(dp[searchIndex - 2], rightMin(searchIndex + 1, i + 1));
                if (cost < dp[i]) {
                    dp[i] = cost;
                    minIndex = searchIndex;
                }
                d *= 2;
                searchIndex = i + 2 - d;
            }
            do {
                minIndex++;
                cost = minIndex + Math.max(dp[minIndex - 2], rightMin(minIndex + 1, i + 1));
                if (cost < dp[i]) {
                    dp[i] = cost;
                }
            } while (cost <= dp[i]);
        }

        return dp[n - 1];
    }

    public int rightMin(int start, int end) {
        int min = 0;
        while (end - start > 1) {
            start = (start + end) / 2 + 1;
            min += start - 1;
        }
        return min;
    }

    public static void main(String[] args) {
        GuessNumberHigherOrLower2 g = new GuessNumberHigherOrLower2();
        System.out.println(g.getMoneyAmount(130));
        for (int i = 100; i < 110; i++) {
            System.out.println(i + " " + g.rightMin(i, 130));
        }
    }
}
