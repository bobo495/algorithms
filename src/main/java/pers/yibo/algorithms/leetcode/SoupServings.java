package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 808. 分汤
 * <p>
 * https://leetcode-cn.com/problems/soup-servings
 * <p>
 * 有A和B 两种类型的汤。一开始每种类型的汤有N毫升。有四种分配操作：
 * <p>
 * 提供 100ml 的汤A 和 0ml 的汤B。
 * 提供 75ml 的汤A 和 25ml 的汤B。
 * 提供 50ml 的汤A 和 50ml 的汤B。
 * 提供 25ml 的汤A 和 75ml 的汤B。
 * 当我们把汤分配给某人之后，汤就没有了。每个回合，我们将从四种概率同为0.25的操作中进行分配选择。如果汤的剩余量不足以完成某次操作，我们将尽可能分配。当两种类型的汤都分配完时，停止操作。
 * <p>
 * 注意不存在先分配100 ml汤B的操作。
 * <p>
 * 需要返回的值：汤A先分配完的概率 + 汤A和汤B同时分配完的概率 / 2。
 *
 * @author yibo
 * @date 2021-11-08 16:24
 **/
public class SoupServings {

    public double soupServings(int n) {
        int length = n / 25 + (n % 25 == 0 ? 0 : 1);
        if (length >= 500) {
            return 1;
        }
        double[][] dp = new double[length + 1][length + 1];

        dp[0][0] = 0.5;
        for (int i = 1; i <= length; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                dp[i][j] = (dp[Math.max(i - 4, 0)][j]
                        + dp[Math.max(i - 3, 0)][j - 1]
                        + dp[Math.max(i - 2, 0)][Math.max(j - 2, 0)]
                        + dp[i - 1][Math.max(j - 3, 0)]) / 4;
            }
        }

        return dp[length][length];
    }

    public static void main(String[] args) {
        SoupServings s = new SoupServings();
        System.out.println(s.soupServings(100));
    }
}
