package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 629. K个逆序对数组
 * <p>
 * https://leetcode-cn.com/problems/k-inverse-pairs-array
 * <p>
 * 给出两个整数n和k，找出所有包含从1到n的数字，且恰好拥有k个逆序对的不同的数组的个数。
 * <p>
 * 逆序对的定义如下：对于数组的第i个和第j个元素，如果满足 i<j 且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * <p>
 * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
 * <p>
 * f[i][j]=f[i][j−1]−f[i−1][j−i]+f[i−1][j]
 * <p>
 * 参考：https://leetcode-cn.com/problems/k-inverse-pairs-array/solution/kge-ni-xu-dui-shu-zu-by-leetcode-solutio-0hkr/
 * <p>
 * 针对[i][j]，数字为1-i，排列为1...k-1,k,k+1...i，第k个元素的逆序对可分为两部分：
 * <p>
 * 1. k与前i-1个元素产生的逆序对数量
 * 2. 前i-1个元素内部产生的逆序对数量
 * <p>
 * 针对1：k最多可产生i-k个逆序对
 * 针对2：需要有j-(i-k)个逆序对
 *
 * @author yibo
 * @date 2021-11-11 09:53
 **/
public class KInversePairsArray {
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = ((j >= 1 ? dp[i][j - 1] : 0) - (j >= i ? dp[i - 1][j - i] : 0) + dp[i - 1][j]);
                if (dp[i][j] >= mod) {
                    dp[i][j] -= mod;
                }
                if (dp[i][j] < 0) {
                    dp[i][j] += mod;
                }
            }
        }
        for (int[] a : dp) {
            System.out.println(Arrays.toString(a));
        }
        return dp[n][k] % mod;
    }

    public static void main(String[] args) {
        KInversePairsArray k = new KInversePairsArray();
        System.out.println(k.kInversePairs(4, 4));
    }
}
